package com.yunxin.cb.mall.entity;

import java.util.Date;

public class OrderLog {
    /** 订单日志 */
    private Integer ordersLogId;

    /** 操作者 */
    private String handler;

    /** 订单号 */
    private String orderCode;

    /** 操作事项 */
    private String remark;

    /** 操作时间 */
    private Date time;

    public Integer getOrdersLogId() {
        return ordersLogId;
    }

    public void setOrdersLogId(Integer ordersLogId) {
        this.ordersLogId = ordersLogId;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler == null ? null : handler.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}