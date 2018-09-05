package com.yunxin.cb.rb.entity.meta;

/**
 * 功能描述:返现类型枚举类
 *
 * @auther yangzhen
 * @date 2018/9/4 15:55
 */
public enum LoanRepaymentType {

    INSURANCE_REBATE_REPAYMENT(0),//保险返利还款
    COMMODITY_REIMBURESE_REPAYMENT(1),//商品报账还款
    MANUAL_REIMBURSEMENT_REPAYMENT(2),//手动还款
    CAR_REBATE_REPAYMENT(3);//汽车返利还款
    private Integer value;

    LoanRepaymentType(Integer value) {
        this.value=value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
