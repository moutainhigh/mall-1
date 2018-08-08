package com.yunxin.cb.mall.vo;

import com.yunxin.cb.util.LogicUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @title: 银行卡VO对象
 * @auther: eleven
 * @date: 2018/8/7 21:10
 */
@ApiModel(value="银行卡",description="银行卡VO对象 BankInfoVO")
public class BankInfoVO implements java.io.Serializable{
    private static final long serialVersionUID = 3473099803823395986L;

    /** 银行ID*/
    @ApiModelProperty(value="银行ID",name="bankId",example="3")
    private Integer bankId;


    @ApiModelProperty(value="银行卡号",name="bankCardNumber",example="6222600260001072444")
    private String bankCardNumber;

    @ApiModelProperty(value="银行名称",name="bankName",example="招商银行")
    private String bankName;

    @ApiModelProperty(value="银行Logo",name="bankLogo",example="show.jpg")
    private String bankLogo;

    @ApiModelProperty(value="有效期",name="effectiveTime",example="2018-06-15")
    private String effectiveTime;

    @ApiModelProperty(value="持卡人",name="cardholder",example="小明")
    private String cardholder;

    @ApiModelProperty(value="证件类型",name="cardType",example="居民身份证")
    private String cardType;

    @ApiModelProperty(value="证件号",name="customerCardNo",example="421023199212048394")
    private String customerCardNo;

    @ApiModelProperty(value="手机号",name="mobile",example="18071250651")
    private String mobile;

    @ApiModelProperty(value="创建时间",name="createTime",example="2018-06-15")
    private Date createTime;

    @ApiModelProperty(value="验证码",name="code",example="666666")
    private String code;

    @ApiModelProperty(value="银行名称类型和卡号脱敏",name="showNameTypeNumber",example="招商银行 信用卡 2316")
    private String showNameTypeNumber;

    @ApiModelProperty(value="银行卡号脱敏",name="showBankCardNumber",example="**** **** **** 2444")
    private String showBankCardNumber;

    @ApiModelProperty(value="银行名称和卡号脱敏",name="showNameNumber",example="招商银行(2316)")
    private String showNameNumber;

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
        this.bankCardNumber = bankCardNumber;
        if(LogicUtils.isNotNull(bankCardNumber)){
            this.showBankCardNumber="**** **** **** "+bankCardNumber.substring(bankCardNumber.length()-4,bankCardNumber.length());
        }
        setNameTypeNumber();
        setNameNumber();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
        setNameTypeNumber();
        setNameNumber();
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
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
        this.cardholder = cardholder;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
        setNameTypeNumber();
    }

    public String getCustomerCardNo() {
        return customerCardNo;
    }

    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShowNameTypeNumber() {
        return showNameTypeNumber;
    }

    public void setShowNameTypeNumber(String showNameTypeNumber) {
        this.showNameTypeNumber = showNameTypeNumber;
    }

    public String getShowBankCardNumber() {
        return showBankCardNumber;
    }

    public void setShowBankCardNumber(String showBankCardNumber) {
        this.showBankCardNumber = showBankCardNumber;
    }

    public String getShowNameNumber() {
        return showNameNumber;
    }

    public void setShowNameNumber(String showNameNumber) {
        this.showNameNumber = showNameNumber;
    }

    //格式化前台展示：招商银行 信用卡 2316
    private void setNameTypeNumber() {
        if (LogicUtils.isNotNull(this.bankName) && LogicUtils.isNotNull(this.cardType) && LogicUtils.isNotNull(this.bankCardNumber)) {
            this.showNameTypeNumber=this.bankName+" "+this.cardType+" "+this.bankCardNumber.substring(bankCardNumber.length()-4,bankCardNumber.length());
        }
    }

    //格式化前台展示：招商银行(2316)
    private void setNameNumber() {
        if (LogicUtils.isNotNull(this.bankName) && LogicUtils.isNotNull(this.bankCardNumber)) {
            this.showNameNumber=this.bankName+"("+this.bankCardNumber.substring(bankCardNumber.length()-4,bankCardNumber.length())+")";
        }
    }

    @Override
    public String toString() {
        return "BankInfoVO{" +
                "bankId=" + bankId +
                ", showNameTypeNumber='" + showNameTypeNumber + '\'' +
                ", bankCardNumber='" + bankCardNumber + '\'' +
                ", showBankCardNumber='" + showBankCardNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankLogo='" + bankLogo + '\'' +
                ", effectiveTime='" + effectiveTime + '\'' +
                ", cardholder='" + cardholder + '\'' +
                ", cardType='" + cardType + '\'' +
                ", customerCardNo='" + customerCardNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime=" + createTime +
                ", code='" + code + '\'' +
                '}';
    }
}
