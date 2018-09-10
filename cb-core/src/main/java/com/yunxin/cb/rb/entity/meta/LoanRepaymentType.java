package com.yunxin.cb.rb.entity.meta;

/**
 * 功能描述:返现类型枚举类
 *
 * @auther yangzhen
 * @date 2018/9/4 15:55
 */
public enum LoanRepaymentType {

    INSURANCE_REBATE_REPAYMENT("保险返利自动还款","保险返利"),
    COMMODITY_REIMBURSEMENT_REPAYMENT("商品报帐自动还款","商品报帐"),
    MANUAL_REIMBURSEMENT_REPAYMENT("手动还款","手动还款"),
    CAR_REBATE_REPAYMENT("汽车返利自动还款","汽车返利");

    private String name;

    private String shortType;

    LoanRepaymentType(String name, String shortType) {
        this.name = name;
        this.shortType = shortType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortType() {
        return shortType;
    }

    public void setShortType(String shortType) {
        this.shortType = shortType;
    }

    @Override
    public String toString() {
        return super.toString() + "("+name+")";
    }

}
