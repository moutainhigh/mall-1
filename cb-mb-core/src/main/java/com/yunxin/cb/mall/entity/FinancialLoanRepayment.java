package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialLoanRepayment {
    /**  */
    private Integer repaymentId;

    /**  */
    private Integer customerId;

    /**  */
    private Integer loanId;

    /** 还款金额 */
    private BigDecimal amount;

    /** 第几期还款 */
    private Integer seq;

    /** 还款滞纳金 */
    private BigDecimal lateFee;

    /** 还款利息 */
    private BigDecimal interest;

    /** 创建时间 */
    private Date createTime;

    /** 实际还款金 */
    private BigDecimal repayAmount;

    /** 实际还款时间 */
    private Date readyRepaymentTime;

    /** 规定还款时间 */
    private Date repayTime;

    public FinancialLoanRepayment(Integer repaymentId) {
        this.repaymentId = repaymentId;
    }

    public FinancialLoanRepayment(Integer customerId, Integer loanId, BigDecimal amount, Date createTime, BigDecimal repayAmount, Date readyRepaymentTime) {
        this.customerId = customerId;
        this.loanId = loanId;
        this.amount = amount;
        this.createTime = createTime;
        this.repayAmount = repayAmount;
        this.readyRepaymentTime = readyRepaymentTime;
        this.seq = 0;
        this.lateFee = new BigDecimal(0);
        this.interest = new BigDecimal(0);
        this.repayTime = new Date();
    }

    public Integer getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(Integer repaymentId) {
        this.repaymentId = repaymentId;
    }

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
}