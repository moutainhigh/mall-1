package com.yunxin.cb.mall.entity.meta;

/**
 * Created by tanggangyi
 */
public enum ObjectType {
    COMMODITY("商品"),
    BRAND("品牌"),
    ADVERT("广告"),
    ATTRIBUTE("商品属性");

    private String name;

    private ObjectType(String name) {
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
