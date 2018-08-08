package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Description: 用户钱包VO
 * @Author: likang
 * @CreateDate: 2018/8/7 15:24
 */
@ApiModel(value = "用户钱包VO", description = "用户钱包VO FinacialWalletVO")
public class FinacialWalletVO implements java.io.Serializable {
    private static final long serialVersionUID = -2695946271501714063L;
    @ApiModelProperty(value = "钱包ID", name = "walletId", example = "1")
    private Integer walletId;

    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "资产总额", name = "assets", example = "100")
    private BigDecimal assets;

    @ApiModelProperty(value = "可用余额", name = "balance", example = "100")
    private BigDecimal balance;

    @ApiModelProperty(value = "预期收益", name = "expectedAmount", example = "100")
    private BigDecimal expectedAmount;

    @ApiModelProperty(value = "总负债", name = "debtTotal", example = "100")
    private BigDecimal debtTotal;

    @ApiModelProperty(value = "预期收益贷 ", name = "debtExpected", example = "100")
    private BigDecimal debtExpected;

    @ApiModelProperty(value = "信用贷", name = "debtCredit", example = "100")
    private BigDecimal debtCredit;

    @ApiModelProperty(value = "信用额度", name = "creditAmount", example = "100")
    private BigDecimal creditAmount;

    @ApiModelProperty(value = "保险额度", name = "insuranceAmount", example = "100")
    private BigDecimal insuranceAmount;

    @ApiModelProperty(value = "总额度", name = "totalAmount", example = "100")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "冻结金额，例如提现未到账，资金划拨未审核等", name = "freezingAmount", example = "100")
    private BigDecimal freezingAmount;

    @ApiModelProperty(value = "版本号", name = "version", example = "100")
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

    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
