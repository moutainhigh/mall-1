package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @title: 修改购物车VO
 * @auther: gws
 * @date: 2018/9/10 17:25
 */
@ApiModel(value="修改购物车",description="修改购物车VO对象")
public class UpdateShoppingCartVO {

    /**
     * 购物车id
     */
    @ApiModelProperty(value="购物车id",name="cartId",example="1", required = true)
    private Integer cartId;

    /**
     * 货品数量
     */
    @NotNull(message = "货品数量货品id不能为空")
    @ApiModelProperty(value="货品数量",name="productNum",example="2", required = true)
    private Integer productNum;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "UpdateShoppingCartVO{" +
                "cartId=" + cartId +
                ", productNum=" + productNum +
                '}';
    }
}
