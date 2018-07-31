package com.yunxin.cb.search.service.impl;


import com.google.gson.Gson;
import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.repository.CommodityDao;
import com.yunxin.cb.search.service.CommodityService;
import com.yunxin.cb.search.vo.CommoditySpec;
import com.yunxin.cb.search.vo.CommodityVO;
import com.yunxin.cb.search.vo.SearchVo;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @author tanggangyi
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    private static final Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Resource
    private CommodityDao commodityDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void addCommodity(Commodity commodity) {
        commodityDao.save(commodity);
    }

    @Override
    public void deleteById(Integer id) {
        commodityDao.deleteById(id);
    }



    public Page<Commodity> keywordSearch(String keyword, Pageable pageable) throws Exception {

        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()

                .or(new Criteria("commodityCode").contains(keyword))
                .or(new Criteria("commodityName").contains(keyword))
                .or(new Criteria("commodityPYName").contains(keyword))
                .or(new Criteria("shortName").contains(keyword))
                .or(new Criteria("commodityTitle").contains(keyword))
                .or(new Criteria("description").contains(keyword))

                .or(new Criteria("categories.categoryName").contains(keyword))

                .or(new Criteria("seller.sellerCode").contains(keyword))
                .or(new Criteria("seller.sellerName").contains(keyword))

                .or(new Criteria("brand.brandNo").contains(keyword))
                .or(new Criteria("brand.brandName").contains(keyword))
                .or(new Criteria("brand.brandEnName").contains(keyword))
                .or(new Criteria("brand.brandTitle").contains(keyword))
                )
                .setPageable(pageable)
                .addSort(Sort.by(new Sort.Order(Sort.Direction.DESC, "commodityId")));

        Page<Commodity> page = elasticsearchTemplate.queryForPage(criteriaQuery, Commodity.class);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .build();
        Page<Commodity> sampleEntities =
                elasticsearchTemplate.queryForPage(searchQuery,Commodity.class);
        return page;
    }

    public Page<Commodity> categorySearch(SearchVo searchVo, Pageable pageable) throws Exception {

        Criteria criteria= new Criteria();
        if(searchVo.getBrandId()!=0) {
            criteria.and(new Criteria("brand.brandId").is(searchVo.getBrandId()));
        }
        if(searchVo.getSellerId()!=0) {
            criteria.and(new Criteria("seller.sellerId").is(searchVo.getSellerId()));
        }
        if(searchVo.getCategoryId()!=0) {
            criteria.and(new Criteria("categories.categoryId").is(searchVo.getCategoryId()));
        }
        if(searchVo.getLowestPrice()!=0) {
            criteria.and(new Criteria("sellPrice").greaterThanEqual(searchVo.getLowestPrice()));
        }
        if(searchVo.getHighestPrice()!=0) {
            criteria.and(new Criteria("sellPrice").lessThanEqual(searchVo.getHighestPrice()));
        }
        if(searchVo.getPriceSection()!=null) {
            criteria.and(new Criteria("priceSection.startPrice").is(searchVo.getPriceSection().getStartPrice()));
            criteria.and(new Criteria("priceSection.endPrice").is(searchVo.getPriceSection().getEndPrice()));
        }

        if(!searchVo.getCommoditySpecs().isEmpty()) {
            for(CommoditySpec commoditySpec :searchVo.getCommoditySpecs()) {
                criteria.and(new Criteria("commoditySpecs."+commoditySpec.getSpecName()).is(commoditySpec.getValue()));
            }
        }

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        criteriaQuery.setPageable(pageable);
        if(searchVo.getSortBy()==null){
            criteriaQuery.addSort(Sort.by(new Sort.Order(Sort.Direction.DESC, "commodityId")));
        }else {
            criteriaQuery.addSort(Sort.by(new Sort.Order(searchVo.getDirection(), searchVo.getSortBy().name())));
        }

        Page<Commodity> page = elasticsearchTemplate.queryForPage(criteriaQuery, Commodity.class);

        return page;
    }
    public void updateCommodity(Commodity commodity){
        commodityDao.save(commodity);
    }

    public long bulkIndex(List<Commodity> queries ) throws Exception
    {
        long count = 0;
        if(!elasticsearchTemplate.indexExists(Commodity.index_name)) {
            elasticsearchTemplate.createIndex(Commodity.index_name);
        }
        List<IndexQuery> indexQueries=new ArrayList<>();
        for (Commodity commodity :queries)
        {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(String.valueOf(commodity.getCommodityId()));
            Gson gson = new Gson();
            indexQuery.setSource(gson.toJson(commodity));
            indexQuery.setIndexName(Commodity.index_name);
            indexQuery.setType(Commodity.index_type);
            indexQueries.add(indexQuery);
            count++;
        }
        elasticsearchTemplate.bulkIndex(indexQueries);
        elasticsearchTemplate.refresh(Commodity.index_name);
        return count;
    }



}
