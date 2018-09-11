package com.yunxin.cb.mall.entity;

import java.util.Date;

public class ShoppingCart {
    /** 购物车id */
    private Integer cartId;

    /** 买家id */
    private Integer customerId;

    /** 卖家id */
    private Integer sellerId;

    /** 货品id */
    private Integer productId;

    /** 货品数量 */
    private Integer productNum;

    /** 创建时间 */
    private Date createTime;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
}