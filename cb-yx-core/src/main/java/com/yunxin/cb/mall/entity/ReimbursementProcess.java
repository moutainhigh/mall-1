package com.yunxin.cb.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class ReimbursementProcess implements Serializable {
    private static final long serialVersionUID = -8480433893158188796L;

    /**
     *主键
     */
    private Integer processId;

    /**
     *报账信息
     */
    private Integer reimbursementId;

    /**
     *操作人
     */
    private Integer userId;

    /**
     *备注
     */
    private String remarks;

    /**
     *操作状态
     */
    private Integer orderState;

    /**
     *操作时间
     */
    private Date createTime;

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(Integer reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
