package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value="加入购物车",description="加入购物车VO对象")
public class AddShoppingCartVO {

    /**
     * 商家id
     */
    @NotNull(message = "商家id不能为空")
    @ApiModelProperty(value="商家id",name="sellerId",example="1", required = true)
    private Integer sellerId;

    /**
     * 货品id
     */
    @NotNull(message = "货品信息货品id不能为空")
    @ApiModelProperty(value="货品id",name="productId",example="1", required = true)
    private Integer productId;

    /**
     * 货品数量
     */
    @NotNull(message = "货品数量货品id不能为空")
    @ApiModelProperty(value="货品数量",name="productNum",example="2", required = true)
    private Integer productNum;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "AddShoppingCartVO{" +
                "sellerId=" + sellerId +
                ", productId=" + productId +
                ", productNum=" + productNum +
                '}';
    }
}
