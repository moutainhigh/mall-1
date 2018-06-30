package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 2016/4/25.
 */
public enum RefundReason {
    EMPTY_PACKAGE("空包裹"), LOGISTICS_NOT_SENT("快递/物流一直未送到"), LOGISTICS_NO_TRACE_RECORD("快递/物流无跟踪记录");

    private String name;

    private RefundReason(String name) {
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
