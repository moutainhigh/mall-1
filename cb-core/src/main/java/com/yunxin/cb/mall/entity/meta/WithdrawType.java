package com.yunxin.cb.mall.entity.meta;

/**
 * @title: 提现类型枚举类
 * @auther: eleven
 * @date: 2018/8/9 10:15
 */
public enum WithdrawType {

    BZ("报账转账",0), BX("保险返利转账",1),CAR("汽车返利转账",2);
    private String name;
    private Integer value;

    private WithdrawType(String name,Integer value) {
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
        return super.toString() + "("+name+")";
    }
    
}
