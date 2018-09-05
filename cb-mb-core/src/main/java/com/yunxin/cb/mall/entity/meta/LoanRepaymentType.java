package com.yunxin.cb.mall.entity.meta;

/**
 * 功能描述:返现类型枚举类
 *
 * @auther yangzhen
 * @date 2018/9/4 15:55
 */
public enum LoanRepaymentType {

    INSURANCE_REBATE_REPAYMENT("保险返利还款",0),
    COMMODITY_REIMBURESE_REPAYMENT("商品报账还款",1),
    MANUAL_REIMBURSEMENT_REPAYMENT("手动还款",2),
    CAR_REBATE_REPAYMENT("汽车返利还款",3);
    private String name;
    private Integer value;

    LoanRepaymentType(String name, Integer value) {
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
