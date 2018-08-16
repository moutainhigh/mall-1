/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.query.CommodityQuery;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author gonglei
 */
public interface ICommodityService {

    public Commodity addCommodity(Commodity commodity) throws EntityExistException;

    public Commodity updateCommodity(Commodity commodity) throws EntityExistException;

    public void updateCommodityES(int commodityId)throws Exception;

    @Transactional(readOnly = true)
    List<CommoditySpec> getCommoditySpecsByCommodity(Commodity commodity);

    public Commodity getCommodityDetailById(int commodityId);

    @Transactional(readOnly = true)
    List<Commodity> getCommodityCategoryOrderBySaleNum(Commodity commodity, int limit);

    public void removeCommodityById(int commodityId);

    public Page<Commodity> pageCommodities(PageSpecification<Commodity> pageSpecification);

    public Page<Commodity> choosePagedCommodities(PageSpecification<Commodity> pageSpecification);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Commodity> pageCommodities(CommodityQuery commodityQuery);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Commodity> getCommoditiesByRecommendTopN(boolean recommend);

    public void updateCommodityStatus(CommodityState commodityState, int commodityId);

    public void updateCommodityStatus(Product product,int commodityId);

    public List<CommoditySpec> getCommoditySpecsByCommodityId(int commodityId);

    List<Commodity> getCommoditysByCreateTime(int limit);

    public Commodity updateDefaultPicPath(int commodityId, String defaultPicPath);


    List<Commodity> getCommodityByCategory(Catalog catalog, int limit);

    List<Commodity> getCommodityByBrand(Brand brand, int limit);

    List<Commodity> findCommodityByPopular(boolean popular, int limit);

    List<Commodity> findCommodityBySpecial(boolean special, int limit);

    List<Commodity> findCommodityByRecommend(boolean recommend, int limit);

    List<Commodity> findByRecommendAndCategoryCode(boolean recommend, String categoryCode, int limit);

    List<Commodity> findByPopularAndCategoryCode(boolean popular, String categoryCode, int limit);

    @Transactional(readOnly = true)
    List<Commodity> findByCategoryOrderBySaleNum(Category category, int limit);

    Page<Commodity> pageCommodities(String categoryCode, int brandId, int page, int pageSize);

//	List<Commodity> findCommoditysBycategoryCodeLikeAndbrandIdAndpropertyId(String categoryCode, int brandId, int propertyId, int page, int pageSize)throws Exception;

    //List<Commodity> findCommoditysByParams(String[] params, int page, int pageSize)throws Exception;

    /**
     * 根据参数获取页数
     *
     * @return
     * @throws Exception
     */
    //public int findCommodityCountByParams(String[] params, int pageSize) throws Exception;

//	Page<Commodity> findCommoditysBycategoryCodeLikeAndbrandIdAndpropertyId(PageSpecification<Commodity> query,String categoryCode, int brandId, int propertyId, int page, int pageSize)throws Exception;

    List<Commodity> getCommodityByCategoryOrderBySaleNum(Catalog catalog, int limit);

    List<Commodity> getCommodityByBrandOrderBySaleNum(Brand brand, int limit);

    List<Commodity> getCommoditysByPrice(float price, int limit);

    List<Commodity> getCommoditysByIds(List<Integer> commodityIds);


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Combination> getCombinationCommoditiesByCommodityId(int commodityId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Combination> getCombinationCommoditiesByCommodityId(int commodityId, PublishState publishState);

    public Commodity findByCommodityId(int commodityId);

    /**
     * 根据分类获取相应商品数量
     */
    public Long getCommodityNumberByCategory(String categoryName) throws Exception;

    public Commodity findByCommodityCode(String commodityCode);


    public List<Commodity> findByStatus(CommodityState commodityState) throws Exception;

//
//	/**
//	 * 統計商品購買記錄
//	 * @return
//	 */
//	public  List<CommoditySaleVo>  findCommoditySaleCount();

//	public Page<CommoditySaleVo> pageCommodities(
//			PageSpecification<CommoditySaleVo> query);

    /**
     * 根据ID查找商品
     *
     * @param commodityId
     * @return
     */
    public Commodity findCommodityById(int commodityId);

    public Commodity getCommodityByProductId(int productId);


    Page<Spec> pageSpecs(PageSpecification<Spec> specQuery);

    List<Spec> getSpecsByCatalogId(int catalogId);

    Spec addSpec(Spec spec) throws EntityExistException;

    Spec updateSpec(Spec spec) throws EntityExistException;

    List<CommoditySpec> getCommoditySpecsByCatalogId(int catalogId);


    void removeSpecById(int catalogId);

    /**
     * 商品上下架
     *
     * @param commodityId
     * @param publishState
     * @return
     */
    public boolean upOrDownShelvesCommodity(int commodityId, PublishState publishState) throws Exception;

    void removeCombinationById(int combinationId);

    Combination addCombination(Combination combination);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Commodity> getCommoditiesExceptOne(int commodityId);

    boolean addCombinationCommodities(int commodityId, int[] combinedCommodityId);

    void commodityAudit(int commodityId, CommodityState commodityState, String auditRemark);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getCommodityCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long countByPublishState(PublishState publishState);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long countByPublishStateAndPopular(PublishState publishState, boolean popular);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long countByPublishStateAndRecommend(PublishState publishState, boolean recommend);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<ActivityCommodity> getActivityCommoditiesByCommodityId(int commodityId);


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<ActivityCommodity> getActivityCommoditiesByCommodityIds(Set<Integer> commodityIds);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long countCommodityEvaluatesByCommodityId(int commodityId);

    public Commodity getCommodityAndCatalogByComId(int commodityId);

    public List<Commodity> getCommoditySellerByCommodityCode(List<String> commodityCodes);

    public void syncESCommodity();

}
