package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value="我的购物车商家信息",description="我的购物车商家信息VO对象")
public class MyShoppingCartSellerVO {

    /**
     * 商家id
     */
    @ApiModelProperty(value="商家id",name="sellerId",example="1", required = true)
    private Integer sellerId;
    /**
     * 商家名称
     */
    @ApiModelProperty(value="商家名称",name="sellerName",example="商家名称", required = true)
    private String sellerName;

    @ApiModelProperty(value="我的购物车货品信息",name="我的购物车货品信息VO对象列表")
    private List<MyShoppingCartProductVO> productListVO;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<MyShoppingCartProductVO> getProductListVO() {
        return productListVO;
    }

    public void setProductListVO(List<MyShoppingCartProductVO> productListVO) {
        this.productListVO = productListVO;
    }

    @Override
    public String toString() {
        return "MyShoppingCartSellerVO{" +
                "sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", productListVO=" + productListVO +
                '}';
    }
}
