package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
* @author gws
* @date 2018/7/24 19:59
* @param 
* @return
*/
@ApiModel(value="售后退款申请页面数据",description="售后退款申请页面数据VO对象")
public class ProductReturnApplyDataVO implements java.io.Serializable{

    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id",name="orderId",example="1")
    private Integer orderId;
    /**
     * 退货原因
     */
    @ApiModelProperty(value="退货原因集合",name="returnReason",example="我不想买了")
    private Map returnReason;

    @ApiModelProperty(value="退货用户手机号",name="returnMobile",example="13875956623")
    private String returnMobile;

    @ApiModelProperty(value="退货用户姓名",name="returnName",example="张三")
    private String returnName;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Map getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(Map returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnMobile() {
        return returnMobile;
    }

    public void setReturnMobile(String returnMobile) {
        this.returnMobile = returnMobile;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    @Override
    public String toString() {
        return "ProductReturnApplyDataVO{" +
                "orderId=" + orderId +
                ", returnReason=" + returnReason +
                ", returnMobile='" + returnMobile + '\'' +
                ", returnName='" + returnName + '\'' +
                '}';
    }
}
