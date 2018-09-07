package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class FinancialWallet implements Serializable {
    private static final long serialVersionUID = -4076546313843225350L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer walletId;

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
     * 累计贷款次数
     */
    private Integer frequency;

    /**
     * 累计借款利息
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal loanInterest;

    /**
     * 累计借款金额
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal sumAmount;

    /**
     * 版本号
     */
    private Integer version;

    public FinancialWallet() {
    }

    /**
     * 初始化
     */
    public FinancialWallet(Customer customer) {
        this.customer = customer;
        this.loanInterest = new BigDecimal(0);
        this.sumAmount = new BigDecimal(0);
        this.debtCar = new BigDecimal(0);
        this.debtCredit = new BigDecimal(0);
        this.creditAmount = new BigDecimal(0);
        this.freezingAmount = new BigDecimal(0);
        this.version = 1;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
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

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(BigDecimal loanInterest) {
        this.loanInterest = loanInterest;
    }

    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "FinancialWallet{" +
                "walletId=" + walletId +
                ", customer=" + customer +
                ", frequency=" + frequency +
                ", loanInterest=" + loanInterest +
                ", sumAmount=" + sumAmount +
                ", debtCar=" + debtCar +
                ", debtCredit=" + debtCredit +
                ", creditAmount=" + creditAmount +
                ", freezingAmount=" + freezingAmount +
                ", version=" + version +
                '}';
    }
}
