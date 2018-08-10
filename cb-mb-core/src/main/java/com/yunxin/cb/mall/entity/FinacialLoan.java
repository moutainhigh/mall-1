package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.LoanType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FinacialLoan {
    /**  */
    private Integer loanId;

    /**  */
    private Integer customerId;

    /** 贷款金额 */
    private BigDecimal amount;

    /** 还款期数 */
    private Integer term;

    /** 贷款利率 */
    private BigDecimal interestRate;

    /** 贷款类型：1.信用贷款，2.预期收益贷 */
    private LoanType type;

    /** 每月几日还款 */
    private Integer repayDay;

    /** 贷款状态：1.申请，2.审核，3.发放 */
    private LoanState state;

    /** 贷款日期 */
    private Date createTime;

    /** 更新日期，审核为审核日期，发放为发放日期 */
    private Date updateTime;

    /** 还款期限 */
    private Integer repaymentTerm;

    /** 最后还款时间 */
    private Date finalRepaymentTime;

    /** 应还总额 */
    private BigDecimal repayAmount;

    /** 实际已还 */
    private BigDecimal readyAmount;

    /** 剩余需还 */
    private BigDecimal surplusAmount;

    /** 还款滞纳金 */
    private BigDecimal lateFee;

    /** 还款利息 */
    private BigDecimal interest;

    /** 逾期次数 */
    private Integer overdueNumer;

    /** 银行卡ID */
    private Integer bankId;

    /**查询使用*/
    private List<LoanState> stateList;

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

    public Integer getRepayDay() {
        return repayDay;
    }

    public void setRepayDay(Integer repayDay) {
        this.repayDay = repayDay;
    }

    public LoanState getState() {
        return state;
    }

    public void setState(LoanState state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public List<LoanState> getStateList() {
        return stateList;
    }

    public void setStateList(List<LoanState> stateList) {
        this.stateList = stateList;
    }
}