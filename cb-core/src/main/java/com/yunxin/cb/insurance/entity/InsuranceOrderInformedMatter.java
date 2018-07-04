/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceOrderInformedMatter implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 事项采集ID
     */
    @Max(9999999999L)
    private int collectId;
    /**
     * 订单ID
     */
    @NotNull
    @Max(9999999999L)
    private int orderId;
    /**
     * 告知事项ID
     */
    @NotNull
    @Max(9999999999L)
    private int matterId;
    /**
     * 采集结果
     */
    @NotNull
    @Max(9999999999L)
    private int result;
    //columns END


    public InsuranceOrderInformedMatter() {
    }

    public InsuranceOrderInformedMatter(
            int collectId
    ) {
        this.collectId = collectId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getCollectId() {
        return this.collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public int getMatterId() {
        return this.matterId;
    }

    public void setMatterId(int matterId) {
        this.matterId = matterId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }


    private InsuranceInformedMatter insuranceInformedMatter;

    public void setInsuranceInformedMatter(InsuranceInformedMatter insuranceInformedMatter) {
        this.insuranceInformedMatter = insuranceInformedMatter;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "MATTER_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceInformedMatter getInsuranceInformedMatter() {
        return insuranceInformedMatter;
    }

    private InsuranceOrder insuranceOrder;

    public void setInsuranceOrder(InsuranceOrder insuranceOrder) {
        this.insuranceOrder = insuranceOrder;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ORDER_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceOrder getInsuranceOrder() {
        return insuranceOrder;
    }


}