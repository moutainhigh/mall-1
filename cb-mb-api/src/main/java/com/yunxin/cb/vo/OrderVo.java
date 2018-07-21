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
    /**
     * 购买数量
     */
    private Integer productNum;
    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人手机号码
     */
    private String consigneeMobile;
    /**
     * 收货人地址
     */
    private String consigneeAddress;

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

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }
}
