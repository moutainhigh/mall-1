package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.BusinessType;
import com.yunxin.cb.mall.entity.meta.OperationType;

import java.util.Date;

public class CustomerTradingRecord {
    private Integer tradeRecordId;

    /** 客户id */
    private Integer customerId;

    /**业务类型*/
    private BusinessType businessType;

    /** 操作类型:0增加，1减少*/
    private OperationType operationType;

    /** 操作余额 */
    private Double amount;

    /** 创建时间 */
    private Date createTime;

    /** 备注 */
    private String remark;

    public Integer getTradeRecordId() {
        return tradeRecordId;
    }

    public void setTradeRecordId(Integer tradeRecordId) {
        this.tradeRecordId = tradeRecordId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}