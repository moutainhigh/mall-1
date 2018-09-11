package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Auther: hfy
 * @Date: 2018/9/11 10:03
 * @Description: 申请售后请求VO
 */
@ApiModel(value="申请售后请求VO",description="申请售后请求VO ApplyAfterSaleRequestVO")
public class ApplyAfterSaleRequestVO implements Serializable {
    private static final long serialVersionUID = 2448254718296960090L;
    /** 售后原因 */
    @ApiModelProperty(value="售后原因",name="reason",example="不买了 售后原因")
    private String reason;

    /** 问题描述 */
    @ApiModelProperty(value="问题描述",name="problemDescription",example="质量有问题, 问题描述")
    private String problemDescription;

    /** 联系人 **/
    @ApiModelProperty(value="联系人",name="contactPerson",example="张三,联系人")
    private String contactPerson;

    /** 联系电话 */
    @ApiModelProperty(value="联系电话",name="contactPhone",example="135xxxxxxxx ,联系电话")
    private String contactPhone;

    /** 订单ID */
    @NotNull
    @ApiModelProperty(value="订单ID",name="orderId",example="1 ,订单ID")
    private Integer orderId;

    /** 商家ID */
    @NotNull
    @ApiModelProperty(value="商家ID",name="sellerId",example="1, 商家ID")
    private Integer sellerId;


    /** 需要售后的订单条目ID集合 **/
    @NotNull
    @ApiModelProperty(value="订单条目ID集合",name="orderItemIds",example="[1,2,3] ,需要售后的订单条目ID集合")
    private List<Integer> orderItemIds;

    /** 售后服务类型 退款：0,退货：1，换货：2 */
    @NotNull
    @ApiModelProperty(value="售后服务类型",name="afterSaleServeType",example="0, 售后服务类型 退款：0,退货：1，换货：2")
    private Integer afterSaleServeType;

    @Override
    public String toString() {
        return "ApplyAfterSaleRequestVO{" +
                "reason='" + reason + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", orderId=" + orderId +
                ", sellerId=" + sellerId +
                ", orderItemIds=" + orderItemIds +
                ", afterSaleServeType=" + afterSaleServeType +
                '}';
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getAfterSaleServeType() {
        return afterSaleServeType;
    }

    public void setAfterSaleServeType(Integer afterSaleServeType) {
        this.afterSaleServeType = afterSaleServeType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<Integer> getOrderItemIds() {
        return orderItemIds;
    }

    public void setOrderItemIds(List<Integer> orderItemIds) {
        this.orderItemIds = orderItemIds;
    }
}
