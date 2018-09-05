package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author chenpeng
 * @Description 信用额度变动记录
 * @Date 2018/9/4 18:17
 **/
public class FinancialCreditLineBill {
    /**  */
    private Integer creditLineId;

    /** 客户ID */
    private Integer customerId;

    /** 资金类型 */
    private CapitalType type;

    /** 交易类型 */
    private TransactionType transactionType;

    /** 交易描述 */
    private String transactionDesc;

    /** 交易额度 */
    private BigDecimal amount;

    /** 时间 */
    private LocalDateTime createTime;

    public Integer getCreditLineId() {
        return creditLineId;
    }

    public void setCreditLineId(Integer creditLineId) {
        this.creditLineId = creditLineId;
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
}