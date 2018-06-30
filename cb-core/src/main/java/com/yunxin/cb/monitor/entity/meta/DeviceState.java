package com.yunxin.cb.monitor.entity.meta;


public enum DeviceState {

    WAIT_WORK("待工作"), WORKING("工作中"), WORK_END("工作结束"), MALFUNCTION("工作故障");

    private String name;

    DeviceState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
