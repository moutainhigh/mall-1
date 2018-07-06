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
    private int prodId;
    /**
     * 产品名称
     */
    @NotBlank
    @Length(max = 64)
    private String prodName;
    /**
     * 产品描述
     */
    @Length(max = 65535)
    private String description;
    /**
     * 产品图片
     */
    @NotBlank
    @Length(max = 512)
    private String prodImg;
    /**
     * 产品详情图片
     */
    @NotBlank
    @Length(max = 512)
    private String descriptionImg;
    /**
     * 标签（多个用逗号隔开）
     */
    @Length(max = 255)
    private String tags;
    /**
     * 保险期间
     */
    @NotBlank
    @Length(max = 32)
    private String insurePeriod;
    /**
     * 保障年限(10年，20年，终生)
     */
    @NotBlank
    @Length(max = 32)
    private String protectionYear;
    /**
     * 投保须知
     */
    @NotBlank
    @Length(max = 1024)
    private String instruction;
    /**
     * 创建时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    private Set insuranceOrders = new HashSet(0);

    private Set insuranceProductPrices = new HashSet(0);

    private Set<InsuranceInformedMatter> insuranceInformedMatters = new HashSet(0);
    //columns END


    public InsuranceProduct() {
    }

    public InsuranceProduct(
            int prodId
    ) {
        this.prodId = prodId;
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

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsurePeriod() {
        return this.insurePeriod;
    }

    public void setInsurePeriod(String insurePeriod) {
        this.insurePeriod = insurePeriod;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getProtectionYear() {
        return this.protectionYear;
    }

    public void setProtectionYear(String protectionYear) {
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
    public Set<InsuranceInformedMatter> getInsuranceInformedMatters() {
        return insuranceInformedMatters;
    }

    public void setInsuranceInformedMatters(Set<InsuranceInformedMatter> insuranceInformedMatters) {
        this.insuranceInformedMatters = insuranceInformedMatters;
    }
}