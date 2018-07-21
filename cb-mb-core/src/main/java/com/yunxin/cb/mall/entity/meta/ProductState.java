package com.yunxin.cb.mall.entity.meta;

public enum ProductState {

    WAIT_AUDITED("待审核"), AUDITED("审核通过"), NOT_AUDITED("审核未通过"), DEL("删除");

    private String name;

    private ProductState(String name) {
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
