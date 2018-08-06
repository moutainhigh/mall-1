package com.yunxin.cb.mall.entity.meta;

public enum OrderState {
    PENDING_PAYMENT("待付款"), PAID_PAYMENT("已付款"), OUT_STOCK("待收货"), RECEIVED("已收货"), REFUSE("拒签收"),
    RETURN_GOODS("退货"), CHANGE_GOODS("换货"), CANCELED("已取消"), TIMEOUT("超时"), WAIT_EVALUATE("待评价"), EVALUATED("已评价"), SUCCESS("交易成功");

    private String name;

    private OrderState(String name) {
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
