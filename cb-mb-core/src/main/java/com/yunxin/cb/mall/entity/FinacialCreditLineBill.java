package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FinacialCreditLineBill {
    /**  */
    private Integer finacialCreditLineId;

    /** 客户ID */
    private Integer customerId;

    /** 资金类型 */
    private String type;

    /** 交易类型 */
    private String transactionType;

    /** 交易描述 */
    private String transactionDesc;

    /** 交易额度 */
    private BigDecimal amount;

    /** 时间 */
    private Date createTime;

    public Integer getFinacialCreditLineId() {
        return finacialCreditLineId;
    }

    public void setFinacialCreditLineId(Integer finacialCreditLineId) {
        this.finacialCreditLineId = finacialCreditLineId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType == null ? null : transactionType.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}