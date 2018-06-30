package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.AuditState;
import com.yunxin.cb.mall.entity.meta.BarterState;
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
public class ProductBarter implements java.io.Serializable {

    private int barterId;

    /***
     * 换货编号
     */
    private String barterCode;
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
    /***
     * 商家是否收货
     */
    private boolean receivedSellerProduct;

    /***
     * 换货数量
     */
    private int barterQuantity;
    /**
     * 换货原因
     */
    private String reason;
    /**
     * 图片路径
     */
    private String picPath;

    /**
     * 换货状态
     */
    private BarterState barterState;

    /***物流公司编码*/
    private String logisticCode;
    /**
     * 快递单号
     */
    private String courierNumber;

    /**
     * 审核状态
     */
    private AuditState auditState;

    /***
     * 收货人信息
     */
    private ConsigneeInfo consigneeInfo;
    /***
     * 审核备注
     */
    private String auditRemark;

    private int[] orderItemIds;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getBarterId() {
        return barterId;
    }

    public void setBarterId(int barterId) {
        this.barterId = barterId;
    }

    @Column(length = 32, nullable = false)
    public String getBarterCode() {
        return barterCode;
    }

    public void setBarterCode(String barterCode) {
        this.barterCode = barterCode;
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

    @Column(nullable = true, precision = 1)
    public boolean isReceivedSellerProduct() {
        return receivedSellerProduct;
    }

    public void setReceivedSellerProduct(boolean receivedSellerProduct) {
        this.receivedSellerProduct = receivedSellerProduct;
    }

    @Column(length = 11, nullable = false)
    public int getBarterQuantity() {
        return barterQuantity;
    }

    public void setBarterQuantity(int barterQuantity) {
        this.barterQuantity = barterQuantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public BarterState getBarterState() {
        return barterState;
    }

    public void setBarterState(BarterState barterState) {
        this.barterState = barterState;
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

    @Embedded
    public ConsigneeInfo getConsigneeInfo() {
        return consigneeInfo;
    }

    public void setConsigneeInfo(ConsigneeInfo consigneeInfo) {
        this.consigneeInfo = consigneeInfo;
    }

    @Transient
    public int[] getOrderItemIds() {
        return orderItemIds;
    }

    public void setOrderItemIds(int[] orderItemIds) {
        this.orderItemIds = orderItemIds;
    }


}
