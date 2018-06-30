package com.yunxin.cb.mall.entity.meta;

/**
 * Created by k001389 on 2015/1/8.
 */
public enum CouponState {
    UNUSED("未使用"), BEEN_USED("已使用"), LOCKED("锁定"), DISABLE("禁用");

    private String name;

    private CouponState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
