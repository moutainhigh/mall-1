package com.yunxin.cb.mall.entity.meta;

public enum ReimbursementQueryState {

    CAN_REIMBURSEMENT("可报账"),CANNOT_REIMBURSEMENT("不可报账");
    private String name;
    private ReimbursementQueryState(String name) {
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
