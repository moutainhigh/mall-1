/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 保险订单
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceOrder implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 保险订单ID
     */
    @ApiModelProperty(value="保险订单ID",name="orderId",example="1")
    @Max(9999999999L)
    private int orderId;
    /**
     * 订单号
     */
    @ApiModelProperty(value="订单号",name="orderCode",example="1453245")
    @Length(max = 32)
    private String orderCode;
    /**
     * 条形码 base64
     */
    @ApiModelProperty(value="条形码",name="barCode",example="条形码")
    private String barCode;
    /**
     * 合同编号
     */
    @ApiModelProperty(value="合同编号",name="contractNo",example="14548735438637548")
    @Length(max = 32)
    private String contractNo;
    /**
     * 产品
     */
    @ApiModelProperty(value="产品",name="insuranceProduct",example="产品")
    @NotNull
    private InsuranceProduct insuranceProduct;
    /**
     * 保额
     */
    @ApiModelProperty(value="保额",name="insuranceProductPrice",example="保额")
    private InsuranceProductPrice insuranceProductPrice;
    private int price;
    /**
     * 客户
     */
    @ApiModelProperty(value="客户",name="customer",example="客户")
    private Customer customer;
    /**
     * 被保人
     */
    @ApiModelProperty(value="被保人",name="insuranceOrderInsured",example="被保人")
    @NotNull
    private InsuranceOrderInsured insuranceOrderInsured;
    /**
     * 投保人
     */
    @ApiModelProperty(value="投保人",name="insuranceOrderPolicyholder",example="投保人")
    @NotNull
    private InsuranceOrderPolicyholder insuranceOrderPolicyholder;
    /**
     * 投保人银行
     */
    @ApiModelProperty(value="投保人银行",name="insuranceOrderPolicyholderBank",example="投保人银行")
    @NotNull
    private InsuranceOrderPolicyholderBank insuranceOrderPolicyholderBank;
    /**
     * 异地投保
     */
    @ApiModelProperty(value="异地投保",name="insuranceOrderOffsite",example="异地投保")
    private InsuranceOrderOffsite insuranceOrderOffsite;
    /**
     * 是否法定收益人
     */
    @ApiModelProperty(value="是否法定收益人",name="legalBeneficiary",example="true")
    @NotNull
    private boolean legalBeneficiary;
    /**
     * 订单状态
     */
    @ApiModelProperty(value="订单状态",name="orderState",example="UN_PAID(\"待支付\"),ON_PAID(\"已支付\"),BEEN_COMPLETED(\"已完成\"),UN_SURRENDER(\"退保审核\"),ON_SURRENDER(\"已退保\")")
    private InsuranceOrderState orderState;
    /**
     * 订单创建时间
     */
    @ApiModelProperty(value="订单创建时间",name="createTime",example="2018-07-09 20:10")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 受益人
     */
    @ApiModelProperty(value="受益人",name="insuranceOrderBeneficiarys",example="受益人")
    private Set insuranceOrderBeneficiarys = new HashSet(0);
    /**
     * 告知事项
     */
    @ApiModelProperty(value="告知事项",name="insuranceOrderInformedMatters",example="告知事项")
    private Set<InsuranceOrderInformedMatter> insuranceOrderInformedMatters = new HashSet(0);
    //columns END


    public InsuranceOrder() {
    }

    public InsuranceOrder(
            int orderId
    ) {
        this.orderId = orderId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Lob
    @Column
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Column(length = 32)
    public String getContractNo() {
        return this.contractNo;
    }
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "insuranceOrder")
    public Set<InsuranceOrderBeneficiary> getInsuranceOrderBeneficiarys() {
        return insuranceOrderBeneficiarys;
    }

    public void setInsuranceOrderBeneficiarys(Set<InsuranceOrderBeneficiary> insuranceOrderBeneficiary) {
        this.insuranceOrderBeneficiarys = insuranceOrderBeneficiary;
    }


    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "insuranceOrder")
    @OrderBy(value = "insuranceInformedMatter.matterId asc")
    public Set<InsuranceOrderInformedMatter> getInsuranceOrderInformedMatters() {
        return insuranceOrderInformedMatters;
    }

    public void setInsuranceOrderInformedMatters(Set<InsuranceOrderInformedMatter> insuranceOrderInformedMatter) {
        this.insuranceOrderInformedMatters = insuranceOrderInformedMatter;
    }


    public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
        this.insuranceProduct = insuranceProduct;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PROD_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceProduct getInsuranceProduct() {
        return insuranceProduct;
    }



    public void setInsuranceOrderPolicyholder(InsuranceOrderPolicyholder insuranceOrderPolicyholder) {
        this.insuranceOrderPolicyholder = insuranceOrderPolicyholder;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "POLICYHOLDER_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceOrderPolicyholder getInsuranceOrderPolicyholder() {
        return insuranceOrderPolicyholder;
    }



    public void setInsuranceOrderPolicyholderBank(InsuranceOrderPolicyholderBank insuranceOrderPolicyholderBank) {
        this.insuranceOrderPolicyholderBank = insuranceOrderPolicyholderBank;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "BANK_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceOrderPolicyholderBank getInsuranceOrderPolicyholderBank() {
        return insuranceOrderPolicyholderBank;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "OFFSITE_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceOrderOffsite getInsuranceOrderOffsite() {
        return insuranceOrderOffsite;
    }

    public void setInsuranceOrderOffsite(InsuranceOrderOffsite insuranceOrderOffsite) {
        this.insuranceOrderOffsite = insuranceOrderOffsite;
    }

    public void setInsuranceProductPrice(InsuranceProductPrice insuranceProductPrice) {
        this.insuranceProductPrice = insuranceProductPrice;
    }
    @Transient
    public InsuranceProductPrice getInsuranceProductPrice() {
        return insuranceProductPrice;
    }



    public void setInsuranceOrderInsured(InsuranceOrderInsured insuranceOrderInsured) {
        this.insuranceOrderInsured = insuranceOrderInsured;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "INSURED_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceOrderInsured getInsuranceOrderInsured() {
        return insuranceOrderInsured;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CUSTOMER_ID", nullable = false, insertable = true, updatable = true)
    })
    public Customer getCustomer() {
        return customer;
    }

    @Column
    public boolean isLegalBeneficiary() {
        return legalBeneficiary;
    }

    public void setLegalBeneficiary(boolean legalBeneficiary) {
        this.legalBeneficiary = legalBeneficiary;
    }

    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.ORDINAL)
    public InsuranceOrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(InsuranceOrderState orderState) {
        this.orderState = orderState;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}