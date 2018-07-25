package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.CustomerType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangteng  客户基本信息
 */
public class CustomerInfoVo implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private int customerId;
    /**
     * 账户名
     */
    @ApiModelProperty(value = "账户名", name = "accountName", example = "186456789")
    private String accountName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "2018-07-12 12:00")
    private Date createTime;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别
     */
    private boolean sex = true;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 用户头像
     */
    private String avatarUrl;
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
     * 备注
     */
    private String remark;
    /**
     * 推荐人点赞
     */
    private boolean praise;
    /**
     * 收到点赞次数
     */
    private int praiseNum;
    /**
     * 融云token
     */
    private String rongCloudToken;
    /**
     * 用户账号类型
     */
    private CustomerType customerType;
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
     * 等级
     */
    private int customerLevel;
    /**
     * 等级编码
     */
    private String levelCode;
    /**
     * 邀请码
     */
    private String invitationCode;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isPraise() {
        return praise;
    }

    public void setPraise(boolean praise) {
        this.praise = praise;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getRongCloudToken() {
        return rongCloudToken;
    }

    public void setRongCloudToken(String rongCloudToken) {
        this.rongCloudToken = rongCloudToken;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
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

    public int getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(int customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
