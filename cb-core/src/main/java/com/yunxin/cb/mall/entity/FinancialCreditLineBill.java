package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class FinancialCreditLineBill {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer creditLineId;

    /** 客户*/
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** 资金类型 */
    private CapitalType type;

    /** 交易类型 */
    private TransactionType transactionType;

    /** 交易描述 */
    private String transactionDesc;

    /** 交易额度 */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    /** 交易时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(insertable = false, updatable = false)
    private Date createTime;

    public Integer getCreditLineId() {
        return creditLineId;
    }

    public void setCreditLineId(Integer creditLineId) {
        this.creditLineId = creditLineId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}