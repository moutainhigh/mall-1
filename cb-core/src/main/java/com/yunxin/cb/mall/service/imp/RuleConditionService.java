package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.RuleConditionDao;
import com.yunxin.cb.mall.entity.RuleCondition;
import com.yunxin.cb.mall.entity.RuleCondition_;
import com.yunxin.cb.mall.service.IRuleConditionService;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Aidy_He on 16/3/10.
 */
@Service
@Transactional
public class RuleConditionService implements IRuleConditionService {

    @Resource
    private RuleConditionDao ruleConditionDao;

    @Override
    public RuleCondition addRuleCondition(RuleCondition ruleCondition) throws EntityExistException {
        if (!ruleConditionDao.isUnique(RuleCondition_.ruleCode)) {
            throw new EntityExistException("规则编码已存在");
        }
        return ruleConditionDao.save(ruleCondition);
    }

    @Override
    public RuleCondition updateRuleCondition(RuleCondition ruleCondition) throws EntityExistException {
        if (!ruleConditionDao.isUnique(RuleCondition_.ruleCode)) {
            throw new EntityExistException("规则编码已存在");
        }
        RuleCondition dbRuleCondition = ruleConditionDao.findOne(ruleCondition.getRuleId());
        AttributeReplication.copying(ruleCondition, dbRuleCondition, RuleCondition_.ruleName, RuleCondition_.ruleValue, RuleCondition_.remark);
        return dbRuleCondition;
    }

    @Override
    public void removeRuleConditionById(int ruleConditionId) {
        ruleConditionDao.delete(ruleConditionId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public RuleCondition getRuleConditionByCode(String code) {
        RuleCondition ruleCondition = ruleConditionDao.findByRuleCode(code);
        return ruleCondition;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<RuleCondition> getRuleConditionsLikeCode(String code) {
        List<RuleCondition> ruleConditions = ruleConditionDao.findAll(new Specification<RuleCondition>() {
            @Override
            public Predicate toPredicate(Root<RuleCondition> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.like(root.get(RuleCondition_.ruleCode), code + "%");
            }
        });
        return ruleConditions;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<RuleCondition> pageRuleConditions(final PageSpecification<RuleCondition> query) {
        query.setCustomSpecification(new CustomSpecification<RuleCondition>() {
            @Override
            public void addConditions(Root<RuleCondition> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        return ruleConditionDao.findAll(query, query.getPageRequest());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public RuleCondition getRuleConditionById(int ruleId) {
        return ruleConditionDao.findOne(ruleId);
    }
}
