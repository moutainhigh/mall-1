/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModelProperty;
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

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 保险产品价格
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
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
    @ApiModelProperty(value="产品价格ID",name="priceId",example="1")
    private int priceId;
    /**
     * 产品
     */
    @NotNull
    @ApiModelProperty(value="产品",name="insuranceProduct",example="产品")
    private InsuranceProduct insuranceProduct;
    /**
     * 产品价格
     */
    @NotNull
    @Max(9999999999L)
    @ApiModelProperty(value="产品价格",name="price",example="55")
    private int price;
    /**
     * 价格单位
     */
    @NotBlank
    @Length(max = 32)
    @ApiModelProperty(value="价格单位",name="unit",example="元")
    private String unit;
    /**
     * 描述
     */
    @Length(max = 65535)
    @ApiModelProperty(value="描述",name="description",example="产品描述")
    private String description;
    /**
     * 创建时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-05-06 10:23")
    private Date createTime;
    /**
     * 备注
     */
    @Length(max = 512)
    @ApiModelProperty(value="备注",name="remark",example="备注")
    private String remark;

    //columns END


    public InsuranceProductPrice() {
    }

    public InsuranceProductPrice(
            int priceId
    ) {
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




    public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
        this.insuranceProduct = insuranceProduct;
    }

    @JsonIgnore
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PROD_ID", nullable = false, insertable = true, updatable = true)
    })
    public InsuranceProduct getInsuranceProduct() {
        return insuranceProduct;
    }


}