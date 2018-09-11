package com.yunxin.cb.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class AfterSaleServe implements Serializable {
    private static final long serialVersionUID = 2936343221354646580L;
    /** 售后服务ID */
    private Integer afterSaleServeId;

    /** 售后服务单号 */
    private Integer afterSaleServeCode;

    /** 售后服务类型 退款：0,退货：1，换货：2 */
    private Boolean afterSaleServeType;

    /** 订单ID */
    private Integer orderId;

    /** 卖家id */
    private Integer sellerId;

    /** 买家id */
    private Integer userId;

    /** 原因 */
    private String reason;

    /** 问题描述 */
    private String problemDescription;

    /** 联系人 */
    private String contactPerson;

    /** 联系电话 */
    private String contactPhone;

    /** 审核状态 0：平台审核中，1：商家审核中，2：财务审核中，3：买家待寄回，4：卖家待收货，5：卖家已收货，6：财务已打款：7：退货审核不通过 */
    private Boolean auditState;

    /** 售后服务状态 正常:0,取消:1 */
    private Boolean afterSaleServeState;

    /** 换货成功寄回快递单号 */
    private String courierNumber;

    /** 商家收货人手机号码 */
    private String phone;

    /** 商家收货人名字 */
    private String name;

    /** 商家收货人地址 */
    private String address;

    /** 寄回商家物流公司编码 */
    private String logisticCode;

    /** 同意退款金额 */
    private Float actualRefund;

    /** 退换货开始时间 */
    private Date barterStartTime;

    /** 退换货结束时间 */
    private Date barterEndTime;

    /** 创建时间（申请时间） */
    private Date createTime;

    /** 卖家处理结束时间 */
    private Date disposeEndTime;

    public Integer getAfterSaleServeId() {
        return afterSaleServeId;
    }

    public void setAfterSaleServeId(Integer afterSaleServeId) {
        this.afterSaleServeId = afterSaleServeId;
    }

    public Integer getAfterSaleServeCode() {
        return afterSaleServeCode;
    }

    public void setAfterSaleServeCode(Integer afterSaleServeCode) {
        this.afterSaleServeCode = afterSaleServeCode;
    }

    public Boolean getAfterSaleServeType() {
        return afterSaleServeType;
    }

    public void setAfterSaleServeType(Boolean afterSaleServeType) {
        this.afterSaleServeType = afterSaleServeType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription == null ? null : problemDescription.trim();
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public Boolean getAuditState() {
        return auditState;
    }

    public void setAuditState(Boolean auditState) {
        this.auditState = auditState;
    }

    public Boolean getAfterSaleServeState() {
        return afterSaleServeState;
    }

    public void setAfterSaleServeState(Boolean afterSaleServeState) {
        this.afterSaleServeState = afterSaleServeState;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber == null ? null : courierNumber.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode == null ? null : logisticCode.trim();
    }

    public Float getActualRefund() {
        return actualRefund;
    }

    public void setActualRefund(Float actualRefund) {
        this.actualRefund = actualRefund;
    }

    public Date getBarterStartTime() {
        return barterStartTime;
    }

    public void setBarterStartTime(Date barterStartTime) {
        this.barterStartTime = barterStartTime;
    }

    public Date getBarterEndTime() {
        return barterEndTime;
    }

    public void setBarterEndTime(Date barterEndTime) {
        this.barterEndTime = barterEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDisposeEndTime() {
        return disposeEndTime;
    }

    public void setDisposeEndTime(Date disposeEndTime) {
        this.disposeEndTime = disposeEndTime;
    }
}