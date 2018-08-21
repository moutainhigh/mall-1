package com.yunxin.cb.search.service.impl;


import com.google.gson.Gson;
import com.yunxin.cb.search.document.*;
import com.yunxin.cb.search.repository.*;
import com.yunxin.cb.search.service.CommodityService;
import com.yunxin.cb.search.vo.CombinationVO;
import com.yunxin.cb.search.vo.CommoditySpec;
import com.yunxin.cb.search.vo.SearchVo;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.join.query.HasParentQueryBuilder;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import static org.elasticsearch.index.query.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * @author tanggangyi
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    private static final Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Resource
    private CommodityEsDao commodityEsDao;
    @Resource
    private SpecEsDao specEsDao;
    @Resource
    private BrandEsDao brandEsDao;
    @Resource
    private CategoryEsDao categoryEsDao;
    @Resource
    private PriceSectionEsDao priceSectionEsDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void addCommodity(Commodity commodity) {
        commodityEsDao.save(commodity);
    }

    @Override
    public void deleteById(Integer id) {
        commodityEsDao.deleteById(id.toString());
    }


    public Page<Commodity> keywordSearch(String keyword, GeoPoint geoPoint, Pageable pageable) {

        return queryPage(geoPoint, keywordSearchQuery(keyword), null, pageable);
    }

    private QueryBuilder keywordSearchQuery(String keyword) {

        if (StringUtils.isBlank(keyword)) {
            return matchAllQuery();
        }
        return QueryBuilders.multiMatchQuery(keyword, "commodityCode", "commodityName", "commodityPYName",
                "shortName", "commodityTitle", "description", "categories.categoryName", "seller.sellerCode",
                "seller.sellerName", "brand.brandNo", "brand.brandName", "brand.brandEnName", "brand.brandTitle");
    }

    public Page<Commodity> categorySearch(SearchVo searchVo, Pageable pageable) throws Exception {

//        Criteria criteria= new Criteria();
//        if(searchVo.getBrandId()!=0) {
//            criteria.and(new Criteria("brand.brandId").is(searchVo.getBrandId()));
//        }
//        if(searchVo.getSellerId()!=0) {
//            criteria.and(new Criteria("seller.sellerId").is(searchVo.getSellerId()));
//        }
//        if(searchVo.getCategoryId()!=0) {
//            criteria.and(new Criteria("categories.categoryId").is(searchVo.getCategoryId()));
//        }
//        if(searchVo.getLowestPrice()!=0) {
//            criteria.and(new Criteria("sellPrice").greaterThanEqual(searchVo.getLowestPrice()));
//        }
//        if(searchVo.getHighestPrice()!=0) {
//            criteria.and(new Criteria("sellPrice").lessThanEqual(searchVo.getHighestPrice()));
//        }
//        if(searchVo.getPriceSection()!=null) {
//            criteria.and(new Criteria("priceSection.startPrice").is(searchVo.getPriceSection().getStartPrice()));
//            criteria.and(new Criteria("priceSection.endPrice").is(searchVo.getPriceSection().getEndPrice()));
//        }
//
//        if(!searchVo.getCommoditySpecs().isEmpty()) {
//            for(CommoditySpec commoditySpec :searchVo.getCommoditySpecs()) {
//                criteria.and(new Criteria("commoditySpecs.specName").is(commoditySpec.getSpecName()));
//                criteria.and(new Criteria("commoditySpecs.value").is(commoditySpec.getValue()));
//            }
//        }
//
//        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
//        criteriaQuery.setPageable(pageable);
//        if(searchVo.getSortBy()==null){
//            criteriaQuery.addSort(Sort.by(new Sort.Order(Sort.Direction.DESC, "commodityId")));
//        }else {
//            criteriaQuery.addSort(Sort.by(new Sort.Order(searchVo.getDirection(), searchVo.getSortBy().name())));
//        }
//
//        Page<Commodity> page = elasticsearchTemplate.queryForPage(criteriaQuery, Commodity.class);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(searchVo.getBrandId()!=0) {
            boolQueryBuilder.must(termQuery("brand.brandId", searchVo.getBrandId()));
        }
        if(searchVo.getSellerId()!=0) {
            boolQueryBuilder.must(termQuery("seller.sellerId", searchVo.getSellerId()));
        }
        if(searchVo.getCategoryId()!=0) {
            boolQueryBuilder.must(termQuery("categories.categoryId", searchVo.getCategoryId()));
        }
        if (searchVo.getLowestPrice() > 0 || searchVo.getHighestPrice() > 0) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("sellPrice");
            if (searchVo.getLowestPrice() > 0) {
                rangeQueryBuilder.gte(searchVo.getLowestPrice());
            }
            if (searchVo.getHighestPrice() > 0) {
                rangeQueryBuilder.lte(searchVo.getHighestPrice());
            }
            boolQueryBuilder.must(rangeQueryBuilder);
        }
        if(searchVo.getPriceSection()!=null) {
            if (searchVo.getPriceSection().getStartPrice() >= 0) {
                boolQueryBuilder.must(
                        QueryBuilders.rangeQuery("priceSection.startPrice")
                                .gte(searchVo.getPriceSection().getStartPrice()));
            }
            if (searchVo.getPriceSection().getEndPrice() >= 0) {
                boolQueryBuilder.must(
                        QueryBuilders.rangeQuery("priceSection.endPrice")
                                .lte(searchVo.getPriceSection().getEndPrice()));
            }
        }
        if(!searchVo.getCommoditySpecs().isEmpty()) {
            for (CommoditySpec commoditySpec : searchVo.getCommoditySpecs()) {
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(commoditySpec.getSpecName()).field("commoditySpecs.specName").defaultOperator(AND));
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(commoditySpec.getValue()).field("commoditySpecs.value").defaultOperator(AND));
            }
        }
        if (StringUtils.isNotBlank(searchVo.getCityCode())) {
            boolQueryBuilder.must(termQuery("seller.city", searchVo.getCityCode()));
        }

        GeoPoint geoPoint = null;
        if (searchVo.getLat() != null && searchVo.getLon() != null) {
            geoPoint = new GeoPoint(searchVo.getLat(), searchVo.getLon());
        }

        SortBuilder sortBuilder = null;
        if (searchVo.getDirection() != null && searchVo.getSortBy() != null) {
            sortBuilder = SortBuilders.fieldSort(searchVo.getSortBy().name())
                    .order(SortOrder.valueOf(searchVo.getDirection().name()));
        }

        return queryPage(geoPoint, boolQueryBuilder, sortBuilder, pageable);
    }

    private Page<Commodity> queryPage(GeoPoint geoPoint, QueryBuilder queryBuilder, SortBuilder sortBuilder, Pageable pageable) {

        SortBuilder defaultSort;
        Page<Commodity> page;
        boolean geoSearch = false;

        if (geoPoint != null) {
            geoSearch = true;
            // 获取距离以及距离排序查询
            defaultSort = SortBuilders.geoDistanceSort("location", geoPoint).unit(DistanceUnit.KILOMETERS).order(SortOrder.ASC);
        } else {
            // 默认排序
            defaultSort = SortBuilders.fieldSort("commodityId").order(SortOrder.DESC);
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withIndices(Commodity.index_name)
                .withTypes(Commodity.index_type)
                .withPageable(pageable);
        if (sortBuilder != null) {
            // 指定的排序，第一排序
            nativeSearchQueryBuilder.withSort(sortBuilder);
        }
        SearchQuery searchQuery = nativeSearchQueryBuilder
                .withSort(defaultSort)  //默认排序，第二排序
                .build();

        if (geoSearch) {
            page = elasticsearchTemplate.query(searchQuery, response -> {
                Gson gson = new Gson();
                List<Commodity> commodities = new ArrayList<>();
                SearchHits hits = response.getHits();
                SearchHit[] searchHists = hits.getHits();
                for (SearchHit hit : searchHists) {
                    // 获取距离值，并保留两位小数点
                    Commodity commodity = gson.fromJson(hit.getSourceAsString(), Commodity.class);
                    commodities.add(commodity);
                    Object[] sorts = hit.getSortValues();
                    // 商品没有坐标，得到无穷距离，不返回距离
                    Double distance_d = (Double) sorts[sorts.length - 1];
                    if (distance_d.isInfinite() || distance_d.isNaN()) {
                        continue;
                    }
                    BigDecimal geoDis = new BigDecimal(distance_d);
                    BigDecimal distance = geoDis.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    commodity.setDistance(distance);
                }
                return new PageImpl<>(commodities, pageable, hits.getTotalHits());
            });
        } else {
            page = elasticsearchTemplate.queryForPage(searchQuery, Commodity.class);
        }

        return page;
    }
    public void updateCommodity(Commodity commodity){
        commodityEsDao.save(commodity);
    }

    public void bulkIndex(List<Commodity> queries) throws Exception {
//        if(!elasticsearchTemplate.indexExists(Commodity.index_name)) {
//            elasticsearchTemplate.createIndex(Commodity.index_name);
//        }
//        List<IndexQuery> indexQueries=new ArrayList<>();
//        List<IndexQuery> specQueries = new ArrayList<>();
//        for (Commodity commodity :queries)
//        {
//            IndexQuery indexQuery = new IndexQuery();
//            indexQuery.setId(String.valueOf(commodity.getCommodityId()));
//            Gson gson = new Gson();
//            indexQuery.setSource(gson.toJson(commodity));
//            indexQuery.setIndexName(Commodity.index_name);
//            indexQuery.setType(Commodity.index_type);
//            indexQueries.add(indexQuery);
//            count++;
//
//            if (commodity.getCommoditySpecs() == null || commodity.getCommoditySpecs().size() == 0) {
//                continue;
//            }
//
//            for (CommoditySpec spec : commodity.getCommoditySpecs()) {
//                Spec one = new Spec();
//                String uuid = UUID.randomUUID().toString();
////                spec.getSpecName() + String.valueOf(commodity.getCommodityId()
//                one.setId(uuid);
//                one.setSpecName(spec.getSpecName());
//                one.setValue(spec.getValue());
//                one.setParentId(String.valueOf(commodity.getCommodityId()));
//                IndexQuery specQuery = new IndexQueryBuilder().withId(uuid)
//                        .withObject(one).withParentId(String.valueOf(commodity.getCommodityId())).build();
//                specQueries.add(specQuery);
////                indexQueries.add(specQuery);
////                elasticsearchTemplate.index(specQuery);
//            }
////            elasticsearchTemplate.bulkIndex(specQueries);
//        }
//        elasticsearchTemplate.bulkIndex(indexQueries);
//        elasticsearchTemplate.bulkIndex(specQueries);
//        elasticsearchTemplate.refresh(Commodity.index_name);

        if (!elasticsearchTemplate.typeExists(Commodity.index_name, Commodity.index_type)) {
            elasticsearchTemplate.putMapping(Commodity.class);
        }

        // commodity list is empty
        if (CollectionUtils.isEmpty(queries)) {
            return;
        }

        // spec && brand && category && priceSection
        List<Spec> specs = new ArrayList<>();
        List<Brand> brands = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<PriceSection> priceSections = new ArrayList<>();
        for (Commodity commodity : queries) {
            // spec
            if (!CollectionUtils.isEmpty(commodity.getCommoditySpecs())) {
                for (CommoditySpec spec : commodity.getCommoditySpecs()) {
                    Spec one = new Spec();
//                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    String uuid = Spec.index_type + "-" + spec.getSpecName() + "-" + Commodity.index_type + "-" + commodity.getCommodityId();
                    one.setId(uuid);
                    one.setSpecName(spec.getSpecName());
                    one.setValue(spec.getValue());
                    one.setParentId(commodity.getId());
                    specs.add(one);
                }
            }

            // brand
            if (commodity.getBrand() != null) {
                Brand brand = new Brand();
                BeanUtils.copyProperties(commodity.getBrand(), brand);
                brand.setId(Brand.index_type + "-" + Commodity.index_type + "-" + commodity.getId());
                brand.setParentId(commodity.getId());
                brands.add(brand);
            }

            // category
            if (!CollectionUtils.isEmpty(commodity.getCategories())) {
                for (com.yunxin.cb.search.vo.Category category : commodity.getCategories()) {
                    Category one = new Category();
                    BeanUtils.copyProperties(category, one);
                    one.setId(Category.index_type + "-" + category.getCategoryId() + "-" + Commodity.index_type + "-" + commodity.getId());
                    one.setParentId(commodity.getId());
                    categories.add(one);
                }
            }

            // priceSection
            if (commodity.getPriceSection() != null) {
                PriceSection priceSection = new PriceSection();
                BeanUtils.copyProperties(commodity.getPriceSection(), priceSection);
                priceSection.setId(PriceSection.index_type + "-" + Commodity.index_type + "-" + commodity.getId());
                priceSection.setParentId(commodity.getId());
                priceSections.add(priceSection);
            }
        }

        // commodity
        commodityEsDao.saveAll(queries);

        if (!CollectionUtils.isEmpty(specs)) {
            specEsDao.saveAll(specs);
        }
        if (!CollectionUtils.isEmpty(brands)) {
            brandEsDao.saveAll(brands);
        }
        if (!CollectionUtils.isEmpty(categories)) {
            categoryEsDao.saveAll(categories);
        }
        if (!CollectionUtils.isEmpty(priceSections)) {
            priceSectionEsDao.saveAll(priceSections);
        }

    }

    /**
     * 根据商品ID查询商品
     * @param commodityId
     * @return
     */
    public Commodity selectByCommodityId(int commodityId){
        Optional<Commodity> commodity = commodityEsDao.findById(commodityId+"");
        Commodity commod = commodity.get();
        return commod;
    }

    /**
     * 查询所有ES中所有商品
     */
    public List<Commodity> findByAll(){
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria());
        List<Commodity> list = elasticsearchTemplate.queryForList(criteriaQuery,Commodity.class);
        return list;
    }

    @Override
    public CombinationVO getCombination(String keyword) {

//        List<Spec> specs = getSearchSpecs(keyword);
//        List<Brand> brands = getSearchBrands(keyword);
        List<PriceSection> priceSections = getSearchPriceSection(keyword);
        List<Category> categories = getSearchCategories(keyword);

        CombinationVO combinationVO = new CombinationVO();
        Map<String, List<Object>> specVos = new HashMap<>();
        List<com.yunxin.cb.search.vo.PriceSection> priceSectionVos = new ArrayList<>();
        Set<com.yunxin.cb.search.vo.Category> categoryVos = new HashSet<>();

//        specs.forEach(spec -> {
//            List<Object> values = specVos.get(spec.getSpecName());
//            if (values == null || values.size() == 0) {
//                values = new ArrayList<>();
//                values.add(spec.getValue());
//            } else {
//                values.add(spec.getValue());
//            }
//            specVos.put(spec.getSpecName(), values);
//        });

        priceSections.forEach(priceSection -> {
            com.yunxin.cb.search.vo.PriceSection section = new com.yunxin.cb.search.vo.PriceSection();
            BeanUtils.copyProperties(priceSection, section);
            priceSectionVos.add(section);
        });

        categories.forEach(category -> {
            com.yunxin.cb.search.vo.Category cat = new com.yunxin.cb.search.vo.Category();
            BeanUtils.copyProperties(category, cat);
            categoryVos.add(cat);
        });

        combinationVO.setCondition(specVos);
        combinationVO.setPriceSection(priceSectionVos);
        combinationVO.setCategories(categoryVos);

        return combinationVO;
    }

    public List<Spec> getSearchSpecs(String keyword) {

        HasParentQueryBuilder hpqb = JoinQueryBuilders.hasParentQuery("commodity",
                keywordSearchQuery(keyword), true);

        AbstractAggregationBuilder aggregation =
                AggregationBuilders
                        .terms("byName").field("specName")
                        .subAggregation(
                                AggregationBuilders.terms("byValue").field("value")
                                        .subAggregation(
                                                AggregationBuilders.topHits("top").size(1)
                                        )
                        );

        // es默认只拿第一页，10条。这里设置拿1000条
        PageRequest pageRequest = PageRequest.of(0, 1000);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(hpqb)
                .withIndices(Commodity.index_name)
                .withTypes(Spec.index_type)
                .addAggregation(aggregation)
                .withPageable(pageRequest)
                .build();

        List<Spec> result = elasticsearchTemplate.query(searchQuery, response -> {
            Gson gson = new Gson();
            List<Spec> specs = new ArrayList<>();
            Terms agg = response.getAggregations().get("byName");
            for (Terms.Bucket entry : agg.getBuckets()) {
                String key = String.valueOf(entry.getKey());
                long docCount = entry.getDocCount();
                logger.info(("key:" + key + " doc_count:" + docCount));

                Terms agg1 = entry.getAggregations().get("byValue");
                for (Terms.Bucket entry1 : agg1.getBuckets()) {
                    String key1 = String.valueOf(entry1.getKey());
                    long docCount1 = entry1.getDocCount();
                    logger.info(("key:" + key1 + " doc_count:" + docCount1));

                    TopHits topHits = entry1.getAggregations().get("top");
                    for (SearchHit hit : topHits.getHits()) {
                        logger.info(" -> id: " + hit.getId() + " json: " + hit.getSource().toString());
                        Spec spec = gson.fromJson(hit.getSourceAsString(), Spec.class);
                        specs.add(spec);
                    }
                }
            }
            return specs;
        });

        return result;
    }

    public List<Brand> getSearchBrands(String keyword) {

        HasParentQueryBuilder hpqb = JoinQueryBuilders.hasParentQuery("commodity",
                keywordSearchQuery(keyword), true);

        AbstractAggregationBuilder aggregation =
                AggregationBuilders
                        .terms("byName").field("brandId")
                        .subAggregation(
                                AggregationBuilders.topHits("top").size(1)
                        );

        // es默认只拿第一页，10条。这里设置拿1000条
        PageRequest pageRequest = PageRequest.of(0, 1000);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(hpqb)
                .withIndices(Commodity.index_name)
                .withTypes(Brand.index_type)
                .addAggregation(aggregation)
                .withPageable(pageRequest)
                .build();

        List<Brand> result = elasticsearchTemplate.query(searchQuery, response -> {
            Gson gson = new Gson();
            List<Brand> brands = new ArrayList<>();
            Terms agg = response.getAggregations().get("byName");
            for (Terms.Bucket entry : agg.getBuckets()) {
                String key = String.valueOf(entry.getKey());
                long docCount = entry.getDocCount();
                logger.info(("key:" + key + " doc_count:" + docCount));

                TopHits topHits = entry.getAggregations().get("top");
                for (SearchHit hit : topHits.getHits()) {
                    logger.info(" -> id: " + hit.getId() + " json: " + hit.getSource().toString());
                    Brand brand = gson.fromJson(hit.getSourceAsString(), Brand.class);
                    brands.add(brand);
                }
            }
            return brands;
        });

        return result;
    }

    public List<Category> getSearchCategories(String keyword) {

        HasParentQueryBuilder hpqb = JoinQueryBuilders.hasParentQuery("commodity",
                keywordSearchQuery(keyword), true);

        AbstractAggregationBuilder aggregation =
                AggregationBuilders
                        .terms("byName").field("categoryId")
                        .subAggregation(
                                AggregationBuilders.topHits("top").size(1)
                        );

        // es默认只拿第一页，10条。这里设置拿1000条
        PageRequest pageRequest = PageRequest.of(0, 1000);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(hpqb)
                .withIndices(Commodity.index_name)
                .withTypes(Category.index_type)
                .addAggregation(aggregation)
                .withPageable(pageRequest)
                .build();

        List<Category> result = elasticsearchTemplate.query(searchQuery, response -> {
            Gson gson = new Gson();
            List<Category> categories = new ArrayList<>();
            Terms agg = response.getAggregations().get("byName");
            for (Terms.Bucket entry : agg.getBuckets()) {
                String key = String.valueOf(entry.getKey());
                long docCount = entry.getDocCount();
                logger.info(("key:" + key + " doc_count:" + docCount));

                TopHits topHits = entry.getAggregations().get("top");
                for (SearchHit hit : topHits.getHits()) {
                    logger.info(" -> id: " + hit.getId() + " json: " + hit.getSource().toString());
                    Category category = gson.fromJson(hit.getSourceAsString(), Category.class);
                    categories.add(category);
                }
            }
            return categories;
        });

        return result;
    }

    public List<PriceSection> getSearchPriceSection(String keyword) {

        HasParentQueryBuilder hpqb = JoinQueryBuilders.hasParentQuery("commodity",
                keywordSearchQuery(keyword), true);

        AbstractAggregationBuilder aggregation =
                AggregationBuilders
                        .terms("byStart").field("startPrice")
                        .subAggregation(
                                AggregationBuilders.terms("byEnd").field("endPrice")
                                        .subAggregation(
                                                AggregationBuilders.topHits("top").size(1)
                                        )
                        );

        // es默认只拿第一页，10条。这里设置拿1000条
        PageRequest pageRequest = PageRequest.of(0, 1000);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(hpqb)
                .withIndices(Commodity.index_name)
                .withTypes(PriceSection.index_type)
                .addAggregation(aggregation)
                .withPageable(pageRequest)
                .build();

        List<PriceSection> result = elasticsearchTemplate.query(searchQuery, response -> {
            Gson gson = new Gson();
            List<PriceSection> priceSections = new ArrayList<>();
            Terms agg = response.getAggregations().get("byStart");
            for (Terms.Bucket entry : agg.getBuckets()) {
                String key = String.valueOf(entry.getKey());
                long docCount = entry.getDocCount();
                logger.info(("key:" + key + " doc_count:" + docCount));

                Terms agg1 = entry.getAggregations().get("byEnd");
                for (Terms.Bucket entry1 : agg1.getBuckets()) {
                    String key1 = String.valueOf(entry1.getKey());
                    long docCount1 = entry1.getDocCount();
                    logger.info(("key:" + key1 + " doc_count:" + docCount1));

                    TopHits topHits = entry1.getAggregations().get("top");
                    for (SearchHit hit : topHits.getHits()) {
                        logger.info(" -> id: " + hit.getId() + " json: " + hit.getSource().toString());
                        PriceSection priceSection = gson.fromJson(hit.getSourceAsString(), PriceSection.class);
                        priceSections.add(priceSection);
                    }
                }
            }
            return priceSections;
        });

        return result;
    }

}
