package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.PaymentType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
* @author gws
* @date 2018/7/24 19:59
* @param 
* @return
*/
@ApiModel(value="商城订单确认",description="商城订单确认VO对象")
public class OrderConfirmVO implements java.io.Serializable{

    /**
     * 支付方式
     */
    @ApiModelProperty(value="支付方式",name="paymentType",example="FULL_SECTION", required = true)
    private PaymentType paymentType;

     /**
     * 收货人姓名
     */
    @ApiModelProperty(value="收货人姓名",name="consigneeName",example="张三", required = true)
    private String consigneeName;

    /**
     * 收货人手机号
     */
    @ApiModelProperty(value="收货人手机号",name="consigneeMobile",example="13856953362", required = true)
    private String consigneeMobile;

    /**
     * 自提地址
     */
    @ApiModelProperty(value="自提地址",name="consigneeAddress",example="深圳市", required = true)
    private String consigneeAddress;
    /**
     * 商家id
     */
    @ApiModelProperty(value="商家id",name="sellerId",example="1", required = true)
    private String sellerId;
    /**
     * 购买货品信息
     */
    private List<OrderConfirmProductVO> orderConfirmProductList;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<OrderConfirmProductVO> getOrderConfirmProductList() {
        return orderConfirmProductList;
    }

    public void setOrderConfirmProductList(List<OrderConfirmProductVO> orderConfirmProductList) {
        this.orderConfirmProductList = orderConfirmProductList;
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

    @Override
    public String toString() {
        return "OrderConfirmVO{" +
                "paymentType=" + paymentType +
                ", consigneeName='" + consigneeName + '\'' +
                ", consigneeMobile='" + consigneeMobile + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", orderConfirmProductList=" + orderConfirmProductList +
                '}';
    }
}
