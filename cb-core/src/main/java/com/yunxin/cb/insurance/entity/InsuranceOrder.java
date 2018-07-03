/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
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
*  *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 *
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
        @Max(9999999999L)
        private int orderId;
        /**
        * 订单号
        */
        @NotBlank @Length(max=32)
        private String orderCode;
        /**
        * 产品ID
        */
        @NotNull @Max(9999999999L)
        private int prodId;
        /**
        * 保额
        */
        @Max(9999999999L)
        private int priceId;
        /**
        * 客户ID
        */
        @NotNull @Max(9999999999L)
        private int customerId;
        /**
        * 被保人ID
        */
        @NotNull @Max(9999999999L)
        private int insuredId;
        /**
        * 投保人ID
        */
        @NotNull @Max(9999999999L)
        private int policyholderId;
        /**
        * 投保人银行ID
        */
        @NotNull @Max(9999999999L)
        private int bankId;
        /**
        * 订单创建时间
        */
        @NotNull 
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date createTime;
    //columns END


	public InsuranceOrder(){
	}

	public InsuranceOrder(
		int orderId
	){
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

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
        public int getProdId() {
        return this.prodId;
        }

        public void setProdId(int prodId) {
        this.prodId = prodId;
        }

        @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
        public int getPriceId() {
        return this.priceId;
        }

        public void setPriceId(int priceId) {
        this.priceId = priceId;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
        public int getCustomerId() {
        return this.customerId;
        }

        public void setCustomerId(int customerId) {
        this.customerId = customerId;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
        public int getInsuredId() {
        return this.insuredId;
        }

        public void setInsuredId(int insuredId) {
        this.insuredId = insuredId;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
        public int getPolicyholderId() {
        return this.policyholderId;
        }

        public void setPolicyholderId(int policyholderId) {
        this.policyholderId = policyholderId;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
        public int getBankId() {
        return this.bankId;
        }

        public void setBankId(int bankId) {
        this.bankId = bankId;
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


    private Set insuranceOrderBeneficiarys = new HashSet(0);

    @OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "insuranceOrder")
    public Set<InsuranceOrderBeneficiary> getInsuranceOrderBeneficiarys() {
    return insuranceOrderBeneficiarys;
    }

    public void setInsuranceOrderBeneficiarys(Set<InsuranceOrderBeneficiary> insuranceOrderBeneficiary){
    this.insuranceOrderBeneficiarys = insuranceOrderBeneficiary;
    }

    private Set insuranceOrderInformedMatters = new HashSet(0);

    @OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "insuranceOrder")
    public Set<InsuranceOrderInformedMatter> getInsuranceOrderInformedMatters() {
    return insuranceOrderInformedMatters;
    }

    public void setInsuranceOrderInformedMatters(Set<InsuranceOrderInformedMatter> insuranceOrderInformedMatter){
    this.insuranceOrderInformedMatters = insuranceOrderInformedMatter;
    }

    private InsuranceProduct insuranceProduct;
    public void setInsuranceProduct(InsuranceProduct insuranceProduct){
    this.insuranceProduct = insuranceProduct;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "PROD_ID",nullable = false, insertable = true, updatable = true) 
    })
    public InsuranceProduct getInsuranceProduct() {
    return insuranceProduct;
    }

    private InsuranceOrderPolicyholder insuranceOrderPolicyholder;
    public void setInsuranceOrderPolicyholder(InsuranceOrderPolicyholder insuranceOrderPolicyholder){
    this.insuranceOrderPolicyholder = insuranceOrderPolicyholder;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "POLICYHOLDER_ID",nullable = false, insertable = true, updatable = true) 
    })
    public InsuranceOrderPolicyholder getInsuranceOrderPolicyholder() {
    return insuranceOrderPolicyholder;
    }

    private InsuranceOrderPolicyholderBank insuranceOrderPolicyholderBank;
    public void setInsuranceOrderPolicyholderBank(InsuranceOrderPolicyholderBank insuranceOrderPolicyholderBank){
    this.insuranceOrderPolicyholderBank = insuranceOrderPolicyholderBank;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "BANK_ID",nullable = false, insertable = true, updatable = true) 
    })
    public InsuranceOrderPolicyholderBank getInsuranceOrderPolicyholderBank() {
    return insuranceOrderPolicyholderBank;
    }

    private InsuranceProductPrice insuranceProductPrice;
    public void setInsuranceProductPrice(InsuranceProductPrice insuranceProductPrice){
    this.insuranceProductPrice = insuranceProductPrice;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "PRICE_ID",nullable = false, insertable = true, updatable = true) 
    })
    public InsuranceProductPrice getInsuranceProductPrice() {
    return insuranceProductPrice;
    }

    private InsuranceOrderInsured insuranceOrderInsured;
    public void setInsuranceOrderInsured(InsuranceOrderInsured insuranceOrderInsured){
    this.insuranceOrderInsured = insuranceOrderInsured;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "INSURED_ID",nullable = false, insertable = true, updatable = true) 
    })
    public InsuranceOrderInsured getInsuranceOrderInsured() {
    return insuranceOrderInsured;
    }

    private Customer customer;
    public void setCustomer(Customer customer){
    this.customer = customer;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CUSTOMER_ID",nullable = false, insertable = true, updatable = true) 
    })
    public Customer getCustomer() {
    return customer;
    }



}