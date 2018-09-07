package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.WithdrawState;
import com.yunxin.cb.mall.entity.meta.WithdrawType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @title: 提现表实体类
 * @auther: eleven
 * @date: 2018/8/8 15:59
 */
public class FinancialWithdraw implements Serializable {

    private static final long serialVersionUID = -8295725539307112445L;
    /**  */
    private Integer withdrawId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 提现人银行卡
     */
    private Integer bankId;

    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 实际提现金额
     */
    private BigDecimal realAmount;

    /**
     * 提现手续费
     */
    private BigDecimal chargeFee;

    /**
     * 状态：0.审核中 1.审核失败 2.待发放 3.转账中 4.交易完成
     */
    private WithdrawState state;

    /**
     * 提现类型：0.报账转账 1.保险返利转账
     */
    private WithdrawType withdrawType;

    /**
     * 审核时间
     */
    private LocalDateTime auditDate;

    /**
     * 审核员
     */
    private String auditOperator;

    /**
     * 审核意见
     */
    private String auditMessage;

    /**
     * 发放时间
     */
    private LocalDateTime grantDate;

    /**
     * 发放员
     */
    private String grantOperator;

    /**
     * 提现时间
     */
    private LocalDateTime applyDate;

    /**
     * 修改时间
     */
    private LocalDateTime updateDate;

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

    public WithdrawState getState() {
        return state;
    }

    public void setState(WithdrawState state) {
        this.state = state;
    }

    public WithdrawType getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(WithdrawType withdrawType) {
        this.withdrawType = withdrawType;
    }

    public LocalDateTime getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(LocalDateTime auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditOperator() {
        return auditOperator;
    }

    public void setAuditOperator(String auditOperator) {
        this.auditOperator = auditOperator;
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    public LocalDateTime getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(LocalDateTime grantDate) {
        this.grantDate = grantDate;
    }

    public String getGrantOperator() {
        return grantOperator;
    }

    public void setGrantOperator(String grantOperator) {
        this.grantOperator = grantOperator;
    }

    public LocalDateTime getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDateTime applyDate) {
        this.applyDate = applyDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setWithdrawTypeOnPayment(FinancialLoanRepayment.Type type) {
        if (FinancialLoanRepayment.Type.INSURANCE_REPAYMENT.equals(type)) {
            this.withdrawType = WithdrawType.BX;
        }else if (FinancialLoanRepayment.Type.PRODUCT_RB_REPAYMENT.equals(type)) {
            this.withdrawType = WithdrawType.BZ;
        }else if (FinancialLoanRepayment.Type.CAR_REPAYMENT.equals(type)) {
            this.withdrawType = WithdrawType.CAR;
        }
    }
}