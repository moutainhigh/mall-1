package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.LoanType;
import com.yunxin.cb.mall.entity.meta.RepaymentState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FinancialLoan {

    /**  */
    private Integer loanId;

    /**  */
    private Integer customerId;

    /**
     * 银行卡ID
     */
    private Integer bankId;

    /**
     * 贷款金额
     */
    private BigDecimal amount;

    /**
     * 贷款周期
     */
    private Integer term;

    /**
     * 贷款利率
     */
    private BigDecimal interestRate;

    /**
     * 贷款利息
     */
    private BigDecimal interest;

    /**
     * 贷款类型：0.信用贷款
     */
    private LoanType type;

    /**
     * 最后还款日
     */
    private LocalDate finalRepaymentTime;

    /**
     * 操作版本号
     */
    private Integer version;

    /**
     * 应还总额
     */
    private BigDecimal repayAmount;

    /**
     * 实际已还
     */
    private BigDecimal readyAmount;

    /**
     * 剩余需还本金
     */
    private BigDecimal leftAmount;

    /**
     * 剩余需还利息（优先还利息）
     */
    private BigDecimal leftInterest;

    /**
     * 逾期次数
     */
    private Integer overdueNumber;

    /**
     * 逾期利息
     */
    private BigDecimal lateFee;

    /**
     * 贷款状态：0.申请中-待审核，1.审核通过-已审核，2.审核不通过-已拒绝，3.已取消，4.已转账
     */
    private LoanState state;

    /**
     * 还款状态：0.未还款，1.已逾期，2.已还款
     */
    private RepaymentState repaymentState;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核备注
     */
    private String auditRemark;

    /**
     * 转账时间
     */
    private LocalDateTime transferTime;
    /**
     * 转账备注
     */
    private String transferRemark;

    /**
     * 贷款日期
     */
    private LocalDateTime createTime;


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

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
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

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public LocalDate getFinalRepaymentTime() {
        return finalRepaymentTime;
    }

    public void setFinalRepaymentTime(LocalDate finalRepaymentTime) {
        this.finalRepaymentTime = finalRepaymentTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public BigDecimal getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(BigDecimal leftAmount) {
        this.leftAmount = leftAmount;
    }

    public BigDecimal getLeftInterest() {
        return leftInterest;
    }

    public void setLeftInterest(BigDecimal leftInterest) {
        this.leftInterest = leftInterest;
    }

    public Integer getOverdueNumber() {
        return overdueNumber;
    }

    public void setOverdueNumber(Integer overdueNumber) {
        this.overdueNumber = overdueNumber;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public LoanState getState() {
        return state;
    }

    public void setState(LoanState state) {
        this.state = state;
    }

    public RepaymentState getRepaymentState() {
        return repaymentState;
    }

    public void setRepaymentState(RepaymentState repaymentState) {
        this.repaymentState = repaymentState;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public LocalDateTime getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(LocalDateTime transferTime) {
        this.transferTime = transferTime;
    }

    public String getTransferRemark() {
        return transferRemark;
    }

    public void setTransferRemark(String transferRemark) {
        this.transferRemark = transferRemark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}