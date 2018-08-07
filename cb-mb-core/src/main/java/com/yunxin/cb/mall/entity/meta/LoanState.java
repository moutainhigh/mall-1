package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2018/7/21.
 */
public enum LoanState {
    WAIT_LOAN("申请中"), APPLY_SUCCESS("申请通过"), APPLY_FAILURE("申请失败"), CANCELED("已取消");

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
        return super.toString() + "("+name+")";
    }
}
