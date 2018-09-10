package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FinancialFreezingBill {
    /**  */
    private Integer freezingBillId;

    /** 客户ID */
    private Integer customerId;

    /** 资金类型：1.支出，2.收入 */
    private CapitalType type;

    /** 交易类型：1.保险返利 2.保险购买 */
    private TransactionType transactionType;

    /** 交易描述 */
    private String transactionDesc;

    /** 交易金 */
    private BigDecimal amount;

    /** 交易时间 */
    private LocalDateTime createTime;

    public Integer getFreezingBillId() {
        return freezingBillId;
    }

    public void setFreezingBillId(Integer freezingBillId) {
        this.freezingBillId = freezingBillId;
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
        this.transactionDesc = transactionDesc == null ? null : transactionDesc.trim();
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
}