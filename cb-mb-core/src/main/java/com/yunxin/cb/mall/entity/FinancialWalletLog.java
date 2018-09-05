package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.OperationType;

import java.math.BigDecimal;

public class FinancialWalletLog {

    /**  */
    private Integer walletLogId;

    /**  */
    private Integer walletId;

    /**  */
    private Integer customerId;

    /**
     * 冻结金额
     */
    private BigDecimal freezingAmount;

    /**
     * 信用额度
     */
    private BigDecimal creditAmount;

    /**
     * 汽车贷款
     */
    private BigDecimal debtCar;

    /**
     * 信用贷款
     */
    private BigDecimal debtCredit;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 操作类型
     */
    private OperationType type;

    /**
     * 变动金额
     */
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;

    public Integer getWalletLogId() {
        return walletLogId;
    }

    public void setWalletLogId(Integer walletLogId) {
        this.walletLogId = walletLogId;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getFreezingAmount() {
        return freezingAmount;
    }

    public void setFreezingAmount(BigDecimal freezingAmount) {
        this.freezingAmount = freezingAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getDebtCar() {
        return debtCar;
    }

    public void setDebtCar(BigDecimal debtCar) {
        this.debtCar = debtCar;
    }

    public BigDecimal getDebtCredit() {
        return debtCredit;
    }

    public void setDebtCredit(BigDecimal debtCredit) {
        this.debtCredit = debtCredit;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}