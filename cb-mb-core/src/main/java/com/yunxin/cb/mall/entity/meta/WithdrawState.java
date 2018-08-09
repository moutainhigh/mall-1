package com.yunxin.cb.mall.entity.meta;

/**
 * @title: 提现状态枚举类
 * @auther: eleven
 * @date: 2018/8/9 10:15
 */
public enum WithdrawState {

    AUDIT("审核中",1), AUDIT_NOT("审核失败",2), WAIT_GRANT("待发放",3), TRANSFER("转账中",4), FANISHED("交易完成",5);
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
