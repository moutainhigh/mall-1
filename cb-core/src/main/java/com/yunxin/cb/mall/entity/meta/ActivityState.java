package com.yunxin.cb.mall.entity.meta;

public enum ActivityState {

    WAIT_TAKE_EFFECT("待生效"), TAKE_EFFECT("生效"), DISCONTINUE("中止");

    private String name;

    private ActivityState(String name) {
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
