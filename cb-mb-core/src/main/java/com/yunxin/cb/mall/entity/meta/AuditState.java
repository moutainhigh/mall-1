package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2016/3/18.
 */
public enum AuditState {
    WAIT_AUDIT("待审核"), AUDITED("审核通过"), NOT_AUDIT("审核未通过");

    private String name;

    private AuditState(String name) {
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
