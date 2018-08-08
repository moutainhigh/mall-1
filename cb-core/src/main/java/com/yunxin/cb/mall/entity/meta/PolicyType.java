package com.yunxin.cb.mall.entity.meta;

public enum PolicyType {
    NOTPURCHASED("未购买"),UNPAID("未付款"),PAYMENT("已支付");
    private String name;

    private PolicyType(String name) {
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
