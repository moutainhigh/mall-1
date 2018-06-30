package com.yunxin.cb.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 文章栏目
 * Created by chenxing on 2016/1/15.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Programa implements java.io.Serializable {
    private static final long serialVersionUID = -2468929515432015076L;
    /**
     * ID
     */
    private int programaId;

    /**
     * 栏目名称
     */
    private String programaName;

    /**
     * 频道编码
     **/
    private String programaCode;
    /**
     * 频道
     */
    private ArticleChannel articleChannel;

    /**
     *
     */
    private List<SpecialSubject> specialSubjects = new ArrayList<>(0);

    /**
     * 排序号
     */
    private int sortOrder;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 栏目描述
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
     * 是否推荐
     */
    private boolean recommend;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 文章数量
     */
    private int articleAmount;

    private Set<Article> articles = new HashSet<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getProgramaId() {
        return programaId;
    }

    public void setProgramaId(int programaId) {
        this.programaId = programaId;
    }

    @Column(nullable = false, length = 64)
    public String getProgramaName() {
        return programaName;
    }

    public void setProgramaName(String categoryName) {
        this.programaName = categoryName;
    }

    @Column(nullable = false, length = 32)
    public String getProgramaCode() {
        return programaCode;
    }

    public void setProgramaCode(String programaCode) {
        this.programaCode = programaCode;
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

    @Column(length = 128)
    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

    @Column(length = 255)
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

    @Column(nullable = false, precision = 1)
    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommand) {
        this.recommend = recommand;
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


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CHANNEL_ID", nullable = false)
    public ArticleChannel getArticleChannel() {
        return articleChannel;
    }

    public void setArticleChannel(ArticleChannel parentArticleChannel) {
        this.articleChannel = parentArticleChannel;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programa", cascade = CascadeType.REMOVE)
    public List<SpecialSubject> getSpecialSubjects() {
        return specialSubjects;
    }

    public void setSpecialSubjects(List<SpecialSubject> articleCategories) {
        this.specialSubjects = articleCategories;
    }

    @Column(nullable = false)
    public int getArticleAmount() {
        return articleAmount;
    }

    public void setArticleAmount(int articleAmount) {
        this.articleAmount = articleAmount;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programa", cascade = CascadeType.REMOVE)
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Column(length = 32)
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
