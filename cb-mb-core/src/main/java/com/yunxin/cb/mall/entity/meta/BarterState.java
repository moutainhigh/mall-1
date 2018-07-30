package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2016/3/17.
 */
public enum BarterState {
    APPLY_BARTER("申请换货"), WAIT_BARTER("待换货"), BARTER_SUCCESS("换货成功"), BARTER_FAIL("换货失败"), BARTER_DENIED("拒绝换货");
    private String name;

    private BarterState(String name) {
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
