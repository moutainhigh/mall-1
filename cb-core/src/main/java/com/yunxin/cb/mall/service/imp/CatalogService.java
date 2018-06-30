/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.CatalogDao;
import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.Catalog_;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author gonglei
 */
@Service
@Transactional
public class CatalogService implements ICatalogService {

    private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);

    private static DecimalFormat decimalFormat = new DecimalFormat("000");

    @Resource
    private CatalogDao catalogDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getCategorysByParentId(int categoryId) {
        List<Catalog> categories = catalogDao
                .findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderBySortOrderAsc(
                        categoryId, 1, true);
        return categories;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getCategorysNotDelByParentId(int categoryId) {
        List<Catalog> categories = catalogDao
                .findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderBySortOrderAsc(
                        categoryId, 1, true);
        return categories;
    }


    @Override
    public Catalog addCatalog(Catalog catalog) throws EntityExistException, CommonException {
        if (!catalogDao.isOrUnique(catalog, Catalog_.catalogName)) {
            throw new EntityExistException("商品分类名称已存在");
        }
        Catalog pCatalog = catalogDao.findOne(catalog.getParentCatalog()
                .getCatalogId());
        // 兼容旧数据,旧数据中有商品分类编码为空
        if (StringUtils.isBlank(pCatalog.getCatalogCode())) {
            throw new CommonException("父商品分类编码为空!");
        }
        int tcode = calParentCategoryCode(pCatalog);
        catalog.setParentCatalog(pCatalog);
        catalog.setCreateTime(new Date());
        catalog.setCatalogCode(pCatalog.getCatalogCode()
                + decimalFormat.format(tcode));
        Catalog catalog2 = catalogDao.save(catalog);
        return catalog2;
    }

    @Override
    public Catalog updateCatalog(Catalog catalog) throws EntityExistException {
        if (!catalogDao.isOrUnique(catalog, Catalog_.catalogName)) {
            throw new EntityExistException("商品分类名称已存在");
        }
        Catalog dbCatalog = catalogDao.findOne(catalog.getCatalogId());

        AttributeReplication.copying(catalog, dbCatalog, Catalog_.catalogName, Catalog_.enabled, Catalog_.supportAddedTax, Catalog_.leaf, Catalog_.sortOrder, Catalog_.remark, Catalog_.parentCatalog);
        return dbCatalog;
    }

    private synchronized int calParentCategoryCode(Catalog pCatalog) {
        int tcode = 0;
        List<String> categoryCodeStrings = catalogDao
                .getCategoryCodesByParentCategory(pCatalog);
        if (categoryCodeStrings.size() > 0) {
            boolean[] ddd = new boolean[1000];
            for (String categoryCode : categoryCodeStrings) {
                if (StringUtils.isNotBlank(categoryCode)) {
                    String ssString = categoryCode.substring(categoryCode
                            .length() - 3);
                    if (NumberUtils.isNumber(ssString)) {
                        int code = Integer.parseInt(ssString);
                        ddd[code] = true;
                    }
                }
            }
            for (int i = 0; i < ddd.length; i++) {
                if (!ddd[i]) {
                    tcode = i;
                    break;
                }
            }
        }
        return tcode;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Catalog getCategoryById(int categoryId) {
        return catalogDao.findByCategoryId(categoryId);
    }

    @Override
    public void removeCategoryById(int categoryId) {
        catalogDao.delete(categoryId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getAllCatalogs() {
        return catalogDao.findAll(new Sort(Direction.ASC, "sortOrder"));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getCategorysByParentIdAndChild(int categoryId) {
        List<Catalog> categories = catalogDao
                .findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderBySortOrderAsc(
                        categoryId, 1, true);
        for (int i = 0; i < categories.size(); i++) {
            Catalog cate = categories.get(i);
            List<Catalog> childCate = getCategorysByParentIdAndChild(cate
                    .getCatalogId());
            cate.setCatalogs(childCate);
        }
        return categories;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getCategorysByParentId(int categoryId, int limit) {
        List<Catalog> categories = catalogDao
                .findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderByCatalogNameAsc(
                        categoryId, 1, true, new PageRequest(0, limit));
        return categories;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getRootCategorysByMallShow(int limit) {
        List<Catalog> categories = catalogDao
                .findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderByCatalogNameAsc(
                        2, 1, true, new PageRequest(0, limit));
        return categories;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getRootCategorysByMallShow() {
        List<Catalog> categories = catalogDao
                .findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderByCatalogNameAsc(
                        2, 1, true);
        return categories;
    }

    @Override
    public void refreshCategoryLevelCode() {
        Catalog dept = catalogDao.findOne(1);
        if (dept != null) {
            dept.setCatalogCode("000");
            refreshDeptsLevelCode(dept);
        }
    }

    private void refreshDeptsLevelCode(Catalog pdept) {
        List<Catalog> depts = catalogDao
                .findByParentCatalog_CatalogIdOrderByCatalogNameAsc(pdept
                        .getCatalogId());
        for (int i = 0; i < depts.size() && i < 1000; i++) {
            Catalog dept = depts.get(i);
            if (dept.getCatalogId() != 1) {
                dept.setCatalogCode(pdept.getCatalogCode()
                        + decimalFormat.format(i));
                logger.info(dept.getCatalogName() + " 编号："
                        + dept.getCatalogCode());
                refreshDeptsLevelCode(dept);
            }
        }
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Catalog findOne(int categoryId) {
        return catalogDao.findOne(categoryId);
    }

    /**
     * 统计商品分类数量
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Long getCategoryNumber() {
        return catalogDao.findCategoryQuantity();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Long> getCategoryNum() {
        List<Catalog> categories = catalogDao.findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderByCatalogNameAsc(2, 1, false);
        Map<String, Long> categoryNum = new HashMap<String, Long>();
        if (categories.size() > 4) {
            for (int i = 0; i < 4; i++) {
                String cname = categories.get(i).getCatalogName();
                Long number = catalogDao.findCategoryNum(categories.get(i).getCatalogId());
                categoryNum.put(cname, number);
            }
            return categoryNum;
        } else {
            for (Catalog c : categories) {
                String cname = c.getCatalogName();
                Long number = catalogDao.findCategoryNum(c.getParentCatalog().getCatalogId());
                categoryNum.put(cname, number);
            }
            return categoryNum;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Long getCategoryNum(int pcategoryId) {
        return catalogDao.findCategoryNum(pcategoryId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getParentsByCategoryId(int categoryId) {
        Catalog current = catalogDao.findOne(categoryId);
        String curcode = current.getCatalogCode();
        List<String> codes = new ArrayList<String>();
        codes.add("0");
        int length = curcode.length();
        while (length > 6) {
            length = length - 3;
            curcode = curcode.substring(0, length);
            codes.add(curcode);
        }
        String[] a = new String[codes.size()];
        List<Catalog> list = catalogDao
                .findByCatalogCodeIn(codes.toArray(a));
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getCategorysByIds(final int[] categoryIds) {
        return catalogDao.findByCatalogIdIn(categoryIds);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Catalog> getAllEnableCategory() {
        return catalogDao.findByEnabledOrderByCatalogCodeAsc(true);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public TreeViewItem getCatalogTree() {
        List<Catalog> catalogs = catalogDao.findByEnabledOrderByCatalogIdAsc(true);
        Catalog catalog = catalogs.get(0);
        catalogs.remove(catalog);
        TreeViewItem root = catalog.cloneTreeItem();
        buildCatalogTree(root, catalogs);
        return root;
    }

    private void buildCatalogTree(TreeViewItem catalog, List<Catalog> catalogs) {
        for (Catalog catalog1 : catalogs) {
            if (Integer.valueOf(catalog.getId()) == catalog1.getParentCatalog().getCatalogId()) {
                TreeViewItem newCatalog = catalog1.cloneTreeItem();
                catalog.getItems().add(newCatalog);
                buildCatalogTree(newCatalog, catalogs);
            }
        }
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Catalog checkCategoryNameInSameParentCategoryForAdd(int parentCategoryId, String categoryName) {
        Catalog catalog = catalogDao.findByParentCatalog_CatalogIdAndCatalogNameAndEnabled(parentCategoryId, categoryName, true);
        return catalog;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Catalog checkCategoryNameInSameParentCategoryForUpdate(int parentCategoryId, int categoryId, String categoryName) {
        Catalog catalog = catalogDao.findByParentCatalog_CatalogIdAndCatalogIdNotAndCatalogNameAndEnabled(parentCategoryId, categoryId, categoryName, false);
        return catalog;
    }

    @Override
    public void enableCatalogById(int catalogId, boolean enabled) {
        catalogDao.enableCatalogById(enabled, catalogId);
    }
}
