package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.OrderState;

import java.io.Serializable;
import java.util.Date;

public class ReimbursementQuery implements Serializable {
    private static final long serialVersionUID = 6952797607210915461L;
    /**
     * 订单ID
     */
    private int orderId;
    /**
     * 货品ID
     */
    private int productId;
    /**
     * 货品图片
     */
    private String productImg;
    /**
     * 货品售价
     */
    private float salePrice;
    /**
     * 货品数量
     */
    private int productNum;
    /**
     * 订单创建时间
     */
    private Date createTime;
    /**
     * 商家地址
     */
    private String sellerAddress;
    /**
     * 货品默认图片
     */
    private String defaultPicPath;
    /**
     * 商品ID
     */
    private int commodityId;
    /**
     * 商品属性
     */
    private String productName;
    /**
     * 商品大类
     */
    private int categoryId;
    /**
     * 用户ID
     */
    private int customerId;
    /**
     * 总售价
     */
    private float accountSalePrice;
    /**
     * 订单状态
     */
    private OrderState orderState;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getAccountSalePrice() {
        return accountSalePrice;
    }

    public void setAccountSalePrice(float accountSalePrice) {
        this.accountSalePrice = accountSalePrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
