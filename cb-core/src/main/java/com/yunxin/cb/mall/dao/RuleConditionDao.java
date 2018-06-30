package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.RuleCondition;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RuleConditionDao extends JpaRepository<RuleCondition, Integer>, JpaSpecificationExecutor<RuleCondition>, BaseDao<RuleCondition> {

    RuleCondition findByRuleCode(String code);

}
