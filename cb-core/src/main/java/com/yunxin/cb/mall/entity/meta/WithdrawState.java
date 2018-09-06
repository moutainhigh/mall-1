package com.yunxin.cb.mall.entity.meta;

/**
 * @title: 提现状态枚举类
 * @auther: eleven
 * @date: 2018/8/9 10:15
 */
public enum WithdrawState {

    AUDIT("审核中",0), AUDIT_NOT("审核失败",1), WAIT_GRANT("待发放",2), TRANSFER("转账中",3), FINISHED("已转账",4);
    private String name;
    private Integer value;

    private WithdrawState(String name,Integer value) {
        this.name = name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
