package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(value="我的购物车",description="我的购物车VO对象")
public class MyShoppingCartVO {

    /**
     * 商品数量(不计算货品数量)
     */
    @ApiModelProperty(value="商品数量",name="commodityNum",example="3", required = true)
    private Integer commodityNum;

    /**
     * 合计价格
     */
    @ApiModelProperty(value="合计价格",name="totalPrice",example="220", required = true)
    private BigDecimal totalPrice;

    @ApiModelProperty(value="我的购物车商家信息",name="我的购物车商家信息VO对象列表")
    private List<MyShoppingCartSellerVO> sellerListVO;

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<MyShoppingCartSellerVO> getSellerListVO() {
        return sellerListVO;
    }

    public void setSellerListVO(List<MyShoppingCartSellerVO> sellerListVO) {
        this.sellerListVO = sellerListVO;
    }

    @Override
    public String toString() {
        return "MyShoppingCartVO{" +
                ", commodityNum=" + commodityNum +
                ", totalPrice=" + totalPrice +
                ", sellerListVO=" + sellerListVO +
                '}';
    }
}
