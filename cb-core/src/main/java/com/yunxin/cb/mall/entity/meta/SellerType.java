package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/1/27.
 */
public enum SellerType {
    SELLER("商家"), SELF_OPERATION("平台自营");

    private String name;

    private SellerType(String name) {
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
