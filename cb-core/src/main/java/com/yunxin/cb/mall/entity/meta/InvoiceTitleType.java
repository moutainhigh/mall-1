package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/1/27.
 */
public enum InvoiceTitleType {
    PERSONAL("个人"), COMPANY("公司");
    private String name;

    private InvoiceTitleType(String name) {
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
