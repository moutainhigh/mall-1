package com.yunxin.cb.mall.service.imp;


import com.google.common.collect.Lists;
import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CombinationType;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.query.CommodityQuery;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.search.restful.RestfulFactory;
import com.yunxin.cb.search.service.SearchRestService;
import com.yunxin.cb.search.vo.CommodityVO;
import com.yunxin.cb.search.vo.ResponseResult;
import com.yunxin.cb.search.vo.meta.Result;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.CalendarUtils;
import com.yunxin.core.util.LogicUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author gonglei
 */
@Service
@Transactional
public class CommodityService implements ICommodityService {

    private static final Logger logger = LoggerFactory.getLogger(CommodityService.class);

    @Resource
    private CommodityDao commodityDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private AttributeDao attributeDao;

    @Resource
    private ActivityCommodityDao activityCommodityDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private CatalogDao catalogDao;

    @Resource
    private CombinationDao combinationDao;


    @Resource
    private PriceSectionDao priceSectionDao;

    @Resource
    private SpecDao specDao;

    @Resource
    private CommoditySpecDao commoditySpecDao;

    @Resource
    private CommodityCategoryDao commodityCategoryDao;

    @Resource
    private ProductEvaluateDao productEvaluateDao;

    @Resource
    private CatalogService catalogService;

    @Override
    public Commodity addCommodity(Commodity commodity) throws EntityExistException {
        if (!commodityDao.isOrUnique(commodity, Commodity_.commodityCode, Commodity_.commodityName)) {
            throw new EntityExistException("商品编码或商品名已存在");
        }
        commodity.setCreateTime(new Date());
        commodity.setCommodityState(CommodityState.WAIT_AUDITED);
        commodity.setPublishState(PublishState.WAIT_UP_SHELVES);
        commodity = commodityDao.save(commodity);
        int[] specId = commodity.getSpecId();
        String[] specValue = commodity.getSpecValue();
        if (specId != null) {
            for (int i = 0; i < specId.length; i++) {
                CommoditySpecId id = new CommoditySpecId(commodity.getCommodityId(), specId[i]);
                CommoditySpec commoditySpec = new CommoditySpec();
                commoditySpec.setCommodity(commodity);
                commoditySpec.setSpec(new Spec(specId[i]));
                commoditySpec.setId(id);
                commoditySpec.setValue(specValue[i]);
                commoditySpecDao.save(commoditySpec);
            }
        }
        return commodity;
    }

