/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author Aidy
 */
public enum LoanType {

    LOAN("正常贷款");

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

    public String getEnum(){
        return super.toString();
    }
}

