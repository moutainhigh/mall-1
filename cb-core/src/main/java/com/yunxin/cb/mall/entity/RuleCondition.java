package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Aidy_He on 16/3/10.
 * 规则条件
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class RuleCondition implements Serializable {

    /**
     * ID
     */
    private int ruleId;
    /**
     * 规则编码
     */
    private String ruleCode;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 计算操作符
     */
    private String operator;
    /**
     * 规则值
     */
    private String ruleValue;
    /**
     * 备注
     */
    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    @Column(nullable = false, length = 64)
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @Column(nullable = false, unique = true, length = 32)
    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    @Column(nullable = false, length = 1)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(nullable = false)
    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
