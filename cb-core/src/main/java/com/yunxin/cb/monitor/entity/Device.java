package com.yunxin.cb.monitor.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.monitor.entity.meta.DeviceState;
import com.yunxin.cb.monitor.entity.meta.DeviceType;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import com.yunxin.core.web.json.serializer.JsonTimestampNotSecondSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by gonglei on 16/1/5.
 * 厨电设备
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Device implements java.io.Serializable {


    private int deviceId;

    private Concent concent;

    /**
     * 设备ID编号
     */
    private String deviceCode;

    /**
     * 设备类型
     */
    private DeviceType deviceType;

    /**
     * 设备状态
     */
    private DeviceState deviceState;

    /**
     * 生产日期
     */
    private Date factoryDate;

    /**
     * 生成时间
     */
    private Date createTime;

    /**
     * 设备版本号
     */
    private String version;
    /**
     * 在线
     */
    private boolean online;

    private Seller seller;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONCENT_ID", nullable = false)
    public Concent getConcent() {
        return concent;
    }

    public void setConcent(Concent concent) {
        this.concent = concent;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampNotSecondSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 2, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @Column(length = 2, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public DeviceState getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(DeviceState deviceState) {
        this.deviceState = deviceState;
    }

    @Column(length = 32, nullable = true)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(length = 32, nullable = false)
    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(length = 7, nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    @Column(precision = 1, nullable = false)
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID", nullable = false)
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
