/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author Aidy
 */
public enum LoanType {

    INSURANCE_LOAN("保险贷"), CREDIT_LOAN("信用贷");

    private String name;

    private LoanType(String name) {
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

