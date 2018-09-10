package com.yunxin.cb.mall.entity.meta;

public enum CarAppOrderStateEnum {
    //前端订单状态(1未支付,2待收货,3已完成,4已取消)
    UNPAID("未支付"), WAIT_RECEIVED("待收货"),FINISHED("已完成"),CANCLE("已取消");


    private String name;

    CarAppOrderStateEnum(String name) {
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
