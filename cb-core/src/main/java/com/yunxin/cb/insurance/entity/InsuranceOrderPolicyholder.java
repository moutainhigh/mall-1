/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
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
 * 保险订单投保人
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceOrderPolicyholder implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 投保人ID
     */
    @Max(9999999999L)
    private int policyholderId;
    /**
     * 投保人姓名
     */
    @NotBlank
    @Length(max = 32)
    private String policyholderName;
    /**
     * 投保人性别
     */
    @NotBlank
    @Length(max = 16)
    private String policyholderGender;
    /**
     * 投保人生日
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date policyholderBirthday;
    /**
     * 投保人职业
     */
    @NotBlank
    @Length(max = 32)
    private String policyholderCareer;
    /**
     * 投保人证件类型
     */
    @NotBlank
    @Length(max = 32)
    private String policyholderCardType;
    /**
     * 投保人证件号
     */
    @NotBlank
    @Length(max = 32)
    private String policyholderCardNo;
    /**
     * 投保人国籍
     */
    @NotBlank
    @Length(max = 64)
    private String policyholderCountry;
    /**
     * 投保人证件有效期
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date policyholderCardPeroid;
    /**
     * 投保人身高
     */
    @Max(9999999999L)
    private int policyholderHeight;
    /**
     * 投保人体重
     */

    private Float policyholderBodyWeight;
    /**
     * 投保人收入
     */
    @Max(9999999999L)
    private int policyholderIncome;
    /**
     * 投保人婚否
     */
    @Length(max = 32)
    private String policyholderMarriage;
    /**
     * 投保人固定电话
     */
    @Length(max = 15)
    private String policyholderTel;
    /**
     * 投保人手机号
     */
    @Length(max = 11)
    private String policyholderMobile;
    /**
     * 投保人邮箱
     */
    @Length(max = 32)
    private String policyholderEmail;
    /**
     * 投保人所在省
     */
    @NotBlank
    @Length(max = 6)
    private String policyholderProvince;
    /**
     * 投保人所在市
     */
    @NotBlank
    @Length(max = 6)
    private String policyholderCity;
    /**
     * 投保人所在区
     */
    @NotBlank
    @Length(max = 6)
    private String policyholderDistrict;
    /**
     * 投保人详细地址
     */
    @NotBlank
    @Length(max = 255)
    private String policyholderAddress;
    /**
     * 涉税人身份信息
     */
    @NotBlank
    @Length(max = 64)
    private String policyholderTaxRelated;
    /**
     * 投保人签名
     */
    @NotBlank
    @Length(max = 255)
    private String policyholderSign;
    /**
     * 投保人意见书签名
     */
    @NotBlank
    @Length(max = 255)
    private String submissionSign;
    /**
     * 投保人头像
     */
    @NotBlank
    @Length(max = 255)
    private String policyholderAvatar;
    /**
     * 投保人身份证正面照片
     */
    @NotBlank
    @Length(max = 255)
    private String cardPositiveImg;
    /**
     * 投保人身份证反面照片
     */
    @NotBlank
    @Length(max = 255)
    private String cardNegativeImg;
    /**
     * 其他资料1
     */
    @Length(max = 255)
    private String otherImg1;
    /**
     * 其他资料2
     */
    @Length(max = 255)
    private String otherImg2;
    /**
     * 其他资料3
     */
    @Length(max = 255)
    private String otherImg3;

    private Set insuranceOrders = new HashSet(0);
    //columns END


    public InsuranceOrderPolicyholder() {
    }

    public InsuranceOrderPolicyholder(
            int policyholderId
    ) {
        this.policyholderId = policyholderId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getPolicyholderId() {
        return this.policyholderId;
    }

    public void setPolicyholderId(int policyholderId) {
        this.policyholderId = policyholderId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getPolicyholderName() {
        return this.policyholderName;
    }

    public void setPolicyholderName(String policyholderName) {
        this.policyholderName = policyholderName;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 16)
    public String getPolicyholderGender() {
        return this.policyholderGender;
    }

    public void setPolicyholderGender(String policyholderGender) {
        this.policyholderGender = policyholderGender;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public Date getPolicyholderBirthday() {
        return this.policyholderBirthday;
    }

    public void setPolicyholderBirthday(Date policyholderBirthday) {
        this.policyholderBirthday = policyholderBirthday;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getPolicyholderCareer() {
        return this.policyholderCareer;
    }

    public void setPolicyholderCareer(String policyholderCareer) {
        this.policyholderCareer = policyholderCareer;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getPolicyholderCardType() {
        return this.policyholderCardType;
    }

    public void setPolicyholderCardType(String policyholderCardType) {
        this.policyholderCardType = policyholderCardType;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getPolicyholderCardNo() {
        return this.policyholderCardNo;
    }

    public void setPolicyholderCardNo(String policyholderCardNo) {
        this.policyholderCardNo = policyholderCardNo;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    public String getPolicyholderCountry() {
        return this.policyholderCountry;
    }

    public void setPolicyholderCountry(String policyholderCountry) {
        this.policyholderCountry = policyholderCountry;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public Date getPolicyholderCardPeroid() {
        return this.policyholderCardPeroid;
    }

    public void setPolicyholderCardPeroid(Date policyholderCardPeroid) {
        this.policyholderCardPeroid = policyholderCardPeroid;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getPolicyholderHeight() {
        return this.policyholderHeight;
    }

    public void setPolicyholderHeight(int policyholderHeight) {
        this.policyholderHeight = policyholderHeight;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 12)
    public Float getPolicyholderBodyWeight() {
        return this.policyholderBodyWeight;
    }

    public void setPolicyholderBodyWeight(Float policyholderBodyWeight) {
        this.policyholderBodyWeight = policyholderBodyWeight;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getPolicyholderIncome() {
        return this.policyholderIncome;
    }

    public void setPolicyholderIncome(int policyholderIncome) {
        this.policyholderIncome = policyholderIncome;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    public String getPolicyholderMarriage() {
        return this.policyholderMarriage;
    }

    public void setPolicyholderMarriage(String policyholderMarriage) {
        this.policyholderMarriage = policyholderMarriage;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 15)
    public String getPolicyholderTel() {
        return this.policyholderTel;
    }

    public void setPolicyholderTel(String policyholderTel) {
        this.policyholderTel = policyholderTel;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 11)
    public String getPolicyholderMobile() {
        return this.policyholderMobile;
    }

    public void setPolicyholderMobile(String policyholderMobile) {
        this.policyholderMobile = policyholderMobile;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getPolicyholderEmail() {
        return this.policyholderEmail;
    }

    public void setPolicyholderEmail(String policyholderEmail) {
        this.policyholderEmail = policyholderEmail;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getPolicyholderProvince() {
        return this.policyholderProvince;
    }

    public void setPolicyholderProvince(String policyholderProvince) {
        this.policyholderProvince = policyholderProvince;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getPolicyholderCity() {
        return this.policyholderCity;
    }

    public void setPolicyholderCity(String policyholderCity) {
        this.policyholderCity = policyholderCity;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getPolicyholderDistrict() {
        return this.policyholderDistrict;
    }

    public void setPolicyholderDistrict(String policyholderDistrict) {
        this.policyholderDistrict = policyholderDistrict;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    public String getPolicyholderAddress() {
        return this.policyholderAddress;
    }

    public void setPolicyholderAddress(String policyholderAddress) {
        this.policyholderAddress = policyholderAddress;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    public String getPolicyholderTaxRelated() {
        return this.policyholderTaxRelated;
    }

    public void setPolicyholderTaxRelated(String policyholderTaxRelated) {
        this.policyholderTaxRelated = policyholderTaxRelated;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    public String getPolicyholderSign() {
        return this.policyholderSign;
    }

    public void setPolicyholderSign(String policyholderSign) {
        this.policyholderSign = policyholderSign;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    public String getPolicyholderAvatar() {
        return policyholderAvatar;
    }

    public void setPolicyholderAvatar(String policyholderAvatar) {
        this.policyholderAvatar = policyholderAvatar;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    public String getCardPositiveImg() {
        return this.cardPositiveImg;
    }

    public void setCardPositiveImg(String cardPositiveImg) {
        this.cardPositiveImg = cardPositiveImg;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    public String getCardNegativeImg() {
        return this.cardNegativeImg;
    }

    public void setCardNegativeImg(String cardNegativeImg) {
        this.cardNegativeImg = cardNegativeImg;
    }

    @Column(length = 255)
    public String getOtherImg1() {
        return otherImg1;
    }

    public void setOtherImg1(String otherImg1) {
        this.otherImg1 = otherImg1;
    }

    @Column(length = 255)
    public String getOtherImg2() {
        return otherImg2;
    }

    public void setOtherImg2(String otherImg2) {
        this.otherImg2 = otherImg2;
    }

    @Column(length = 255)
    public String getOtherImg3() {
        return otherImg3;
    }

    public void setOtherImg3(String otherImg3) {
        this.otherImg3 = otherImg3;
    }

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "insuranceOrderPolicyholder")
    public Set<InsuranceOrder> getInsuranceOrders() {
        return insuranceOrders;
    }

    public void setInsuranceOrders(Set<InsuranceOrder> insuranceOrder) {
        this.insuranceOrders = insuranceOrder;
    }

    @Column(length = 255)
    public String getSubmissionSign() {
        return submissionSign;
    }

    public void setSubmissionSign(String submissionSign) {
        this.submissionSign = submissionSign;
    }
}