package com.yunxin.cb.vo;

import com.yunxin.cb.mall.entity.meta.OrderState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @title: 订单列表VO
 * @auther: gws
 * @date: 2018/9/10 17:25
 */
@ApiModel(value="订单列表VO",description="订单列表VO TempOrderVO")
public class OrderListVO {
    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id",name="orderId",example="1", required = true)
    private Integer orderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value="订单编号",name="orderCode",example="1111111")
    private String orderCode;

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
     * 订单状态
     */
    @ApiModelProperty(value="订单状态",name="orderState",example="PENDING_PAYMENT")
    private OrderState orderState;

    /**
     * 货品总数量
     */
    @ApiModelProperty(value="货品总数量",name="prodQuantity",example="1")
    private Integer prodQuantity;

    /**
     * 订单总价格
     */
    @ApiModelProperty(value="订单付费总计",name="feeTotal",example="1")
    private Double feeTotal;

    /**
     * 订单商品信息
     */
    @ApiModelProperty(value="货品信息",name="orderItemDetails",example="货品信息对象")
    private List<OrderListItemVO> OrderListItemVO;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

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

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Integer getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Integer prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public Double getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(Double feeTotal) {
        this.feeTotal = feeTotal;
    }

    public List<com.yunxin.cb.vo.OrderListItemVO> getOrderListItemVO() {
        return OrderListItemVO;
    }

    public void setOrderListItemVO(List<OrderListItemVO> orderListItemVO) {
        OrderListItemVO = orderListItemVO;
    }

    @Override
    public String toString() {
        return "OrderListVO{" +
                "orderId=" + orderId +
                ", orderCode='" + orderCode + '\'' +
                ", sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", orderState=" + orderState +
                ", prodQuantity=" + prodQuantity +
                ", feeTotal=" + feeTotal +
                ", OrderListItemVO=" + OrderListItemVO +
                '}';
    }
}
