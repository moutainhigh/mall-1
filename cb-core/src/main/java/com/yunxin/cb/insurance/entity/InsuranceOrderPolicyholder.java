/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
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
    @ApiModelProperty(value="投保人ID",name="policyholderId",example="1")
    private int policyholderId;
    /**
     * 投保人姓名
     */
    @NotBlank
    @Length(max = 32)
    @ApiModelProperty(value="投保人姓名",name="policyholderName",example="张三")
    private String policyholderName;
    /**
     * 投保人性别
     */
    @NotBlank
    @Length(max = 16)
    @ApiModelProperty(value="投保人性别",name="policyholderGender",example="男")
    private String policyholderGender;
    /**
     * 投保人生日
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="投保人生日",name="policyholderBirthday",example="1979-04-05 10:23")
    private Date policyholderBirthday;
    /**
     * 投保人职业
     */
    @NotBlank
    @Length(max = 32)
    @ApiModelProperty(value="投保人职业",name="policyholderCareer",example="教师")
    private String policyholderCareer;
    /**
     * 投保人证件类型
     */
    @NotBlank
    @Length(max = 32)
    @ApiModelProperty(value="投保人证件类型",name="policyholderCardType",example="身份证")
    private String policyholderCardType;
    /**
     * 投保人证件号
     */
    @NotBlank
    @Length(max = 32)
    @ApiModelProperty(value="投保人证件号",name="policyholderCardNo",example="281777197904056014")
    private String policyholderCardNo;
    /**
     * 投保人国籍
     */
    @NotBlank
    @Length(max = 64)
    @ApiModelProperty(value="投保人国籍",name="policyholderCountry",example="中国")
    private String policyholderCountry;
    /**
     * 投保人证件有效期
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="投保人证件有效期",name="policyholderCardPeroid",example="2018-10-20 08:20")
    private Date policyholderCardPeroid;
    /**
     * 投保人身高
     */
    @Max(9999999999L)
    @ApiModelProperty(value="投保人身高",name="policyholderHeight",example="170")
    private int policyholderHeight;
    /**
     * 投保人体重
     */
    @ApiModelProperty(value="投保人体重",name="policyholderBodyWeight",example="90")
    private Float policyholderBodyWeight;
    /**
     * 投保人收入
     */
    @Max(9999999999L)
    @ApiModelProperty(value="投保人收入",name="policyholderIncome",example="50000")
    private int policyholderIncome;
    /**
     * 投保人婚否
     */
    @Length(max = 32)
    @ApiModelProperty(value="投保人婚否",name="policyholderMarriage",example="投保人婚否")
    private String policyholderMarriage;
    /**
     * 投保人固定电话
     */
    @Length(max = 15)
    @ApiModelProperty(value="投保人固定电话",name="policyholderTel",example="0755-88888888")
    private String policyholderTel;
    /**
     * 投保人手机号
     */
    @Length(max = 11)
    @ApiModelProperty(value="投保人手机号",name="policyholderMobile",example="18888888888")
    private String policyholderMobile;
    /**
     * 投保人邮箱
     */
    @Length(max = 32)
    @ApiModelProperty(value="投保人邮箱",name="policyholderEmail",example="45454545@qq.com")
    private String policyholderEmail;
    /**
     * 投保人所在省
     */
    @NotBlank
    @Length(max = 6)
    @ApiModelProperty(value="投保人所在省",name="policyholderProvince",example="广东省")
    private String policyholderProvince;
    /**
     * 投保人所在市
     */
    @NotBlank
    @Length(max = 6)
    @ApiModelProperty(value="投保人所在市",name="policyholderCity",example="深圳市")
    private String policyholderCity;
    /**
     * 投保人所在区
     */
    @NotBlank
    @Length(max = 6)
    @ApiModelProperty(value="投保人所在区",name="policyholderDistrict",example="龙岗区")
    private String policyholderDistrict;
    /**
     * 投保人详细地址
     */
    @NotBlank
    @Length(max = 255)
    @ApiModelProperty(value="投保人详细地址",name="policyholderAddress",example="广东省深圳市龙岗区坂田街道205室")
    private String policyholderAddress;
    /**
     * 涉税人身份信息
     */
    @NotBlank
    @Length(max = 64)
    @ApiModelProperty(value="涉税人身份信息",name="policyholderTaxRelated",example="涉税人身份信息")
    private String policyholderTaxRelated;
    /**
     * 投保人签名
     */
    @NotBlank
    @Length(max = 255)
    @ApiModelProperty(value="投保人签名",name="policyholderSign",example="张三")
    private String policyholderSign;
    /**
     * 投保人意见书签名
     */
    @NotBlank
    @Length(max = 255)
    @ApiModelProperty(value="投保人意见书签名",name="submissionSign",example="张三")
    private String submissionSign;
    /**
     * 投保人头像
     */
    @NotBlank
    @Length(max = 255)
    @ApiModelProperty(value="投保人头像",name="policyholderAvatar",example="xxx.jpg")
    private String policyholderAvatar;
    /**
     * 投保人身份证正面照片
     */
    @NotBlank
    @Length(max = 255)
    @ApiModelProperty(value="投保人身份证正面照片",name="cardPositiveImg",example="xxx.jpg")
    private String cardPositiveImg;
    /**
     * 投保人身份证反面照片
     */
    @NotBlank
    @Length(max = 255)
    @ApiModelProperty(value="投保人身份证反面照片",name="cardNegativeImg",example="xxx.jpg")
    private String cardNegativeImg;
    /**
     * 其他资料1
     */
    @Length(max = 255)
    @ApiModelProperty(value="其他资料1",name="otherImg1",example="其他资料1")
    private String otherImg1;
    /**
     * 其他资料2
     */
    @Length(max = 255)
    @ApiModelProperty(value="其他资料2",name="otherImg2",example="其他资料2")
    private String otherImg2;
    /**
     * 其他资料3
     */
    @Length(max = 255)
    @ApiModelProperty(value="其他资料3",name="otherImg3",example="其他资料3")
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