package com.yunxin.cb.mall.entity.meta;

public enum FiaciaLogPayType {

    VX("微信"),ALIPAY("支付宝"),
    RB("报账"),LOAN("还款");

    private String name;

    private FiaciaLogPayType(String name) {
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
