package com.yunxin.cb.mall.entity.meta;

public enum RepaymentType {
    BANK_CARD("银行卡"),WALLET("钱包");
    private String name;

    RepaymentType(String name) {
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
