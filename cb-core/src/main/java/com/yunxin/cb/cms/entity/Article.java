package com.yunxin.cb.cms.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hankcs.lucene.HanLPIndexAnalyzer;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 文章
 * Created by gonglei on 16/1/14.
 */
//@Indexed(index = "Article")
//@Analyzer(impl = HanLPIndexAnalyzer.class)
//@Cacheable
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Article implements Serializable {

    private int articleId;

    /**
     * 所属栏目
     */
    private Programa programa;

    /**
     * 专属专题
     */
    private SpecialSubject specialSubject;
    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 文章编码
     */
    private String articleCode;

    /**
     * 简短标题
     */
    private String shortTitle;

    /**
     * 标题颜色
     */
    private String titleColor;

    /**
     * 图标路径
     */
    private String picPath;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 作者
     */
    private String author;

    /**
     * 来源
     */
    private String origin;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    /**
     * 排序时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;

    /***
     * 启用步骤
     */
    private boolean stepEnabled;

    private String[] stepName;

    private String[] stepDescription;

    private String[] stepPicPath;

    private int[] stepWhen;

    private int[] stepOrder;

    private String[] stepRemark;

    private List<ArticleRecipeStep> articleRecipeSteps = new ArrayList<>(0);

    @Id
    @DocumentId  /*以字段id作为搜索引擎文档id*/
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROGRAMA_ID", nullable = false)
    @IndexedEmbedded(depth = 1)
    public Programa getPrograma() {
        return programa;
    }


    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "SUBJECT_ID", nullable = true)
    public SpecialSubject getSpecialSubject() {
        return specialSubject;
    }

    public void setSpecialSubject(SpecialSubject specialSubject) {
        this.specialSubject = specialSubject;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = false, precision = 1)
    public boolean isStepEnabled() {
        return stepEnabled;
    }

    public void setStepEnabled(boolean stepEnabled) {
        this.stepEnabled = stepEnabled;
    }

    @Column(nullable = false, length = 512)
    @Field(index = Index.YES, analyze = Analyze.YES, store = org.hibernate.search.annotations.Store.COMPRESS)
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String title) {
        this.articleTitle = title;
    }

    @Column(length = 5000)
    @Field(index = Index.YES, analyze = Analyze.YES, store = org.hibernate.search.annotations.Store.COMPRESS)
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

    public void setCreateTime(Date creatTime) {
        this.createTime = creatTime;
    }

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, length = 128)
    @Field(index = Index.YES, analyze = Analyze.YES, store = org.hibernate.search.annotations.Store.COMPRESS)
    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    @Column(nullable = false, length = 16)
    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    @Column(nullable = false, length = 512)
    @Field(index = Index.YES, analyze = Analyze.YES, store = org.hibernate.search.annotations.Store.COMPRESS)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Column(nullable = false, length = 64)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(nullable = false, length = 64)
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Temporal(TemporalType.DATE)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Column(nullable = false, length = 255)
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Column(nullable = false, length = 32)
    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.REMOVE)
    public List<ArticleRecipeStep> getArticleRecipeSteps() {
        return articleRecipeSteps;
    }

    public void setArticleRecipeSteps(List<ArticleRecipeStep> articleRecipeSteps) {
        this.articleRecipeSteps = articleRecipeSteps;
    }

    @Transient
    public String[] getStepName() {
        return stepName;
    }

    public void setStepName(String[] stepName) {
        this.stepName = stepName;
    }

    @Transient
    public String[] getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String[] stepDescription) {
        this.stepDescription = stepDescription;
    }

    @Transient
    public String[] getStepPicPath() {
        return stepPicPath;
    }

    public void setStepPicPath(String[] stepPicPath) {
        this.stepPicPath = stepPicPath;
    }

    @Transient
    public int[] getStepWhen() {
        return stepWhen;
    }

    public void setStepWhen(int[] stepWhen) {
        this.stepWhen = stepWhen;
    }

    @Transient
    public int[] getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int[] stepOrder) {
        this.stepOrder = stepOrder;
    }

    @Transient
    public String[] getStepRemark() {
        return stepRemark;
    }

    public void setStepRemark(String[] stepRemark) {
        this.stepRemark = stepRemark;
    }
}
