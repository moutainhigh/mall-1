package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AddReimbursementRequestVO implements Serializable {
    private static final long serialVersionUID = 7383428194946721725L;
    @ApiModelProperty(value="订单ID",name="orderId",example="1")
    private int orderId;
    @ApiModelProperty(value="货品ID",name="productId",example="1")
    private int productId;
    @ApiModelProperty(value="商品ID",name="commodityId",example="1")
    private int commodityId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public String toString() {
        return "AddReimbursementRequestVO{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", commodityId=" + commodityId +
                '}';
    }
}
