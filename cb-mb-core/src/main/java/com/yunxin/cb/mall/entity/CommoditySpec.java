package com.yunxin.cb.mall.entity;

/**
 * @title: 商品规格实体类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
public class CommoditySpec {
    /** 商品id */
    private Integer commodityId;

    /** 规格id */
    private Integer specId;

    /** 内容 */
    private String value;

    //规格
    private Spec spec;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }
}