/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.ChannelType;
import com.yunxin.cb.mall.entity.meta.PayNotifyState;
import com.yunxin.cb.mall.entity.meta.PayState;
import com.yunxin.cb.mall.entity.meta.PayType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Aidy
 * 支付
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Payment {


    private int payId;

    /**
     * 支付/退款发送流水号
     */
    private String payFlowCode;
    /***
     * 退货编号
     */
    private String returnCode;
    /**
     * 支付通道
     */
    private ChannelType channelType;
    /**
     * 订单
     */
    private Order order;
    /**
     * 客户
     */
    private Customer customer;
    /**
     * 支付金额
     */
    private double payAmount;
    /**
     * 错误代码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 通知第三方支付平台 0:未通知1:通知成功(对方返回预期的响应结果)2:通知失败
     */
    private PayNotifyState payNotifyState;
    /**
     * 0:处理中1:处理成功2:处理失败3:部分成功
     */
    private PayState payState;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 通知时间
     */
    private Date notifyTime;
    /**
     * 交易完成时间
     */
    private Date completeTime;
    /**
     * 交易类型:1.支付2.退款
     */
    private PayType payType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作人Id
     */
    private int operatorId;
    /**
     * 买家付款支付账号
     */
    private String buyer_account;

    /**************临时变量************/
    /**
     * 操作人Id
     */
    private String userIPAddress;
    /**
     * 商品路径
     */
    private String commodityUrl;

    private String batchNo;

    private int batchNum;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    @Column(nullable = false, length = 64)
    public String getPayFlowCode() {
        return payFlowCode;
    }

    public void setPayFlowCode(String payFlowCode) {
        this.payFlowCode = payFlowCode;
    }


    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    @Transient
    public String getChannelTypeName() {
        return channelType.getName();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = true)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    @Column(nullable = true, length = 32)
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Column(nullable = true, length = 255)
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Column(nullable = true, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public PayNotifyState getPayNotifyState() {
        return payNotifyState;
    }

    public void setPayNotifyState(PayNotifyState payNotifyState) {
        this.payNotifyState = payNotifyState;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public PayState getPayState() {
        return payState;
    }

    public void setPayState(PayState payState) {
        this.payState = payState;
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
    @Column(nullable = true, length = 7)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    @Column(nullable = true, precision = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public String getUserIPAddress() {
        return userIPAddress;
    }

    public void setUserIPAddress(String userIPAddress) {
        this.userIPAddress = userIPAddress;
    }

    @Transient
    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl = commodityUrl;
    }

    @Column(nullable = false, precision = 12)
    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    @Column(nullable = true, length = 64)
    public String getBuyer_account() {
        return buyer_account;
    }

    public void setBuyer_account(String buyer_account) {
        this.buyer_account = buyer_account;
    }

    @Column(length = 32)
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Column(nullable = true, length = 32)
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    @Column(nullable = true, precision = 4)
    public int getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(int batchNum) {
        this.batchNum = batchNum;
    }
}
