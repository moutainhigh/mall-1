package com.yunxin.cb.mall.service.imp;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.mall.dao.CategoryDao;
import com.yunxin.cb.mall.dao.CommodityCategoryDao;
import com.yunxin.cb.mall.dao.FilterItemDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.vo.CategoryNode;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Aidy_He on 16/1/15.
 */
@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private CommodityCategoryDao commodityCategoryDao;

    @Resource
    private FilterItemDao filterItemDao;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryDao.findAll(new Sort(Sort.Direction.ASC, "sortOrder"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllEnabledCategories() {
        return categoryDao.findAll(new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Category_.sortOrder)));
                return criteriaBuilder.equal(root.get(Category_.enabled), true);
            }
        });
    }

    @Override
    public Category addCategory(Category category) throws EntityExistException {
        if (!categoryDao.isOrUnique(category, Category_.categoryNo, Category_.categoryName)) {
            throw new EntityExistException("运营分类编号或运营分类名称已存在");
        }
        Category parentCategory = categoryDao.findOne(category.getParentCategoryId());
        category.setCreateTime(new Date());
        category.setLevel(parentCategory.getLevel() + 1);
        category.setParentCategory(parentCategory);
        return categoryDao.save(category);
    }

    @Override
    public Category updateCategory(Category category) throws EntityExistException {
        if (!categoryDao.isOrUnique(category, Category_.categoryNo, Category_.categoryName)) {
            throw new EntityExistException("运营分类编号或运营分类名称已存在");
        }
        Category category1 = categoryDao.findOne(category.getCategoryId());
//        Category parentCategory = categoryDao.findOne(category.getParentCategoryId());
//        category1.setLevel(parentCategory.getLevel() + 1);
//        category1.setParentCategory(parentCategory);
        AttributeReplication.copying(category, category1,
                Category_.categoryNo,
                Category_.seoDescription,
                Category_.categoryKey,
                Category_.description,
                Category_.remark,
                Category_.recommend,
                Category_.seoTitle,
                Category_.categoryName,
                Category_.enabled,
                Category_.sortOrder,
                Category_.lowestPrice,
                Category_.highestPrice);
        return category1;
    }

    @Override
    public void updateIconPath(int categoryId, String iconPath) {
        Category categoryDb = categoryDao.findOne(categoryId);
        categoryDb.setIconPath(iconPath);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    @Override
    public void removeCategoryById(int categoryId) {
        categoryDao.delete(categoryId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<CommodityCategory> pageCommodityCategories(PageSpecification<CommodityCategory> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<CommodityCategory>() {
            public void buildFetch(Root<CommodityCategory> root) {
                Fetch<CommodityCategory, Commodity> fetch = root.fetch(CommodityCategory_.commodity, JoinType.LEFT);
                fetch.fetch(Commodity_.brand, JoinType.LEFT);
                fetch.fetch(Commodity_.catalog, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<CommodityCategory> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                Path<CommodityState> statusPath = root.get(CommodityCategory_.commodity).get(Commodity_.commodityState);
                predicates.add(builder.notEqual(statusPath, CommodityState.DEL.ordinal()));
            }
        });
        Page<CommodityCategory> pages = commodityCategoryDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategoryByLevel(int level) {
        return categoryDao.findByLevelAndEnabled(level, true);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategoriesByParentCategory(Category category) {
        return categoryDao.findByParentCategoryOrderBySortOrderAsc(category.getCategoryId());
    }

    @Override
    @Transactional(readOnly = true)
    public TreeViewItem getCategoryTree() {
        List<Category> catalogs = categoryDao.findByEnabledOrderByCategoryIdAsc(true);
        Category catalog = catalogs.get(0);
        catalogs.remove(catalog);
        TreeViewItem root = catalog.cloneTreeItem();
        buildCategoryTree(root, catalogs);
        return root;
    }

    private void buildCategoryTree(TreeViewItem catalog, List<Category> catalogs) {
        for (Category catalog1 : catalogs) {
            if (Integer.valueOf(catalog.getId()) == catalog1.getParentCategory().getCategoryId()) {
                TreeViewItem newCatalog = catalog1.cloneTreeItem();
                catalog.getItems().add(newCatalog);
                buildCategoryTree(newCatalog, catalogs);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryNode> getCategoryNodes() {
        List<Category> categories = categoryDao.findByEnabledOrderByCategoryIdAsc(true);
        Category category = categories.get(0);
        categories.remove(category);
        CategoryNode root = new CategoryNode(category);
        buildCategoryNode(root, categories);
        return root.getCategoryNodes();
    }

    private void buildCategoryNode(CategoryNode parentNode, List<Category> categories) {
        for (Category catalog1 : categories) {
            if (parentNode.getCategoryId() == catalog1.getParentCategory().getCategoryId()) {
                CategoryNode childNode = new CategoryNode(catalog1);
                parentNode.addChildCategory(childNode);
                buildCategoryNode(childNode, categories);
            }
        }
    }

    @Override
    public boolean addCommodityCategories(int categoryId, int[] commodityId) {
        Category category = categoryDao.findById(categoryId);
        if (commodityId != null) {
            for (int cid : commodityId) {
                Long count = commodityCategoryDao.countByCategoryAndCommodity_CommodityId(category, cid);
                if (count == null || count.intValue() == 0) {
                    CommodityCategory cc = new CommodityCategory();
                    cc.setCategory(category);
                    cc.setCommodity(new Commodity(cid));
                    commodityCategoryDao.save(cc);
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void removeCommodityCategoryById(int cocaId) {
        commodityCategoryDao.delete(cocaId);
    }

    @Override
    @Transactional(readOnly = true)
    public CommodityCategory getCommodityCategoryById(int cocaId) {
        return commodityCategoryDao.findOne(cocaId);
    }


    @Override
    public CommodityCategory saveCategoryCommodityFilterItems(int cocaId, int[] itemIds) {
        CommodityCategory commodityCategory = commodityCategoryDao.findOne(cocaId);
        commodityCategory.getFilterItems().clear();
        commodityCategoryDao.flush();
        if (itemIds != null) {
            for (int itemId : itemIds) {
                FilterItem item = filterItemDao.findOne(itemId);
                commodityCategory.getFilterItems().add(item);
            }
        }
        return commodityCategory;
    }

}
