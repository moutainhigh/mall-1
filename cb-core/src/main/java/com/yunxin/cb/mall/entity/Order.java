package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@JsonAutoDetect
@Entity
@Table(name = "order_form")
@DynamicInsert
@DynamicUpdate
public class Order implements java.io.Serializable {


    private int orderId;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 买家编号
     */
    private Customer customer;
    /**
     * 卖家编号
     */
    private Seller seller;
    /**
     * 订单金额
     */
    private double totalPrice;
    /**
     * 优惠总金额
     */
    private double discountTotal;
    /**
     * 配送费用总额
     */
    private double deliveryFeeTotal;
    /**
     * 优惠配送费
     */
    private double discountDeliveryFeeTotal;
    /**
     * 使用积分
     */
    private int usedScore;
    /**
     * 使用积分对应金额
     */
    private double payByIntegral;
    /**
     * 订单付费总计
     */
    private double feeTotal;
    /**
     * 用户可获积分总计
     */
    private int scoreTotal;
    /**
     * 付款方式
     */
    private PaymentType paymentType;
    /**
     * 支付时间
     */
    private Date paymentTime;
    /**
     * 递送方式
     */
    private DeliveryType deliveryType;
    /**
     * 订单配送状态
     */
    private DeliveryState deliveryState;

    /**
     * 订单基本状态
     */
    private OrderState orderState;

    /***
     * 换货状态
     */
    private BarterState barterState;
    /**
     * 退货退款状态
     */
    private ReturnRefundState returnRefundState;
    /**
     * 货品数量
     */
    private int prodQuantity;
    /**
     * 重量
     */
    private double weightTotal;
    /**
     * 体积 单位立方米
     */
    private double volumeTotal;
    /**
     * 退换货产生的新订单引用原定单
     */
    private String originOrderCode;
    /**
     * 买家留言
     */
    private String buyerMessage;
    /**
     * 支付流水号
     */
    private String paymentSequence;
    /**
     * 订单取消原因*
     */
    private String cancelReason;
    /**
     * 优惠券抵用金额
     */
    private double couponsFee;
    /**
     * 是否包邮
     */
    private boolean delivery;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 取消时间
     */
    private Date cancelTime;
    /**
     * 订单完成时间
     */
    private Date finishTime;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 物流公司
     */
    private Logistic logistic;
    /**
     * 快递单号
     */
    private String courierNumber;

    /**
     * 发货时间
     */
    private Date deliverTime;

    /**
     * 收货时间
     */
    private Date collectTime;

    /*********************收货人信息*****************************/
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
     * 审核状态
     */
    private AuditState auditState;

    /***
     * 审核备注
     */
    private String auditRemark;

    /** 审核时间 */
    private Date auditTime;

    private List<OrderItem> orderItems = new ArrayList<>();

    private Set<OrderInvoice> orderInvoices = new HashSet<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(length = 255)
    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    @Column(length = 255)
    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public double getCouponsFee() {
        return couponsFee;
    }

    public void setCouponsFee(double couponsFee) {
        this.couponsFee = couponsFee;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(nullable = false, precision = 1)
    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public double getDeliveryFeeTotal() {
        return deliveryFeeTotal;
    }

    public void setDeliveryFeeTotal(double deliveryFeeTotal) {
        this.deliveryFeeTotal = deliveryFeeTotal;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public DeliveryState getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(DeliveryState deliveryState) {
        this.deliveryState = deliveryState;
    }

    @Column(precision = 12, scale = 2)
    public double getDiscountDeliveryFeeTotal() {
        return discountDeliveryFeeTotal;
    }

    public void setDiscountDeliveryFeeTotal(double discountDeliveryFeeTotal) {
        this.discountDeliveryFeeTotal = discountDeliveryFeeTotal;
    }

    @Column(precision = 12, scale = 2)
    public double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(double discountTotal) {
        this.discountTotal = discountTotal;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(precision = 12, scale = 2)
    public double getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(double feeTotal) {
        this.feeTotal = feeTotal;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public BarterState getBarterState() {
        return barterState;
    }

    public void setBarterState(BarterState barterState) {
        this.barterState = barterState;
    }

    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public ReturnRefundState getReturnRefundState() {
        return returnRefundState;
    }

    public void setReturnRefundState(ReturnRefundState returnRefundState) {
        this.returnRefundState = returnRefundState;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "order")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "order")
    public Set<OrderInvoice> getOrderInvoices() {
        return orderInvoices;
    }

    public void setOrderInvoices(Set<OrderInvoice> orderInvoices) {
        this.orderInvoices = orderInvoices;
    }

    @Column(nullable = false, length = 32)
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderNo) {
        this.orderCode = orderNo;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    @Column(length = 32)
    public String getOriginOrderCode() {
        return originOrderCode;
    }

    public void setOriginOrderCode(String originalOrderNo) {
        this.originOrderCode = originalOrderNo;
    }

    @Column(precision = 12, scale = 2)
    public double getPayByIntegral() {
        return payByIntegral;
    }

    public void setPayByIntegral(double payByIntegral) {
        this.payByIntegral = payByIntegral;
    }

    @Column(length = 32)
    public String getPaymentSequence() {
        return paymentSequence;
    }

    public void setPaymentSequence(String paymentSequence) {
        this.paymentSequence = paymentSequence;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double priceTotal) {
        this.totalPrice = priceTotal;
    }

    @Column(nullable = false, precision = 12)
    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, precision = 12)
    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID", nullable = true)
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(precision = 12)
    public int getUsedScore() {
        return usedScore;
    }

    public void setUsedScore(int usedScore) {
        this.usedScore = usedScore;
    }

    @Column(precision = 12, scale = 2)
    public double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    @Column(precision = 12, scale = 2)
    public double getWeightTotal() {
        return weightTotal;
    }

    public void setWeightTotal(double weightTotal) {
        this.weightTotal = weightTotal;
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

    @Column(length = 16, nullable = true)
    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGISTIC_ID", nullable = true)
    public Logistic getLogistic() {
        return logistic;
    }

    public void setLogistic(Logistic logistic) {
        this.logistic = logistic;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public void setDeliveryAddress(DeliveryAddress address) {
        consigneeName = address.getConsigneeName();
        /**省*/
        province = address.getProvince();
        /**市*/
        city = address.getCity();
        /**区，县*/
        district = address.getDistrict();
        /**收货人地址*/
        consigneeAddress = address.getConsigneeAddress();
        /**邮政编码*/
        postCode = address.getPostCode();
        /**固话*/
        consigneeTelephone = address.getConsigneeTelephone();
        /**手机*/
        consigneeMobile = address.getConsigneeMobile();
    }
}
