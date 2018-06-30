package com.yunxin.cb.mall.entity.meta;

/**
 * Created by chenxing on 16/3/16.
 */
public enum AdvertisementURLType {
    URL("纯URL"), COMMODITY_ID("商品");

    private String name;


    AdvertisementURLType(String name) {
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
        return this.name;
    }
}
