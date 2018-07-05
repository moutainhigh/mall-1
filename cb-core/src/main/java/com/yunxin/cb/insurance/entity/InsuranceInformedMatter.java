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
    @Max(9999999999L)
    private int matterId;
    /**
     * 事项描述
     */
    @NotBlank
    @Length(max = 512)
    private String matterDescription;
    /**
     * 创建时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 序号
     */
    @Max(9999999999L)
    private int serNo;
    /**
     * 是否启用
     */
    @Max(9999999999L)
    private int enabled;
    /**
     * 所属组
     */
    private InsuranceInformedMatterGroup matterGroup;

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