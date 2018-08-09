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

    @ApiModelProperty(value = "总额度(信用额度+保险额度)", name = "totalAmount", example = "100")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "信用额度", name = "creditAmount", example = "100")
    private BigDecimal creditAmount;

    @ApiModelProperty(value = "保险额度", name = "insuranceAmount", example = "100")
    private BigDecimal insuranceAmount;

    @ApiModelProperty(value = "信用金额(总信用额度+预期收益)", name = "totalAmount", example = "100")
    private BigDecimal totalAvailableAmount;

    @ApiModelProperty(value = "预期收益", name = "expectedAmount", example = "100")
    private BigDecimal expectedAmount;

    @ApiModelProperty(value = "已借款次数", name = "loanCount", example = "4")
    private int loanCount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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

    public BigDecimal getTotalAvailableAmount() {
        this.totalAvailableAmount = this.totalAmount.add(this.expectedAmount);
        return totalAvailableAmount;
    }

    public void setTotalAvailableAmount(BigDecimal totalAvailableAmount) {
        this.totalAvailableAmount = totalAvailableAmount;
    }

    public BigDecimal getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(BigDecimal expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(int loanCount) {
        this.loanCount = loanCount;
    }

    @Override
    public String toString() {
        return "FinacialCreditLineVO{" +
                "totalAmount=" + totalAmount +
                ", creditAmount=" + creditAmount +
                ", insuranceAmount=" + insuranceAmount +
                ", totalAvailableAmount=" + totalAvailableAmount +
                ", expectedAmount=" + expectedAmount +
                ", loanCount=" + loanCount +
                '}';
    }
}
