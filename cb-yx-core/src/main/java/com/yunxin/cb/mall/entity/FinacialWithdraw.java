package com.yunxin.cb.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @title: 提现表实体类
 * @auther: eleven
 * @date: 2018/8/8 15:59
 */
public class FinacialWithdraw implements Serializable {
    private static final long serialVersionUID = -8295725539307112445L;
    /**  */
    private Integer withdrawId;

    /** 用户id */
    private Integer customerId;

    /** 提现人银行卡 */
    private Integer bankId;

    /** 提现金额 */
    private BigDecimal amount;

    /** 实际提现金额 */
    private BigDecimal realAmount;

    /** 提现手续费 */
    private BigDecimal chargeFee;

    /** 状态：0.审核中 1.审核失败 2.待发放 3.转账中 4.交易完成 */
    private Integer state;

    /** 提现类型：0.报账转账 1.保险返利转账 */
    private Integer withdrawType;

    /** 审核时间 */
    private Date auditDate;

    /** 审核员 */
    private String auditOperator;

    /** 审核意见 */
    private String auditMessage;

    /** 发放时间 */
    private Date grantDate;

    /** 发放员 */
    private String grantOperator;

    /** 提现时间 */
    private Date applyDate;

    /** 修改时间 */
    private Date updateDate;

    public Integer getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(BigDecimal chargeFee) {
        this.chargeFee = chargeFee;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        this.withdrawType = withdrawType;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditOperator() {
        return auditOperator;
    }

    public void setAuditOperator(String auditOperator) {
        this.auditOperator = auditOperator == null ? null : auditOperator.trim();
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage == null ? null : auditMessage.trim();
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public String getGrantOperator() {
        return grantOperator;
    }

    public void setGrantOperator(String grantOperator) {
        this.grantOperator = grantOperator == null ? null : grantOperator.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}