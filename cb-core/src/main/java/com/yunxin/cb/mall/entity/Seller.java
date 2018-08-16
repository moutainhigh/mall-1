package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.entity.meta.ChannelType;
import com.yunxin.cb.mall.entity.meta.SellerType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 商家(卖家)
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Seller implements java.io.Serializable {

    /****/
    private int sellerId;
    /**
     * 商家编码
     **/
    private String sellerCode;
    /**
     * 商家名称
     **/
    private String sellerName;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 商家地址
     **/
    private String sellerAddress;
    /**
     * 商家类型
     **/
    private SellerType sellerType;
    /**
     * 联系人
     **/
    private String linkman;
    /**
     * 手机
     **/
    private String mobile;
    /**
     * 联系电话
     **/
    private String telephone;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * QQ
     **/
    private String qq;
    /**
     * 商家微信
     **/
    private String wechat;
    /**
     * 商家支付平台类型
     **/
    private ChannelType channelType;
    /**
     * 商家支付平台号
     **/
    private String channelAccount;

    /**
     * 商家简介
     **/
    private String introduction;
    /**
     * 审核状态
     **/
    private boolean audit;
    /**
     * 营业执照名称
     **/
    private String busName;
    /**
     * 营业执照注册号
     **/
    private String buslicenseNo;
    /**
     * 营业执照图片路径
     **/
    private String buslicensePicPath;
    /**
     * 银行户口
     **/
    private String accountName;
    /**
     * 对公户名
     **/
    private String publicAccount;
    /**
     * 开户银行
     **/
    private String bankAccount;
    /**
     * 开户银行地址
     **/
    private String bankAccountAddress;
    /**
     * 身份证号
     **/
    private String idCardNum;
    /**
     * 手持身份证照片路径
     **/
    private String holdIdPotoPicPath;
    /**
     * 身份证正面路径
     **/
    private String positiveIdPotoPicPath;
    /**
     * 协议保存地址
     **/
    private String agreementPicPath;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;

    /**
     * 商家平台账号
     **/
    private Set<User> users = new HashSet<>();

    /**
     * 商家地理位置经度 X轴坐标
     **/
    private String positionX;

    /**
     * 商家地理位置玮度 Y轴坐标
     **/
    private String positionY;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Column(nullable = false, length = 32)
    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    @Column(nullable = false, length = 32)
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Column(length = 255)
    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    @Column(precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public SellerType getSellerType() {
        return sellerType;
    }

    public void setSellerType(SellerType sellerType) {
        this.sellerType = sellerType;
    }

    @Column(nullable = false, length = 32)
    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    @Column(nullable = false, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(length = 16)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(length = 32, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 16)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(length = 32)
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Column(precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    @Column(length = 32, nullable = false)
    public String getChannelAccount() {
        return channelAccount;
    }

    public void setChannelAccount(String channelAccount) {
        this.channelAccount = channelAccount;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Column(length = 255)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(precision = 1)
    public boolean isAudit() {
        return audit;
    }

    public void setAudit(boolean audit) {
        this.audit = audit;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = false, length = 128)
    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    @Column(nullable = false, length = 128)
    public String getBuslicenseNo() {
        return buslicenseNo;
    }

    public void setBuslicenseNo(String buslicenseNo) {
        this.buslicenseNo = buslicenseNo;
    }

    @Column(length = 255)
    public String getBuslicensePicPath() {
        return buslicensePicPath;
    }

    public void setBuslicensePicPath(String buslicensePicPath) {
        this.buslicensePicPath = buslicensePicPath;
    }

    @Column(nullable = false, length = 128)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(nullable = false, length = 128)
    public String getPublicAccount() {
        return publicAccount;
    }

    public void setPublicAccount(String publicAccount) {
        this.publicAccount = publicAccount;
    }

    @Column(nullable = false, length = 64)
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Column(nullable = false, length = 255)
    public String getBankAccountAddress() {
        return bankAccountAddress;
    }

    public void setBankAccountAddress(String bankAccountAddress) {
        this.bankAccountAddress = bankAccountAddress;
    }

    @Column(nullable = false, length = 18)
    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    @Column(length = 255)
    public String getHoldIdPotoPicPath() {
        return holdIdPotoPicPath;
    }

    public void setHoldIdPotoPicPath(String holdIdPotoPicPath) {
        this.holdIdPotoPicPath = holdIdPotoPicPath;
    }

    @Column(length = 255)
    public String getPositiveIdPotoPicPath() {
        return positiveIdPotoPicPath;
    }

    public void setPositiveIdPotoPicPath(String positiveIdPotoPicPath) {
        this.positiveIdPotoPicPath = positiveIdPotoPicPath;
    }

    @Column(length = 255)
    public String getAgreementPicPath() {
        return agreementPicPath;
    }

    public void setAgreementPicPath(String agreementPicPath) {
        this.agreementPicPath = agreementPicPath;
    }

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, length = 7)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 32)
    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    @Column(length = 32)
    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    @Column(length = 32)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(length = 32)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(length = 32)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
