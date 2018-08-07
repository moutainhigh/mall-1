package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 2016/4/23.
 */
public enum ReturnReason {
    RETURN_FREIGHT("退运费"), PERFORMANCE_FAULT("性能故障"), DESC_DOES_NOT_MATCH("描述不符"), FUNCTION_DOES_NOT_MATCH("功能/效果不符"),
    LESS_LEAKAGE("少件/漏发"), PACKING_GOODS_DAMAGED("包装/商品破损"), INVOICE_PROBLEM("发票问题");

    private String name;

    private ReturnReason(String name) {
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
