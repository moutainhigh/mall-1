package com.yunxin.cb.mall.entity.meta;

/**
 * Created by k001389 on 2015/1/30.
 */
public enum CustomerCouponStatus {
    //    状态：激活 使用 作废cancellation
    ACTIVITION("激活"), USING("使用"), CANCELLATION("作废");
    private String name;

    private CustomerCouponStatus(String name) {
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
