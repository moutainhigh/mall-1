package com.yunxin.cb.mall.entity;

import java.util.Date;

/**
 * @title: 商家实体类
 * @auther: eleven
 * @date: 2018/7/19 19:52
 */
public class Seller {
    /** 商家id */
    private Integer sellerId;

    /** 银行户口 */
    private String accountName;

    /** 协议保存地址 */
    private String agreementPicPath;

    /** 审核状态 */
    private Boolean audit;

    /** 开户银行 */
    private String bankAccount;

    /** 开户银行地址 */
    private String bankAccountAddress;

    /** 营业执照名称 */
    private String busName;

    /** 营业执照注册号 */
    private String buslicenseNo;

    /** 营业执照图片路径 */
    private String buslicensePicPath;

    /** 商家支付平台号 */
    private String channelAccount;

    /** 商家支付平台类型 */
    private Integer channelType;

    /** 创建时间 */
    private Date createTime;

    /** 邮箱 */
    private String email;

    /** 状态：true 启用；false 停用 */
    private Boolean enabled;

    /** 手持身份证照片路径 */
    private String holdIdPotoPicPath;

    /** 身份证号 */
    private String idCardNum;

    /** 商家简介 */
    private String introduction;

    /** 联系人 */
    private String linkman;

    /** 手机 */
    private String mobile;

    /** 身份证正面路径 */
    private String positiveIdPotoPicPath;

    /** 对公户名 */
    private String publicAccount;

    /** QQ */
    private String qq;

    /** 备注 */
    private String remark;
    /**
     * 省
     */
    private String province;
    private String provinceName;
    /**
     * 市
     */
    private String city;
    private String cityName;
    /**
     * 区
     */
    private String district;
    private String districtName;

    /** 商家地址 */
    private String sellerAddress;

    /** 商家编码 */
    private String sellerCode;

    /** 商家名称 */
    private String sellerName;

    /** 商家类型 */
    private Integer sellerType;

    /** 联系电话 */
    private String telephone;

    /** 商家微信 */
    private String wechat;

    /** 商家X轴坐标：经度 */
    private String positionX;

    /** 商家Y轴坐标：纬度*/
    private String positionY;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAgreementPicPath() {
        return agreementPicPath;
    }

    public void setAgreementPicPath(String agreementPicPath) {
        this.agreementPicPath = agreementPicPath == null ? null : agreementPicPath.trim();
    }

    public Boolean getAudit() {
        return audit;
    }

    public void setAudit(Boolean audit) {
        this.audit = audit;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankAccountAddress() {
        return bankAccountAddress;
    }

    public void setBankAccountAddress(String bankAccountAddress) {
        this.bankAccountAddress = bankAccountAddress == null ? null : bankAccountAddress.trim();
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName == null ? null : busName.trim();
    }

    public String getBuslicenseNo() {
        return buslicenseNo;
    }

    public void setBuslicenseNo(String buslicenseNo) {
        this.buslicenseNo = buslicenseNo == null ? null : buslicenseNo.trim();
    }

    public String getBuslicensePicPath() {
        return buslicensePicPath;
    }

    public void setBuslicensePicPath(String buslicensePicPath) {
        this.buslicensePicPath = buslicensePicPath == null ? null : buslicensePicPath.trim();
    }

    public String getChannelAccount() {
        return channelAccount;
    }

    public void setChannelAccount(String channelAccount) {
        this.channelAccount = channelAccount == null ? null : channelAccount.trim();
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getHoldIdPotoPicPath() {
        return holdIdPotoPicPath;
    }

    public void setHoldIdPotoPicPath(String holdIdPotoPicPath) {
        this.holdIdPotoPicPath = holdIdPotoPicPath == null ? null : holdIdPotoPicPath.trim();
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum == null ? null : idCardNum.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPositiveIdPotoPicPath() {
        return positiveIdPotoPicPath;
    }

    public void setPositiveIdPotoPicPath(String positiveIdPotoPicPath) {
        this.positiveIdPotoPicPath = positiveIdPotoPicPath == null ? null : positiveIdPotoPicPath.trim();
    }

    public String getPublicAccount() {
        return publicAccount;
    }

    public void setPublicAccount(String publicAccount) {
        this.publicAccount = publicAccount == null ? null : publicAccount.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress == null ? null : sellerAddress.trim();
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode == null ? null : sellerCode.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public Integer getSellerType() {
        return sellerType;
    }

    public void setSellerType(Integer sellerType) {
        this.sellerType = sellerType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}