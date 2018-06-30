package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Integral implements java.io.Serializable {
    /**
     * ID
     */
    private int integralId;
    /**
     * 客户
     */
    private Customer customer;
    /**
     * 积分规则
     */
    private RuleCondition ruleCondition;
    /**
     * 来源
     */
    private String origin;
    /**
     * 积分值
     */
    private int score;
    /**
     * 积分时间
     */
    private Date integralTime;
    /**
     * 备注
     */
    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getIntegralId() {
        return integralId;
    }


    public void setIntegralId(int integralId) {
        this.integralId = integralId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_ID", nullable = true)
    public RuleCondition getRuleCondition() {
        return ruleCondition;
    }

    public void setRuleCondition(RuleCondition ruleCondition) {
        this.ruleCondition = ruleCondition;
    }

    @Column(nullable = false, length = 64)
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Column(nullable = false, precision = 11)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getIntegralTime() {
        return integralTime;
    }

    public void setIntegralTime(Date integralTime) {
        this.integralTime = integralTime;
    }

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
