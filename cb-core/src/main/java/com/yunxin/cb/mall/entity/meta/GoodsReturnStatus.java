package com.yunxin.cb.mall.entity.meta;

public enum GoodsReturnStatus {
    APP_GOODSRETURN("申请退货"), GOODSRETURN_SUCCESS("退货成功"), GOODSRETURN_FAIL("退货失败"), WAITREFUND("等待退款"), WAINTDEPOSITORY("待仓库接收");

    private String name;

    private GoodsReturnStatus(String name) {
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
