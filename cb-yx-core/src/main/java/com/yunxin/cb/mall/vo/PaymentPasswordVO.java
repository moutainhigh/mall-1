package com.yunxin.cb.mall.vo;

import com.yunxin.cb.util.LogicUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="支付密码设置对象",description="支付密码设置对象VO")
public class PaymentPasswordVO {
    /**
     * 账户名
     */
    @ApiModelProperty(value="账户名",name="accountName",example="186456789")
    private String accountName;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名",name="realName",example="张三")
    private String realName;

    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码",name="customerCardNo",example="432522984654655")
    private String customerCardNo;

    /**
     * 手机验证码
     */
    @ApiModelProperty(value="手机验证码",name="mobileVaildCode",example="123456")
    private String mobileVaildCode;

    /**
     * 第一次交易密码
     */
    @ApiModelProperty(value="第一次输入交易密码",name="paymentPassword1",example="123456")
    private String paymentPassword1;

    /**
     * 第二次交易密码
     */
    @ApiModelProperty(value="第二次输入交易密码",name="paymentPassword2",example="123456")
    private String paymentPassword2;

    @ApiModelProperty(value="账户名脱敏",name="showAccountName",example="138****1569")
    private String showAccountName;

    @ApiModelProperty(value="姓名脱敏",name="showRealName",example="*想")
    private String showRealName;


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
        if(LogicUtils.isNotNull(accountName)){
            this.showAccountName = accountName.substring(0,3) + "****"+ accountName.substring(accountName.length()-4,accountName.length());
        }
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
        if(LogicUtils.isNotNull(realName)){
            this.showRealName = "*"+ realName.substring(realName.length()-1,realName.length());
        }
    }

    public String getCustomerCardNo() {
        return customerCardNo;
    }

    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public String getMobileVaildCode() {
        return mobileVaildCode;
    }

    public void setMobileVaildCode(String mobileVaildCode) {
        this.mobileVaildCode = mobileVaildCode;
    }

    public String getPaymentPassword1() {
        return paymentPassword1;
    }

    public void setPaymentPassword1(String paymentPassword1) {
        this.paymentPassword1 = paymentPassword1;
    }

    public String getPaymentPassword2() {
        return paymentPassword2;
    }

    public void setPaymentPassword2(String paymentPassword2) {
        this.paymentPassword2 = paymentPassword2;
    }

    public String getShowAccountName() {
        return showAccountName;
    }

    public void setShowAccountName(String showAccountName) {
        this.showAccountName = showAccountName;
    }

    public String getShowRealName() {
        return showRealName;
    }

    public void setShowRealName(String showRealName) {
        this.showRealName = showRealName;
    }

    @Override
    public String toString() {
        return "PaymentPasswordVO{" +
                "accountName='" + accountName + '\'' +
                ", realName='" + realName + '\'' +
                ", customerCardNo='" + customerCardNo + '\'' +
                ", mobileVaildCode='" + mobileVaildCode + '\'' +
                ", paymentPassword1='" + paymentPassword1 + '\'' +
                ", paymentPassword2='" + paymentPassword2 + '\'' +
                '}';
    }
}
