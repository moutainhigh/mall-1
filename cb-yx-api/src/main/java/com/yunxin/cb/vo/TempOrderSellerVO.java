package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title: 临时订单商家信息VO
 * @auther: gws
 * @date: 2018/9/10 17:25
 */
@ApiModel(value="临时订单商家VO",description="临时订单商家VO TempOrderSellerVO")
public class TempOrderSellerVO {

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

    /**
     * 商品数量
     */
    @ApiModelProperty(value="商品数量",name="itemNum",example="3", required = true)
    private Integer itemNum;

    /**
     * 运费
     */
    @ApiModelProperty(value="运费",name="deliveryFeeTotal",example="18", required = true)
    private BigDecimal deliveryFeeTotal;

    /**
     * 小计价格（该店铺的价格小计，包含运费）
     */
    @ApiModelProperty(value="小计价格",name="deliveryFeeTotal",example="18", required = true)
    private BigDecimal price;

    /** 购买货品信息 */
    @ApiModelProperty(value="购买货品信息",name="tempOrderItemListVO",example="货品")
    private List<TempOrderItemVO> tempOrderItemListVO;

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

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public BigDecimal getDeliveryFeeTotal() {
        return deliveryFeeTotal;
    }

    public void setDeliveryFeeTotal(BigDecimal deliveryFeeTotal) {
        this.deliveryFeeTotal = deliveryFeeTotal;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<TempOrderItemVO> getTempOrderItemListVO() {
        return tempOrderItemListVO;
    }

    public void setTempOrderItemListVO(List<TempOrderItemVO> tempOrderItemListVO) {
        this.tempOrderItemListVO = tempOrderItemListVO;
    }

    @Override
    public String toString() {
        return "TempOrderSellerVO{" +
                "sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", itemNum=" + itemNum +
                ", deliveryFeeTotal=" + deliveryFeeTotal +
                ", price=" + price +
                ", tempOrderItemListVO=" + tempOrderItemListVO +
                '}';
    }
}
