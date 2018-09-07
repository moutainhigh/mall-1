package com.yunxin.cb.mall.entity.meta;

public enum CarOrderAppPaymentStateEnum {
    //前端支付状态(1未支付,2已支付,3确认提车)
    UNPAID("未支付"), PAID("已支付"),CONFIRMED_LIFT_CAR("确认提车");


    private String name;

    CarOrderAppPaymentStateEnum(String name) {
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