    @Override
    public Commodity updateCommodity(Commodity commodity) throws EntityExistException {
        if (!commodityDao.isOrUnique(commodity, Commodity_.commodityCode, Commodity_.commodityName)) {
            throw new EntityExistException("商品编码或商品名已存在");
        }
        Commodity dbCommodity = commodityDao.findOne(commodity.getCommodityId());
        //S add by lxc    2018-08-09 15:51      根据比例配置更新货品销售价
        float ratio = 1.0f;
        boolean updateProductSalePrice = true;
        if( commodity.getRatio() != null) {
            ratio = commodity.getRatio().floatValue();
            if(dbCommodity.getRatio() != null ){
                if(commodity.getRatio().compareTo(dbCommodity.getRatio()) == 0) {
                    updateProductSalePrice = false;
                }
            }
        }else{
            Catalog catalog = catalogService.findOneLevelCatalogByCatalogCode(dbCommodity.getCatalog().getCatalogCode());
            ratio = catalog.getRatio().floatValue();
        }
        if(updateProductSalePrice) {
            int j = productDao.updateSalePriceByCommodityId(ratio, dbCommodity.getCommodityId());
            if(j > 0){
                logger.info("更新货品的销售价成功....");
            }
        }
        //E
        commodity.setRatio(new BigDecimal(ratio));
        AttributeReplication.copying(commodity, dbCommodity, Commodity_.catalog, Commodity_.priceSection, Commodity_.brand, Commodity_.seller,
                Commodity_.commodityCode, Commodity_.commodityName, Commodity_.commodityPYName, Commodity_.shortName, Commodity_.commodityTitle,
                Commodity_.costPrice, Commodity_.sellPrice, Commodity_.marketPrice, Commodity_.unit, Commodity_.province, Commodity_.city, Commodity_.seoKey,
                Commodity_.seoTitle, Commodity_.seoDescription, Commodity_.popular, Commodity_.special, Commodity_.recommend, Commodity_.giveaway,
                Commodity_.barter, Commodity_.preSell, Commodity_.content, Commodity_.deliveryType, Commodity_.weight, Commodity_.volume, Commodity_.defaultPicPath, Commodity_.explainContent,
                Commodity_.settingContent, Commodity_.ratio);
        List<CommoditySpec> commoditySpecs = commoditySpecDao.getCommoditySpecsByCommodityId(commodity.getCommodityId());
        for (CommoditySpec cSpec : commoditySpecs) {
            commoditySpecDao.delete(cSpec);
        }

        int[] specId = commodity.getSpecId();
        String[] specValue = commodity.getSpecValue();
        if (specId != null) {
            for (int i = 0; i < specId.length; i++) {
                CommoditySpecId id = new CommoditySpecId(commodity.getCommodityId(), specId[i]);
                CommoditySpec commoditySpec = new CommoditySpec();
                commoditySpec.setCommodity(commodity);
                commoditySpec.setSpec(new Spec(specId[i]));
                commoditySpec.setId(id);
                commoditySpec.setValue(specValue[i]);
                commoditySpecDao.save(commoditySpec);
            }
        }
        // 删除未选中的图



        return dbCommodity;
    }

