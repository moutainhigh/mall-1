package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;

public class FinancialWallet {
    /**  */
    private Integer walletId;

    /**  */
    private Integer customerId;

    /** 冻结金额 */
    private BigDecimal freezingAmount;

    /** 信用额度 */
    private BigDecimal creditAmount;

    /** 汽车贷款 */
    private BigDecimal debtCar;

    /** 信用贷款 */
    private BigDecimal debtCredit;

    /** 版本号 */
    private Integer version;

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
}