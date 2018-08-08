package com.yunxin.cb.mall.entity;

import java.util.Date;

public class Customer {
    /**  */
    private Integer customerId;

    /**  */
    private String accountName;

    /**  */
    private String address;

    /**  */
    private Date birthday;

    /**  */
    private String cardNo;

    /**  */
    private String city;

    /**  */
    private Date createTime;

    /**  */
    private Integer customerType;

    /**  */
    private String district;

    /**  */
    private String email;

    /**  */
    private Boolean enabled;

    /**  */
    private Integer exchangeIntegral;

    /**  */
    private Integer integral;

    /**  */
    private Boolean mailChecked;

    /**  */
    private String mobile;

    /**  */
    private Boolean mobileChecked;

    /**  */
    private String password;

    /**  */
    private String postCode;

    /**  */
    private String province;

    /**  */
    private String qqAccessToken;

    /**  */
    private String qqFigureUrl;

    /**  */
    private String qqNickName;

    /**  */
    private String qqOpenId;

    /**  */
    private String realName;

    /**  */
    private String remark;

    /**  */
    private Boolean sex;

    /**  */
    private String telephone;

    /**  */
    private Integer totalIntegral;

    /**  */
    private Integer rank;

    /**  */
    private Boolean emailChecked;

    /** 融云TOKEN */
    private String rongCloudToken;

    /** 头像 */
    private String avatarUrl;

    /** 推荐人 */
    private Integer recommendCustomerId;

    /**  */
    private String nickName;

    /** 推荐人点赞数 */
    private Integer praise;

    /**  */
    private Integer praiseNum;

    /** 证件类型 */
    private String cardType;

    /** 证件号码 */
    private String customerCardNo;

    /** 证件证明照 */
    private String cardPositiveImg;

    /** 证件反面照 */
    private String cardNegativeImg;

    /** 银行卡图片 */
    private String bankCardImg;

    /** 等级 */
    private Integer customerLevel;

    /** 等级编码 */
    private String levelCode;

    /** 邀请码 */
    private String invitationCode;

    /**  */
    private Integer policy;

    /** 国籍 */
    private String customerCountry;

    /** 证件有效期 */
    private Date customerCardPeroid;

    /** 职业类别 */
    private String occupationalCategory;

    /** 支付密码 */
    private String paymentPassword;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(Integer exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Boolean getMailChecked() {
        return mailChecked;
    }

    public void setMailChecked(Boolean mailChecked) {
        this.mailChecked = mailChecked;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getMobileChecked() {
        return mobileChecked;
    }

    public void setMobileChecked(Boolean mobileChecked) {
        this.mobileChecked = mobileChecked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getQqAccessToken() {
        return qqAccessToken;
    }

    public void setQqAccessToken(String qqAccessToken) {
        this.qqAccessToken = qqAccessToken == null ? null : qqAccessToken.trim();
    }

    public String getQqFigureUrl() {
        return qqFigureUrl;
    }

    public void setQqFigureUrl(String qqFigureUrl) {
        this.qqFigureUrl = qqFigureUrl == null ? null : qqFigureUrl.trim();
    }

    public String getQqNickName() {
        return qqNickName;
    }

    public void setQqNickName(String qqNickName) {
        this.qqNickName = qqNickName == null ? null : qqNickName.trim();
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId == null ? null : qqOpenId.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Boolean getEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(Boolean emailChecked) {
        this.emailChecked = emailChecked;
    }

    public String getRongCloudToken() {
        return rongCloudToken;
    }

    public void setRongCloudToken(String rongCloudToken) {
        this.rongCloudToken = rongCloudToken == null ? null : rongCloudToken.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public Integer getRecommendCustomerId() {
        return recommendCustomerId;
    }

    public void setRecommendCustomerId(Integer recommendCustomerId) {
        this.recommendCustomerId = recommendCustomerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
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

    public String getCardPositiveImg() {
        return cardPositiveImg;
    }

    public void setCardPositiveImg(String cardPositiveImg) {
        this.cardPositiveImg = cardPositiveImg == null ? null : cardPositiveImg.trim();
    }

    public String getCardNegativeImg() {
        return cardNegativeImg;
    }

    public void setCardNegativeImg(String cardNegativeImg) {
        this.cardNegativeImg = cardNegativeImg == null ? null : cardNegativeImg.trim();
    }

    public String getBankCardImg() {
        return bankCardImg;
    }

    public void setBankCardImg(String bankCardImg) {
        this.bankCardImg = bankCardImg == null ? null : bankCardImg.trim();
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode == null ? null : levelCode.trim();
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    public Integer getPolicy() {
        return policy;
    }

    public void setPolicy(Integer policy) {
        this.policy = policy;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry == null ? null : customerCountry.trim();
    }

    public Date getCustomerCardPeroid() {
        return customerCardPeroid;
    }

    public void setCustomerCardPeroid(Date customerCardPeroid) {
        this.customerCardPeroid = customerCardPeroid;
    }

    public String getOccupationalCategory() {
        return occupationalCategory;
    }

    public void setOccupationalCategory(String occupationalCategory) {
        this.occupationalCategory = occupationalCategory == null ? null : occupationalCategory.trim();
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword == null ? null : paymentPassword.trim();
    }
}