    /**
     * 更新搜索器ES中的商品
     * @param commodityId
     */
    public void updateCommodityES(int commodityId)throws Exception{
        Commodity commodity = commodityDao.findOne(commodityId);
        if (commodity.getPublishState() == PublishState.WAIT_UP_SHELVES || commodity.getPublishState() == PublishState.UP_SHELVES) {
            SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
            CommodityVO commodityVO = new CommodityVO(commodity);
            Call<ResponseResult> call = restService.updateCommodity(commodityVO);
            ResponseResult result = call.execute().body();
            logger.info("[elasticsearch] updateCommodityES:", result.getResult());
        }
    }



    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<CommoditySpec> getCommoditySpecsByCommodityId(int commodityId) {
        return commoditySpecDao.getCommoditySpecsByCommodityId(commodityId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<CommoditySpec> getCommoditySpecsByCommodity(Commodity commodity) {
        return commoditySpecDao.getCommoditySpecsByCommodity(commodity);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commodity getCommodityDetailById(int commodityId) {
        Commodity commodity = commodityDao.getCommodityDetailById(commodityId);
        return commodity;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commodity findCommodityById(int commodityId) {
        Commodity commodity = commodityDao.findOne(commodityId);
        return commodity;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommodityCategoryOrderBySaleNum(Commodity commodity, int limit) {
        return commodityCategoryDao.findByCategoryCommodityCategoryOrderBySaleNum(commodity, new PageRequest(0, limit));
    }

    @Override
    public void removeCommodityById(int commodityId) {
        commodityDao.delete(commodityId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Commodity> pageCommodities(PageSpecification<Commodity> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<Commodity>() {
            public void buildFetch(Root<Commodity> root) {
                root.fetch(Commodity_.brand, JoinType.LEFT);
                root.fetch(Commodity_.catalog, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Commodity> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                Path<CommodityState> statusPath = root.get(Commodity_.commodityState);
                predicates.add(builder.notEqual(statusPath, CommodityState.DEL.ordinal()));
                query.orderBy(builder.desc(root.get(Commodity_.createTime)));
            }
        });
        Page<Commodity> pages = commodityDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Commodity> choosePagedCommodities(PageSpecification<Commodity> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<Commodity>() {
            public void buildFetch(Root<Commodity> root) {
            }

            @Override
            public void addConditions(Root<Commodity> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                Path<CommodityState> statusPath = root.get(Commodity_.commodityState);
                predicates.add(builder.notEqual(statusPath, CommodityState.DEL.ordinal()));
                query.orderBy(builder.desc(root.get(Commodity_.createTime)));
            }
        });
        Page<Commodity> pages = commodityDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    /**
     * 网站前台查询分页
     *
     * @param commodityQuery
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Commodity> pageCommodities(CommodityQuery commodityQuery) {
        commodityQuery.setCustomSpecification(new CustomSpecification<Commodity>() {
            public void buildFetch(Root<Commodity> root) {
                root.fetch(Commodity_.brand, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Commodity> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                predicates.add(builder.notEqual(root.get(Commodity_.commodityState), CommodityState.DEL));
                Category category = commodityQuery.getCategory();
                SetJoin<Commodity, CommodityCategory> caPath = root.join(Commodity_.commodityCategories, JoinType.LEFT);
                if (category != null) {
                    switch (category.getLevel()) {
                        case 1:
                            predicates.add(builder.equal(caPath.get(CommodityCategory_.category).get(Category_.parentCategory).get(Category_.parentCategory), category));
                            break;
                        case 2:
                            predicates.add(builder.equal(caPath.get(CommodityCategory_.category).get(Category_.parentCategory), category));
                            break;
                        case 3:
                            predicates.add(builder.equal(caPath.get(CommodityCategory_.category), category));
                            break;
                    }
                }
                PriceSection priceSection = commodityQuery.getPriceSection();
                if (priceSection != null) {
                    predicates.add(builder.equal(root.get(Commodity_.priceSection), priceSection));
                }
                String[] itemId = commodityQuery.getItemId();
                if (itemId != null && itemId.length > 0) {
                    List<FilterItem> filterItems = new ArrayList<>();
                    for (String s : itemId) {
                        filterItems.add(new FilterItem(Integer.parseInt(s)));
                    }
                    SetJoin<CommodityCategory, FilterItem> cacPath = caPath.join(CommodityCategory_.filterItems, JoinType.LEFT);
                    predicates.add(cacPath.in(filterItems));
                }
                if (commodityQuery.getSort() == null) {
                    query.orderBy(builder.desc(caPath.get(CommodityCategory_.recommendValue)));
                }
            }
        });
        Page<Commodity> pages = commodityDao.findAll(commodityQuery, commodityQuery.getPageRequest());
        return pages;
    }

    /**
     * 最新推荐的5个商品
     *
     * @param recommend
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommoditiesByRecommendTopN(boolean recommend) {
        Page<Commodity> comPage = commodityDao.findAll(
                new Specification<Commodity>() {

                    @Override
                    public Predicate toPredicate(Root<Commodity> root,
                                                 CriteriaQuery<?> query, CriteriaBuilder builder) {
                        List<Predicate> predicates = Lists.newArrayList();
                        predicates.add(builder.equal(
                                root.get(Commodity_.recommend), recommend));
                        predicates.add(builder.equal(
                                root.get(Commodity_.commodityState), CommodityState.AUDITED));
                        predicates.add(builder.equal(
                                root.get(Commodity_.publishState), PublishState.UP_SHELVES));
                        query.orderBy(builder.desc(root.get(Commodity_.createTime)));
                        // 将所有条件用 and 联合起来
                        if (predicates.size() > 0) {
                            return builder.and(predicates
                                    .toArray(new Predicate[predicates.size()]));
                        }

                        return builder.conjunction();
                    }

                }, new PageRequest(0, 5)
        );
        return comPage.getContent();
    }

    @Override
    public void updateCommodityStatus(CommodityState commodityState,
                                      int commodityId) {
        commodityDao.updateCommodityStatusById(commodityState, commodityId);
    }

    @Override
    public void updateCommodityStatus(Product product,int commodityId) {
        commodityDao.updateDefaultProductById(product, commodityId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommoditysByCreateTime(int limit) {
        PageRequest pr = new PageRequest(0, limit);
        return commodityDao.findByCommodityStateOrderByCreateTimeDesc(
                CommodityState.AUDITED, pr);
    }

    @Override
    public Commodity updateDefaultPicPath(int commodityId, String defaultPicPath) {
        Commodity commodity = commodityDao.findOne(commodityId);
        commodity.setDefaultPicPath(defaultPicPath);
        return commodity;

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommodityByCategory(Catalog catalog, int limit) {
        return commodityDao.findByCatalog(catalog, new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommodityByCategoryOrderBySaleNum(
            Catalog catalog, int limit) {
        return commodityDao.findByCatalogOrderBySaleNumDesc(catalog,
                new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommodityByBrand(Brand brand, int limit) {
        return commodityDao.findByBrand(brand, new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommodityByBrandOrderBySaleNum(Brand brand,
                                                             int limit) {
        return commodityDao.findByBrandOrderBySaleNumDesc(brand,
                new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommoditysByPrice(float price, int limit) {
        return commodityDao.findBySellPriceBetween(1.1f * price, 0.9f * price,
                new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommoditysByIds(List<Integer> commodityIds) {
        return commodityDao.findByCommodityIdIn(commodityIds);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> findCommodityByPopular(boolean popular, int limit) {
        return commodityDao.findByPopular(popular, new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> findCommodityBySpecial(boolean special, int limit) {
        return commodityDao.findBySpecial(special, new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> findCommodityByRecommend(boolean recommend, int limit) {
        return commodityDao.findByRecommend(recommend,
                new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> findByRecommendAndCategoryCode(boolean recommend,
                                                          String categoryCode, int limit) {
        return commodityDao
                .findByRecommendAndCatalog_CatalogCodeLikeOrderByCreateTimeDesc(
                        recommend, categoryCode + "%",
                        new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> findByPopularAndCategoryCode(boolean popular, String categoryCode, int limit) {
        return commodityDao.findByPopularAndCatalog_CatalogCodeLikeOrderByCreateTimeDesc(popular, categoryCode + "%", new PageRequest(0, limit));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> findByCategoryOrderBySaleNum(Category category, int limit) {
        Pageable pageable = new PageRequest(0, limit);
        switch (category.getLevel()) {
            case 1:
                return commodityDao.findByCategory1OrderBySaleNumDesc(category, pageable);
            case 2:
                return commodityDao.findByCategory2OrderBySaleNumDesc(category, pageable);
            case 3:
                return commodityDao.findByCategory3OrderBySaleNumDesc(category, pageable);
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Commodity> pageCommodities(final String categoryCode,
                                           final int brandId, int page, int pageSize) {
        Page<Commodity> comPage = commodityDao.findAll(
                new Specification<Commodity>() {

                    @Override
                    public Predicate toPredicate(Root<Commodity> root,
                                                 CriteriaQuery<?> query, CriteriaBuilder builder) {
                        List<Predicate> predicates = Lists.newArrayList();
                        if (StringUtils.isNotBlank(categoryCode)) {
                            Path<Catalog> expression = root
                                    .get(Commodity_.catalog);
                            predicates.add(builder.like(
                                    expression.get(Catalog_.catalogCode),
                                    categoryCode + "%"));
                        }
                        if (brandId > 0) {
                            predicates.add(builder.equal(
                                    root.get(Commodity_.brand).get(
                                            Brand_.brandId), brandId
                            ));
                        }
                        // 将所有条件用 and 联合起来
                        if (predicates.size() > 0) {
                            return builder.and(predicates
                                    .toArray(new Predicate[predicates.size()]));
                        }
                        return builder.conjunction();
                    }
                }, new PageRequest(page - 1, pageSize)
        );
        return comPage;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Combination> getCombinationCommoditiesByCommodityId(int commodityId) {
        return combinationDao.findByCommodity_CommodityId(commodityId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Combination> getCombinationCommoditiesByCommodityId(int commodityId, PublishState publishState) {
        return combinationDao.findByCommodity_CommodityId(commodityId, publishState);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commodity findByCommodityId(int commodityId) {
        return commodityDao.findOne(commodityId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Long getCommodityNumberByCategory(String categoryName)
            throws Exception {
        return commodityDao.findCommodityQuantityByCategoryName(categoryName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commodity findByCommodityCode(String commodityCode) {
        return commodityDao.findTopByCommodityCode(commodityCode);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> findByStatus(CommodityState commodityState)
            throws Exception {
        return commodityDao.findByCommodityState(commodityState);
    }

    /**
     * 商品审核未通过，将其下货品下架
     **/
    @Override
    public void commodityAudit(int commodityId, CommodityState commodityState, String auditRemark) {
        if (commodityState == CommodityState.NOT_AUDITED || commodityState == CommodityState.WAIT_AUDITED) {
            commodityDao.commodityAudit(commodityId, commodityState, auditRemark);
            Commodity commodity = commodityDao.findOne(commodityId);
            commodity.setPublishState(PublishState.DOWN_SHELVES);
            List<Product> products = commodity.getProducts();
            for (Product p : products) {
                if (p.getPublishState() == PublishState.UP_SHELVES || p.getProductState() == ProductState.AUDITED) {
                    p.setPublishState(PublishState.DOWN_SHELVES);
                }
            }
        } else {
            commodityDao.commodityAudit(commodityId, commodityState, auditRemark);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commodity getCommodityByProductId(int productId) {
        Commodity commodity = commodityDao.findByProductId(productId);
        return commodity;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Spec> pageSpecs(PageSpecification<Spec> specQuery) {
        specQuery.setCustomSpecification(new CustomSpecification<Spec>() {

            public void buildFetch(Root<Spec> root) {
            }

            @Override
            public void addConditions(Root<Spec> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Spec_.specName)));
            }

        });
        Page<Spec> pages = specDao.findAll(specQuery,
                specQuery.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<CommoditySpec> getCommoditySpecsByCatalogId(final int catalogId) {
        return commoditySpecDao.getCommoditySpecsByCatalogId(catalogId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Spec> getSpecsByCatalogId(int catalogId) {
        return specDao.findByCatalog_CatalogId(catalogId);
    }

    @Override
    public Spec addSpec(Spec spec) throws EntityExistException {
        if (specDao.findTopBySpecNameAndCatalog_CatalogId(spec.getSpecName(), spec.getCatalog().getCatalogId()) != null) {
            throw new EntityExistException("规格名称已存在");
        }
        spec = specDao.save(spec);
        return spec;
    }

    @Override
    public Spec updateSpec(Spec spec) throws EntityExistException {
        if (specDao.findTopBySpecNameAndCatalog_CatalogIdAndSpecIdNot(spec.getSpecName(), spec.getCatalog().getCatalogId(), spec.getSpecId()) != null) {
            throw new EntityExistException("规格名称已存在");
        }
        Spec oldSpec = specDao.findOne(spec.getSpecId());
        AttributeReplication.copying(spec, oldSpec, Spec_.specName, Spec_.remark);
        return oldSpec;
    }

    /**
     * 克隆源分类下的规格到目标分类下
     * @param cloneCatalogId 源分类ID
     * @param catalogId 目标分类ID
     */
    @Override
    public void cloneSpec(int cloneCatalogId, int catalogId) {
        //该分类本身所有规格
        List<Spec> specs = getSpecsByCatalogId(catalogId);
        //需要克隆的分类所有规格
        List<Spec> cloneSpecs = getSpecsByCatalogId(cloneCatalogId);
        if(cloneSpecs != null){
            List<Spec> newSpecs = new ArrayList<>();
            cloneSpecs.forEach(spec -> {
                if(specs != null){
                    boolean exist = specs.contains(spec);
                    if(!exist){
                        Spec cloneSpec = new Spec(spec.getSpecName(), catalogId);
                        newSpecs.add(cloneSpec);
                    }
                }
            });
            specDao.save(newSpecs);
        }
    }

    @Override
    public int removeSpecById(int catalogId) {
        int result=0;
        try {
            specDao.delete(catalogId);
            result=1;
        } catch (Exception e) {
            logger.error("removeSpecById result flag is = "+e);
        }
        return result;
    }

    /**
     * 商品上下架
     *
     * @param commodityId
     * @param publishState
     * @return
     */
    @Override
    public ResponseResult upOrDownShelvesCommodity(int commodityId, PublishState publishState) throws Exception {
        ResponseResult responseResult = new ResponseResult(Result.FAILURE);
        Commodity commodity = commodityDao.findDefaultProductById(commodityId);
        if (commodity.getCommodityState() != CommodityState.AUDITED) {
            responseResult.setMessage("商品未审核，不能上下架操作！");
            return responseResult;
        }
        if ((commodity.getPublishState() == PublishState.WAIT_UP_SHELVES || commodity.getPublishState() == PublishState.DOWN_SHELVES)
                && publishState == PublishState.UP_SHELVES) {
            List<Product> products = productDao.findByCommodity_commodityId(commodityId);
            if (LogicUtils.isNotNullAndEmpty(products)) {
                List<Integer> prodIds=new ArrayList<Integer>();
                Product defaultProduct=commodity.getDefaultProduct();
                for (int i = 0; i < products.size(); i++) {
                    if(products.get(i).getPublishState()==PublishState.UP_SHELVES){
                        prodIds.add(products.get(i).getProductId());
                        if (i==0&&defaultProduct.getPublishState()!=PublishState.UP_SHELVES) {
                            defaultProduct=products.get(i);
                        }
                    }
                }
                if(prodIds.size()<=0){//没有已上架的货品，商品不能上架
                    responseResult.setMessage("没有已上架的货品，商品不能上架！");
                    return responseResult;
                }
                commodity.setDefaultProduct(defaultProduct);
                commodity.setPublishState(PublishState.UP_SHELVES);
                //productDao.updateUpOrDownShelvesInProductId(PublishState.UP_SHELVES, prodIds.toArray(new Integer[prodIds.size()]));//上架商品只上架已上架了的货品
                //商品上架，将商品添加到搜索容器
                SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
                CommodityVO commodityVO = new CommodityVO(commodity);
                Call<ResponseResult> call = restService.addCommodity(commodityVO);
                ResponseResult result = call.execute().body();
                logger.info("[elasticsearch] Commodity Sync State:" + result);
                responseResult.setResult(Result.SUCCESS);
                responseResult.setMessage("操作成功！");
                return responseResult;
            } else {
                responseResult.setMessage("商品没有所属货品，不能进行上下架操作！");
                return responseResult;
            }
        } else if ((commodity.getPublishState() == PublishState.WAIT_UP_SHELVES || commodity.getPublishState() == PublishState.UP_SHELVES)
                && publishState == PublishState.DOWN_SHELVES) {
            List<Product> products = productDao.findByCommodity_commodityId(commodityId);
            if (LogicUtils.isNotNullAndEmpty(products)) {
                Integer[] prodIds = new Integer[products.size()];
                for (int i = 0; i < products.size(); i++) {
                    prodIds[i] = products.get(i).getProductId();
                }
                commodity.setPublishState(PublishState.DOWN_SHELVES);
                productDao.updateUpOrDownShelvesInProductId(PublishState.DOWN_SHELVES, prodIds);
                //商品下架，将搜索容器中的商品删除
                SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
                Call<ResponseResult> call = restService.removeCommodity(commodityId);
                ResponseResult result = call.execute().body();
                logger.info("[elasticsearch] remove commodity state:" + result);
                responseResult.setResult(Result.SUCCESS);
                responseResult.setMessage("操作成功！");
            } else {
                responseResult.setMessage("商品没有所属货品，不能进行上下架操作！");
                return responseResult;
            }
        }
        return responseResult;
    }

    @Override
    public void removeCombinationById(int combinationId) {
        combinationDao.delete(combinationId);
    }

    @Override
    public Combination addCombination(Combination combination) {
        return combinationDao.save(combination);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommoditiesExceptOne(int commodityId) {
        return commodityDao.findCommoditiesExceptOne(commodityId);
    }


    @Override
    public boolean addCombinationCommodities(int commodityId, int[] combinedCommodityId) {
        if (null != combinedCommodityId && combinedCommodityId.length > 0) {
            for (int combinid : combinedCommodityId) {
                // 商品和自己组合，不存入数据库
                if (commodityId == combinid) {
                    continue;
                }
                long countCombin = combinationDao.countByCommodityIdAndCombinedCommodityId(commodityId, combinid);
                // 组合重复
                if (0 != countCombin) {
                    continue;
                }
                Combination combination = new Combination();
                combination.setCombinationType(CombinationType.TJDP);
                combination.setCommodity(commodityDao.findOne(commodityId));
                combination.setCombinedCommodity(commodityDao.findOne(combinid));
                combinationDao.save(combination);
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getCommodityCount() {
        return commodityDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long countByPublishState(PublishState publishState) {
        return commodityDao.countByPublishState(publishState);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long countByPublishStateAndPopular(PublishState publishState, boolean popular) {
        return commodityDao.countByPublishStateAndPopular(publishState, popular);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long countByPublishStateAndRecommend(PublishState publishState, boolean recommend) {
        return commodityDao.countByPublishStateAndRecommend(publishState, recommend);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ActivityCommodity> getActivityCommoditiesByCommodityId(int commodityId) {
        return activityCommodityDao.getActivityCommoditiesByCommodityId(commodityId, new Date(), new Date());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ActivityCommodity> getActivityCommoditiesByCommodityIds(Set<Integer> commodityIds) {
        Date date = CalendarUtils.getFormatDate(new Date());
        return activityCommodityDao.getActivityCommoditiesByCommodityIds(commodityIds, date, date);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long countCommodityEvaluatesByCommodityId(int commodityId) {
        return productEvaluateDao.countCommodityEvaluateByCommodityId(commodityId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Commodity getCommodityAndCatalogByComId(int commodityId) {
        return commodityDao.findOne(new Specification<Commodity>() {
            @Override
            public Predicate toPredicate(Root<Commodity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                root.fetch(Commodity_.catalog, JoinType.LEFT);
                return criteriaBuilder.equal(root.get(Commodity_.commodityId), commodityId);
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> getCommoditySellerByCommodityCode(List<String> commodityCodes) {
        return commodityDao.getCommoditySellerByCommodityCode(commodityCodes);
    }


    public void syncESCommodity() {
        try {
            List<Commodity> list = commodityDao.findAll();
            List<CommodityVO> listVo = new ArrayList<>();
            for(Commodity commodity : list){
                if(commodity.getPublishState() == PublishState.UP_SHELVES){
                    List<CommodityCategory> listCC = commodityCategoryDao.findByCommodity_CommodityId(commodity.getCommodityId());
                    if(listCC.size()>0){
                        Set<com.yunxin.cb.search.vo.Category> categories = new HashSet<>();
                        for(CommodityCategory commodityCategory : listCC){
                            Category category = commodityCategory.getCategory();
                            com.yunxin.cb.search.vo.Category category1 = new com.yunxin.cb.search.vo.Category();
                            category1.setCategoryId(category.getCategoryId());
                            category1.setCategoryName(category.getCategoryName());
                            category1.setCategoryNo(category.getCategoryNo());
                            category1.setIconPath(category.getIconPath());
                            category1.setSortOrder(category.getSortOrder());
                            categories.add(category1);
                        }
                        CommodityVO commodityVO = new CommodityVO(commodity);
                        commodityVO.setCategories(categories);
                        listVo.add(commodityVO);
                    }else{
                        CommodityVO commodityVO = new CommodityVO(commodity);
                        listVo.add(commodityVO);
                    }
                }
            }
            SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
            Call<ResponseResult> call = restService.bulkIndex(listVo);
            ResponseResult result = call.execute().body();
            logger.info("[elasticsearch] syncESCommodity state:" + result.getResult());
        } catch (Exception e) {
            logger.error("syncESCommodity failed", e);
        }
    }
}
