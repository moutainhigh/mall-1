package com.yunxin.cb.mall.entity.meta;

/**
 * @Author chenpeng
 * @Description 还款状态
 * @Date 2018/9/4 11:20
 **/
public enum RepaymentState {
    
    NON_REPAYMENT("未还款"), OVERDUE_REPAYMENT("已逾期"), ALREADY_REPAYMENT("已还款");

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
        return super.toString() + "("+name+")";
    }
}
