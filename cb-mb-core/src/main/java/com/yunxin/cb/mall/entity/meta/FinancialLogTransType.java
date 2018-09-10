package com.yunxin.cb.mall.entity.meta;

/**
 * @title: 账单交易类型枚举
 * @auther: eleven
 * @date: 2018/8/9 17:36
 */
public enum FinancialLogTransType {
    INSURANCE_PURCHASE("保险购买"),INSURANCE_REBATE("保险返利"),
    CAR_REBATE("汽车返利"), PRODUCT_RB("商品报账"),
    GOOD_PAY("商品购买"),GOOD_RETURN("商品退货"),LOAN("借款"),
    MANUAL_REPAYMENT("手动还款"),INSURANCE_REPAYMENT("保险返利自动还款"),
    CAR_REPAYMENT("汽车返利自动还款"),PRODUCT_RB_REPAYMENT("商品报帐自动还款");

    private String name;

    private FinancialLogTransType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + "("+name+")";
    }
}
