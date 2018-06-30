package com.yunxin.cb.mall.entity.meta;

public enum OrderItemState {

    PENDING_PAYMENT("待付款"), PAID("已付款"), IN_STOCK("待出库"), OUT_STOCK("已出库"), UPON_RECEIPT("待签收"), RECEIVED("已签收"), RETURN_GOODS("退货");

    private String name;

    private OrderItemState(String name) {
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
