package com.yunxin.cb.mall.entity.meta;

public enum ActivityScope {
    COMMODITY("商品");

    private String name;

    private ActivityScope(String name) {
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
