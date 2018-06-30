package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/9/22.
 */
public enum Unit {
    CATTY("斤"), PIECE("件"), BOTTLE("瓶"), SINGLE("个"), OTHER("其他");

    private String name;

    private Unit(String name) {
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
