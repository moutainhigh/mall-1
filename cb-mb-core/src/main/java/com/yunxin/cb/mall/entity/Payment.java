package com.yunxin.cb.mall.entity;

import java.util.Date;

public class Payment {
    /** 支付id */
    private Integer payId;

    /** 买家付款支付账号 */
    private String buyerAccount;

    /** 支付通道 */
    private Integer channelType;

    /** 交易完成时间 */
    private Date completeTime;

    /** 创建时间 */
    private Date createTime;

    /** 错误代码 */
    private String errorCode;

    /** 错误信息 */
    private String errorMsg;

    /** 通知时间 */
    private Date notifyTime;

    /** 操作人Id */
    private Integer operatorId;

    /** 支付金额 */
    private Double payAmount;

    /** 支付/退款发送流水号 */
    private String payFlowCode;

    /** 通知第三方支付平台 0:未通知1:通知成功(对方返回预期的响应结果)2:通知失败 */
    private Integer payNotifyState;

    /** 支付状态0:处理中1:处理成功2:处理失败3:部分成功 */
    private Integer payState;

    /** 支付类型1.支付2.退款 */
    private Integer payType;

    /** 备注 */
    private String remark;

    /** 客户id */
    private Integer customerId;

    /** 订单id */
    private Integer orderId;

    /** 批次号 */
    private String batchNo;

    /** 批次数量 */
    private Integer batchNum;

    /** 退货编号 */
    private String returnCode;

    /**订单对象*/
    private Order order;
    /**************临时变量************/
    /**
     * ip
     */
    private String userIPAddress;

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount == null ? null : buyerAccount.trim();
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayFlowCode() {
        return payFlowCode;
    }

    public void setPayFlowCode(String payFlowCode) {
        this.payFlowCode = payFlowCode == null ? null : payFlowCode.trim();
    }

    public Integer getPayNotifyState() {
        return payNotifyState;
    }

    public void setPayNotifyState(Integer payNotifyState) {
        this.payNotifyState = payNotifyState;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getUserIPAddress() {
        return userIPAddress;
    }

    public void setUserIPAddress(String userIPAddress) {
        this.userIPAddress = userIPAddress;
    }
}