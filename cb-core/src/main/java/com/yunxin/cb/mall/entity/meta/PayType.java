/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author Aidy
 */
public enum PayType {

    PAYMENT("支付"), REIMBURSE("退款");

    private String name;

    private PayType(String name) {
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

