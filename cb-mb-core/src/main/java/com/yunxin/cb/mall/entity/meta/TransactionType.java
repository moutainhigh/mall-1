package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2016/3/17.
 */
public enum TransactionType {
    //冻结金额类型
    INSURANCE_REBATE("保险返利"), INSURANCE_PURCHASE("保险购买"),
    //负债类型
    MANUAL_REPAYMENT("手动还款"),INSURANCE_REPAYMENT("保险返利自动还款"),
    PRODUCT_RB_REPAYMENT("商品报帐自动还款"),LOAN("借款"),
    INTEREST("借款利息"),
    //额度类型
    APPLY_LOAN("申请借款"), CANCEL_LOAN("取消借款"),
    REFUSE_LOAN("借款被拒绝");



    private String name;

    private TransactionType(String name) {
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
