package com.yunxin.cb.mall.vo;

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
    @ApiModelProperty(value="支付方式",name="paymentType",example="0")
    private Integer paymentType;

    /**
     * 收货地址id
     */
    @ApiModelProperty(value="收货地址id",name="addressId",example="1")
    private int addressId;
    /**
     * 商家id
     */
    @ApiModelProperty(value="商家id",name="sellerId",example="1")
    private String sellerId;
    /**
     * 购买货品信息
     */
    private List<OrderConfirmProductVO> orderConfirmProductList;

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public List<OrderConfirmProductVO> getOrderConfirmProductList() {
        return orderConfirmProductList;
    }

    public void setOrderConfirmProductList(List<OrderConfirmProductVO> orderConfirmProductList) {
        this.orderConfirmProductList = orderConfirmProductList;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "OrderConfirmVO{" +
                "paymentType=" + paymentType +
                ", addressId=" + addressId +
                ", sellerId='" + sellerId + '\'' +
                ", orderConfirmProductList=" + orderConfirmProductList +
                '}';
    }
}
