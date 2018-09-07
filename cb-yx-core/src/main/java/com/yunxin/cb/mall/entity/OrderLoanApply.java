package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.AuditState;
import com.yunxin.cb.mall.entity.meta.LoanState;

import java.util.Date;

public class OrderLoanApply {
    /** 贷款id */
    private Integer loanId;

    /** 贷款编号 */
    private String loanCode;

    /** 客户id */
    private Integer customerId;

    /** 订单id */
    private Integer orderId;

    /** 贷款状态 0:申请贷款 1:贷款通过 2:贷款失败  */
    private LoanState loanState;

    /** 贷款金额 */
    private Double loanPrice;

    /** 审核备注 */
    private String auditRemark;

    /** 审核状态 0:待审核 1:审核通过，2：审核失败 */
    private AuditState auditState;

    /** 申请时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
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

    public LoanState getLoanState() {
        return loanState;
    }

    public void setLoanState(LoanState loanState) {
        this.loanState = loanState;
    }

    public Double getLoanPrice() {
        return loanPrice;
    }

    public void setLoanPrice(Double loanPrice) {
        this.loanPrice = loanPrice;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public AuditState getAuditState() {
        return auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}