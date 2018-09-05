package com.yunxin.cb.sms;

/**
 * Created by MWJ on 2018/1/30.
 */
public enum SendType {
    BANK("银行卡");

    private String name;

    private SendType(String name) {
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
