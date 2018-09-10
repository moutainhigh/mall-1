package com.yunxin.cb.mall.entity.meta;

public enum CarOrderPaymentStateEnum {
    //支付状态(1未支付,2已支付,3已确认,4已设置评估门店,5已评估,6已设置二手车价格,
    // 7已付尾款,8已提车,9已取消,10退款中,11退款审核通过,12退款不通过,13确认退款)
    UNPAID("未支付"), PAID("已支付"),CONFIRMED("已确认"),SETED_ASSESS_STORE("已设置评估门店"),
    ASSESSED("已评估"),SETED_USED_CAR_PRICE("已设置二手车价格"),
    PAID_TAIL("已付尾款"),LIFT_CAR("已提车"),CANCLE("已取消"),REFUNDING("退款中"),
    REFUND_ADUITED("退款审核通过"),REFUND_NO_PASS("退款不通过"),REFUNDED("确认退款");


    private String name;

    CarOrderPaymentStateEnum(String name) {
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
