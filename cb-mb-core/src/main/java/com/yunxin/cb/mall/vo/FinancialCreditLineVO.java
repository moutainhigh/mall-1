package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Description: 信用额度VO
 * @Author: gws
 * @CreateDate: 2018/8/9 15:24
 */
@ApiModel(value = "信用额度VO", description = "信用额度VO FinancialCreditLineVO")
public class FinancialCreditLineVO implements java.io.Serializable {

    private static final long serialVersionUID = -2695946271501714063L;

    @ApiModelProperty(value = "信用额度", name = "creditAmount", example = "100")
    private BigDecimal creditAmount;

    @ApiModelProperty(value = "已借款次数", name = "loanCount", example = "4")
    private int loanCount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(int loanCount) {
        this.loanCount = loanCount;
    }

}
