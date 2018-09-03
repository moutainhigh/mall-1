package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Description: 用户钱包VO
 * @Author: likang
 * @CreateDate: 2018/8/7 15:24
 */
@ApiModel(value = "用户钱包VO", description = "用户钱包VO FinancialWalletVO")
public class FinancialWalletVO implements java.io.Serializable {

    private static final long serialVersionUID = -2695946271501714063L;

    @ApiModelProperty(value = "钱包ID", name = "walletId", example = "1")
    private Integer walletId;

    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "冻结金额", name = "freezingAmount", example = "100")
    private BigDecimal freezingAmount;

    @ApiModelProperty(value = "信用额度", name = "creditAmount", example = "100")
    private BigDecimal creditAmount;

    @ApiModelProperty(value = "汽车贷款", name = "debtCar", example = "100")
    private BigDecimal debtCar;

    @ApiModelProperty(value = "信用贷款", name = "debtCredit", example = "100")
    private BigDecimal insuranceAmount;

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

    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
