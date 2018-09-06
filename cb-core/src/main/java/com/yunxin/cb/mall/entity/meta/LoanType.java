package com.yunxin.cb.mall.entity.meta;

public enum LoanType {

    CREDIT_LOAN("信用贷款");

    private String name;

    private LoanType(String name) {
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
