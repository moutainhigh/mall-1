package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2016/3/17.
 */
public enum CapitalType {
    ADD("+"), SUBTRACT("-");
    private String name;

    private CapitalType(String name) {
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
