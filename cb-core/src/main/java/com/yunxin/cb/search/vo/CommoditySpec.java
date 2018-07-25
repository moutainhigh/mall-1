package com.yunxin.cb.search.vo;

/**
 * 商品规格
 */
public class CommoditySpec implements java.io.Serializable {

    private String specName;

    private String value;

    public CommoditySpec() {
    }

    public CommoditySpec(String specName, String value) {
        this.specName = specName;
        this.value = value;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
