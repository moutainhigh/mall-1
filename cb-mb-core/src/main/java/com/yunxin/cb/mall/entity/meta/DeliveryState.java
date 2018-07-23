package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/1/26.
 */
public enum DeliveryState {
    ALREADY_PICKING("已拣货"), ALREADY_PACKED("已打包"), TRANSPORT("运输中"), DISTRIBUTION("配送中"), SEND_DELIVERY("已送达"), RECEIVED("已签收"), REJECTION("拒收");

    private String name;

    private DeliveryState(String name) {
        this.setName(name);
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
