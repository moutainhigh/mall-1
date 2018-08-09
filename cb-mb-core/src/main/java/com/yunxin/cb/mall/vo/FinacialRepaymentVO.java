package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "用户还款VO", description = "用户还款VO FinacialRepaymentVO")
public class FinacialRepaymentVO {

    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "借款ID", name = "loanId", example = "1")
    private Integer loanId;

    @ApiModelProperty(value = "还款金额", name = "amount", example = "100")
    private BigDecimal amount;

    @ApiModelProperty(value = "第几期还款", name = "seq", example = "1")
    private Integer seq;

    @ApiModelProperty(value = "还款滞纳金", name = "lateFee", example = "100")
    private BigDecimal lateFee;

    @ApiModelProperty(value = "还款利息", name = "interest", example = "100")
    private BigDecimal interest;

    @ApiModelProperty(value = "创建时间", name = "createTime", example = "2018-10-10")
    private Date createTime;

    @ApiModelProperty(value = "实际还款金", name = "repayAmount", example = "100")
    private BigDecimal repayAmount;

    @ApiModelProperty(value = "实际还款时间", name = "readyRepaymentTime", example = "2018-10-10")
    private Date readyRepaymentTime;

    @ApiModelProperty(value = "规定还款时间", name = "repayTime", example = "2018-10-10")
    private Date repayTime;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Date getReadyRepaymentTime() {
        return readyRepaymentTime;
    }

    public void setReadyRepaymentTime(Date readyRepaymentTime) {
        this.readyRepaymentTime = readyRepaymentTime;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    @Override
    public String toString() {
        return "FinacialRepaymentVO{" +
                "customerId=" + customerId +
                ", loanId=" + loanId +
                ", amount=" + amount +
                ", seq=" + seq +
                ", lateFee=" + lateFee +
                ", interest=" + interest +
                ", createTime=" + createTime +
                ", repayAmount=" + repayAmount +
                ", readyRepaymentTime=" + readyRepaymentTime +
                ", repayTime=" + repayTime +
                '}';
    }
}