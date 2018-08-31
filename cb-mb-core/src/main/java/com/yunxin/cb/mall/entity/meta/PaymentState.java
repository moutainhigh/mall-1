package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/1/26.
 */
public enum PaymentState {
    TO_BE_PAID("待支付"), PART_PAID("部分支付"), SUCCESS_PAID("支付成功"), FAIL_PAID("支付失败");

    private String name;

    private PaymentState(String name) {
        this.setName(name);
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
