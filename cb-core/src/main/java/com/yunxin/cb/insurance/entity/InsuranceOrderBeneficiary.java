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

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 保险订单受益人
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceOrderBeneficiary implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 受益人ID
     */
    @ApiModelProperty(value="受益人ID",name="beneficiaryId",example="1")
    @Max(9999999999L)
    private int beneficiaryId;
    /**
     * 订单
     */
    @ApiModelProperty(value="订单",name="insuranceOrder",example="订单")
    @NotNull
    private InsuranceOrder insuranceOrder;
    /**
     * 受益人姓名
     */
    @ApiModelProperty(value="受益人姓名",name="beneficiaryName",example="张三")
    @NotBlank
    @Length(max = 32)
    private String beneficiaryName;
    /**
     * 受益人性别
     */
    @ApiModelProperty(value="受益人性别",name="beneficiaryGender",example="男")
    @NotBlank
    @Length(max = 16)
    private String beneficiaryGender;
    /**
     * 受益人生日
     */
    @ApiModelProperty(value="受益人生日",name="beneficiaryBirthday",example="1990-05-05 20:14")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beneficiaryBirthday;
    /**
     * 受益人职业
     */
    @ApiModelProperty(value="受益人职业",name="beneficiaryCareer",example="教师")
    @Length(max = 32)
    private String beneficiaryCareer;
    /**
     * 受益人证件类型
     */
    @ApiModelProperty(value="受益人证件类型",name="beneficiaryCardType",example="身份证")
    @NotBlank
    @Length(max = 32)
    private String beneficiaryCardType;
    /**
     * 受益人证件号
     */
    @ApiModelProperty(value="受益人证件号",name="beneficiaryCardNo",example="4538754786543245324")
    @NotBlank
    @Length(max = 18)
    private String beneficiaryCardNo;
    /**
     * 收益顺序
     */
    @ApiModelProperty(value="收益顺序",name="beneficiaryOrder",example="1")
    private int beneficiaryOrder;
    /**
     * 收益份额
     */
    @ApiModelProperty(value="收益份额",name="beneficiaryProportion",example="1")
    private int beneficiaryProportion;
    /**
     * 受益人国籍
     */
    @ApiModelProperty(value="受益人国籍",name="beneficiaryCountry",example="中国")
    @NotBlank
    @Length(max = 64)
    private String beneficiaryCountry;
    /**
     * 受益人证件有效期
     */
    @ApiModelProperty(value="受益人证件有效期",name="beneficiaryCardPeroid",example="2020-10-20 20:20")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beneficiaryCardPeroid;
    /**
     * 受益人身高
     */
    @ApiModelProperty(value="受益人身高",name="beneficiaryHeight",example="185")
    @Max(9999999999L)
    private int beneficiaryHeight;
    /**
     * 受益人体重
     */
    @ApiModelProperty(value="受益人体重",name="beneficiaryBodyWeight",example="98")
    private Float beneficiaryBodyWeight;
    /**
     * 受益人收入
     */
    @ApiModelProperty(value="受益人收入",name="beneficiaryIncome",example="5000")
    @NotNull
    @Max(9999999999L)
    private int beneficiaryIncome;
    /**
     * 受益人婚否
     */
    @ApiModelProperty(value="受益人婚否",name="beneficiaryMarriage",example="已婚")
    @Length(max = 32)
    private String beneficiaryMarriage;
    /**
     * 受益人固定电话
     */
    @ApiModelProperty(value="受益人固定电话",name="beneficiaryTel",example="0755-88888888")
    @Length(max = 15)
    private String beneficiaryTel;
    /**
     * 受益人手机号
     */
    @ApiModelProperty(value="受益人手机号",name="beneficiaryMobile",example="18888888888")
    @Length(max = 11)
    private String beneficiaryMobile;
    /**
     * 受益人邮箱
     */
    @ApiModelProperty(value="受益人邮箱",name="beneficiaryEmail",example="56456456@qq.com")
    @Length(max = 32)
    private String beneficiaryEmail;
    /**
     * 受益人所在省
     */
    @ApiModelProperty(value="受益人所在省",name="beneficiaryProvince",example="广东省")
    @Length(max = 6)
    private String beneficiaryProvince;
    /**
     * 受益人所在市
     */
    @ApiModelProperty(value="受益人所在市",name="beneficiaryCity",example="深圳市")
    @Length(max = 6)
    private String beneficiaryCity;
    /**
     * 受益人所在区
     */
    @ApiModelProperty(value="受益人所在区",name="beneficiaryDistrict",example="龙岗区")
    @Length(max = 6)
    private String beneficiaryDistrict;
    /**
     * 受益人详细地址
     */
    @ApiModelProperty(value="受益人详细地址",name="beneficiaryAddress",example="广东省深圳市龙岗区坂田街道205号")
    @Length(max = 255)
    private String beneficiaryAddress;
    /**
     * 与被保人关系
     */
    @ApiModelProperty(value="与被保人关系",name="insuredRelation",example="兄弟")
    @NotBlank
    @Length(max = 32)
    private String insuredRelation;
    //columns END


    public InsuranceOrderBeneficiary() {
    }

    public InsuranceOrderBeneficiary(
            int beneficiaryId
    ) {
        this.beneficiaryId = beneficiaryId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getBeneficiaryId() {
        return this.beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }



    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 16)
    public String getBeneficiaryGender() {
        return this.beneficiaryGender;
    }

    public void setBeneficiaryGender(String beneficiaryGender) {
        this.beneficiaryGender = beneficiaryGender;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public Date getBeneficiaryBirthday() {
        return this.beneficiaryBirthday;
    }

    public void setBeneficiaryBirthday(Date beneficiaryBirthday) {
        this.beneficiaryBirthday = beneficiaryBirthday;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getBeneficiaryCareer() {
        return this.beneficiaryCareer;
    }

    public void setBeneficiaryCareer(String beneficiaryCareer) {
        this.beneficiaryCareer = beneficiaryCareer;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getBeneficiaryCardType() {
        return this.beneficiaryCardType;
    }

    public void setBeneficiaryCardType(String beneficiaryCardType) {
        this.beneficiaryCardType = beneficiaryCardType;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 18)
    public String getBeneficiaryCardNo() {
        return this.beneficiaryCardNo;
    }

    public void setBeneficiaryCardNo(String beneficiaryCardNo) {
        this.beneficiaryCardNo = beneficiaryCardNo;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    public String getBeneficiaryCountry() {
        return this.beneficiaryCountry;
    }

    public void setBeneficiaryCountry(String beneficiaryCountry) {
        this.beneficiaryCountry = beneficiaryCountry;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public Date getBeneficiaryCardPeroid() {
        return this.beneficiaryCardPeroid;
    }

    public void setBeneficiaryCardPeroid(Date beneficiaryCardPeroid) {
        this.beneficiaryCardPeroid = beneficiaryCardPeroid;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public int getBeneficiaryHeight() {
        return this.beneficiaryHeight;
    }

    public void setBeneficiaryHeight(int beneficiaryHeight) {
        this.beneficiaryHeight = beneficiaryHeight;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 12)
    public Float getBeneficiaryBodyWeight() {
        return this.beneficiaryBodyWeight;
    }

    public void setBeneficiaryBodyWeight(Float beneficiaryBodyWeight) {
        this.beneficiaryBodyWeight = beneficiaryBodyWeight;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public int getBeneficiaryIncome() {
        return this.beneficiaryIncome;
    }

    public void setBeneficiaryIncome(int beneficiaryIncome) {
        this.beneficiaryIncome = beneficiaryIncome;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getBeneficiaryMarriage() {
        return this.beneficiaryMarriage;
    }

    public void setBeneficiaryMarriage(String beneficiaryMarriage) {
        this.beneficiaryMarriage = beneficiaryMarriage;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 15)
    public String getBeneficiaryTel() {
        return this.beneficiaryTel;
    }

    public void setBeneficiaryTel(String beneficiaryTel) {
        this.beneficiaryTel = beneficiaryTel;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 11)
    public String getBeneficiaryMobile() {
        return this.beneficiaryMobile;
    }

    public void setBeneficiaryMobile(String beneficiaryMobile) {
        this.beneficiaryMobile = beneficiaryMobile;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    public String getBeneficiaryEmail() {
        return this.beneficiaryEmail;
    }

    public void setBeneficiaryEmail(String beneficiaryEmail) {
        this.beneficiaryEmail = beneficiaryEmail;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getBeneficiaryProvince() {
        return this.beneficiaryProvince;
    }

    public void setBeneficiaryProvince(String beneficiaryProvince) {
        this.beneficiaryProvince = beneficiaryProvince;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getBeneficiaryCity() {
        return this.beneficiaryCity;
    }

    public void setBeneficiaryCity(String beneficiaryCity) {
        this.beneficiaryCity = beneficiaryCity;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
    public String getBeneficiaryDistrict() {
        return this.beneficiaryDistrict;
    }

    public void setBeneficiaryDistrict(String beneficiaryDistrict) {
        this.beneficiaryDistrict = beneficiaryDistrict;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    public String getBeneficiaryAddress() {
        return this.beneficiaryAddress;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getInsuredRelation() {
        return this.insuredRelation;
    }

    public void setInsuredRelation(String insuredRelation) {
        this.insuredRelation = insuredRelation;
    }

    @Column
    public int getBeneficiaryOrder() {
        return beneficiaryOrder;
    }

    public void setBeneficiaryOrder(int beneficiaryOrder) {
        this.beneficiaryOrder = beneficiaryOrder;
    }

    @Column
    public int getBeneficiaryProportion() {
        return beneficiaryProportion;
    }

    public void setBeneficiaryProportion(int beneficiaryProportion) {
        this.beneficiaryProportion = beneficiaryProportion;
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