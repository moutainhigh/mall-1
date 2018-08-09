package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.FiaciaLogPayType;
import com.yunxin.cb.mall.entity.meta.FiaciaLogTransType;
import com.yunxin.cb.mall.entity.meta.OperationType;
import com.yunxin.cb.mall.entity.meta.PayState;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @title: 账单实体类
 * @auther: eleven
 * @date: 2018/8/9 17:12
 */
public class FiaciaLog implements Serializable {
    private static final long serialVersionUID = 8601417579276046520L;

    private Integer logId;

    /** 用户id */
    private Integer customerId;

    /** 交易人 */
    private String customerName;

    /** 标题 */
    private String title;

    /** 图片 */
    private String image;

    /** 交易金额 */
    private BigDecimal amount;

    /** 交易类型：0.收入，1.支出 */
    private OperationType type;

    /** 交易类型：0.保险购买1.保险返利2.商品购买3.商品退货4.借款5.手动还款6.保险返利自动还款7.商品报帐自动还款 */
    private FiaciaLogTransType transactionType;

    /** 支付方式：0.微信，1.支付宝，2.报账，3.还款 */
    private FiaciaLogPayType payType;

    /** 交易时间 */
    private Date createTime;

    /** 交易状态 */
    private PayState state;

    /** 交易订单号 */
    private String transactionNo;

    /** 交易描述 */
    private String transactionDesc;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public FiaciaLogTransType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(FiaciaLogTransType transactionType) {
        this.transactionType = transactionType;
    }

    public FiaciaLogPayType getPayType() {
        return payType;
    }

    public void setPayType(FiaciaLogPayType payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PayState getState() {
        return state;
    }

    public void setState(PayState state) {
        this.state = state;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}