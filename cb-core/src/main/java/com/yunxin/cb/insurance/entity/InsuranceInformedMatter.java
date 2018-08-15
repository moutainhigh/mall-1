/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

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
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 告知事项
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceInformedMatter implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 事项ID
     */
    @ApiModelProperty(value="事项ID",name="matterId",example="1")
    @Max(9999999999L)
    private int matterId;
    /**
     * 事项描述
     */
    @ApiModelProperty(value="事项描述",name="matterDescription",example="事项描述")
    @NotBlank
    @Length(max = 512)
    private String matterDescription;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-07-05 20:14")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 序号
     */
    @ApiModelProperty(value="序号",name="serNo",example="1")
    @Max(9999999999L)
    private int serNo;
    /**
     * 是否启用
     */
    @ApiModelProperty(value="是否启用",name="enabled",example="1")
    @Max(9999999999L)
    private int enabled;
    /**
     * 类型
     */
    @ApiModelProperty(value="类型",name="matterType",example="1")
    private int matterType;
    /**
     * 所属组
     */
    @ApiModelProperty(value="所属组",name="matterGroup",example="所属组")
    private InsuranceInformedMatterGroup matterGroup;

    @ApiModelProperty(value="投保人",name="insurePeople",example="0：不是；1：是")
    private int insurePeople;

    @ApiModelProperty(value="被保人",name="insuredPeople",example="0：不是；1：是")
    private int insuredPeople;

    private Set<InsuranceProduct> insuranceProducts = new HashSet(0);
    //columns END


    public InsuranceInformedMatter() {
    }

    public InsuranceInformedMatter(
            int matterId
    ) {
        this.matterId = matterId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getMatterId() {
        return this.matterId;
    }

    public void setMatterId(int matterId) {
        this.matterId = matterId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 512, columnDefinition = "COMMENT '描述'")
    public String getMatterDescription() {
        return this.matterDescription;
    }

    public void setMatterDescription(String matterDescription) {
        this.matterDescription = matterDescription;
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

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getSerNo() {
        return this.serNo;
    }

    public void setSerNo(int serNo) {
        this.serNo = serNo;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getEnabled() {
        return this.enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getInsurePeople() {
        return insurePeople;
    }

    public void setInsurePeople(int insurePeople) {
        this.insurePeople = insurePeople;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getInsuredPeople() {
        return insuredPeople;
    }

    public void setInsuredPeople(int insuredPeople) {
        this.insuredPeople = insuredPeople;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    public InsuranceInformedMatterGroup getMatterGroup() {
        return matterGroup;
    }

    public void setMatterGroup(InsuranceInformedMatterGroup matterGroup) {
        this.matterGroup = matterGroup;
    }

    private Set insuranceOrderInformedMatters = new HashSet(0);

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "insuranceInformedMatter")
    public Set<InsuranceOrderInformedMatter> getInsuranceOrderInformedMatters() {
        return insuranceOrderInformedMatters;
    }

    public void setInsuranceOrderInformedMatters(Set<InsuranceOrderInformedMatter> insuranceOrderInformedMatter) {
        this.insuranceOrderInformedMatters = insuranceOrderInformedMatter;
    }
    @Column(length = 10)
    public int getMatterType() {
        return matterType;
    }

    public void setMatterType(int matterType) {
        this.matterType = matterType;
    }

    @ManyToMany(targetEntity = InsuranceProduct.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "insurance_product_informed_matter", joinColumns = {@JoinColumn(name = "MATTER_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "PROD_ID", nullable = false, updatable = false)})
    public Set<InsuranceProduct> getInsuranceProducts() {
        return insuranceProducts;
    }

    public void setInsuranceProducts(Set<InsuranceProduct> insuranceProducts) {
        this.insuranceProducts = insuranceProducts;
    }
}