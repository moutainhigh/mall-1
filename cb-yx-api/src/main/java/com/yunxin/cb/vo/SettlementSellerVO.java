package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value="加入购物车商家信息",description="加入购物车商家信息VO对象")
public class SettlementSellerVO {

    /**
     * 商家id
     */
    @NotNull(message = "商家id不能为空")
    @ApiModelProperty(value="商家id",name="sellerId",example="1", required = true)
    private Integer sellerId;

    /**
     * 货品信息
     */
    @NotNull(message = "购物车货品信息不能为空")
    @ApiModelProperty(value="货品信息",name="productListVO", required = true)
    private List<SettlementProductVO> productListVO;

    /**
     * 买家留言
     */
    @ApiModelProperty(value="买家留言",name="buyerMessage",example="短一点", required = false)
    private String buyerMessage;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public List<SettlementProductVO> getProductListVO() {
        return productListVO;
    }

    public void setProductListVO(List<SettlementProductVO> productListVO) {
        this.productListVO = productListVO;
    }

    @Override
    public String toString() {
        return "SettlementSellerVO{" +
                "sellerId='" + sellerId + '\'' +
                ", productListVO=" + productListVO +
                '}';
    }
}
