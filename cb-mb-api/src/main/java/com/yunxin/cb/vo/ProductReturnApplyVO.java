package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @author gws
* @date 2018/7/24 19:59
* @param 
* @return 
*/
@ApiModel(value="售后退款申请",description="售后退款申请VO对象")
public class ProductReturnApplyVO implements java.io.Serializable{

    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id",name="orderId",example="1")
    private Integer orderId;
    /**
     * 订单商品id
     */
    @ApiModelProperty(value="订单商品id",name="itemId",example="1")
    private Integer itemId;
    /**
     * 退货原因
     */
    @ApiModelProperty(value="退货原因",name="returnReason",example="我不想买了")
    private String returnReason;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    @Override
    public String toString() {
        return "ProductReturnApplyVO{" +
                "orderId=" + orderId +
                ", itemId=" + itemId +
                ", returnReason='" + returnReason + '\'' +
                '}';
    }
}
