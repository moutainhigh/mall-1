package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.ProductEvaluateDao;
import com.yunxin.cb.mall.dao.ProductEvaluateReplyDao;
import com.yunxin.cb.mall.entity.Customer_;
import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.ProductEvaluateReply;
import com.yunxin.cb.mall.entity.ProductEvaluate_;
import com.yunxin.cb.mall.service.IEvaluateService;
import com.yunxin.cb.mall.service.IOrderService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.*;

@Service
@Transactional
public class EvaluateService implements IEvaluateService {

    @Resource
    private ProductEvaluateDao productEvaluateDao;

    @Resource
    private ProductEvaluateReplyDao productEvaluateReplyDao;

    @Resource
    private IOrderService orderService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<ProductEvaluate> pageProductEvaluates(final PageSpecification<ProductEvaluate> query) {
        query.setCustomSpecification(new CustomSpecification<ProductEvaluate>() {
            @Override
            public void buildFetch(Root<ProductEvaluate> root) {
                root.fetch(ProductEvaluate_.commodity, JoinType.LEFT);
                root.fetch(ProductEvaluate_.customer, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<ProductEvaluate> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(ProductEvaluate_.createTime)));
            }
        });
        return productEvaluateDao.findAll(query, query.getPageRequest());
    }


    @Override
    public ProductEvaluate addProductEvaluate(ProductEvaluate productEvaluate) {
        int itemId = productEvaluate.getOrderItem().getItemId();
        productEvaluate.setCreateTime(new Date());
        productEvaluate.setReplyStatus(false);
        ProductEvaluate dbEvaluate = productEvaluateDao.save(productEvaluate);
        if (null != dbEvaluate) {
            orderService.updateOrderItemEvaluate(itemId);
        }
        return dbEvaluate;
    }

    @Override
    public ProductEvaluateReply addProductEvaluateReply(ProductEvaluateReply productEvaluateReply) throws EntityExistException {
        ProductEvaluate evaluate = productEvaluateDao.findOne(productEvaluateReply.getProductEvaluate().getEvaluateId());
        if (evaluate.isReplyStatus()) {
            throw new EntityExistException("该评价已回复");
        }
        productEvaluateReply.setCreateTime(new Date());

        productEvaluateReply.setProductEvaluate(evaluate);
        ProductEvaluateReply reply = productEvaluateReplyDao.save(productEvaluateReply);
        if (null != reply) {
            evaluate.setReplyStatus(true);
            productEvaluateDao.save(evaluate);
        }
        return reply;
    }

    @Override
    public void removeProductEvaluateReplyById(int replyId) {
        productEvaluateReplyDao.delete(replyId);
    }

    @Override
    public ProductEvaluate updateProductEvaluate(ProductEvaluate productEvaluate) {
        ProductEvaluate ce = productEvaluateDao.findOne(productEvaluate.getEvaluateId());
        ce.setReplyStatus(true);
        AttributeReplication.copying(productEvaluate, ce, ProductEvaluate_.commodity, ProductEvaluate_.order, ProductEvaluate_.customer, ProductEvaluate_.score, ProductEvaluate_.content, ProductEvaluate_.replyStatus, ProductEvaluate_.createTime);
        Set<ProductEvaluateReply> productEvaluateReplies = productEvaluateDao.findProductEvaluateReplysByEvaluateId(productEvaluate.getEvaluateId());
        ce.setProductEvaluateReplies(productEvaluateReplies);
        return ce;
    }

    @Override
    public void removeProductEvaluateById(int evaluateId) {
        productEvaluateDao.delete(evaluateId);
    }

    @Override
    public ProductEvaluate addCommodityEvaluate(ProductEvaluate productEvaluates) {
        productEvaluates.setCreateTime(new Date());
        productEvaluates.setReplyStatus(false);
        return productEvaluateDao.save(productEvaluates);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ProductEvaluate getCommodityEvaluateById(int evaId) {
        ProductEvaluate ce = productEvaluateDao.findOne(evaId);
        return ce;
    }

    @Override
    public ProductEvaluate updateCommodityEvaluate(ProductEvaluate productEvaluates) {
        ProductEvaluate ce = productEvaluateDao.findOne(productEvaluates.getEvaluateId());
        ce.setReplyStatus(true);
        return ce;
    }

    @Override
    public void removeCommodityEvaluateById(int evaId) {
        productEvaluateDao.delete(evaId);
    }


    @Override
    public ProductEvaluateReply addCommodityEvalReply(
            ProductEvaluateReply productEvaluateReply) {
        productEvaluateReply.setCreateTime(new Date());
        return productEvaluateReplyDao.save(productEvaluateReply);
    }


    @Override
    public Page<ProductEvaluate> pageProductEvaluatesByCommodityId(final PageSpecification<ProductEvaluate> evaluateQuery, int commodityId) {
        evaluateQuery.setCustomSpecification(new CustomSpecification<ProductEvaluate>() {
            public void buildFetch(Root<ProductEvaluate> root) {
                root.fetch(ProductEvaluate_.customer, JoinType.LEFT).fetch(Customer_.rank, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<ProductEvaluate> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                predicates.add(builder.equal(root.get(ProductEvaluate_.commodity), commodityId));
                query.orderBy(builder.desc(root.get(ProductEvaluate_.createTime)));
            }
        });
        return productEvaluateDao.findAll(evaluateQuery, evaluateQuery.getPageRequest());
    }

    public Map<String, Integer> scoreCalculation(int commodityId) {
        Map<String, Integer> scoreMap = new HashMap<>();
        float good = 0f;
        float middle = 0f;
        float bad = 0f;
        float totalCount = productEvaluateDao.countCommodityEvaluateByCommodityId(commodityId);
        List<Integer> scores = productEvaluateDao.getAllEvaluatesByCommodityId(commodityId);
        for (Integer score : scores) {
            if (score < 3) {
                ++bad;
            } else if (score == 3) {
                ++middle;
            } else {
                ++good;
            }
        }
        scoreMap.put("totalCount", (int) totalCount);
        scoreMap.put("good", (int) good);
        scoreMap.put("middle", (int) middle);
        scoreMap.put("bad", (int) bad);
        scoreMap.put("goodRate", Math.round(good / totalCount * 100));
        scoreMap.put("middleRate", Math.round(middle / totalCount * 100));
        scoreMap.put("badRate", Math.round(bad / totalCount * 100));

        return scoreMap;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ProductEvaluate getEvaluateById(int evaluateId) {
        return productEvaluateDao.findOne(new Specification<ProductEvaluate>() {
            @Override
            public Predicate toPredicate(Root<ProductEvaluate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                root.fetch(ProductEvaluate_.commodity);
                root.fetch(ProductEvaluate_.customer);
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(ProductEvaluate_.createTime)));
                criteriaQuery.where(criteriaBuilder.equal(root.get(ProductEvaluate_.evaluateId), evaluateId));
                return null;
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<ProductEvaluateReply> getProductEvaluateReplysByEvaluateId(int evaluateId) {
        Set<ProductEvaluateReply> productEvaluateReplies = productEvaluateDao.findProductEvaluateReplysByEvaluateId(evaluateId);
        return productEvaluateReplies;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductEvaluate> getLastedEvaluates(int limit) {
        return productEvaluateDao.findAll(new Specification<ProductEvaluate>() {
            @Override
            public Predicate toPredicate(Root<ProductEvaluate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(ProductEvaluate_.createTime)));
                return null;
            }
        });
    }

}
