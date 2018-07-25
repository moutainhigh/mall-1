package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2016/3/18.
 */
public enum BusinessType {
    BALANBCE("余额"), LOAN_EXPECTED_RETURN("贷款预期收益"), LOAN_QUOTA("贷款额度"), LOAN_ARREARS_AMOUNT("贷款欠款金额");

    private String name;

    private BusinessType(String name) {
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
