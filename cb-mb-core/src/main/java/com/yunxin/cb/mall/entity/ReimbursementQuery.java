package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.ReimbursementQueryState;

import java.io.Serializable;
import java.util.Date;

public class ReimbursementQuery implements Serializable {
    private static final long serialVersionUID = 6952797607210915461L;
    /**
     * 可报账ID
     */
    private Integer reimbursementQueryId;
    /**
     * 订单ID
     */
    private Integer itemId;
    /**
     * 货品ID
     */
    private Integer productId;
    /**
     * 货品售价
     */
    private float salePrice;
    /**
     * 货品数量
     */
    private Integer productNum;
    /**
     * 订单创建时间
     */
    private Date createTime;
    /**
     * 商家名称
     */
    private String sellerName;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 货品默认图片
     */
    private String defaultPicPath;
    /**
     * 商品ID
     */
    private Integer commodityId;
    /**
     * 商品属性
     */
    private String productName;
    /**
     * 用户ID
     */
    private Integer customerId;
    /**
     * 总售价
     */
    private float accountSalePrice;
    /**
     * 报账状态
     */
    private ReimbursementQueryState reimbursementQueryState;

    public Integer getReimbursementQueryId() {
        return reimbursementQueryId;
    }

    public void setReimbursementQueryId(Integer reimbursementQueryId) {
        this.reimbursementQueryId = reimbursementQueryId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public float getAccountSalePrice() {
        return accountSalePrice;
    }

    public void setAccountSalePrice(float accountSalePrice) {
        this.accountSalePrice = accountSalePrice;
    }

    public ReimbursementQueryState getReimbursementQueryState() {
        return reimbursementQueryState;
    }

    public void setReimbursementQueryState(ReimbursementQueryState reimbursementQueryState) {
        this.reimbursementQueryState = reimbursementQueryState;
    }
}
