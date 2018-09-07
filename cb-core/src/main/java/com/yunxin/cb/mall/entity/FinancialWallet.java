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

    /**  */
    private Integer walletId;

    /**  */
    private Customer customer;

    /** 累计贷款次数 */
    private Integer frequency;

    /** 累计借款利息 */
    private BigDecimal loanInterest;

    /** 累计借款金额 */
    private BigDecimal sumAmount;

    /** 汽车贷款 */
    private BigDecimal debtCar;

    /** 信用贷 */
    private BigDecimal debtCredit;

    /** 信用额度 */
    private BigDecimal creditAmount;

    /** 冻结金额，例如提现未到账，资金划拨未审核等 */
    private BigDecimal freezingAmount;

    /** 版本号 */
    private Integer version;

    public FinancialWallet() {
    }

    /** 初始化*/
    public FinancialWallet(Customer customer) {
        this.customer = customer;
        this.loanInterest=new BigDecimal(0);
        this.sumAmount=new BigDecimal(0);
        this.debtCar=new BigDecimal(0);
        this.debtCredit=new BigDecimal(0);
        this.creditAmount=new BigDecimal(0);
        this.freezingAmount=new BigDecimal(0);
        this.version=1;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
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



    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getDebtCredit() {
        return debtCredit;
    }

    public void setDebtCredit(BigDecimal debtCredit) {
        this.debtCredit = debtCredit;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getFreezingAmount() {
        return freezingAmount;
    }

    public void setFreezingAmount(BigDecimal freezingAmount) {
        this.freezingAmount = freezingAmount;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    @Column(nullable = false, precision = 12, scale = 2)
    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(BigDecimal loanInterest) {
        this.loanInterest = loanInterest;
    }
    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
    }
    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getDebtCar() {
        return debtCar;
    }

    public void setDebtCar(BigDecimal debtCar) {
        this.debtCar = debtCar;
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
