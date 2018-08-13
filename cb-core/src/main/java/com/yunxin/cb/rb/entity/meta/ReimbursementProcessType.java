package com.yunxin.cb.rb.entity.meta;

public enum  ReimbursementProcessType {

    FINANCE_IN_APPROVAL("财务员已审批"),
    DIRECTOR_IN_APPROVAL("财务主管已审批"),
    FINANCE_NOT_PASS_THROUGH("财务员审批不通过"),
    DIRECTOR_NOT_PASS_THROUGH("财务主管审批不通过");

    private String name;
    private ReimbursementProcessType(String name) {
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
