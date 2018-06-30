package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.cb.mall.entity.meta.AdvertisementType;
import com.yunxin.cb.mall.entity.meta.AdvertisementURLType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 广告
 * Created by chenxing on 2016/1/15.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Advertisement {

    private int advertId;

    /**
     * 标题
     */
    private String advertTitle;

    /**
     * 广告编码，用于前台网页的标记
     */
    private String advertCode;

    /**
     * 广告类型
     */
    private AdvertisementType advertisementType;

    /**
     * 客户端类型
     */
    private String clientTypes;

    /**
     * 广告位
     */
    private AdvertisementPlace advertisementPlace;

    /**
     * 视频路径
     */
    private String videoPath;

    /**
     * 广告URL类型
     */
    private AdvertisementURLType advertisementURLType;

    /**
     * 广告URL
     */
    private String advertURL;

    /**
     * 广告图片路径
     */
    private String picPath;

    /**
     * 内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;

    private String templatePath;

    private String[] clientTypesTemporary;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getAdvertId() {
        return advertId;
    }

    public void setAdvertId(int advertId) {
        this.advertId = advertId;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = false, length = 64)
    public String getAdvertCode() {
        return advertCode;
    }

    public void setAdvertCode(String advertCode) {
        this.advertCode = advertCode;
    }

    @Column(nullable = false, length = 512)
    public String getAdvertTitle() {
        return advertTitle;
    }

    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public AdvertisementType getAdvertisementType() {
        return advertisementType;
    }

    public void setAdvertisementType(AdvertisementType advertisementType) {
        this.advertisementType = advertisementType;
    }

    @Column(length = 5000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, length = 512)
    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }


    @Column(length = 255)
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Column(length = 512)
    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public AdvertisementPlace getAdvertisementPlace() {
        return advertisementPlace;
    }

    public void setAdvertisementPlace(AdvertisementPlace advertisementPlace) {
        this.advertisementPlace = advertisementPlace;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public AdvertisementURLType getAdvertisementURLType() {
        return advertisementURLType;
    }

    public void setAdvertisementURLType(AdvertisementURLType advertisementURLType) {
        this.advertisementURLType = advertisementURLType;
    }


    @Column(length = 512)
    public String getAdvertURL() {
        return advertURL;
    }

    public void setAdvertURL(String advertURL) {
        this.advertURL = advertURL;
    }

    @Column(nullable = false, length = 64)
    public String getClientTypes() {
        return clientTypes;
    }

    public void setClientTypes(String clientTypes) {
        this.clientTypes = clientTypes;
    }

    @Transient
    public String[] getClientTypesTemporary() {
        return clientTypesTemporary;
    }

    public void setClientTypesTemporary(String[] clientTypesTemporary) {
        this.clientTypesTemporary = clientTypesTemporary;
    }
}
