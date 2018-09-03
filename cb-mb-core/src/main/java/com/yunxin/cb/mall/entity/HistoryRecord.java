package com.yunxin.cb.mall.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 我的浏览实体类
 * @auther: eleven
 * @date: 2018/8/14 11:14
 */
public class HistoryRecord implements Serializable {
    private static final long serialVersionUID = -1457381452137741650L;
    /**  */
    private Integer recordId;

    /**  */
    private Date createTime;

    /**  */
    private Float salePrice;

    /**  */
    private Integer commodityId;

    /** 货品id */
    private Integer productId;

    /**  */
    private Integer customerId;

    private Commodity commodity;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}