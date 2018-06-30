package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yunxin.cb.mall.entity.meta.AddressType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 收货地址
 *
 * @author x001393
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class DeliveryAddress implements java.io.Serializable {

    /***/
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private int addressId;
    /**
     * 客户
     */
    private Customer customer;
    /**
     * 收货地址类型
     */
    private AddressType addressType;
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
     * 默认收货地址
     */
    private boolean defaultAddress;
    /**
     * 备注
     */
    private String remark;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int deliveryAddressId) {
        this.addressId = deliveryAddressId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(precision = 5, scale = 0)
    @Enumerated(EnumType.ORDINAL)
    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType deliveryAddrName) {
        this.addressType = deliveryAddrName;
    }

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

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }


}
