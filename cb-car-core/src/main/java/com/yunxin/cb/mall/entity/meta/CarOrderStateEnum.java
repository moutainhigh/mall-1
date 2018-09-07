package com.yunxin.cb.mall.entity.meta;

public enum CarOrderStateEnum {
    //订单状态(1未支付,2未确认,3未设置评估门店,4未评估,5未设置二手车价格,6未付尾款,
    // 7未提车,8交易成功.9已取消,10退款中,11退款审核通过,12退款不通过,13确认退款)
    UNPAID("未支付"), UNCONFIRMED("未确认"),NO_SET_ASSESS_STORE("未设置评估门店"),NO_ASSESS("未评估"),NO_SET_USED_CAR_PRICE("未设置二手车价格"),
    UNPAID_TAIL("未付尾款"),UN_LIFT_CAR("未提车"),FINISHED("交易成功"),CANCLE("已取消"),REFUNDING("退款中"),
    REFUND_ADUITED("退款审核通过"),REFUND_NO_PASS("退款不通过"),REFUNDED("确认退款");


    private String name;

    CarOrderStateEnum(String name) {
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
