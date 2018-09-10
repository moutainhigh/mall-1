package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.FinancialLogPayType;
import com.yunxin.cb.mall.entity.meta.FinancialLogTransType;
import com.yunxin.cb.mall.entity.meta.PayState;
import com.yunxin.cb.rb.entity.meta.LoanRepaymentType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Author chenpeng
 * @Description 账单实体类
 * @Date 2018/9/10 15:45 
 **/
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class FinancialLogBill {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer logId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** 交易人 */
    private String customerName;

    /** 标题 */
    private String title;

    /** 图片 */
    private String image;

    /** 交易金额 */
    private BigDecimal amount;

    /** 交易类型：0.收入，1.支出 */
    private OperationType type;

    /** 交易类型：0.保险购买1.保险返利2.商品购买3.商品退货4.借款5.手动还款6.保险返利自动还款7.商品报帐自动还款 */
    private FinancialLogTransType transactionType;

    /** 支付方式：0.微信，1.支付宝，2.报账，3.还款 */
    private FinancialLogPayType payType;

    /** 交易状态 */
    private PayState state;

    /** 交易订单号 */
    private String transactionNo;

    /** 交易描述 */
    private String transactionDesc;

    /** 交易时间 */
    private Date createTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public FinancialLogTransType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(FinancialLogTransType transactionType) {
        this.transactionType = transactionType;
    }

    public FinancialLogPayType getPayType() {
        return payType;
    }

    public void setPayType(FinancialLogPayType payType) {
        this.payType = payType;
    }

    public PayState getState() {
        return state;
    }

    public void setState(PayState state) {
        this.state = state;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setTransactionTypeOnPayment(LoanRepaymentType type, boolean repayment) {
        if (LoanRepaymentType.INSURANCE_REBATE_REPAYMENT.equals(type)) {
            this.transactionType = FinancialLogTransType.INSURANCE_REBATE;
            if (repayment) {
                this.transactionType = FinancialLogTransType.INSURANCE_REPAYMENT;
            }
        }else if (LoanRepaymentType.COMMODITY_REIMBURSEMENT_REPAYMENT.equals(type)) {
            this.transactionType = FinancialLogTransType.PRODUCT_RB;
            if (repayment) {
                this.transactionType = FinancialLogTransType.PRODUCT_RB_REPAYMENT;
            }
        }else if (LoanRepaymentType.CAR_REBATE_REPAYMENT.equals(type)) {
            this.transactionType = FinancialLogTransType.CAR_REBATE;
            if (repayment) {
                this.transactionType = FinancialLogTransType.CAR_REPAYMENT;
            }
        }else if (LoanRepaymentType.MANUAL_REIMBURSEMENT_REPAYMENT.equals(type)) {
            this.transactionType = FinancialLogTransType.MANUAL_REPAYMENT;
        }
    }

    public void setPayTypeOnPayment(LoanRepaymentType type) {
        if (LoanRepaymentType.INSURANCE_REBATE_REPAYMENT.equals(type)) {
            this.payType = FinancialLogPayType.TRANSFER;
        }else if (LoanRepaymentType.COMMODITY_REIMBURSEMENT_REPAYMENT.equals(type)) {
            this.payType = FinancialLogPayType.RB;
        }else if (LoanRepaymentType.CAR_REBATE_REPAYMENT.equals(type)) {
            this.payType = FinancialLogPayType.TRANSFER;
        }else if (LoanRepaymentType.MANUAL_REIMBURSEMENT_REPAYMENT.equals(type)) {
            this.payType = FinancialLogPayType.LOAN;
        }
    }
}