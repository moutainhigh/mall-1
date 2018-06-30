package com.yunxin.cb.mall.entity.meta;

/**
 * Created by chenxing on 16/3/16.
 */
public enum ClientType {
    PC("PC网页端"), PAD("平板"), MOBILE("手机");

    private String name;


    ClientType(String name) {
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
        return this.name;
    }
}
