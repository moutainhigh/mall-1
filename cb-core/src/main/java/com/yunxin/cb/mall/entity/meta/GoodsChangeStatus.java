package com.yunxin.cb.mall.entity.meta;

public enum GoodsChangeStatus {
    APP_GOODSCHANGE("申请换货"), GOODSCHANGE_SUCCESS("换货成功"), GOODSCHANGE_FAIL("换货失败"), GOODSCHANGE_PASS("待仓库接收"),
    DEPOSITORYRECEIVE("仓库接收"), DELIVERGOODS("仓库发货");
    private String name;

    private GoodsChangeStatus(String name) {
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
