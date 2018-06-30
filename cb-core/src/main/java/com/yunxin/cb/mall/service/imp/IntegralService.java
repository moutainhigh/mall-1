package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.IntegralDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.IIntegralService;
import com.yunxin.cb.mall.service.IRuleConditionService;
import com.yunxin.cb.util.CalculateHelper;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author k001389
 */
@Service
@Transactional
public class IntegralService implements IIntegralService {

    @Resource
    private IntegralDao integralDao;

    @Resource
    private IRuleConditionService ruleConditionService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Integral> pageIntegrals(PageSpecification<Integral> query, Customer customer) {
        query.setCustomSpecification(new CustomSpecification<Integral>() {
            @Override
            public void buildFetch(Root<Integral> root) {
                Fetch<Integral, Customer> customerFetch = root.fetch(Integral_.customer, JoinType.LEFT);
                customerFetch.fetch(Customer_.rank, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Integral> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                predicates.add(builder.equal(root.get(Integral_.customer), customer));
                query.orderBy(builder.desc(root.get(Integral_.integralTime)));
            }
        });
        Page<Integral> pages = integralDao.findAll(query, query.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Integral> pageIntegrals(PageSpecification<Integral> query) {
        query.setCustomSpecification(new CustomSpecification<Integral>() {
            @Override
            public void buildFetch(Root<Integral> root) {
                root.fetch(Integral_.customer, JoinType.LEFT);
                root.fetch(Integral_.ruleCondition, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Integral> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Integral_.integralTime)));
            }
        });
        Page<Integral> pages = integralDao.findAll(query, query.getPageRequest());
        return pages;
    }

    @Override
    public Integral addIntegral(Integral integral) {
        if (null == integral) {
            throw new NullPointerException("integral is null");
        }
        integral.setIntegralTime(new Date());
        return integralDao.save(integral);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getOrderCalculateIntegral(int customerId, double calculateValue) {
        RuleCondition ruleCondition = ruleConditionService.getRuleConditionByCode("INTEGRAL_RATIO");
        double result = CalculateHelper.calculate(calculateValue, Double.valueOf(ruleCondition.getRuleValue()), CalculateHelper.DIVIDE);
        int getIntegral = (int) Math.round(result); // 购物获得可用积分
        return getIntegral;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRegisterCalculateIntegral() {
        RuleCondition ruleCondition = ruleConditionService.getRuleConditionByCode("INTEGRAL_REGISTER");
        int getIntegral = (int) Math.round(Double.valueOf(ruleCondition.getRuleValue())); // 注册获得可用积分
        return getIntegral;
    }

}
