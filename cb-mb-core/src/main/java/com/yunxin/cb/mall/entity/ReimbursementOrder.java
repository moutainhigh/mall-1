package com.yunxin.cb.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReimbursementOrder implements Serializable {
    private static final long serialVersionUID = -4279789127193883246L;
    /**
     *主键
     */
    private Integer reimbursementOrderId;

    /**
     *订单ID
     */
    private Integer orderItemId;

    /**
     *货品ID
     */
    private Integer productId;

    /**
     *单个商品报账金额
     */
    private BigDecimal amount;

    /**
     *税
     */
    private BigDecimal tax;

    /**
     *商品销售价
     */
    private BigDecimal productPrice;

    /**
     *报账信息ID
     */
    private Integer reimbursementId;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     * 可报账ID
     */
    private Integer reimbursementQueryId;

    public Integer getReimbursementOrderId() {
        return reimbursementOrderId;
    }


    public void setReimbursementOrderId(Integer reimbursementOrderId) {
        this.reimbursementOrderId = reimbursementOrderId;
    }


    public Integer getOrderItemId() {
        return orderItemId;
    }


    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }


    public Integer getProductId() {
        return productId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public BigDecimal getTax() {
        return tax;
    }


    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }


    public BigDecimal getProductPrice() {
        return productPrice;
    }


    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }


    public Integer getReimbursementId() {
        return reimbursementId;
    }


    public void setReimbursementId(Integer reimbursementId) {
        this.reimbursementId = reimbursementId;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReimbursementQueryId() {
        return reimbursementQueryId;
    }

    public void setReimbursementQueryId(Integer reimbursementQueryId) {
        this.reimbursementQueryId = reimbursementQueryId;
    }
}
