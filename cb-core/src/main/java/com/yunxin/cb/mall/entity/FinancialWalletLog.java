package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class FinancialWalletLog {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer walletLogId;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private FinancialWallet financialWallet;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * 冻结金额
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal freezingAmount;

    /**
     * 信用额度
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal creditAmount;

    /**
     * 汽车贷款
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal debtCar;

    /**
     * 信用贷款
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal debtCredit;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 操作类型
     */
    private OperationType type;

    /**
     * 变动金额
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;


    public Integer getWalletLogId() {
        return walletLogId;
    }

    public void setWalletLogId(Integer walletLogId) {
        this.walletLogId = walletLogId;
    }

    public FinancialWallet getFinancialWallet() {
        return financialWallet;
    }

    public void setFinancialWallet(FinancialWallet financialWallet) {
        this.financialWallet = financialWallet;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getFreezingAmount() {
        return freezingAmount;
    }

    public void setFreezingAmount(BigDecimal freezingAmount) {
        this.freezingAmount = freezingAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getDebtCar() {
        return debtCar;
    }

    public void setDebtCar(BigDecimal debtCar) {
        this.debtCar = debtCar;
    }

    public BigDecimal getDebtCredit() {
        return debtCredit;
    }

    public void setDebtCredit(BigDecimal debtCredit) {
        this.debtCredit = debtCredit;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}