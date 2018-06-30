package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.ActivityScope;
import com.yunxin.cb.mall.entity.meta.ActivityState;
import com.yunxin.cb.mall.entity.meta.ActivityType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 活动
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private int activityId;

    /**
     * 运营分类
     */
    private Set<Category> categories = new HashSet<>();
    /**
     * 规则条件
     */
    private RuleCondition ruleCondition;

    private int[] activityCategoryIds;

    /**
     * 活动名
     */
    private String activityName;
    /**
     * 活动别名
     */
    private String activityAlias;
    /**
     * 活动号
     */
    private String activityCode;
    /**
     * 活动开始时间
     */
    private Date startTime;
    /**
     * 活动结束时间
     */
    private Date endTime;
    /**
     * 活动创建时间
     */
    private Date createTime;
    /**
     * 类型：
     */
    private ActivityType activityType;
    /**
     * 活动状态
     */
    private ActivityState activityState;
    /**
     * 活动范围：商品，分类，全站,品牌
     */
    private ActivityScope activityScope;
    /***是否限制数量*/
    private boolean limitAmount;
    /***限制数量*/
    private int limitAmountSize;
    /***活动规则是否互斥*/
    private boolean ruleMutex;
    /***是否支持优惠券*/
    private boolean supportCoupon;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;
    /**
     * 图片
     */
    private String picPath;
    /**
     * 备注
     */
    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CATEGORY_ACTIVITY", joinColumns = {@JoinColumn(name = "ACTIVITY_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false)})
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Column(length = 32)
    public String getActivityAlias() {
        return activityAlias;
    }

    public void setActivityAlias(String activityAlias) {
        this.activityAlias = activityAlias;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public ActivityScope getActivityScope() {
        return activityScope;
    }

    public void setActivityScope(ActivityScope activityScope) {
        this.activityScope = activityScope;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_ID", nullable = false)
    public RuleCondition getRuleCondition() {
        return ruleCondition;
    }

    public void setRuleCondition(RuleCondition ruleCondition) {
        this.ruleCondition = ruleCondition;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date deadLine) {
        this.endTime = deadLine;
    }

    @Column(nullable = false, length = 255)
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType type) {
        this.activityType = type;
    }

    @Column(nullable = false, length = 128)
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Column(nullable = false, length = 32)
    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public ActivityState getActivityState() {
        return activityState;
    }

    public void setActivityState(ActivityState status) {
        this.activityState = status;
    }

    @Transient
    public String getStatusName() {
        return activityState.getName();
    }

    @Column(nullable = false, precision = 1)
    public boolean isLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(boolean limitAmount) {
        this.limitAmount = limitAmount;
    }

    @Column(precision = 4)
    public int getLimitAmountSize() {
        return limitAmountSize;
    }

    public void setLimitAmountSize(int limitAmountSize) {
        this.limitAmountSize = limitAmountSize;
    }

    @Column(nullable = false, precision = 1)
    public boolean isRuleMutex() {
        return ruleMutex;
    }

    public void setRuleMutex(boolean ruleMutex) {
        this.ruleMutex = ruleMutex;
    }

    @Column(nullable = false, precision = 1)
    public boolean isSupportCoupon() {
        return supportCoupon;
    }

    public void setSupportCoupon(boolean supportCoupon) {
        this.supportCoupon = supportCoupon;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    public int[] getActivityCategoryIds() {
        return activityCategoryIds;
    }

    public void setActivityCategoryIds(int[] activityCategoryIds) {
        this.activityCategoryIds = activityCategoryIds;
    }
}
