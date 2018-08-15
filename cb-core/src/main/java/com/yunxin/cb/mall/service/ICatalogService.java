package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;

import java.util.List;
import java.util.Map;


/**
 * @author gonglei
 */
public interface ICatalogService {


    Catalog addCatalog(Catalog catalog) throws EntityExistException, CommonException;

    public Catalog updateCatalog(Catalog catalog) throws EntityExistException;

    public Catalog getCategoryById(int categoryId);

    public void removeCategoryById(int categoryId);

    List<Catalog> getCategorysByParentId(int categoryId);

    public List<Catalog> getCategorysNotDelByParentId(int categoryId);

    List<Catalog> getAllCatalogs();

    List<Catalog> getCategorysByParentIdAndChild(int categoryId);

    List<Catalog> getRootCategorysByMallShow(int limit);

    List<Catalog> getRootCategorysByMallShow();

    void refreshCategoryLevelCode();

    List<Catalog> getCategorysByParentId(int categoryId, int limit);

    public Catalog findOne(int categoryId);

    /**
     * 查询商品分类的数量
     */
    public Long getCategoryNumber();

    public Long getCategoryNum(int pcategoryId);

    public List<Catalog> getParentsByCategoryId(int categoryId);

    /***
     * 根据分类ID集合获取对应的集合列表
     * @param categoryIds
     * @return
     */
    List<Catalog> getCategorysByIds(int[] categoryIds);

    /**
     * 查询所有可用的分类集合
     *
     * @return
     */
    List<Catalog> getAllEnableCategory();

    /**
     * 获取商品分类的二级分类名称和其下商品分类的数量
     *
     * @return
     */
    public Map<String, Long> getCategoryNum();

    TreeViewItem getCatalogTree();

    /**
     * 根据分类名称检验对应父级分类下有无相同名称存在
     *
     * @param categoryName
     * @return
     */
    public Catalog checkCategoryNameInSameParentCategoryForAdd(int parentCategoryId, String categoryName);

    /**
     * 根据分类名称检验对应父级分类下有无相同名称存在（自身除外）
     *
     * @param categoryName
     * @return
     */
    public Catalog checkCategoryNameInSameParentCategoryForUpdate(int parentCategoryId, int categoryId, String categoryName);

    void enableCatalogById(int catalogId, boolean enabled);

    /**
     * @Description:            根据分类编码查询一级分类
     * @author: lxc
     * @param catalogCode       分类编码
     * @Return com.yunxin.cb.mall.entity.Catalog:
     * @DateTime: 2018/8/14 19:39
     */
    Catalog findOneLevelCatalogByCatalogCode(String catalogCode);
}
