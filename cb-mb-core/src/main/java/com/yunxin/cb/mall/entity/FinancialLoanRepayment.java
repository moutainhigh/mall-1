package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FinancialLoanRepayment {

    /**
     * 还款类型
     **/
    public enum Type {

        INSURANCE_REPAYMENT("保险返利自动还款"),
        PRODUCT_RB_REPAYMENT("商品报帐自动还款"),
        MANUAL_REPAYMENT("手动还款"),
        CAR_REPAYMENT("汽车返利自动还款"),;

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**  */
    private Integer repaymentId;

    /**  */
    private Integer customerId;

    /**  */
    private Integer loanId;

    /**
     * 还款金额
     */
    private BigDecimal repayAmount;

    /**
     * 还款本金
     */
    private BigDecimal repayCapital;

    /**
     * 还款利息
     */
    private BigDecimal repayInterest;

    /**
     * 还款类型
     */
    private FinancialLoanRepayment.Type type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Integer getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(Integer repaymentId) {
        this.repaymentId = repaymentId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public BigDecimal getRepayCapital() {
        return repayCapital;
    }

    public void setRepayCapital(BigDecimal repayCapital) {
        this.repayCapital = repayCapital;
    }

    public BigDecimal getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}