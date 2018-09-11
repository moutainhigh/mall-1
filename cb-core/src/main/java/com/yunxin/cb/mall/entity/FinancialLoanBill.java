package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.rb.entity.meta.LoanRepaymentType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class FinancialLoanBill implements Serializable {
    private static final long serialVersionUID = 4727761080719555837L;
    /**  */
    private Integer loanBillId;

    /** 客户*/
    private Customer customer;

    /** 资金类型：1.支出，2.收入 */
    private CapitalType type;

    /** 交易类型 */
    private TransactionType transactionType;

    /** 交易描述 */
    private String transactionDesc;

    /** 交易金 */
    private BigDecimal amount;

    /** 交易时间 */
    private Date createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public Integer getLoanBillId() {
        return loanBillId;
    }

    public void setLoanBillId(Integer loanBillId) {
        this.loanBillId = loanBillId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }
    @Column
    @Enumerated(EnumType.ORDINAL)
    public CapitalType getType() {
        return type;
    }

    public void setType(CapitalType type) {
        this.type = type;
    }

    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 1024)
    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc == null ? null : transactionDesc.trim();
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 1024)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 1024)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setTransactionTypeOnPayment(LoanRepaymentType type) {
        if (LoanRepaymentType.INSURANCE_REBATE_REPAYMENT.equals(type)) {
            this.transactionType = TransactionType.INSURANCE_REPAYMENT;
        }else if (LoanRepaymentType.COMMODITY_REIMBURSEMENT_REPAYMENT.equals(type)) {
            this.transactionType = TransactionType.PRODUCT_RB_REPAYMENT;
        }else if (LoanRepaymentType.MANUAL_REIMBURSEMENT_REPAYMENT.equals(type)) {
            this.transactionType = TransactionType.CAR_REPAYMENT;
        }else if (LoanRepaymentType.CAR_REBATE_REPAYMENT.equals(type)) {
            this.transactionType = TransactionType.MANUAL_REPAYMENT;
        }
    }
}
