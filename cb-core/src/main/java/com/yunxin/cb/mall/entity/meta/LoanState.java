package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2018/7/21.
 */
public enum LoanState {
    WAIT_AUDIT("待审核"), AUDIT_PASS("审核通过"), AUDIT_REFUSE("审核不通过"), CANCELED("已取消"), TRANSFERRED("已转账");

    private String name;

    private LoanState(String name) {
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
