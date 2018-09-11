package com.yunxin.cb.mall.entity.meta;

public enum OrderSource {

    YX_MALL("云信商城"), CAR_MALL("汽车商城"), PAY_BACK_MONEY("还款");
    private String name;

    private OrderSource(String name) {
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
