package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "借款期限VO", description = "借款期限VO FinancialLoanConfigVO")
public class FinancialLoanConfigVO {
    /**  */
    @ApiModelProperty(value = "借款期限id", name = "loanConfigId", example = "1")
    private Integer loanConfigId;

    /** 还款期数 */
    @ApiModelProperty(value = "还款期数", name = "term", example = "6")
    private Integer term;

    /** 贷款利率 */
    @ApiModelProperty(value = "贷款利率 ", name = "interestRate", example = "0.3")
    private BigDecimal interestRate;

    /** 贷款产品标题 */
    @ApiModelProperty(value = "贷款期限标题 ", name = "title", example = "6个月")
    private String title;

    public Integer getLoanConfigId() {
        return loanConfigId;
    }

    public void setLoanConfigId(Integer loanConfigId) {
        this.loanConfigId = loanConfigId;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}