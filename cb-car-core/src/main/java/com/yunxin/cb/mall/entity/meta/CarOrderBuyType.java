package com.yunxin.cb.mall.entity.meta;

public enum CarOrderBuyType {
    //购买类型(1全款,2置换)
    FULL_MONEY("全款"),EXCHANGE("置换");


    private String name;

    CarOrderBuyType(String name) {
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
