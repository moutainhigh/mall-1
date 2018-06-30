package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 货品属性
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class ProductAttribute implements java.io.Serializable {


    private int proAttrId;


    private Product product;


    private Attribute attribute;


    public ProductAttribute() {
        super();
    }

    public ProductAttribute(Product product) {
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getProAttrId() {
        return proAttrId;
    }

    public void setProAttrId(int prodPropId) {
        this.proAttrId = prodPropId;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_ID", nullable = false)
    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
