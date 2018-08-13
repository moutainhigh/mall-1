package com.yunxin.cb.mall.entity.meta;

/**
 * @title: 保险返现状态
 * @auther: eleven
 * @date: 2018/8/10 12:06
 */
public enum CashbackLogState {

    WAIT("待返现",0), FINISHED("已返现",1),FAILED("返现失败",2);
    private String name;
    private Integer value;

    private CashbackLogState(String name, Integer value) {
        this.name = name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + "("+name+")";
    }
}
