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
 * 告知事项组
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceInformedMatterGroup implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 事项组ID
     */
    @ApiModelProperty(value="事项组ID",name="groupId",example="1")
    @Max(9999999999L)
    private int groupId;
    /**
     * 事项组描述
     */
    @ApiModelProperty(value="事项组描述",name="description",example="事项组描述")
    @NotBlank
    @Length(max = 512)
    private String description;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-07-08 20:10")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 序号
     */
    @ApiModelProperty(value="序号",name="serNo",example="111111")
    @Max(9999999999L)
    private int serNo;
    /**
     * 是否启用
     */
    @ApiModelProperty(value="是否启用",name="enabled",example="1")
    @Max(9999999999L)
    private int enabled;



    private Set<InsuranceInformedMatter> insuranceInformedMatters = new HashSet(0);
    //columns END


    public InsuranceInformedMatterGroup() {
    }

    public InsuranceInformedMatterGroup(
            int groupId
    ) {
        this.groupId = groupId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 512)
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



    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "matterGroup")
    public Set<InsuranceInformedMatter> getInsuranceInformedMatters() {
        return insuranceInformedMatters;
    }

    public void setInsuranceInformedMatters(Set<InsuranceInformedMatter> insuranceOrderInformedMatter) {
        this.insuranceInformedMatters = insuranceOrderInformedMatter;
    }


}