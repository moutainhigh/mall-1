package com.yunxin.cb.mall.entity.meta;

public enum CarOrderAppPaymentStateEnum {
    //前端支付状态(1未支付,2已支付,3已确认,4已评估,5已付尾款,6交易完成,7已取消)
    UNPAID("未支付"), PAID("已支付"),CONFIRMED("已确认"),ASSESSED("已评估"),PAID_TAIL("已付尾款"),FINISHED("交易完成"),CANCLE("已取消");


    private String name;

    CarOrderAppPaymentStateEnum(String name) {
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
