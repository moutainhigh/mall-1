package com.yunxin.cb.mall.entity;

import com.yunxin.cb.rb.entity.meta.LoanRepaymentType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "financial_loan_repayment")
public class FinancialLoanRepayment {
    /**  */
    private Integer repaymentId;

    /** 用户id */
    private Customer customer;

    /**  */
    private Integer loanId;

    /** 还款金额 */
    private BigDecimal repayAmount;

    /** 还款本金 */
    private BigDecimal repayCapital;

    /** 还款利息 */
    private BigDecimal repayInterest;

    /** 还款类型 */
    private LoanRepaymentType loanRepaymentType;

    /** 创建时间 */
    private Date createTime;

    public FinancialLoanRepayment(Integer repaymentId) {
        this.repaymentId = repaymentId;
    }

    public FinancialLoanRepayment(Customer customer, Integer loanId, BigDecimal repayAmount,BigDecimal repayCapital,Date createTime, LoanRepaymentType loanRepaymentType) {
        this.customer = customer;
        this.loanId = loanId;
        this.repayAmount = repayAmount;
        this.repayCapital = repayCapital;
        this.createTime = createTime;
        this.loanRepaymentType = loanRepaymentType;
        this.repayInterest = new BigDecimal(0);
    }

    @Id
    @DocumentId  /*以字段id作为搜索引擎文档id*/
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0, name = "REPAYMENT_ID")
    public Integer getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(Integer repaymentId) {
        this.repaymentId = repaymentId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(unique = true, nullable = false, precision = 12, scale = 0, name = "LOAN_ID")
    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    @Column(unique = true, nullable = false, precision = 12, scale = 0, name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(unique = true, nullable = false, precision = 12, scale = 0, name = "REPAY_AMOUNT")
    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    @Column(length = 128, nullable = false, unique = true, name = "LOAN_REPAYMENT_TYPE")
    @Enumerated(value = EnumType.ORDINAL)//如果枚举值为int类型，则结果封装时按照枚举类中的元素下标进行取值/封装
    public LoanRepaymentType getLoanRepaymentType() {
        return loanRepaymentType;
    }

    public void setLoanRepaymentType(LoanRepaymentType loanRepaymentType) {
        this.loanRepaymentType = loanRepaymentType;
    }

    @Column(unique = true, nullable = false, precision = 12, scale = 0, name = "REPAY_CAPITAL")
    public BigDecimal getRepayCapital() {
        return repayCapital;
    }

    public void setRepayCapital(BigDecimal repayCapital) {
        this.repayCapital = repayCapital;
    }

    @Column(unique = true, nullable = false, precision = 12, scale = 0, name = "REPAY_INTEREST")
    public BigDecimal getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }
}