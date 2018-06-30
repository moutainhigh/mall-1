package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.CouponState;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by k001389 on 2015/1/30.
 * 优惠券
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Coupon implements java.io.Serializable {

    private int couponId;
    /**
     * 优惠券编码
     */
    private String couponCode;
    /**
     * 优惠券方案编号
     */
    private CouponSchema couponSchema;
    /**
     * 优惠券状态:0:未使用,1：已使用,2:锁定,3:作废
     */
    private CouponState couponState;
    /**
     * 领取状态:0  未领取, 1  已领取
     */
    private boolean receiveStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 使用时间
     */
    private Date usedTime;
    /**
     * 使用金额
     */
    private double usedAmount;
    /**
     * 使用会员
     */
    private Customer customer;
    /**
     * 备注
     */
    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    @Column(nullable = false, length = 32)
    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEME_ID", nullable = false)
    public CouponSchema getCouponSchema() {
        return couponSchema;
    }

    public void setCouponSchema(CouponSchema couponSchema) {
        this.couponSchema = couponSchema;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public CouponState getCouponState() {
        return couponState;
    }

    public void setCouponState(CouponState couponState) {
        this.couponState = couponState;
    }

    @Column(nullable = false, precision = 1)
    public boolean isReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(boolean receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    @Column(nullable = true, precision = 12, scale = 2)
    public double getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(double usedAmount) {
        this.usedAmount = usedAmount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
