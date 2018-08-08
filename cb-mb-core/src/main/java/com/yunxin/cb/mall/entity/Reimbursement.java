package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.ReimbursementState;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Reimbursement implements Serializable {
    private static final long serialVersionUID = 7464814763140764059L;
    /**
     * id
     */
    private Integer reimbursementId;
    /**
     * 报账单号
     */
    private String reimbursementNo;

    /**
     * 用户ID
     */
    private Integer customerId;

    /**
     * 报账总金额
     */
    private BigDecimal amount;
    /**
     * 税
     */
    private BigDecimal tax;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 报账状态
     */
    private ReimbursementState orderState;

    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getReimbursementId() {
        return reimbursementId;
    }


    public void setReimbursementId(Integer reimbursementId) {
        this.reimbursementId = reimbursementId;
    }


    public String getReimbursementNo() {
        return reimbursementNo;
    }


    public void setReimbursementNo(String reimbursementNo) {
        this.reimbursementNo = reimbursementNo == null ? null : reimbursementNo.trim();
    }


    public Integer getCustomerId() {
        return customerId;
    }


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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


    public BigDecimal getOrderAmount() {
        return orderAmount;
    }


    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }


    public ReimbursementState getOrderState() {
        return orderState;
    }


    public void setOrderState(ReimbursementState orderState) {
        this.orderState = orderState;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
