package com.yunxin.cb.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @title: 余额交易明细
 * @auther: eleven
 * @date: 2018/8/8 16:00
 */
public class FinacialBill implements Serializable {
    private static final long serialVersionUID = 2337880408034171885L;
    /**  */
    private Integer billId;

    /** 用户id */
    private Integer customerId;

    /** 交易金额 */
    private BigDecimal amount;

    /** 交易类型：1.支出，2.收入 */
    private Integer type;

    /** 交易状态 */
    private Integer state;

    /** 交易方式：1.商品购买，2.报帐，4.贷款，3.提现，5.还款, 6.保险返利 */
    private Integer transactionType;

    /** 交易时间 */
    private Date createTime;

    /** 交易描述 */
    private String transactionDesc;

    /** 交易订单号 */
    private String transactionNo;

    /** 提现银行    （冗余字段，针对提现交易） */
    private String withdrawBank;

    /** 提现手续费  （冗余字段，针对提现交易） */
    private BigDecimal chargeFee;

    /** 实际提现金额（冗余字段，针对提现交易） */
    private BigDecimal realAmount;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc == null ? null : transactionDesc.trim();
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo == null ? null : transactionNo.trim();
    }

    public String getWithdrawBank() {
        return withdrawBank;
    }

    public void setWithdrawBank(String withdrawBank) {
        this.withdrawBank = withdrawBank == null ? null : withdrawBank.trim();
    }

    public BigDecimal getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(BigDecimal chargeFee) {
        this.chargeFee = chargeFee;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }
}