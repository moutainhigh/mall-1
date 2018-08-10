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
public class FinacialWalletLog {

    private Integer walletLogId;
    /**  */
    private FinacialWallet finacialWallet;

    /**  */
    private Customer customer;


    /** 资产总额 */
    private BigDecimal assets;

    /** 可用余额 */
    private BigDecimal balance;

    /** 预期收益 */
    private BigDecimal expectedAmount;

    /** 总负责 */
    private BigDecimal debtTotal;

    /** 预期收益贷 */
    private BigDecimal debtExpected;

    /** 信用贷 */
    private BigDecimal debtCredit;

    /** 信用额度 */
    private BigDecimal creditAmount;

    /** 保险额度 */
    private BigDecimal insuranceAmount;

    /** 总额度 */
    private BigDecimal totalAmount;
    /** 冻结金额，例如提现未到账，资金划拨未审核等 */
    private BigDecimal freezingAmount;

    /** 版本号 */
    private Integer version;
    /** 操作类型 */
    private Integer type;

    /** 变动金额 */
    private BigDecimal amount;


    public FinacialWalletLog() {
    }

    /** 初始化*/
    public FinacialWalletLog(Customer customer) {
        this.customer = customer;
        this.assets=new BigDecimal(0);
        this.balance=new BigDecimal(0);
        this.expectedAmount=new BigDecimal(0);
        this.debtTotal=new BigDecimal(0);
        this.debtExpected=new BigDecimal(0);
        this.debtCredit=new BigDecimal(0);
        this.creditAmount=new BigDecimal(0);
        this.insuranceAmount=new BigDecimal(0);
        this.totalAmount=new BigDecimal(0);
        this.freezingAmount=new BigDecimal(0);
        this.version=1;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public Integer getWalletLogId() {
        return walletLogId;
    }

    public void setWalletLogId(Integer walletLogId) {
        this.walletLogId = walletLogId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "wallet_id", nullable = false, insertable = true, updatable = true)
    })
    public FinacialWallet getFinacialWallet() {
        return finacialWallet;
    }

    public void setFinacialWallet(FinacialWallet finacialWallet) {
        this.finacialWallet = finacialWallet;
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
    public BigDecimal getAssets() {
        return assets;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(BigDecimal expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getDebtTotal() {
        return debtTotal;
    }

    public void setDebtTotal(BigDecimal debtTotal) {
        this.debtTotal = debtTotal;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getDebtExpected() {
        return debtExpected;
    }

    public void setDebtExpected(BigDecimal debtExpected) {
        this.debtExpected = debtExpected;
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
    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}