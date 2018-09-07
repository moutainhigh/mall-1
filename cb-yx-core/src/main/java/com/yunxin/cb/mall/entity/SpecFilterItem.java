package com.yunxin.cb.mall.entity;

/**
 * 规格查询过滤-值
 * add by chenpeng 2018年8月20日
 */
public class SpecFilterItem implements java.io.Serializable {

    private static final long serialVersionUID = 8157610219767875765L;

    private Integer itemId;

    private SpecFilter specFilter;

    private String itemValue;

    private Integer sortOrder;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public SpecFilter getSpecFilter() {
        return specFilter;
    }

    public void setSpecFilter(SpecFilter specFilter) {
        this.specFilter = specFilter;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
