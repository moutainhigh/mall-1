package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FinancialLoanBill {

    /**  */
    private Integer loanBillId;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 资金类型：1.支出，2.收入
     */
    private CapitalType type;

    /**
     * 交易类型：1.保险返利 2.保险购买
     */
    private TransactionType transactionType;

    /**
     * 交易描述
     */
    private String transactionDesc;

    /**
     * 交易金
     */
    private BigDecimal amount;

    /**
     * 交易时间
     */
    private LocalDateTime createTime;

    public Integer getLoanBillId() {
        return loanBillId;
    }

    public void setLoanBillId(Integer loanBillId) {
        this.loanBillId = loanBillId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CapitalType getType() {
        return type;
    }

    public void setType(CapitalType type) {
        this.type = type;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setTransactionTypeOnPayment(FinancialLoanRepayment.Type type) {
        if (FinancialLoanRepayment.Type.INSURANCE_REPAYMENT.equals(type)) {
            this.transactionType = TransactionType.INSURANCE_REPAYMENT;
        }else if (FinancialLoanRepayment.Type.PRODUCT_RB_REPAYMENT.equals(type)) {
            this.transactionType = TransactionType.PRODUCT_RB_REPAYMENT;
        }else if (FinancialLoanRepayment.Type.CAR_REPAYMENT.equals(type)) {
            this.transactionType = TransactionType.CAR_REPAYMENT;
        }else if (FinancialLoanRepayment.Type.MANUAL_REPAYMENT.equals(type)) {
            this.transactionType = TransactionType.MANUAL_REPAYMENT;
        }
    }
}