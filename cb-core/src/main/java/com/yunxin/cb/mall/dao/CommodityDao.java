/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author z001075
 */
public interface CommodityDao extends CommodityPlusDao, JpaRepository<Commodity, Integer>, JpaSpecificationExecutor<Commodity>, BaseDao<Commodity> {

    @Modifying
    @Query("update Commodity c set c.commodityState = ?1 where c.commodityId=?2")
    public void updateCommodityStatusById(CommodityState commodityState, int commodityId);

    @Modifying
    @Query("update Commodity c set c.commodityState = ?2,c.auditRemark=?3 where c.commodityId=?1")
    public void commodityAudit(int commodityId, CommodityState commodityState, String auditRemark);

    //    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select c from Commodity c left join fetch c.brand left join fetch c.catalog left join fetch c.priceSection left join fetch c.seller left join fetch c.logisticPrices where c.commodityId=?1")
    public Commodity getCommodityDetailById(int commodityId);

    public List<Commodity> findByCommodityStateOrderByCreateTimeDesc(CommodityState commodityState, Pageable pageable);

    public List<Commodity> findByCatalog(Catalog catalog, Pageable pageable);

    public List<Commodity> findByCatalogOrderBySaleNumDesc(Catalog catalog, Pageable pageable);

    public List<Commodity> findByCommodityIdIn(List<Integer> commodityIds);

    @Query("select c from Commodity c left join fetch c.catalog  where c.brand=?1")
    public List<Commodity> findByBrand(Brand brand, Pageable pageable);

    public List<Commodity> findByBrandOrderBySaleNumDesc(Brand brand, Pageable pageable);

    public List<Commodity> findBySellPriceBetween(float max, float min, Pageable pageable);

    public List<Commodity> findByPopular(boolean popular, Pageable pageable);

    public List<Commodity> findBySpecial(boolean specia, Pageable pageable);

    public List<Commodity> findByRecommend(boolean recommend, Pageable pageable);

    public List<Commodity> findByRecommendAndCatalog_CatalogCodeLikeOrderByCreateTimeDesc(boolean recommend, String categoryCode, Pageable pageable);

    public List<Commodity> findByPopularAndCatalog_CatalogCodeLikeOrderByCreateTimeDesc(boolean popular, String categoryCode, Pageable pageable);

    /**
     * 查询某个分类下销量最高的商品
     *
     * @param category
     * @param pageable
     * @return
     */
    @Query("select c from Commodity c left join c.commodityCategories cc where cc.category=?1 order by c.saleNum desc")
    List<Commodity> findByCategory3OrderBySaleNumDesc(Category category, Pageable pageable);

    @Query("select c from Commodity c left join c.commodityCategories cc left join cc.category ccc where ccc.parentCategory=?1 order by c.saleNum desc")
    List<Commodity> findByCategory2OrderBySaleNumDesc(Category category, Pageable pageable);

    @Query("select c from Commodity c left join c.commodityCategories cc left join cc.category ccc left join ccc.parentCategory cccc where cccc.parentCategory=?1 order by c.saleNum desc")
    List<Commodity> findByCategory1OrderBySaleNumDesc(Category category, Pageable pageable);

    public List<Commodity> findByCommodityState(CommodityState commodityState);

    public Commodity findByCommodityName(String commodityName);

    Commodity findTopByCommodityCode(String commodityCode);

    long countByPublishState(PublishState publishState);

    long countByPublishStateAndPopular(PublishState publishState, boolean popular);

    long countByPublishStateAndRecommend(PublishState publishState, boolean recommend);

    /**
     * 根据商品分类获取相应商品数量
     */
    @Query("select count(c.commodityId) from Commodity c left join c.catalog where c.catalog.catalogName=?1 ")
    public Long findCommodityQuantityByCategoryName(String name);

    @Query("select c from Commodity c where c.commodityState<>?2 and (c.commodityName=?1 or c.shortName=?1)")
    public List<Commodity> findByCommodityNameAndStatusNot(String name, CommodityState status);

    public Commodity findByCommodityNameAndCommodityIdNotAndCommodityStateNot(String commodityName, int commodityId, CommodityState status);

    public Commodity findByCommodityCodeAndCommodityIdNotAndCommodityStateNot(String commodityCode, int commodityId, CommodityState del);

    public Commodity findByCommodityCodeAndCommodityIdNot(String commodityCode, int commodityId);

    public Commodity findByCommodityCodeAndCommodityStateNot(String commodityCode, CommodityState del);

    //根据品牌ID查询商品
    @Query("select c from Commodity c left join fetch c.brand cb where cb.brandId=?1")
    public List<Commodity> findByBrandId(int brandId);


    @Query("select c from Commodity c left join fetch c.products p where p.productId=?1")
    public Commodity findByProductId(int productId);


    @Query("select  c.brand.brandId from Commodity c where c.commodityId=?1")
    int findBrandIdByCommodityId(Integer key);

    @Query("select  c.catalog.catalogId from Commodity c where c.commodityId=?1")
    int findCategoryIdByCommodityId(Integer key);

    Stream<Commodity> findByPublishState(PublishState publishState);

    @Query("select c from Commodity c where c.commodityId <>?1")
    public List<Commodity> findCommoditiesExceptOne(int commodityId);

    @Query("select c from Commodity c left join fetch c.seller where c.commodityCode in ?1")
    public List<Commodity> getCommoditySellerByCommodityCode(List<String> commodityCodes);
}

interface CommodityPlusDao {


    void indexAll(Stream<Commodity> commodityStream);

    List<Commodity> searchCommodities(String text);

    Page<Commodity> pageSearchCommodities(String text, Pageable pageable);
}

