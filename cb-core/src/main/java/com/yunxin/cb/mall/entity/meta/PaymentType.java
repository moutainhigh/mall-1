package com.yunxin.cb.mall.entity.meta;

/**
 * Created by k001389 on 2014/8/6.
 */
public enum PaymentType {

    UNDER_LINE("线下支付"),;//LOAN("贷款购车"), FULL_SECTION("全款购车"), ALIPAY("支付宝"), TENPAY("财付通"), UNIONPAY("银联"), AFTERREVICED("货到付款");
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
