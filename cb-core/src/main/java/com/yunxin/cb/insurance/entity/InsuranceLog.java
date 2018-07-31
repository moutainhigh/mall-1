package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 保险订单操作日志
 *
 * @author wangteng
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceLog  implements Serializable {
    /**
     * 主键
     */
    @Max(9999999999L)
    private int insuranceLogId;
    /**
     * 保单ID
     */
    private int orderId;
    /**
     * 保单编号
     */
    private String orderCode;
    /**
     * 被保人姓名
     */
    private String insuredName;
    /**
     * 被保人手机号码
     */
    private String insuredMobile;
    /**
     * 投保人姓名
     */
    private String policyholderName;
    /**
     * 投保人手机号码
     */
    private String policyholderMobile;
    /**
     * 投保金额
     */
    private int price;
    /**
     * 操作状态
     */
    private InsuranceOrderState orderState;
    /**
     * 操作人
     */
    private String createName;
    /**
     * 操作人账号
     */
    private String createOper;
    /**
     * IP地址
     */
    private String ipAddress;
    public InsuranceLog(){

    }

    public InsuranceLog(int orderId, String orderCode, String insuredName, String insuredMobile, String policyholderName, String policyholderMobile, int price, InsuranceOrderState orderState, String createName, String createOper, String ipAddress, Date createTime) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.insuredName = insuredName;
        this.insuredMobile = insuredMobile;
        this.policyholderName = policyholderName;
        this.policyholderMobile = policyholderMobile;
        this.price = price;
        this.orderState = orderState;
        this.createName = createName;
        this.createOper = createOper;
        this.ipAddress = ipAddress;
        this.createTime = createTime;
    }

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getInsuranceLogId() {
        return insuranceLogId;
    }

    public void setInsuranceLogId(int insuranceLogId) {
        this.insuranceLogId = insuranceLogId;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsuredMobile() {
        return insuredMobile;
    }

    public void setInsuredMobile(String insuredMobile) {
        this.insuredMobile = insuredMobile;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getPolicyholderName() {
        return policyholderName;
    }

    public void setPolicyholderName(String policyholderName) {
        this.policyholderName = policyholderName;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getPolicyholderMobile() {
        return policyholderMobile;
    }

    public void setPolicyholderMobile(String policyholderMobile) {
        this.policyholderMobile = policyholderMobile;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.ORDINAL)
    public InsuranceOrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(InsuranceOrderState orderState) {
        this.orderState = orderState;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getCreateOper() {
        return createOper;
    }

    public void setCreateOper(String createOper) {
        this.createOper = createOper;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
