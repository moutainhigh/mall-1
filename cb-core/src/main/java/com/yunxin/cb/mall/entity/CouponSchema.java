package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.CouponDistributionMethod;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Administrator on 2016/1/7.
 * 优惠券方案
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class CouponSchema implements java.io.Serializable {

    private int schemaId;
    /**
     * 编号
     */
    private String schemeNo;
    /**
     * 方案名称
     */
    private String schemeName;
    /**
     * 方案发放方式
     */
    private CouponDistributionMethod distributionMethod;
    /**
     * 面值
     */
    private double faceValue;
    /**
     * 发放数量
     */
    private int distributionNum;
    /**
     * 最低消费
     */
    private double lowestConsume;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否限定商品
     */
    private boolean limitCommodity;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 备注
     */
    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(int schemaId) {
        this.schemaId = schemaId;
    }

    @Column(nullable = false, length = 32)
    public String getSchemeNo() {
        return schemeNo;
    }

    public void setSchemeNo(String schemeNo) {
        this.schemeNo = schemeNo;
    }

    @Column(nullable = false, length = 128)
    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public CouponDistributionMethod getDistributionMethod() {
        return distributionMethod;
    }

    public void setDistributionMethod(CouponDistributionMethod distributionMethod) {
        this.distributionMethod = distributionMethod;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public double getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(double faceValue) {
        this.faceValue = faceValue;
    }

    @Column(nullable = false, precision = 12)
    public int getDistributionNum() {
        return distributionNum;
    }

    public void setDistributionNum(int distributionNum) {
        this.distributionNum = distributionNum;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public double getLowestConsume() {
        return lowestConsume;
    }

    public void setLowestConsume(double lowestConsume) {
        this.lowestConsume = lowestConsume;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(nullable = false, precision = 1)
    public boolean isLimitCommodity() {
        return limitCommodity;
    }

    public void setLimitCommodity(boolean limitCommodity) {
        this.limitCommodity = limitCommodity;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(length = 32)
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(length = 256)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
