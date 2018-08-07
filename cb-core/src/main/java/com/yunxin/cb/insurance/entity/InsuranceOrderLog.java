package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceOrderLog implements Serializable {

    @Max(9999999999L)
    @ApiModelProperty(value="编号",name="insuranceLogId",example="1")
    private int insuranceLogId;
    private Customer customer;
    private InsuranceOrder insuranceOrder;
    @ApiModelProperty(value="名称",name="prodName",example="你好")
    private InsuranceOrderState orderState;
    @ApiModelProperty(value="名称",name="prodName",example="你好")
    private String prodName;
    @ApiModelProperty(value="价格",name="price",example="5000")
    private int price;
    @ApiModelProperty(value="订单创建时间",name="createTime",example="2018-07-09 20:10")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getInsuranceLogId() {
        return insuranceLogId;
    }

    public void setInsuranceLogId(int insuranceLogId) {
        this.insuranceLogId = insuranceLogId;
    }
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CUSTOMER_ID", nullable = false, insertable = true, updatable = true)
    })
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @OneToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "INSURANCE_ORDER_ID",nullable = false, insertable = true, updatable = true)
    })
    public InsuranceOrder getInsuranceOrder() {
        return insuranceOrder;
    }

    public void setInsuranceOrder(InsuranceOrder insuranceOrder) {
        this.insuranceOrder = insuranceOrder;
    }
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.ORDINAL)
    public InsuranceOrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(InsuranceOrderState orderState) {
        this.orderState = orderState;
    }
    @Column(nullable = false, length = 200)
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    @Column(nullable = false, length = 32)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
