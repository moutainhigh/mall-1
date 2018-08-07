package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;

public class FinacialWalletLog {
    /**  */
    private Integer walletLogId;

    /**  */
    private Integer walletId;

    /**  */
    private Integer customerId;

    /** 资产总额 */
    private BigDecimal assets;

    /** 可用余额 */
    private BigDecimal balance;

    /** 预期收益 */
    private BigDecimal expectedAmount;

    /** 总负债 */
    private BigDecimal debtTotal;

    /** 预期收益贷 */
    private BigDecimal debtExpected;

    /** 信用贷 */
    private BigDecimal debtCredit;

    /** 信用额度 */
    private BigDecimal creditAmount;

    /** 冻结金额，例如提现未到账，资金划拨未审核等 */
    private BigDecimal freezingAmount;

    /** 操作类型 */
    private Integer type;

    /** 变动金额 */
    private BigDecimal amount;

    /** 版本号 */
    private Integer version;

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

    public BigDecimal getAssets() {
        return assets;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(BigDecimal expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public BigDecimal getDebtTotal() {
        return debtTotal;
    }

    public void setDebtTotal(BigDecimal debtTotal) {
        this.debtTotal = debtTotal;
    }

    public BigDecimal getDebtExpected() {
        return debtExpected;
    }

    public void setDebtExpected(BigDecimal debtExpected) {
        this.debtExpected = debtExpected;
    }

    public BigDecimal getDebtCredit() {
        return debtCredit;
    }

    public void setDebtCredit(BigDecimal debtCredit) {
        this.debtCredit = debtCredit;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getFreezingAmount() {
        return freezingAmount;
    }

    public void setFreezingAmount(BigDecimal freezingAmount) {
        this.freezingAmount = freezingAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}