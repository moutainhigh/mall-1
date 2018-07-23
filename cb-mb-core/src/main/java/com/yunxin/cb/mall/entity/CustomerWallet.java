package com.yunxin.cb.mall.entity;

import java.util.Date;

public class CustomerWallet {
    /** 客户钱包id */
    private Integer customerId;

    /** 可用余额 */
    private Double availableBalance;

    /** 预期收益金额 */
    private Double expectedReturnAmount;

    /** 可贷额度 */
    private Double loanQuota;

    /** 欠款金额 */
    private Double arrearsAmount;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Double getExpectedReturnAmount() {
        return expectedReturnAmount;
    }

    public void setExpectedReturnAmount(Double expectedReturnAmount) {
        this.expectedReturnAmount = expectedReturnAmount;
    }

    public Double getLoanQuota() {
        return loanQuota;
    }

    public void setLoanQuota(Double loanQuota) {
        this.loanQuota = loanQuota;
    }

    public Double getArrearsAmount() {
        return arrearsAmount;
    }

    public void setArrearsAmount(Double arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}