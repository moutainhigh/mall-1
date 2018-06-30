package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.GoodsReturnStatus;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 退货
 */
@JsonAutoDetect
@Entity
@Table(name = "goods_return")
@DynamicInsert
@DynamicUpdate
public class GoodsReturn implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */

    private int goodsReturnId;


    /**
     * 客户
     */

    private Customer customer;
    /**
     * 订单详情
     */

    private OrderItem orderItem;
    /**
     * 订单号
     */

    private String orderCode;
    /**
     * 购买时间
     */

    private Date purchasingTime;
    /**
     * 申请时间
     */

    private Date applicationTime;
    /**
     * 退货原因
     */

    private String reason;
    /**
     * 图片路径
     */

    private String picPath;
    /**
     * 备注 : 问题描述
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


    private GoodsReturnStatus status;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getGoodsReturnId() {
        return goodsReturnId;
    }

    public void setGoodsReturnId(int goodsReturnId) {
        this.goodsReturnId = goodsReturnId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_DETAIL_ID", nullable = false)
    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    @Column(nullable = false, length = 32)
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getPurchasingTime() {
        return purchasingTime;
    }

    public void setPurchasingTime(Date purchasingTime) {
        this.purchasingTime = purchasingTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    @Column(nullable = true, length = 256)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(nullable = true, length = 256)
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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

    @Column(nullable = true, length = 16)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(nullable = true, length = 16)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public GoodsReturnStatus getStatus() {
        return status;
    }

    public void setStatus(GoodsReturnStatus status) {
        this.status = status;
    }

    @Transient
    public String getStatusName() {
        if (status != null) {
            return status.toString();
        } else {
            return "";
        }
    }

}
