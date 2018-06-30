package com.yunxin.cb.mall.entity.meta;

/**
 * Created by k001389 on 2014/8/6.
 */
public enum PaymentType {

    ALIPAY("支付宝"), TENPAY("财付通"), UNIONPAY("银联"), AFTERREVICED("货到付款");
    private String name;

    private PaymentType(String name) {
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
