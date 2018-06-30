package com.yunxin.cb.mall.vo;

/**
 * Created by k001389 on 2015/2/11.
 */
public class CouponScopeVo {
    private int couponScopeId;
    private String couponScopeName;

    public CouponScopeVo() {
    }

    public CouponScopeVo(int couponScopeId, String couponScopeName) {
        this.couponScopeId = couponScopeId;
        this.couponScopeName = couponScopeName;
    }

    public int getCouponScopeId() {
        return couponScopeId;
    }

    public void setCouponScopeId(int couponScopeId) {
        this.couponScopeId = couponScopeId;
    }

    public String getCouponScopeName() {
        return couponScopeName;
    }

    public void setCouponScopeName(String couponScopeName) {
        this.couponScopeName = couponScopeName;
    }
}
