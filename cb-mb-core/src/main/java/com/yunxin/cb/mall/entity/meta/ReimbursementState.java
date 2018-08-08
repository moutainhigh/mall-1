package com.yunxin.cb.mall.entity.meta;
/**
 * 报账状态
 */
public enum ReimbursementState {
    FINANCE_IN_APPROVAL("财务员审批中"),DIRECTOR_IN_APPROVAL("财务主管审批中"),CASHIER_IN_APPROVAL("出纳审批中"),ALREADY_TO_ACCOUNT("已到账"),NOT_PASS_THROUGH("审批不通过"),CANCEL_REIMBURSEMENT("取消报账");
    private String name;
    private ReimbursementState(String name) {
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
