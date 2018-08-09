package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Description: 信用额度VO
 * @Author: gws
 * @CreateDate: 2018/8/9 15:24
 */
@ApiModel(value = "信用额度VO", description = "信用额度VO FinacialCreditLineVO")
public class FinacialCreditLineVO implements java.io.Serializable {
    private static final long serialVersionUID = -2695946271501714063L;
    @ApiModelProperty(value = "钱包ID", name = "walletId", example = "1")
    private Integer walletId;

    @ApiModelProperty(value = "信用额度", name = "creditAmount", example = "100")
    private BigDecimal creditAmount;

    @ApiModelProperty(value = "保险额度", name = "insuranceAmount", example = "100")
    private BigDecimal insuranceAmount;

    @ApiModelProperty(value = "总额度(信用额度+保险额度)", name = "totalAmount", example = "100")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "已借款次数", name = "loanCount", example = "4")
    private int loanCount;

    @ApiModelProperty(value = "最多借款次数", name = "maxLoanCount", example = "5")
    private int maxLoanCount;

    @ApiModelProperty(value = "借款提醒", name = "loanReminder", example = "借款说明")
    private String loanReminder;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
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

    public int getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(int loanCount) {
        this.loanCount = loanCount;
    }

    public int getMaxLoanCount() {
        return maxLoanCount;
    }

    public void setMaxLoanCount(int maxLoanCount) {
        this.maxLoanCount = maxLoanCount;
    }

    public String getLoanReminder() {
        return loanReminder;
    }

    public void setLoanReminder(String loanReminder) {
        this.loanReminder = loanReminder;
    }

    @Override
    public String toString() {
        return "FinacialCreditLineVO{" +
                "walletId=" + walletId +
                ", creditAmount=" + creditAmount +
                ", insuranceAmount=" + insuranceAmount +
                ", totalAmount=" + totalAmount +
                ", loanCount=" + loanCount +
                ", maxLoanCount=" + maxLoanCount +
                ", loanReminder='" + loanReminder + '\'' +
                '}';
    }
}
