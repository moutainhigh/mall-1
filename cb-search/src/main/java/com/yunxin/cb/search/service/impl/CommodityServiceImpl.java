package com.yunxin.cb.search.service.impl;


import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.repository.CommodityDao;
import com.yunxin.cb.search.service.CommodityService;
import com.yunxin.cb.search.vo.SearchVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
//                .field("categories.categoryName", keyword)
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .or(new Criteria("commodityName").contains(keyword))
                .or(new Criteria("commodityPYName").contains(keyword))
                .or(new Criteria("shortName").contains(keyword))
                .or(new Criteria("commodityTitle").contains(keyword))
                .or(new Criteria("description").contains(keyword))

                .or(new Criteria("brand.brandName").contains(keyword))
                .or(new Criteria("brand.brandEnName").contains(keyword))
                .or(new Criteria("brand.brandTitle").contains(keyword)))
                .setPageable(pageable)
                .addSort(Sort.by(new Sort.Order(Sort.Direction.ASC, "commodityId")));

        Page<Commodity> page = elasticsearchTemplate.queryForPage(criteriaQuery, Commodity.class);

        return page;
    }

    public Page<Commodity> categorySearch(SearchVo searchVo, Pageable pageable) throws Exception {
//                .field("categories.categoryName", keyword)

        Criteria criteria= new Criteria();
        if(searchVo.getBrandId()!=0) {
            criteria.or(new Criteria("brand.brandId").is(searchVo.getBrandId()));
        }
        if(searchVo.getSellerId()!=0) {
            criteria.or(new Criteria("seller.sellerId").is(searchVo.getSellerId()));
        }
//        if(searchVo.getCategoryId()!=0) {
//            criteria.or(new Criteria("Category[].categoryId").is(searchVo.getCategoryId()));
//        }
        if(searchVo.getLowestPrice()!=0) {
            criteria.or(new Criteria("sellPrice").greaterThanEqual(searchVo.getLowestPrice()));
        }
        if(searchVo.getHighestPrice()!=0) {
            criteria.or(new Criteria("sellPrice").lessThanEqual(searchVo.getHighestPrice()));
        }
        if(searchVo.getPriceSection()!=null) {
            criteria.or(new Criteria("priceSection.startPrice").is(searchVo.getPriceSection().getStartPrice()));
            criteria.or(new Criteria("priceSection.endPrice").is(searchVo.getPriceSection().getEndPrice()));
        }

//        if(!searchVo.getCommoditySpecs().isEmpty()) {
//            for(CommoditySpec commoditySpec :searchVo.getCommoditySpecs()) {
//                criteria.or(new Criteria("commoditySpecs[]."+commoditySpec.getSpecName()).is(commoditySpec.getValue()));
//            }
//        }

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        criteriaQuery.setPageable(pageable);
        if(StringUtils.isEmpty(searchVo.getSortBy())){
            criteriaQuery.addSort(Sort.by(new Sort.Order(Sort.Direction.DESC, "commodityId")));
        }else {
            criteriaQuery.addSort(Sort.by(new Sort.Order(searchVo.getDirection(), searchVo.getSortBy())));
        }

        Page<Commodity> page = elasticsearchTemplate.queryForPage(criteriaQuery, Commodity.class);

        return page;
    }




}
