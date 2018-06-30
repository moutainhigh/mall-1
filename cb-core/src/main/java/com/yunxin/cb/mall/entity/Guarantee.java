package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 保养,保修
 *
 * @author k001389
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Guarantee implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private int teeId;

    /**
     * 客户
     */
    private Customer customer;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 订单详情
     */
    private OrderItem orderItem;
    /**
     * 可免费保养次数
     */
    private int freeCleanCounts;
    /**
     * 可免费保修次数
     */
    private int freeRepairCounts;
    /**
     * 购买时间
     */
    private Date purchasingTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 联系人
     */
    private String contactName;
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
     * 地址
     */
    private String address;
    /**
     * 电话
     */
    private String tel;
    /**
     * 手机
     */
    private String phone;

    /**
     * 保养年限
     */
    private float cleanYearLimit;
    /**
     * 保修年限
     */
    private float repairYearLimit;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TEE_ID", unique = true, nullable = false, precision = 12, scale = 0)
    public int getTeeId() {
        return teeId;
    }

    public void setTeeId(int freeRepairCleanId) {
        this.teeId = freeRepairCleanId;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(nullable = false, length = 32)
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_DETAIL_ID", nullable = false)
    @JsonIgnore
    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getPurchasingTime() {
        return purchasingTime;
    }

    @Column(nullable = false, length = 2)
    public int getFreeCleanCounts() {
        return freeCleanCounts;
    }

    public void setFreeCleanCounts(int freeCleanCounts) {
        this.freeCleanCounts = freeCleanCounts;
    }

    @Column(nullable = false, length = 2)
    public int getFreeRepairCounts() {
        return freeRepairCounts;
    }

    public void setFreeRepairCounts(int freeRepairCounts) {
        this.freeRepairCounts = freeRepairCounts;
    }

    public void setPurchasingTime(Date purchasingTime) {
        this.purchasingTime = purchasingTime;
    }

    @Column(nullable = true, length = 512)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, length = 16)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    @Column(nullable = false, length = 256)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = true, length = 32)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(nullable = true, length = 32)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(nullable = false, precision = 3, scale = 2)
    public float getCleanYearLimit() {
        return cleanYearLimit;
    }

    public void setCleanYearLimit(float cleanYearLimit) {
        this.cleanYearLimit = cleanYearLimit;
    }

    @Column(nullable = false, precision = 3, scale = 2)
    public float getRepairYearLimit() {
        return repairYearLimit;
    }

    public void setRepairYearLimit(float repairYearLimit) {
        this.repairYearLimit = repairYearLimit;
    }


}
