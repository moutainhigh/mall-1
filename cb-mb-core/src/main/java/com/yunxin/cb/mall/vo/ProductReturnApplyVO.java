package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.ReturnReason;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "订单id不能为空")
    @ApiModelProperty(value="订单id",name="orderId",example="1", required = true)
    private Integer orderId;
    /**
     * 订单商品id
     */
    @ApiModelProperty(value="订单商品id",name="itemId",example="1")
    private Integer[] itemIds;
    /**
     * 退货原因
     */
    @NotNull(message = "退货原因不能为空")
    @ApiModelProperty(value="退货原因",name="returnReason",example="我不想买了", required = true)
    private ReturnReason returnReason;

    /** 原因 */
    @NotNull(message = "原因不能为空")
    @ApiModelProperty(value="原因",name="reason",example="我不想买了", required = true)
    private String reason;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer[] getItemId() {
        return itemIds;
    }

    public void setItemId(Integer[] itemId) {
        this.itemIds = itemId;
    }

    public ReturnReason getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(ReturnReason returnReason) {
        this.returnReason = returnReason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ProductReturnApplyVO{" +
                "orderId=" + orderId +
                ", itemId=" + itemIds +
                ", returnReason='" + returnReason + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
