package com.yunxin.cb.mall.entity;

/**
 * @title: 货品属性关联实体类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
public class ProductAttribute {
    /** 货品属性id */
    private Integer proAttrId;

    /** 属性id */
    private Integer attributeId;

    /** 货品id */
    private Integer productId;

    //属性
    private Attribute attribute;

    public Integer getProAttrId() {
        return proAttrId;
    }

    public void setProAttrId(Integer proAttrId) {
        this.proAttrId = proAttrId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}