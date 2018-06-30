package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.ActivityRuleType;
import com.yunxin.cb.mall.entity.meta.ActivityScope;
import com.yunxin.cb.mall.entity.meta.ActivityState;

import java.util.Date;

public class ActivityVo {
    /**
     * ID
     */
    private int activityId;
    /**
     * 活动名
     */
    private String activityName;
    /**
     * 活动编码
     */
    private String activityCode;
    /**
     * 活动开始时间
     */
    private Date startTime;
    /**
     * 活动结束时间
     */
    private Date deadLine;
    /**
     * 图片
     */
    private String picPath;
    /**
     * 备注
     */
    private String remark;

    /**
     * 活动状态
     */
    private ActivityState status;

    /**
     * RuleID
     */
    private int activityRuleId;
    /**
     * 活动类型：限时特价，限时折扣，满额满减，满额满赠
     */
    private ActivityRuleType type;
    /**
     * 活动范围：物品，分类，全站,品牌
     */
    private ActivityScope scope;

    /**
     * 用来标记，满多少
     */
    private double overPrice;


    /**
     * 活动范围值：如分类ID，商品ID
     */
    private int ActivityScopeValue;
    /**
     * 活动力度：如打几折，满减多少
     */
    private float value;

    private int goodsNumber;

    private String joinResult;

    private boolean joinFlag;

    private int highPrice;

    public int getActivityScopeValue() {
        return ActivityScopeValue;
    }

    public void setActivityScopeValue(int activityScopeValue) {
        ActivityScopeValue = activityScopeValue;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public ActivityVo() {
        super();
    }

    public ActivityVo(int activityId, String activityName, String activityCode,
                      Date startTime, Date deadLine, String picPath, String remark,
                      ActivityState status, int activityRuleId, ActivityRuleType type,
                      ActivityScope scope, double overPrice) {
        super();
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityCode = activityCode;
        this.startTime = startTime;
        this.deadLine = deadLine;
        this.picPath = picPath;
        this.remark = remark;
        this.status = status;
        this.activityRuleId = activityRuleId;
        this.type = type;
        this.scope = scope;
        this.overPrice = overPrice;
    }

    //ActivityVo (ac.activityId,ac.activityName,ac.activityCode,
    // ac.startTime,ac.deadLine,ac.picPath,ac.remark,
    // ac.status,ar.activityRuleId,ar.type,ar.scope,ar.overPrice," +
    //  " ascp.activityScopeValue,ascp.value,ascp.goodsNumber,ascp.highPrice)

    public ActivityVo(int activityId, String activityName, String activityCode,
                      Date startTime, Date deadLine, String picPath, String remark,
                      ActivityState status, int activityRuleId, ActivityRuleType type,
                      ActivityScope scope, double overPrice, int activityScopeValue,
                      float value, int goodsNumber, int highPrice) {
        super();
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityCode = activityCode;
        this.startTime = startTime;
        this.deadLine = deadLine;
        this.picPath = picPath;
        this.remark = remark;
        this.status = status;
        this.activityRuleId = activityRuleId;
        this.type = type;
        this.scope = scope;
        this.overPrice = overPrice;
        ActivityScopeValue = activityScopeValue;
        this.value = value;
        this.goodsNumber = goodsNumber;
        this.highPrice = highPrice;
    }

    public ActivityVo(int activityId) {
        super();
        this.activityId = activityId;
    }

    public ActivityState getStatus() {
        return status;
    }

    public void setStatus(ActivityState status) {
        this.status = status;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getActivityRuleId() {
        return activityRuleId;
    }

    public void setActivityRuleId(int activityRuleId) {
        this.activityRuleId = activityRuleId;
    }

    public ActivityRuleType getType() {
        return type;
    }

    public void setType(ActivityRuleType type) {
        this.type = type;
    }

    public ActivityScope getScope() {
        return scope;
    }

    public void setScope(ActivityScope scope) {
        this.scope = scope;
    }

    public double getOverPrice() {
        return overPrice;
    }

    public void setOverPrice(double overPrice) {
        this.overPrice = overPrice;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getJoinResult() {
        return joinResult;
    }

    public void setJoinResult(String joinResult) {
        this.joinResult = joinResult;
    }

    public boolean isJoinFlag() {
        return joinFlag;
    }

    public void setJoinFlag(boolean joinFlag) {
        this.joinFlag = joinFlag;
    }

    public int getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }
}
