/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.insurance.meta.InsurancePeriod;
import com.yunxin.cb.insurance.meta.InsuranceYear;
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
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 保险产品
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(appliesTo = "insurance_product", comment = "保险产品")
public class InsuranceProduct implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 产品ID
     */
    @Max(9999999999L)
    @ApiModelProperty(value="产品ID",name="prodId",example="1")
    private int prodId;
    /**
     * 产品名称
     */
    @NotBlank
    @Length(max = 64)
    @ApiModelProperty(value="产品名称",name="prodName",example="生命险")
    private String prodName;
    /**
     * 产品描述
     */
    @Length(max = 65535)
    @ApiModelProperty(value="产品描述",name="description",example="该产品主要是用来XXXX")
    private String description;
    /**
     * 产品图片
     */
    @NotBlank
    @Length(max = 512)
    @ApiModelProperty(value="产品图片",name="prodImg",example="xxx.jpg")
    private String prodImg;
    /**
     * 产品详情图片
     */
    @NotBlank
    @Length(max = 512)
    @ApiModelProperty(value="产品详情图片",name="descriptionImg",example="xxx.jpg")
    private String descriptionImg;
    /**
     * 标签（多个用逗号隔开）
     */
    @Length(max = 255)
    @ApiModelProperty(value="标签",name="tags",example="生命，年限")
    private String tags;
    /**
     * 保险期间
     */
    @ApiModelProperty(value="保险期间",name="insurePeriod",example="在")
    private InsurancePeriod insurePeriod;
    /**
     * 保障年限(10年，20年，终生)
     */
    @ApiModelProperty(value="保障年限",name="protectionYear",example="TEN_YEAR")
    private InsuranceYear protectionYear;
    /**
     * 投保须知
     */
    @NotBlank
    @Length(max = 1024)
    @ApiModelProperty(value="投保须知",name="instruction",example="投此保险需要知道与注意的")
    private String instruction;

    /**
     * 是否启用
     */
    @ApiModelProperty(value="是否启用",name="enabled",example="1")
    @Max(9999999999L)
    private int enabled;
    /**
     * 创建时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-06-05 10:20")
    private Date createTime;
    @ApiModelProperty(value="保险订单",name="insuranceOrders",example="")
    private Set insuranceOrders = new HashSet(0);
    @ApiModelProperty(value="保险产品价格",name="insuranceProductPrices",example="5454")
    private Set<InsuranceProductPrice> insuranceProductPrices = new HashSet(0);

    private Set<InsuranceInformedMatter> insuranceInformedMatters = new HashSet(0);
    //columns END

    private int[] matterIds;

    private int[] price;

    private String[] unit;

    @Transient
    public int[] getMatterIds() {
        return matterIds;
    }

    public void setMatterIds(int[] matterIds) {
        this.matterIds = matterIds;
    }

    public InsuranceProduct() {
    }

    public InsuranceProduct(
            int prodId
    ) {
        this.prodId = prodId;
    }
    @Transient
    public int[] getPrice() {
        return price;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }
    @Transient
    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getProdId() {
        return this.prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Lob
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 512)
    public String getProdImg() {
        return this.prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 512)
    public String getDescriptionImg() {
        return this.descriptionImg;
    }

    public void setDescriptionImg(String descriptionImg) {
        this.descriptionImg = descriptionImg;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 255)
    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(length = 128, nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    public InsurancePeriod getInsurePeriod() {
        return this.insurePeriod;
    }

    public void setInsurePeriod(InsurancePeriod insurePeriod) {
        this.insurePeriod = insurePeriod;
    }

    @Column(length = 128, nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    public InsuranceYear getProtectionYear() {
        return this.protectionYear;
    }

    public void setProtectionYear(InsuranceYear protectionYear) {
        this.protectionYear = protectionYear;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 1024)
    public String getInstruction() {
        return this.instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
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

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "insuranceProduct")
    public Set<InsuranceOrder> getInsuranceOrders() {
        return insuranceOrders;
    }

    public void setInsuranceOrders(Set<InsuranceOrder> insuranceOrder) {
        this.insuranceOrders = insuranceOrder;
    }


    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "insuranceProduct")
    public Set<InsuranceProductPrice> getInsuranceProductPrices() {
        return insuranceProductPrices;
    }

    public void setInsuranceProductPrices(Set<InsuranceProductPrice> insuranceProductPrice) {
        this.insuranceProductPrices = insuranceProductPrice;
    }

    @ManyToMany(targetEntity = InsuranceInformedMatter.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "insurance_product_informed_matter", joinColumns = {@JoinColumn(name = "PROD_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "MATTER_ID", nullable = false, updatable = false)})
    @OrderBy(value = "matterId asc")
    public Set<InsuranceInformedMatter> getInsuranceInformedMatters() {
        return insuranceInformedMatters;
    }

    public void setInsuranceInformedMatters(Set<InsuranceInformedMatter> insuranceInformedMatters) {
        this.insuranceInformedMatters = insuranceInformedMatters;
    }
}