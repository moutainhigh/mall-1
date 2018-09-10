package com.yunxin.cb.mall.entity.meta;

public enum PublishState {

    WAIT_UP_SHELVES("待上架"), UP_SHELVES("上架"), DOWN_SHELVES("下架");

    private String name;

    private PublishState(String name) {
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
