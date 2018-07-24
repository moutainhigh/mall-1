package com.yunxin.cb.search.vo.meta;

/**
 * 商品单位
 */
public enum CommodityUnit {

    Pieces("件"), bottles("瓶"), bags("包"), sets("套");

    private String name;

    private CommodityUnit(String name) {
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
