package com.yunxin.cb.mall.vo;

/**
 * 商品规格
 */
public class CommoditySpec implements java.io.Serializable {

    private String specName;

    private String value;

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
