package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.CashbackLogState;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @title: 返现记录表
 * @auther: eleven
 * @date: 2018/8/13 11:13
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@ApiModel(value="返现记录表",description="返现记录FinancialCashbackLog")
public class FinancialCashbackLog implements java.io.Serializable {

    private static final long serialVersionUID = -3554709459548268254L;

    /** 保险返现id */
    @ApiModelProperty(value="保险返现id",name="logId",example="1")
    private Integer logId;

    /** 用户 */
    @ApiModelProperty(value="用户",name="customer",example="customer")
    private Customer customer;

    /** 返现人 */
    @ApiModelProperty(value="返现人",name="customerName",example="张三")
    private String customerName;

    /** 返现人手机号 */
    @ApiModelProperty(value="返现人手机号",name="createTime",example="13588565598")
    private String mobile;

    /** 返现金额 */
    @ApiModelProperty(value="返现金额",name="amount",example="10000")
    private BigDecimal amount;

    /** 状态 */
    @ApiModelProperty(value="状态",name="state",example="1")
    private CashbackLogState state;

    /** 返利保单 */
    @ApiModelProperty(value="返利保单",name="orderNo",example="2255554897")
    private String orderNo;

    /** 返现时间 */
    @ApiModelProperty(value="返现时间",name="createTime",example="2016-03-22 21:58:43")
    private Date createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "customer_id", nullable = false, insertable = true, updatable = true)
    })
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(length = 50)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(precision = 1)
    public CashbackLogState getState() {
        return state;
    }

    public void setState(CashbackLogState state) {
        this.state = state;
    }

    @Column(length = 50)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
