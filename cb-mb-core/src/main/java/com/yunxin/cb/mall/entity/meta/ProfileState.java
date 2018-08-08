package com.yunxin.cb.mall.entity.meta;

public enum ProfileState {

    FINACIAL_FREE_RATE("0.1"),//提现手续费
    ;

    private String name;

    private ProfileState(String name) {
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
