package com.yunxin.cb.monitor.entity.meta;

/**
 * Created by HFY on 16/3/22.
 */
public enum DeviceType {

    STEAM_BOX("蒸箱"), OVEN("烤箱"), DISINFECTION_CABINET("消毒柜"), EXHAUST_HOOD("吸油烟机");

    private String name;

    DeviceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
