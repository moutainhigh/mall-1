package com.yunxin.cb.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @title: 保险返利记录表
 * @auther: eleven
 * @date: 2018/8/10 11:47
 */
public class FinacialInsuCashbackLog implements Serializable {
    private static final long serialVersionUID = 8103278472401545335L;
    /** 保险返现id */
    private Integer logId;

    /** 用户id */
    private Integer customerId;

    /** 返现人 */
    private String customerName;

    /** 返现人手机号 */
    private String mobile;

    /** 返现金额 */
    private BigDecimal amount;

    /** 状态 */
    private Integer state;

    /** 返利保单 */
    private String orderNo;

    /** 返现时间 */
    private Date createTime;

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
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
