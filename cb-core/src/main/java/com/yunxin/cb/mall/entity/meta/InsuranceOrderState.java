package com.yunxin.cb.mall.entity.meta;

public enum InsuranceOrderState {
     UN_PAID("待支付"),ON_PAID("已支付"),BEEN_COMPLETED("已完成"),UN_SURRENDER("退保审核"),ON_SURRENDER("已退保");
    private String name;

    private InsuranceOrderState(String name) {
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
