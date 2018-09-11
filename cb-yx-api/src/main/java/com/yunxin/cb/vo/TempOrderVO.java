package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title: 临时订单VO
 * @auther: gws
 * @date: 2018/9/10 17:25
 */
@ApiModel(value="临时订单VO",description="临时订单VO TempOrderVO")
public class TempOrderVO{

    /** 合计价格(订单总价格) */
    @ApiModelProperty(value="合计价格",name="feeToTal",example="1", required = true)
    private BigDecimal feeToTal;

    /** 默认收货地址 */
    @ApiModelProperty(value="默认收货地址",name="deliveryAddressVO",example="默认收货地址", required = true)
    private TempOrderDeliveryAddressVO deliveryAddressVO;

    /** 商家 */
    @ApiModelProperty(value="商家信息",name="sellerListVO",example="商家对象VO", required = true)
    private List<TempOrderSellerVO> tempOrderSellerListVO;

    public BigDecimal getFeeToTal() {
        return feeToTal;
    }

    public void setFeeToTal(BigDecimal feeToTal) {
        this.feeToTal = feeToTal;
    }

    public TempOrderDeliveryAddressVO getDeliveryAddressVO() {
        return deliveryAddressVO;
    }

    public void setDeliveryAddressVO(TempOrderDeliveryAddressVO deliveryAddressVO) {
        this.deliveryAddressVO = deliveryAddressVO;
    }

    public List<TempOrderSellerVO> getTempOrderSellerListVO() {
        return tempOrderSellerListVO;
    }

    public void setTempOrderSellerListVO(List<TempOrderSellerVO> tempOrderSellerListVO) {
        this.tempOrderSellerListVO = tempOrderSellerListVO;
    }

    @Override
    public String toString() {
        return "TempOrderVO{" +
                "feeToTal=" + feeToTal +
                ", deliveryAddressVO=" + deliveryAddressVO +
                ", tempOrderSellerListVO=" + tempOrderSellerListVO +
                '}';
    }
}
