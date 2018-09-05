package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "用户提交借款VO", description = "用户提交借款VO AddFinancialLoanVO")
public class AddFinancialLoanVO {

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "贷款金额", name = "amount", example = "100")
    private BigDecimal amount;

    @NotNull
    @ApiModelProperty(value = "贷款期限配置id", name = "loanConfigId", example = "6")
    private Integer loanConfigId;

    @NotNull
    @ApiModelProperty(value = "银行卡ID", name = "bankId", example = "6")
    private Integer bankId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getLoanConfigId() {
        return loanConfigId;
    }

    public void setLoanConfigId(Integer loanConfigId) {
        this.loanConfigId = loanConfigId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
}