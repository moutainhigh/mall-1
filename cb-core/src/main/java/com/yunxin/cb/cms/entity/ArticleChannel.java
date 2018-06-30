package com.yunxin.cb.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.cms.entity.meta.ChannelPosition;
import com.yunxin.cb.cms.entity.meta.ChannelStyle;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 频道
 * Created by chenxing on 2016/1/15.
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class ArticleChannel implements java.io.Serializable {
    private static final long serialVersionUID = -2468929515432015076L;
    /**
     * ID
     */
    private int channelId;

    /**
     * 频道名称
     */
    private String channelName;

    /**
     * 频道编码
     **/
    private String channelCode;

    /**
     * 频道类型
     **/
    private String articleChannelTypes;

    /**
     * 提示名称
     */
    private String tipName;

    /**
     * 频道关键字
     */
    private String channelKey;

    /**
     *
     */
    private List<Programa> programas = new ArrayList<Programa>(0);

    /**
     * 排序号
     */
    private int sortOrder;

    /***
     * 频道位置
     */
    private ChannelPosition channelPosition;

    /**
     * 频道前台风格.
     * 当"横幅显示"时,需要用户输入channelVideoURL.
     * 当"简单显示"时,需要用户输入channelURL.
     */
    private ChannelStyle channelStyle;

    /**
     * 外部视频URL
     */
    private String channelVideoURL;

    /**
     * 跳转URL
     */
    private String channelURL;


    /**
     * 频道描述
     */
    private String description;

    /**
     * SEO关键字
     */
    private String seoKey;

    /**
     * SEO标题
     */
    private String seoTitle;

    /**
     * SEO描述
     */
    private String seoDescription;

    /**
     * 分类图片路径
     */
    private String operaImgPath;

    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;

    /**
     * 创建时间
     */
    private Date createTime;


    private String[] channelTypes;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int id) {
        this.channelId = id;
    }


    @Column(nullable = false, length = 64)
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String categoryName) {
        this.channelName = categoryName;
    }

    @Column(nullable = true, length = 32)
    public String getTipName() {
        return tipName;
    }

    public void setTipName(String tipName) {
        this.tipName = tipName;
    }

    @Column(nullable = false, length = 128)
    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    @Column(nullable = false, precision = 4)
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortNo) {
        this.sortOrder = sortNo;
    }

    @Column(length = 512, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(length = 128, nullable = false)
    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

    @Column(length = 256, nullable = false)
    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    @Column(length = 512)
    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    @Column(length = 256)
    public String getOperaImgPath() {
        return operaImgPath;
    }

    public void setOperaImgPath(String operaImgPath) {
        this.operaImgPath = operaImgPath;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    public ChannelStyle getChannelStyle() {
        return channelStyle;
    }

    public void setChannelStyle(ChannelStyle channelStyle) {
        this.channelStyle = channelStyle;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    public ChannelPosition getChannelPosition() {
        return channelPosition;
    }

    public void setChannelPosition(ChannelPosition channelPosition) {
        this.channelPosition = channelPosition;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articleChannel", cascade = CascadeType.REMOVE)
    public List<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programa> articleCategories) {
        this.programas = articleCategories;
    }

    @Column(nullable = false, length = 32)
    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    @Column(nullable = false, length = 255)
    public String getArticleChannelTypes() {
        return articleChannelTypes;
    }

    public void setArticleChannelTypes(String articleChannelTypes) {
        this.articleChannelTypes = articleChannelTypes;
    }

    @Transient
    public String[] getChannelTypes() {
        return channelTypes;
    }

    public void setChannelTypes(String[] channelTypes) {
        this.channelTypes = channelTypes;
    }

    @Column(length = 512)
    public String getChannelVideoURL() {
        return channelVideoURL;
    }

    public void setChannelVideoURL(String channelVideoURL) {
        this.channelVideoURL = channelVideoURL;
    }

    @Column(length = 512)
    public String getChannelURL() {
        return channelURL;
    }

    public void setChannelURL(String channelURL) {
        this.channelURL = channelURL;
    }
}
