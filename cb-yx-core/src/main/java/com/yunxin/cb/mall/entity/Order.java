package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.*;

import java.util.Date;
import java.util.Set;

public class Order {
    /** 订单id */
    private Integer orderId;

    /** 订单编号 */
    private String orderCode;

    /** 订单状态（0待付款，1代发货，2待收货，3交易完成，4已取消） */
    private OrderState orderState;

    /** 支付状态（0未支付，1已支付） */
    private PaymentState paymentState;

    /** 审核状态（0待审核，1审核通过，2待审核） */
    private AuditState auditState;

    /** 收货人姓名 */
    private String consigneeName;

    /** 收货人手机 */
    private String consigneeMobile;

    /** 收货人固话 */
    private String consigneeTelephone;

    /** 邮编 */
    private String postCode;

    /** 收货人省编码 */
    private String province;

    /** 收货人市编码 */
    private String city;

    /** 收货人区，县编码 */
    private String district;

    /** 收货人地址 */
    private String consigneeAddress;

    /** 买家id */
    private Integer customerId;

    /** 卖家id */
    private Integer sellerId;

    /** 审核原因 */
    private String auditRemark;

    /** 审核时间 */
    private Date auditTime;

    /** 发货时间 */
    private Date deliverTime;

    /** 收货时间 */
    private Date collectTime;

    /** 取消时间 */
    private Date cancelTime;

    /** 取消原因 */
    private String cancelReason;

    /** 订单金额 */
    private Double totalPrice;

    /** 配送费用总额 */
    private Double deliveryFeeTotal;

    /** 订单总金额（订单金额+配送费） */
    private Double feeTotal;

    /** 订单货品总数量 */
    private Integer prodQuantity;

    /** 配送状态 */
    private Integer deliveryState;

    /** 配送类型 */
    private DeliveryType deliveryType;

    /** 快递单号 */
    private String courierNumber;

    /** 物流公司名称 */
    private String logisticName;

    /** 物流公司编码 */
    private String logisticCode;

    /** 支付时间 */
    private Date paymentTime;

    /** 支付类型 */
    private PaymentType paymentType;

    /** 订单完成时间 */
    private Date finishTime;

    /** 买家留言 */
    private String buyerMessage;

    /** 是否启用 */
    private Boolean enabled;

    /** 备注 */
    private String remark;

    /** 更新时间 */
    private Date updateTime;

    /** 创建时间 */
    private Date createTime;

    private Set<OrderItem> orderItems;

    private Integer addressId;

    private Seller seller;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }

    public AuditState getAuditState() {
        return auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile == null ? null : consigneeMobile.trim();
    }

    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone == null ? null : consigneeTelephone.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDeliveryFeeTotal() {
        return deliveryFeeTotal;
    }

    public void setDeliveryFeeTotal(Double deliveryFeeTotal) {
        this.deliveryFeeTotal = deliveryFeeTotal;
    }

    public Double getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(Double feeTotal) {
        this.feeTotal = feeTotal;
    }

    public Integer getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Integer prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public Integer getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(Integer deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber == null ? null : courierNumber.trim();
    }

    public String getLogisticName() {
        return logisticName;
    }

    public void setLogisticName(String logisticName) {
        this.logisticName = logisticName == null ? null : logisticName.trim();
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode == null ? null : logisticCode.trim();
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}