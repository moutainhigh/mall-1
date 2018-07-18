package com.yunxin.cb.vo;

public class OrderVo {

    /**
     * 货品id
     */
    private Integer productId;
    /**
     * 支付方式
     */
    private Integer paymentType;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
}
