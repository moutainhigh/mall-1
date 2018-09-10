/*
 * Powered By [microlink-framework]
 * Web Site: http://www.microlinktech.net
 * Since 2015 - 2018
 */


package com.yunxin.cb.mall.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @title: 银行卡实体类
 * @auther: eleven
 * @date: 2018/8/7 21:10
 */
public class BankInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 银行ID*/
    private Integer bankId;

    /** 银行卡号 */
    private String bankCardNumber;

    /** 银行名称 */
    private String bankName;

    /** 银行Logo */
    private String bankLogo;

    /** 有效期 */
    private String effectiveTime;

    /** 持卡人 */
    private String cardholder;

    /** 证件类型 */
    private String cardType;

    /** 证件号 */
    private String customerCardNo;

    /** 手机号 */
    private String mobile;

    /** 客户ID */
    private Integer customerId;

    /** 创建时间 */
    private Date createTime;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber == null ? null : bankCardNumber.trim();
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder == null ? null : cardholder.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCustomerCardNo() {
        return customerCardNo;
    }

    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo == null ? null : customerCardNo.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }
}