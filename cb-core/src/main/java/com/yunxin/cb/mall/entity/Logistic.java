package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 物流公司
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Logistic implements Serializable {

    private int logisticId;
    /**
     * 卖家
     */
    private Seller seller;
    /***物流公司编码*/
    private String logisticCode;
    /**
     * 物流公司名称
     */
    private String logisticName;
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
     * 物流公司地址
     */
    private String logisticAddress;
    /**
     * 物流公司网址
     */
    private String url;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 联系手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 微信公众号
     */
    private String weChatNo;
    /**
     * QQ
     */
    private String qq;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;
    /**
     * 备注
     */
    private String remark;

    private Set<LogisticPrice> logisticPrices = new HashSet<>();

    public Logistic() {
    }

    public Logistic(int logisticId) {
        this.logisticId = logisticId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getLogisticId() {
        return logisticId;
    }

    public void setLogisticId(int logisticsId) {
        this.logisticId = logisticsId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID", nullable = false)
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Column(length = 32, nullable = false)
    public String getLogisticName() {
        return logisticName;
    }


    public void setLogisticName(String logisticsName) {
        this.logisticName = logisticsName;
    }

    @Column(length = 32, nullable = false)
    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticsCode) {
        this.logisticCode = logisticsCode;
    }

    @Column(length = 64, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 64, nullable = false)
    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    @Column(nullable = false, length = 32)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(nullable = false, length = 32)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false, length = 32)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(length = 255, nullable = false)
    public String getLogisticAddress() {
        return logisticAddress;
    }

    public void setLogisticAddress(String logisticAddress) {
        this.logisticAddress = logisticAddress;
    }

    @Column(length = 11, nullable = false)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(length = 16, nullable = true)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(length = 255, nullable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(length = 16, nullable = true)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(length = 255, nullable = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(length = 64, nullable = true)
    public String getWeChatNo() {
        return weChatNo;
    }

    public void setWeChatNo(String weChatNo) {
        this.weChatNo = weChatNo;
    }

    @Column(precision = 1, nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "logistic")
    public Set<LogisticPrice> getLogisticPrices() {
        return logisticPrices;
    }

    public void setLogisticPrices(Set<LogisticPrice> logisticPrices) {
        this.logisticPrices = logisticPrices;
    }
}
