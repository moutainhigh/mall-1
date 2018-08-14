package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author gonglei
 */
public interface CatalogDao extends JpaRepository<Catalog, Integer>, JpaSpecificationExecutor<Catalog>, BaseDao<Catalog> {


    public List<Catalog> findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderByCatalogNameAsc(int categoryId,
                                                                                                     int pcategoryId, boolean enable);

    public List<Catalog> findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderByCatalogNameAsc(int categoryId,
                                                                                                     int pcategoryId, boolean enable, Pageable pageable);

    @Query("select c from Catalog c left join fetch c.parentCatalog cp where c.catalogId=?1")
    public Catalog findByCategoryId(int categoryId);

    List<Catalog> findByCatalogIdIn(int[] categoryIds);

    Catalog findTopByCatalogName(String categoryName);

    @Query("select c.catalogCode from Catalog c  where c.parentCatalog=?1 order by  c.catalogCode asc")
    List<String> getCategoryCodesByParentCategory(Catalog pCatalog);

    List<Catalog> findByParentCatalog_CatalogIdOrderByCatalogNameAsc(int categoryId);

    /**
     * 统计商品分类数量
     *
     * @return
     */
    @Query("select count(c.catalogId) from Catalog c where c.enabled=true")
    Long findCategoryQuantity();

    @Query("select count(c.catalogId) from Catalog c left join c.parentCatalog cp where cp.catalogId=?1 and cp.enabled=true")
    public Long findCategoryNum(int pcategoryId);

    @Query("select c from Catalog c where c.catalogCode in(?1)")
    public List<Catalog> searchParentsByCodes(String[] codes);

    List<Catalog> findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderBySortOrderAsc(int categoryId, int i, boolean b);

    List<Catalog> findByParentCatalog_CatalogIdAndCatalogIdNotAndEnabledOrderBySortOrderAsc(int categoryId, int i, boolean b, PageRequest pageRequest);


    List<Catalog> findByParentCatalog_CatalogIdOrderBySortOrderAsc(int categoryId);

    public List<Catalog> findByCatalogCodeIn(String[] array);


    public List<Catalog> findByEnabledOrderByCatalogCodeAsc(boolean enabled);

    public List<Catalog> findByEnabledOrderByCatalogIdAsc(boolean enabled);

    @Query("select c from Catalog c left join fetch c.catalogAttributeGroups p where p.groupId=?1")
    List<Catalog> findCategoriesByGroupId(int propGroupId);


    public Catalog findByParentCatalog_CatalogIdAndCatalogNameAndEnabled(int parentCategoryId, String categoryName, boolean del);

    public Catalog findByParentCatalog_CatalogIdAndCatalogIdNotAndCatalogNameAndEnabled(int parentCategoryId, int categoryId, String categoryName, boolean b);

    @Query("select c.catalogId from Catalog c where c.parentCatalog.catalogId in ?1 and c.enabled=true")
    List<Integer> fidnByParentCategoryIds(List<Integer> activityScopeIds);


    @Modifying
    @Query("update Catalog a set a.enabled = ?1 where a.catalogId=?2")
    void enableCatalogById(boolean enabled, int catalogId);

    /**
     * @Description:            按分类编码前部分模糊查询
     * @author: lxc
     * @param catalogCode       分类编码
     * @Return java.util.List<com.yunxin.cb.mall.entity.Catalog>:
     * @DateTime: 2018/8/14 15:34
     */
    List<Catalog> findCatalogByCatalogCodeStartingWith(String catalogCode);

    /**
     * @Description:        根据分类编码查询
     * @author: lxc
     * @param catalogCode   分类编码
     * @Return com.yunxin.cb.mall.entity.Catalog:
     * @DateTime: 2018/8/14 19:36
     */
    Catalog findCatalogByCatalogCode(String catalogCode);
}
