package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/1/26.
 */
public enum ReturnRefundState {

    APPLY_RETURN_REFUND("申请退货退款"), WAIT_RETURNED_REFUND("待退货退款"), RETURNED_WAIT_REFUND("已退货待退款"), RETURNED_REFUND("已退货退款"), RETURN_REFUND_DENIED("拒绝退货退款"),
    APPLY_REFUND("申请退款"), WAIT_REFUND("待退款"), REFUNDED("已退款"), REFUND_DENIED("拒绝退款");

    private String name;

    private ReturnRefundState(String name) {
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
