package com.yunxin.cb.mall.entity.meta;

/**
 * Created by sheh on 14-10-27.
 */
public enum DeliveryType {
    WL("物流运输"), ZT("自提"), QD("快递"), QT("其它");

    private String name;

    private DeliveryType(String name) {
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
        return super.toString() + "("+name+")";
    }
}
