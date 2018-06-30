package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.RuleCondition;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Aidy_He on 16/3/10.
 */
public interface IRuleConditionService {

    RuleCondition addRuleCondition(RuleCondition ruleCondition) throws EntityExistException;

    RuleCondition updateRuleCondition(RuleCondition ruleCondition) throws EntityExistException;

    void removeRuleConditionById(int ruleId);

    RuleCondition getRuleConditionByCode(String code);

    List<RuleCondition> getRuleConditionsLikeCode(String code);

    RuleCondition getRuleConditionById(int ruleId);

    Page<RuleCondition> pageRuleConditions(final PageSpecification<RuleCondition> query);

}
