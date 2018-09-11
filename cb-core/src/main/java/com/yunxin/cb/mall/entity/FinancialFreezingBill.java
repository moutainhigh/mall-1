package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
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
public class FinancialFreezingBill {
    /**  */
    private Integer freezingBillId;

    /** 客户*/
    private Customer customer;

    /** 资金类型：1.支出，2.收入 */
    private CapitalType type;

    /** 交易类型：0.保险返利 1.保险购买 */
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
    public Integer getFreezingBillId() {
        return freezingBillId;
    }

    public void setFreezingBillId(Integer freezingBillId) {
        this.freezingBillId = freezingBillId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "customer_id", nullable = false, insertable = true, updatable = true)
     })
    public Customer getCustomer() {
        return customer;
    }
    public CapitalType getType() {
        return type;
    }

    public void setType(CapitalType type) {
        this.type = type;
    }

    @Column(length = 128, nullable = false, unique = true)
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
}