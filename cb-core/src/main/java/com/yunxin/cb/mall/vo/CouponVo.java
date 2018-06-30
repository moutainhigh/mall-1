package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.CouponState;

import java.util.Date;

/**
 * Created by k001389 on 2015/1/13.
 */
public class CouponVo {
    private int customerCouponId;
    private int couponId;
    private Date startTime;
    private Date endTime;
    //未领取或发放，待使用，使用,过期
    private CouponState status;
    //面额或折扣
    private double value;
    //使用条件：满多少使用
    private double overPrice;
    //使用站点：全站，商品，分类 或品牌
//    private CouponRuleScope scope;
    /**
     * 活动范围值：如分类ID，商品ID,价格开始
     */
    private int scopeId;

    private String customerCouponCode;

//    private CouponRule couponRule;


    public CouponVo() {
    }
//
//    public CouponVo(int couponId, CouponRuleScope scope) {
//        this.couponId = couponId;
//        this.scope = scope;
//    }


//    public CouponVo(int couponId, Date startTime, Date endTime, CouponState status,
//                    float value, double overPrice, CouponRuleScope scope, String customerCouponCode, CouponRule couponRule) {
//        this.couponId = couponId;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.status = status;
//        this.value = value;
//        this.overPrice = overPrice;
//        this.scope = scope;
//        this.customerCouponCode = customerCouponCode;
//        this.couponRule = couponRule;
//    }

//    public CouponVo(int couponId, Date startTime, Date endTime, CouponState status,
//                    double value, double overPrice, CouponRuleScope scope, int scopeId, String customerCouponCode, CouponRule couponRule) {
//        this.couponId = couponId;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.status = status;
//        this.value = value;
//        this.overPrice = overPrice;
//        this.scope = scope;
//        this.scopeId = scopeId;
//        this.customerCouponCode = customerCouponCode;
//        this.couponRule = couponRule;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CouponVo couponVo = (CouponVo) o;
        return couponId == couponVo.getCouponId() && customerCouponCode.equals(couponVo.getCustomerCouponCode());
//        return couponId==couponVo.getCouponId()&&startTime==couponVo.getStartTime()&&endTime==couponVo.getEndTime()&&status.equals(couponVo.getStatus())&&
//                value==couponVo.getValue()&&overPrice==couponVo.getOverPrice()&&activityRuleScope==couponVo.getActivityRuleScope()&&activityScopeValue==couponVo.getActivityScopeValue();

    }

    @Override
    public int hashCode() {
//        String in = String.valueOf(couponId) + status + startTime + endTime + value + overPrice + activityRuleScope + activityScopeValue;
        String in = String.valueOf(couponId);
        return in.hashCode();
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public CouponState getStatus() {
        return status;
    }

    public void setStatus(CouponState status) {
        this.status = status;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getOverPrice() {
        return overPrice;
    }

    public void setOverPrice(double overPrice) {
        this.overPrice = overPrice;
    }

//    public CouponRuleScope getScope() {
//        return scope;
//    }
//
//    public void setScope(CouponRuleScope scope) {
//        this.scope = scope;
//    }

//    public String getScopeName() {
//        return scope.getName();
//    }

    public int getScopeId() {
        return scopeId;
    }

    public void setScopeId(int scopeId) {
        this.scopeId = scopeId;
    }

    public String getCustomerCouponCode() {
        return customerCouponCode;
    }

    public void setCustomerCouponCode(String customerCouponCode) {
        this.customerCouponCode = customerCouponCode;
    }

//    public CouponRule getCouponRule() {
//        return couponRule;
//    }
//
//    public void setCouponRule(CouponRule couponRule) {
//        this.couponRule = couponRule;
//    }

    public int getCustomerCouponId() {
        return customerCouponId;
    }

    public void setCustomerCouponId(int customerCouponId) {
        this.customerCouponId = customerCouponId;
    }
}

