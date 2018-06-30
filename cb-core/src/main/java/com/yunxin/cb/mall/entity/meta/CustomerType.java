package com.yunxin.cb.mall.entity.meta;

/**
 * Created by cx on 2016/3/13.
 */
public enum CustomerType {
    PLATFORM_SELF("平台账号"), QQ("qq账号");

    private String name;

    CustomerType(String name) {
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
