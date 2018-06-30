package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yunxin.cb.mall.entity.meta.InvoiceTitleType;
import com.yunxin.cb.mall.entity.meta.InvoiceType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Aidy_He on 16/1/27.
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class OrderInvoice implements java.io.Serializable {

    private int invoiceId;
    /**
     * 发票编号
     */
    private String invoiceCode;
    /**
     * 订单
     */
    private Order order;
    /**
     * 发票类型(1普通,2增值税)
     */
    private InvoiceType invoiceType;
    /**
     * 抬头类型(1个人,2单位...)
     */
    private InvoiceTitleType invoiceTitleType;
    /**
     * 发票抬头(个人或单位名称)
     */
    private String invoiceTitle;
    /**
     * 纳税人识别号
     */
    private String taxpayerNo;
    /**
     * 注册地址
     */
    private String registerAddress;
    /**
     * 注册电活
     */
    private String registerPhone;
    /**
     * 开户行
     */
    private String bankName;
    /**
     * 银行账户
     */
    private String bankAccount;
    /**
     * 发票内容(1.明细,2办公用品(附件清单),3.电脑配件,4.耗材等)
     */
    private String content;
    /**
     * 发票金额
     */
    private float invoiceAmount;
    /**
     * 备注
     */
    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Column(nullable = true, length = 64)
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Column(nullable = true, length = 64)
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(nullable = false, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(nullable = false, length = 32)
    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    @Column(nullable = false, length = 128)
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public InvoiceTitleType getInvoiceTitleType() {
        return invoiceTitleType;
    }

    public void setInvoiceTitleType(InvoiceTitleType invoiceTitleType) {
        this.invoiceTitleType = invoiceTitleType;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(nullable = true, length = 255)
    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    @Column(nullable = true, length = 16)
    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(precision = 12, scale = 2)
    public float getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(float invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    @Column(nullable = true, length = 128)
    public String getTaxpayerNo() {
        return taxpayerNo;
    }

    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo;
    }
}
