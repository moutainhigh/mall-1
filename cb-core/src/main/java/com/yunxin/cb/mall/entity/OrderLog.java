package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author x001393
 * 订单日志
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class OrderLog implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private int ordersLogId;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 操作者
     */
    private String handler;
    /**
     * 操作时间
     */
    private Date time;
    /**
     * 操作事项
     */
    private String remark;

    @Column(nullable = false, length = 64)
    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getOrdersLogId() {
        return ordersLogId;
    }

    public void setOrdersLogId(int ordersLogId) {
        this.ordersLogId = ordersLogId;
    }

    @Column(nullable = false, length = 32)
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Column(length = 1024)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
