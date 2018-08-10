package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;

public class FinacialLoanConfig {
    /**  */
    private Integer loanConfigId;

    /** 还款期数 */
    private Integer term;

    /** 贷款利率 */
    private BigDecimal interestRate;

    /** 贷款类型：1.信用贷款，2.预期收益贷 */
//    private LoanType type;

    /** 贷款产品标题 */
    private String title;

    /** 产品描述 */
    private String remark;

    public Integer getLoanConfigId() {
        return loanConfigId;
    }

    public void setLoanConfigId(Integer loanConfigId) {
        this.loanConfigId = loanConfigId;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

//    public LoanType getType() {
//        return type;
//    }
//
//    public void setType(LoanType type) {
//        this.type = type;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}