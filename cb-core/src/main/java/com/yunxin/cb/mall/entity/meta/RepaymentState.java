package com.yunxin.cb.mall.entity.meta;

public enum RepaymentState {

    NON_REPAYMENT("未还款"), OVERDUE("已逾期"), APPLY_REIMBURSEMENT("已还款");

    private String name;

    private RepaymentState(String name) {
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
