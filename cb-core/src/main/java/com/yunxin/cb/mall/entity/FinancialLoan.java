package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.LoanType;
import com.yunxin.cb.mall.entity.meta.RepaymentState;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @title: 借款表实体类
 * @auther: pengcong
 * @date: 2018/9/4
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class FinancialLoan implements Serializable {

    private static final long serialVersionUID = 2168409858564211787L;
    /**
     *
     */
    private Integer loanId;

    /**
     * 用户ID
     */
    private Customer customer;

    /**
     * 银行ID
     */
    private BankInfo bank;

    /**
     *贷款金额
     */
    private BigDecimal amount;

    /**
     *贷款周期
     */
    private Integer term;

    /**
     *贷款利率
     */
    private BigDecimal interestRate;

    /**
     *贷款利息
     */
    private BigDecimal interest;

    /**
     *贷款类型：0.信用贷款
     */
    private LoanType type;

    /**
     * 最后还款日期
     */
    private Date finalRepaymentTime;

    /**
     *操作版本号
     */
    private Integer version;

    /**
     *应还总额
     */
    private BigDecimal repayAmount;

    /**
     *实际已还
     */
    private BigDecimal readyAmount;

    /**
     *剩余需还本金
     */
    private BigDecimal leftAmount;

    /**
     *剩余需还利息(优先还利息)
     */
    private BigDecimal leftInterest;

    /**
     *逾期次数
     */
    private Integer overdueNumber;

    /**
     *逾期利息
     */
    private BigDecimal lateFee;

    /**
     *贷款状态：0.申请，1.已审核，2.已拒绝，3.已取消，4.已转账
     */
    private LoanState state;

    /**
     *还款状态：0.未还款，1.已逾期，2.已还款
     */
    private RepaymentState repaymentState;

    /**
     *审核日期
     */
    private Date auditTime;

    /**
     *审核意见备注
     */
    private String auditRemark;

    /**
     *转账日期
     */
    private Date transferTime;

    /**
     *转账备注
     */
    private String transferRemark;

    /**
     *贷款日期
     */
    private Date createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID", nullable = false)
    public BankInfo getBank() {
        return bank;
    }

    public void setBank(BankInfo bank) {
        this.bank = bank;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    @Column(length = 12, nullable = true)
    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getFinalRepaymentTime() {
        return finalRepaymentTime;
    }

    public void setFinalRepaymentTime(Date finalRepaymentTime) {
        this.finalRepaymentTime = finalRepaymentTime;
    }
    @Column(length = 12, nullable = true)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getReadyAmount() {
        return readyAmount;
    }

    public void setReadyAmount(BigDecimal readyAmount) {
        this.readyAmount = readyAmount;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(BigDecimal leftAmount) {
        this.leftAmount = leftAmount;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getLeftInterest() {
        return leftInterest;
    }

    public void setLeftInterest(BigDecimal leftInterest) {
        this.leftInterest = leftInterest;
    }
    @Column(length = 12, nullable = true)
    public Integer getOverdueNumber() {
        return overdueNumber;
    }

    public void setOverdueNumber(Integer overdueNumber) {
        this.overdueNumber = overdueNumber;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }
    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public LoanState getState() {
        return state;
    }

    public void setState(LoanState state) {
        this.state = state;
    }
    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public RepaymentState getRepaymentState() {
        return repaymentState;
    }

    public void setRepaymentState(RepaymentState repaymentState) {
        this.repaymentState = repaymentState;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
    @Column(length = 255)
    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }
    @Column(length = 255)
    public String getTransferRemark() {
        return transferRemark;
    }

    public void setTransferRemark(String transferRemark) {
        this.transferRemark = transferRemark == null ? null : transferRemark.trim();
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
