package com.yunxin.cb.mall.vo;

import java.io.Serializable;

/**
 * @author wangteng  客户证件
 */
public class CustomerCardVo implements Serializable {
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 证件类型
     */
    private String cardType;
    /**
     * 证件号码
     */
    private String customerCardNo;
    /**
     * 证件正面照
     */
    private String cardPositiveImg;
    /**
     * 证件反面照
     */
    private String cardNegativeImg;
    /**
     * 银行照片
     */
    private String bankCardImg;
    /**
     * 真实姓名
     */
    private String realName;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCustomerCardNo() {
        return customerCardNo;
    }

    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public String getCardPositiveImg() {
        return cardPositiveImg;
    }

    public void setCardPositiveImg(String cardPositiveImg) {
        this.cardPositiveImg = cardPositiveImg;
    }

    public String getCardNegativeImg() {
        return cardNegativeImg;
    }

    public void setCardNegativeImg(String cardNegativeImg) {
        this.cardNegativeImg = cardNegativeImg;
    }

    public String getBankCardImg() {
        return bankCardImg;
    }

    public void setBankCardImg(String bankCardImg) {
        this.bankCardImg = bankCardImg;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
