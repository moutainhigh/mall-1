package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.LoanType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "用户借款VO", description = "用户借款VO FinacialLoanVO")
public class FinacialLoanVO {
    private static final long serialVersionUID = -2695946271501714063L;

    @ApiModelProperty(value = "借款ID", name = "loanId", example = "1")
    private Integer loanId;

    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "贷款金额", name = "amount", example = "100")
    private BigDecimal amount;

    @ApiModelProperty(value = "还款期数", name = "term", example = "6")
    private Integer term;

    @ApiModelProperty(value = "贷款利率", name = "interestRate", example = "10")
    private BigDecimal interestRate;

    @ApiModelProperty(value = "贷款类型", name = "type", example = "INSURANCE_LOAN")
    private LoanType type;

    @ApiModelProperty(value = "贷款状态", name = "state", example = "WAIT_LOAN")
    private LoanState state;

    @ApiModelProperty(value = "还款期限", name = "repaymentTerm", example = "6")
    private Integer repaymentTerm;

    @ApiModelProperty(value = "最后还款时间", name = "finalRepaymentTime", example = "2018-10-01")
    private Date finalRepaymentTime;

    @ApiModelProperty(value = "应还总额", name = "repayAmount", example = "1000")
    private BigDecimal repayAmount;

    @ApiModelProperty(value = "实际已还", name = "readyAmount", example = "100")
    private BigDecimal readyAmount;

    @ApiModelProperty(value = "剩余需还", name = "surplusAmount", example = "900")
    private BigDecimal surplusAmount;

    @ApiModelProperty(value = "还款滞纳金", name = "lateFee", example = "200")
    private BigDecimal lateFee;

    @ApiModelProperty(value = "还款利息", name = "interest", example = "200")
    private BigDecimal interest;

    @ApiModelProperty(value = "逾期次数", name = "overdueNumer", example = "2")
    private Integer overdueNumer;

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public LoanState getState() {
        return state;
    }

    public void setState(LoanState state) {
        this.state = state;
    }

    public Integer getRepaymentTerm() {
        return repaymentTerm;
    }

    public void setRepaymentTerm(Integer repaymentTerm) {
        this.repaymentTerm = repaymentTerm;
    }

    public Date getFinalRepaymentTime() {
        return finalRepaymentTime;
    }

    public void setFinalRepaymentTime(Date finalRepaymentTime) {
        this.finalRepaymentTime = finalRepaymentTime;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public BigDecimal getReadyAmount() {
        return readyAmount;
    }

    public void setReadyAmount(BigDecimal readyAmount) {
        this.readyAmount = readyAmount;
    }

    public BigDecimal getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(BigDecimal surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Integer getOverdueNumer() {
        return overdueNumer;
    }

    public void setOverdueNumer(Integer overdueNumer) {
        this.overdueNumer = overdueNumer;
    }

    @Override
    public String toString() {
        return "FinacialLoanVO{" +
                "loanId=" + loanId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", term=" + term +
                ", interestRate=" + interestRate +
                ", type=" + type +
                ", state=" + state +
                ", repaymentTerm=" + repaymentTerm +
                ", finalRepaymentTime=" + finalRepaymentTime +
                ", repayAmount=" + repayAmount +
                ", readyAmount=" + readyAmount +
                ", surplusAmount=" + surplusAmount +
                ", lateFee=" + lateFee +
                ", interest=" + interest +
                ", overdueNumer=" + overdueNumer +
                '}';
    }
}