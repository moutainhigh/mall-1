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
     * 收货地址id
     */
    @ApiModelProperty(value="收货地址id",name="addressId",example="1", required = true)
    private int addressId;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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
