package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.AuditState;
import com.yunxin.cb.mall.entity.meta.ReturnReason;
import com.yunxin.cb.mall.entity.meta.ReturnRefundState;

import java.util.Date;

public class ProductReturn {
    /** 退货id */
    private Integer returnId;

    /** 申请时间 */
    private Date applyTime;

    /** 图片路径 */
    private String picPath;

    /** 购买时间 */
    private Date purchasingTime;

    /** 原因 */
    private String reason;

    /** 退货退款状态 0:申请退货退款 1:待退货退款 2:已退货待退款 3:已退货退款 4:拒绝退货退款 5:申请退款 6:待退款 7:已退款 8:拒绝退款 */
    private ReturnRefundState returnRefundState;

    /** 客户id */
    private Integer customerId;

    /** 订单id */
    private Integer orderId;

    /** 订单商品id */
    private Integer itemId;

    /** 审核备注 */
    private String auditRemark;

    /** 审核状态 */
    private AuditState AuditState;

    /** 快递单号 */
    private String courierNumber;

    /** 处理时间 */
    private Date disposeTime;

    /** 物流公司编码 */
    private String logisticCode;

    /** 退货原因 */
    private String reasonRemark;

    /** 买家是否收货 */
    private Boolean receivedBuyerProduct;

    /** 商家是否收货 */
    private Boolean receivedSellerProduct;

    /** 退款金额 */
    private Double refundPrice;

    /** 退款原因 */
    private Integer refundReason;

    /** 退货编号 */
    private String returnCode;

    /** 退货原因 */
    private ReturnReason returnReason;

    /** 首次退货 */
    private Boolean refundOnly;

    /** 备注 */
    private String remark;

    /** 拒绝原因 */
    private String rejectReason;
    /** 订单 */
    private Order order;

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public Date getPurchasingTime() {
        return purchasingTime;
    }

    public void setPurchasingTime(Date purchasingTime) {
        this.purchasingTime = purchasingTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public ReturnRefundState getReturnRefundState() {
        return returnRefundState;
    }

    public void setReturnRefundState(ReturnRefundState returnRefundState) {
        this.returnRefundState = returnRefundState;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public AuditState getAuditState() {
        return AuditState;
    }

    public void setAuditState(AuditState auditState) {
        AuditState = auditState;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber == null ? null : courierNumber.trim();
    }

    public Date getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode == null ? null : logisticCode.trim();
    }

    public String getReasonRemark() {
        return reasonRemark;
    }

    public void setReasonRemark(String reasonRemark) {
        this.reasonRemark = reasonRemark == null ? null : reasonRemark.trim();
    }

    public Boolean getReceivedBuyerProduct() {
        return receivedBuyerProduct;
    }

    public void setReceivedBuyerProduct(Boolean receivedBuyerProduct) {
        this.receivedBuyerProduct = receivedBuyerProduct;
    }

    public Boolean getReceivedSellerProduct() {
        return receivedSellerProduct;
    }

    public void setReceivedSellerProduct(Boolean receivedSellerProduct) {
        this.receivedSellerProduct = receivedSellerProduct;
    }

    public Double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(Double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public Integer getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(Integer refundReason) {
        this.refundReason = refundReason;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public ReturnReason getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(ReturnReason returnReason) {
        this.returnReason = returnReason;
    }

    public Boolean getRefundOnly() {
        return refundOnly;
    }

    public void setRefundOnly(Boolean refundOnly) {
        this.refundOnly = refundOnly;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}