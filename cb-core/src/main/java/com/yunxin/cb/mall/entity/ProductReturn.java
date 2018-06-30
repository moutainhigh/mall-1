package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.AuditState;
import com.yunxin.cb.mall.entity.meta.RefundReason;
import com.yunxin.cb.mall.entity.meta.ReturnReason;
import com.yunxin.cb.mall.entity.meta.ReturnRefundState;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Administrator on 2016/3/17.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class ProductReturn implements java.io.Serializable {

    private int returnId;
    /***
     * 退货编号
     */
    private String returnCode;
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
    private Order order;
    /**
     * 购买时间
     */
    private Date purchasingTime;
    /**
     * 申请时间
     */
    private Date applyTime;
    /***
     * 处理时间
     */
    private Date disposeTime;

    private boolean refundOnly;
    /***
     * 买家是否收货
     */
    private boolean receivedBuyerProduct = true;

    /***
     * 商家是否收货
     */
    private boolean receivedSellerProduct;

    /***
     * 退货原因
     */
    private ReturnReason returnReason;

    /***
     * 退款原因
     */
    private RefundReason refundReason;
    /**
     * 退货原因
     */
    private String reasonRemark;
    /***
     * 退款金额
     */
    private double refundPrice;
    /**
     * 图片路径
     */
    private String picPath;

    /**
     * 退货退款状态
     */
    private ReturnRefundState returnRefundState;

    /**
     * 审核状态
     */
    private AuditState auditState;

    /***
     * 审核备注
     */
    private String auditRemark;

    /***物流公司编码*/
    private String logisticCode;
    /**
     * 快递单号
     */
    private String courierNumber;

    private int[] orderItemIds;

    private String remark;

    /***
     * 拒绝原因
     */
    private String rejectReason;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    @Column(length = 32, nullable = false)
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
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
    @JoinColumn(name = "ITEM_ID", nullable = false)
    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getPurchasingTime() {
        return purchasingTime;
    }

    public void setPurchasingTime(Date purchasingTime) {
        this.purchasingTime = purchasingTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }

    @Column(nullable = false, precision = 1)
    public boolean isRefundOnly() {
        return refundOnly;
    }

    public void setRefundOnly(boolean refundOnly) {
        this.refundOnly = refundOnly;
    }

    @Column(nullable = true, precision = 1)
    public boolean isReceivedBuyerProduct() {
        return receivedBuyerProduct;
    }

    public void setReceivedBuyerProduct(boolean receivedBuyerProduct) {
        this.receivedBuyerProduct = receivedBuyerProduct;
    }

    @Column(nullable = true, precision = 1)
    public boolean isReceivedSellerProduct() {
        return receivedSellerProduct;
    }

    public void setReceivedSellerProduct(boolean receivedSellerProduct) {
        this.receivedSellerProduct = receivedSellerProduct;
    }

    @Column(nullable = true, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public ReturnReason getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(ReturnReason returnReason) {
        this.returnReason = returnReason;
    }

    @Column(nullable = true, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public RefundReason getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(RefundReason refundReason) {
        this.refundReason = refundReason;
    }

    public String getReasonRemark() {
        return reasonRemark;
    }

    public void setReasonRemark(String reasonRemark) {
        this.reasonRemark = reasonRemark;
    }

    @Column(precision = 12, scale = 2)
    public double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public ReturnRefundState getReturnRefundState() {
        return returnRefundState;
    }

    public void setReturnRefundState(ReturnRefundState returnRefundState) {
        this.returnRefundState = returnRefundState;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public AuditState getAuditState() {
        return auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    @Column(length = 32, nullable = true)
    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    @Column(length = 16, nullable = true)
    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Transient
    public int[] getOrderItemIds() {
        return orderItemIds;
    }

    public void setOrderItemIds(int[] orderItemIds) {
        this.orderItemIds = orderItemIds;
    }
}
