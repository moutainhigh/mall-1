package com.yunxin.cb.mall.entity.meta;

public enum ActivityType {
    TG("团购");

    private String name;

    ActivityType(String name) {
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
