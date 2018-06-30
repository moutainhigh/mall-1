package com.yunxin.cb.mall.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Administrator on 2016/3/17.
 */
@Embeddable
public class ConsigneeInfo {
    /**
     * 收货人姓名
     */
    private String consigneeName;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区，县
     */
    private String district;
    /**
     * 收货人地址
     */
    private String consigneeAddress;
    /**
     * 邮政编码
     */
    private String postCode;
    /**
     * 固话
     */
    private String consigneeTelephone;
    /**
     * 手机
     */
    private String consigneeMobile;
    /**
     * 备注
     */
    private String remark;

    @Column(nullable = false, length = 32)
    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
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
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    @Column(length = 6, nullable = true)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(length = 16, nullable = true)
    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
    }

    @Column(length = 11, nullable = true)
    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    @Column(length = 512, nullable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
