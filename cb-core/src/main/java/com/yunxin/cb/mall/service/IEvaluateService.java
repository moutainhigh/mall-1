package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.ProductEvaluateReply;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IEvaluateService {

//	public Page<CommodityEvaluate> pageCommodityEvaluates(CommodityEvaluateQuery query); 

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<ProductEvaluate> pageProductEvaluates(PageSpecification<ProductEvaluate> query);

    ProductEvaluate addProductEvaluate(ProductEvaluate productEvaluates);


    ProductEvaluateReply addProductEvaluateReply(ProductEvaluateReply productEvaluateReply) throws EntityExistException;

    void removeProductEvaluateReplyById(int replyId);

    ProductEvaluate updateProductEvaluate(ProductEvaluate productEvaluates);

    void removeProductEvaluateById(int evaluateId);

    ProductEvaluate addCommodityEvaluate(ProductEvaluate productEvaluates);

    ProductEvaluate getCommodityEvaluateById(int evaId);


    ProductEvaluate updateCommodityEvaluate(ProductEvaluate productEvaluates);

    void removeCommodityEvaluateById(int evaId);

    ProductEvaluateReply addCommodityEvalReply(ProductEvaluateReply commodityEvaluateReply);

//	public Page<CommodityEvalReply> pageCommodityEvaluatesBy(CommodityEvalReplyQuery evaluateQuery);

    public Map<String, Integer> scoreCalculation(int commodityId);

    Page<ProductEvaluate> pageProductEvaluatesByCommodityId(PageSpecification<ProductEvaluate> evaluateQuery, int commodityId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    ProductEvaluate getEvaluateById(int evaluateId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Set<ProductEvaluateReply> getProductEvaluateReplysByEvaluateId(int evaluateId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<ProductEvaluate> getLastedEvaluates(int limit);
}
