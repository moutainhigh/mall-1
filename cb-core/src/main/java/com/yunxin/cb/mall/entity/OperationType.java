package com.yunxin.cb.mall.entity;

/**
 * Created by Administrator on 2016/3/18.
 */
public enum OperationType {
    ADD("+"), SUBTRACT("-");

    private String name;

    private OperationType(String name) {
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
        return name;
    }
}
