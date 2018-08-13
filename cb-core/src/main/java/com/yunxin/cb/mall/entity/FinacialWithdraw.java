package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.WithdrawState;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @title: 提现表实体类
 * @auther: eleven
 * @date: 2018/8/10 14:12
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class FinacialWithdraw implements Serializable {
    private static final long serialVersionUID = -9202329900442055950L;

    /**  */
    private Integer withdrawId;

    /** 用户id */
    private Customer customer;

    /** 提现人银行卡 */
    private BankInfo bank;

    /** 提现金额 */
    private BigDecimal amount;

    private BigDecimal amountBegin;
    private BigDecimal amountEnd;

    /** 实际提现金额 */
    private BigDecimal realAmount;

    /** 提现手续费 */
    private BigDecimal chargeFee;

    /** 状态：0.审核中 1.审核失败 2.待发放 3.转账中 4.交易完成 */
    private WithdrawState state;

    /** 提现类型：0.报账转账 1.保险返利转账 */
    private WithdrawType withdrawType;

    /** 审核时间 */
    private Date auditDate;

    /** 审核员 */
    private String auditOperator;

    /** 审核意见 */
    private String auditMessage;

    /** 发放时间 */
    private Date grantDate;

    /** 发放员 */
    private String grantOperator;

    /** 提现时间 */
    private Date applyDate;

    /** 修改时间 */
    private Date updateDate;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public Integer getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
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

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public BigDecimal getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(BigDecimal chargeFee) {
        this.chargeFee = chargeFee;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public WithdrawState getState() {
        return state;
    }

    public void setState(WithdrawState state) {
        this.state = state;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public WithdrawType getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(WithdrawType withdrawType) {
        this.withdrawType = withdrawType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    @Column(length = 40)
    public String getAuditOperator() {
        return auditOperator;
    }

    public void setAuditOperator(String auditOperator) {
        this.auditOperator = auditOperator;
    }

    @Column(length = 255)
    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    @Column(length = 40)
    public String getGrantOperator() {
        return grantOperator;
    }

    public void setGrantOperator(String grantOperator) {
        this.grantOperator = grantOperator;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getAmountBegin() {
        return amountBegin;
    }

    public void setAmountBegin(BigDecimal amountBegin) {
        this.amountBegin = amountBegin;
    }

    public BigDecimal getAmountEnd() {
        return amountEnd;
    }

    public void setAmountEnd(BigDecimal amountEnd) {
        this.amountEnd = amountEnd;
    }
}
