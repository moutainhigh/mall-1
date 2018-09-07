package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.PaymentType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

//import org.hibernate.validator.constraints.NotBlank;

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
    @NotNull(message = "支付方式不能为空")
    @ApiModelProperty(value="支付方式",name="paymentType",example="FULL_SECTION", required = true)
    private PaymentType paymentType;

     /**
     * 收货人姓名
     */
    @NotBlank(message = "联系人不能为空")
    @ApiModelProperty(value="联系人",name="consigneeName",example="张三", required = true)
    private String consigneeName;

    /**
     * 收货人手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(1)\\d{10}$", message = "请输入正确得手机格式")
    @ApiModelProperty(value="联系人手机号",name="consigneeMobile",example="13856953362", required = true)
    private String consigneeMobile;

    /**
     * 自提地址
     */
    @NotBlank(message = "自提地址不能为空")
    @ApiModelProperty(value="自提地址",name="consigneeAddress",example="深圳市", required = true)
    private String consigneeAddress;
    /**
     * 商家id
     */
    @NotBlank(message = "商家id不能为空")
    @ApiModelProperty(value="商家id",name="sellerId",example="1", required = true)
    private String sellerId;
    /**
     * 购买货品信息
     */
    @Valid
    @NotNull(message = "货品信息不能为空")
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
