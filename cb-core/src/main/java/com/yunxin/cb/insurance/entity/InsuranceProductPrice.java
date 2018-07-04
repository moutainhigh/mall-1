/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class InsuranceProductPrice implements Serializable {

private static final long serialVersionUID = 1L;


    //columns START
        /**
        * 产品价格ID
        */
        @Max(9999999999L)
        private int priceId;
        /**
        * 产品ID
        */
        @NotNull @Max(9999999999L)
        private int prodId;
        /**
        * 产品价格
        */
        @NotNull @Max(9999999999L)
        private int price;
        /**
        * 价格单位
        */
        @NotBlank @Length(max=32)
        private String unit;
        /**
        * 描述
        */
        @Length(max=65535)
        private String description;
        /**
        * 创建时间
        */
        @NotNull 
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date createTime;
        /**
        * 备注
        */
        @Length(max=512)
        private String remark;
    //columns END


	public InsuranceProductPrice(){
	}

	public InsuranceProductPrice(
		int priceId
	){
		this.priceId = priceId;
	}


            @Id
            @GeneratedValue(strategy = IDENTITY)
            @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
            public int getPriceId() {
            return this.priceId;
            }

            public void setPriceId(int priceId) {
            this.priceId = priceId;
            }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
        public int getProdId() {
        return this.prodId;
        }

        public void setProdId(int prodId) {
        this.prodId = prodId;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
        public int getPrice() {
        return this.price;
        }

        public void setPrice(int price) {
        this.price = price;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
        public String getUnit() {
        return this.unit;
        }

        public void setUnit(String unit) {
        this.unit = unit;
        }

        @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
        public String getDescription() {
        return this.description;
        }

        public void setDescription(String description) {
        this.description = description;
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

        @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 512)
        public String getRemark() {
        return this.remark;
        }

        public void setRemark(String remark) {
        this.remark = remark;
        }


    private Set insuranceOrders = new HashSet(0);

    @OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "insuranceProductPrice")
    public Set<InsuranceOrder> getInsuranceOrders() {
    return insuranceOrders;
    }

    public void setInsuranceOrders(Set<InsuranceOrder> insuranceOrder){
    this.insuranceOrders = insuranceOrder;
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



}