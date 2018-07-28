package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.*;

import java.util.Date;
import java.util.Set;

public class Order {
    /** 订单id */
    private Integer orderId;

    /** 换货状态 */
    private BarterState barterState;

    /** 买家留言 */
    private String buyerMessage;

    /** 订单取消原因 */
    private String cancelReason;

    /** 取消时间 */
    private Date cancelTime;

    /** 市 */
    private String city;

    /** 收货人地址 */
    private String consigneeAddress;

    /** 收货人手机 */
    private String consigneeMobile;

    /** 收货人姓名 */
    private String consigneeName;

    /** 收货人固话 */
    private String consigneeTelephone;

    /** 优惠券抵用金额 */
    private Double couponsFee;

    /** 快递单号 */
    private String courierNumber;

    /** 创建时间 */
    private Date createTime;

    /** 是否包邮 */
    private Boolean delivery;

    /** 配送费用总额 */
    private Double deliveryFeeTotal;

    /** 订单配送状态 */
    private DeliveryState deliveryState;

    /** 递送方式 */
    private DeliveryType deliveryType;

    /** 优惠配送费 */
    private Double discountDeliveryFeeTotal;

    /** 优惠总金额 */
    private Double discountTotal;

    /** 区，县 */
    private String district;

    /** 状态：true 启用；false 停用 */
    private Boolean enabled;

    /** 订单付费总计 */
    private Double feeTotal;

    /** 订单完成时间 */
    private Date finishTime;

    /** 订单编号 */
    private String orderCode;

    /** 订单基本状态 */
    private OrderState orderState;

    /** 退换货产生的新订单引用原定单 */
    private String originOrderCode;

    /** 使用积分对应金额 */
    private Double payByIntegral;

    /** 支付流水号 */
    private String paymentSequence;

    /** 支付时间 */
    private Date paymentTime;

    /** 付款方式 */
    private PaymentType paymentType;

    /** 邮政编码 */
    private String postCode;

    /** 货品数量 */
    private Integer prodQuantity;

    /** 省 */
    private String province;

    /** 备注信息 */
    private String remark;

    /** 退货退款状态 */
    private ReturnRefundState returnRefundState;

    /** 用户可获积分总计 */
    private Integer scoreTotal;

    /** 订单金额 */
    private Double totalPrice;

    /** 修改时间 */
    private Date updateTime;

    /** 使用积分 */
    private Integer usedScore;

    /** 体积 单位立方米 */
    private Double volumeTotal;

    /** 重量 */
    private Double weightTotal;

    /** 买家id */
    private Integer customerId;

    /** 物流公司id */
    private Integer logisticId;

    /** 卖家id */
    private Integer sellerId;
    /**卖家信息*/
    private Seller seller;
    /**商品详情 */
    Set<OrderItem> orderItems;
    /**订单发票 */
    OrderInvoice orderInvoice;
    /**收货地址id*/
    private int addressId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BarterState getBarterState() {
        return barterState;
    }

    public void setBarterState(BarterState barterState) {
        this.barterState = barterState;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile == null ? null : consigneeMobile.trim();
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone == null ? null : consigneeTelephone.trim();
    }

    public Double getCouponsFee() {
        return couponsFee;
    }

    public void setCouponsFee(Double couponsFee) {
        this.couponsFee = couponsFee;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber == null ? null : courierNumber.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public Double getDeliveryFeeTotal() {
        return deliveryFeeTotal;
    }

    public void setDeliveryFeeTotal(Double deliveryFeeTotal) {
        this.deliveryFeeTotal = deliveryFeeTotal;
    }

    public DeliveryState getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(DeliveryState deliveryState) {
        this.deliveryState = deliveryState;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Double getDiscountDeliveryFeeTotal() {
        return discountDeliveryFeeTotal;
    }

    public void setDiscountDeliveryFeeTotal(Double discountDeliveryFeeTotal) {
        this.discountDeliveryFeeTotal = discountDeliveryFeeTotal;
    }

    public Double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(Double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Double getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(Double feeTotal) {
        this.feeTotal = feeTotal;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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

    public String getOriginOrderCode() {
        return originOrderCode;
    }

    public void setOriginOrderCode(String originOrderCode) {
        this.originOrderCode = originOrderCode == null ? null : originOrderCode.trim();
    }

    public Double getPayByIntegral() {
        return payByIntegral;
    }

    public void setPayByIntegral(Double payByIntegral) {
        this.payByIntegral = payByIntegral;
    }

    public String getPaymentSequence() {
        return paymentSequence;
    }

    public void setPaymentSequence(String paymentSequence) {
        this.paymentSequence = paymentSequence == null ? null : paymentSequence.trim();
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public Integer getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Integer prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public ReturnRefundState getReturnRefundState() {
        return returnRefundState;
    }

    public void setReturnRefundState(ReturnRefundState returnRefundState) {
        this.returnRefundState = returnRefundState;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUsedScore() {
        return usedScore;
    }

    public void setUsedScore(Integer usedScore) {
        this.usedScore = usedScore;
    }

    public Double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public Double getWeightTotal() {
        return weightTotal;
    }

    public void setWeightTotal(Double weightTotal) {
        this.weightTotal = weightTotal;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getLogisticId() {
        return logisticId;
    }

    public void setLogisticId(Integer logisticId) {
        this.logisticId = logisticId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderInvoice getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoice orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public int getOrderStateId() {
        return this.orderState.ordinal();
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

}