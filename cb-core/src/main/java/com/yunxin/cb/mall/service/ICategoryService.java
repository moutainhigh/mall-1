package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.CommodityCategory;
import com.yunxin.cb.mall.vo.CategoryNode;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aidy_He on 16/1/15.
 */
public interface ICategoryService {

    @Transactional(readOnly = true)
    List<Category> getAllCategories();

    List<Category> getAllEnabledCategories();

    /***
     * 新增运营分类
     *
     * @param catalog
     * @return
     */
    public Category addCategory(Category catalog) throws EntityExistException;

    /***
     * 修改运营分类
     *
     * @param catalog
     * @return
     */
    public Category updateCategory(Category catalog) throws EntityExistException;

    void updateIconPath(int categoryId, String iconPath);

    /***
     * 根据ID获取运营分类
     *
     * @param categoryId
     * @return
     */
    public Category getCategoryById(int categoryId);

    /***
     * 根据ID删除运营分类
     *
     * @param categoryId
     */
    public void removeCategoryById(int categoryId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<CommodityCategory> pageCommodityCategories(PageSpecification<CommodityCategory> pageSpecification);

    /***
     * 根据运营分类级别获取
     *
     * @param level
     * @return
     */
    public List<Category> getCategoryByLevel(int level);

    @Transactional(readOnly = true)
    List<Category> getCategoriesByParentCategory(Category category);

    TreeViewItem getCategoryTree();

    @Transactional(readOnly = true)
    List<CategoryNode> getCategoryNodes();

    boolean addCommodityCategories(int categoryId, int[] commodityId);

    void removeCommodityCategoryById(int cocaId);

    @Transactional(readOnly = true)
    CommodityCategory getCommodityCategoryById(int cocaId);

    CommodityCategory saveCategoryCommodityFilterItems(int cocaId, int[] itemIds);
}
