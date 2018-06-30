package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/1/27.
 */
public enum InvoiceType {
    NORMAL("普通"), VALUE_ADDED_TAX("增值税");
    private String name;

    private InvoiceType(String name) {
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
