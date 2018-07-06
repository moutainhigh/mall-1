package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Rank;
import com.yunxin.cb.mall.entity.meta.CustomerType;

import java.util.Date;

public class FriendVo implements java.io.Serializable {

    private static final long serialVersionUID = 3814946535437297136L;

    /**
     * id
     */
    private int customerId;
    /**
     * 账户名
     */
    private String accountName;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 性别
     */
    private boolean sex = true;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 所在省
     */
    private String province;
    /**
     * 所在市
     */
    private String city;
    /**
     * 所在区
     */
    private String district;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号码
     * 长度设置为32，方便在用户进行QQ登录时，生成本平台新账号时，用openId(长度32)填充手机号字段，以严格保证mobile唯一。
     */
    private String mobile;
    /**
     * 固定电话
     */
    private String telephone;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮编
     */
    private String postCode;
    /**
     * 当前可用积分
     */
    private int integral;
    /**
     * 总积分
     */
    private int totalIntegral;
    /**
     * 已兑换积分
     */
    private int exchangeIntegral;
    /**
     * 会员卡号
     */
    private String cardNo;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;
    /**
     * 手机是否验证
     */
    private boolean mobileChecked;
    /**
     * 邮箱是否验证
     */
    private boolean emailChecked;
    /**
     * 备注
     */
    private String remark;
    /**
     * 等级
     */
    private Rank rank;

    /**
     * openid是唯一对应用户身份的标识，将此ID进行存储便于用户下次登录时辨识其身份，或将其与用户在网站上的原有帐号进行绑定。
     * 以便用户下次登录时可对应到其之前的身份信息，不需要重新授权。
     */
    private String qqOpenId;

    /**
     * 表示当前用户在此网站/应用的登录状态与授权信息
     */
    private String qqAccessToken;

    /**
     * 昵称
     */
    private String qqNickName;

    /**
     * 头像URL
     */
    private String qqFigureUrl;

    /**
     * 用户账号类型
     */
    private CustomerType customerType;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(int totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public int getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(int exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isMobileChecked() {
        return mobileChecked;
    }

    public void setMobileChecked(boolean mobileChecked) {
        this.mobileChecked = mobileChecked;
    }

    public boolean isEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(boolean emailChecked) {
        this.emailChecked = emailChecked;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getQqAccessToken() {
        return qqAccessToken;
    }

    public void setQqAccessToken(String qqAccessToken) {
        this.qqAccessToken = qqAccessToken;
    }

    public String getQqNickName() {
        return qqNickName;
    }

    public void setQqNickName(String qqNickName) {
        this.qqNickName = qqNickName;
    }

    public String getQqFigureUrl() {
        return qqFigureUrl;
    }

    public void setQqFigureUrl(String qqFigureUrl) {
        this.qqFigureUrl = qqFigureUrl;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
