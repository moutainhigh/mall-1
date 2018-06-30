package com.yunxin.cb.mall.entity.meta;

public enum GoodsState {

    IN_STOCK("入库"), OUT_STOCK("出库"), PRE_OCCUPIED_STOCK("预占");

    private String name;

    private GoodsState(String name) {
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
