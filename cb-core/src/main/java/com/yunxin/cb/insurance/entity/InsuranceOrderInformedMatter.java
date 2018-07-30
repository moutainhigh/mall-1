/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 保险订单采集事项
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
    @ApiModelProperty(value="事项采集ID",name="collectId",example="1")
    private int collectId;
    /**
     * 订单
     */
    @ApiModelProperty(value="订单",name="insuranceOrder",example="订单")
    private InsuranceOrder insuranceOrder;
    /**
     * 告知事项
     */
    @NotNull
    @ApiModelProperty(value="告知事项",name="insuranceInformedMatter",example="告知事项")
    private InsuranceInformedMatter insuranceInformedMatter;
    /**
     * 被保人采集结果
     */
    @NotNull
    @ApiModelProperty(value="被保人采集结果",name="insuredResult",example="true")
    private boolean insuredResult;
    /**
     * 投保人采集结果
     */
    @ApiModelProperty(value="投保人采集结果",name="policyholderResult",example="true")
    private boolean policyholderResult;
    /**
     * 选项值，可以为多个，以json方式存储
     */
    @ApiModelProperty(value="选项值",name="collectValues",example="选项值")
    private String collectValues;
    /**
     * 被保人备注
     */
    @ApiModelProperty(value="被保人备注",name="insuredRemark",example="被保人备注")
    private String insuredRemark;
    /**
     * 投保人备注
     */
    @ApiModelProperty(value="投保人备注",name="policyholderRemark",example="投保人备注")
    private String policyholderRemark;

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
    public boolean getInsuredResult() {
        return insuredResult;
    }

    public void setInsuredResult(boolean insuredResult) {
        this.insuredResult = insuredResult;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public boolean getPolicyholderResult() {
        return policyholderResult;
    }

    public void setPolicyholderResult(boolean policyholderResult) {
        this.policyholderResult = policyholderResult;
    }

    @Column(length = 255)
    public String getCollectValues() {
        return collectValues;
    }

    public void setCollectValues(String collectValues) {
        this.collectValues = collectValues;
    }

    @Column(length = 255)
    public String getInsuredRemark() {
        return insuredRemark;
    }

    public void setInsuredRemark(String insuredRemark) {
        this.insuredRemark = insuredRemark;
    }

    @Column(length = 255)
    public String getPolicyholderRemark() {
        return policyholderRemark;
    }

    public void setPolicyholderRemark(String policyholderRemark) {
        this.policyholderRemark = policyholderRemark;
    }

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



    public void setInsuranceOrder(InsuranceOrder insuranceOrder) {
        this.insuranceOrder = insuranceOrder;
    }

    @JsonIgnore
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ORDER_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceOrder getInsuranceOrder() {
        return insuranceOrder;
    }


}