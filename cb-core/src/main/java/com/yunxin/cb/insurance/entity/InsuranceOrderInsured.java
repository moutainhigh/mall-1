/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * 保险订单被保人
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceOrderInsured implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 被保人ID
     */
    @Max(9999999999L)
    private int insuredId;
    /**
     * 被保人生日
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date insuredBirthday;
    /**
     * 被保人性别
     */
    @NotBlank
    @Length(max = 16)
    private String insuredGender;
    /**
     * 被保人职业
     */
    @NotBlank
    @Length(max = 32)
    private String insuredCareer;
    /**
     * 被保人姓名
     */
    @NotBlank
    @Length(max = 32)
    private String insuredName;
    /**
     * 被保人证件类型
     */
    @NotBlank
    @Length(max = 32)
    private String insuredCardType;
    /**
     * 被保人证件号
     */
    @NotBlank
    @Length(max = 32)
    private String insuredCardNo;
    /**
     * 被保人证件有效期
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date insuredCardPeriod;
    /**
     * 被保人国籍
     */
    @NotBlank
    @Length(max = 64)
    private String insuredCountry;
    /**
     * 被保人身高
     */
    @Max(9999999999L)
    private int insuredHeight;
    /**
     * 被保人体重
     */

    private Float insuredBodyWeight;
    /**
     * 被保人收入
     */
    @Max(9999999999L)
    private int insuredIncome;
    /**
     * 被保人婚否
     */
    @Length(max = 32)
    private String insuredMarriage;
    /**
     * 被保人固定电话
     */
    @Length(max = 15)
    private String insuredTel;
    /**
     * 被保人手机号
     */
    @Length(max = 11)
    private String insuredMobile;
    /**
     * 被保人邮箱
     */
    @Length(max = 32)
    private String insuredEmail;
    /**
     * 被保人所在省
     */
    @NotBlank
    @Length(max = 6)
    private String insuredProvince;
    /**
     * 被保人所在市
     */
    @NotBlank
    @Length(max = 6)
    private String insuredCity;
    /**
     * 被保人所在区
     */
    @NotBlank
    @Length(max = 6)
    private String insuredDistrict;
    /**
     * 被保人详细地址
     */
    @NotBlank
    @Length(max = 255)
    private String insuredAddress;
    /**
     * 被保人与投保人关系
     */
    @NotBlank
    @Length(max = 32)
    private String insuredRelation;


    private Set insuranceOrders = new HashSet(0);
    //columns END


    public InsuranceOrderInsured() {
    }

    public InsuranceOrderInsured(
            int insuredId
    ) {
        this.insuredId = insuredId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getInsuredId() {
        return this.insuredId;
    }

    public void setInsuredId(int insuredId) {
        this.insuredId = insuredId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public Date getInsuredBirthday() {
        return this.insuredBirthday;
    }

    public void setInsuredBirthday(Date insuredBirthday) {
        this.insuredBirthday = insuredBirthday;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 16)
    public String getInsuredGender() {
        return this.insuredGender;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsuredCareer() {
        return this.insuredCareer;
    }

    public void setInsuredCareer(String insuredCareer) {
        this.insuredCareer = insuredCareer;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsuredName() {
        return this.insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsuredCardType() {
        return this.insuredCardType;
    }

    public void setInsuredCardType(String insuredCardType) {
        this.insuredCardType = insuredCardType;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsuredCardNo() {
        return this.insuredCardNo;
    }

    public void setInsuredCardNo(String insuredCardNo) {
        this.insuredCardNo = insuredCardNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public Date getInsuredCardPeriod() {
        return this.insuredCardPeriod;
    }

    public void setInsuredCardPeriod(Date insuredCardPeriod) {
        this.insuredCardPeriod = insuredCardPeriod;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    public String getInsuredCountry() {
        return this.insuredCountry;
    }

    public void setInsuredCountry(String insuredCountry) {
        this.insuredCountry = insuredCountry;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getInsuredHeight() {
        return this.insuredHeight;
    }

    public void setInsuredHeight(int insuredHeight) {
        this.insuredHeight = insuredHeight;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 12)
    public Float getInsuredBodyWeight() {
        return this.insuredBodyWeight;
    }

    public void setInsuredBodyWeight(Float insuredBodyWeight) {
        this.insuredBodyWeight = insuredBodyWeight;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getInsuredIncome() {
        return this.insuredIncome;
    }

    public void setInsuredIncome(int insuredIncome) {
        this.insuredIncome = insuredIncome;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    public String getInsuredMarriage() {
        return this.insuredMarriage;
    }

    public void setInsuredMarriage(String insuredMarriage) {
        this.insuredMarriage = insuredMarriage;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 15)
    public String getInsuredTel() {
        return this.insuredTel;
    }

    public void setInsuredTel(String insuredTel) {
        this.insuredTel = insuredTel;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 11)
    public String getInsuredMobile() {
        return this.insuredMobile;
    }

    public void setInsuredMobile(String insuredMobile) {
        this.insuredMobile = insuredMobile;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    public String getInsuredEmail() {
        return this.insuredEmail;
    }

    public void setInsuredEmail(String insuredEmail) {
        this.insuredEmail = insuredEmail;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getInsuredProvince() {
        return this.insuredProvince;
    }

    public void setInsuredProvince(String insuredProvince) {
        this.insuredProvince = insuredProvince;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getInsuredCity() {
        return this.insuredCity;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getInsuredDistrict() {
        return this.insuredDistrict;
    }

    public void setInsuredDistrict(String insuredDistrict) {
        this.insuredDistrict = insuredDistrict;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    public String getInsuredAddress() {
        return this.insuredAddress;
    }

    public void setInsuredAddress(String insuredAddress) {
        this.insuredAddress = insuredAddress;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsuredRelation() {
        return this.insuredRelation;
    }

    public void setInsuredRelation(String insuredRelation) {
        this.insuredRelation = insuredRelation;
    }


    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "insuranceOrderInsured")
    public Set<InsuranceOrder> getInsuranceOrders() {
        return insuranceOrders;
    }

    public void setInsuranceOrders(Set<InsuranceOrder> insuranceOrder) {
        this.insuranceOrders = insuranceOrder;
    }


}