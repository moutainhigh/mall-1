package com.yunxin.cb.mall.entity.meta;

public enum AddressType {

    HOME("家庭"), COMPANY("公司"), OTHER("其他");
    private String name;

    private AddressType(String name) {
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
