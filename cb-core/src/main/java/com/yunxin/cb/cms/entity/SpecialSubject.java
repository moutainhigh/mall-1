package com.yunxin.cb.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by gonglei on 16/1/26.
 * 专题
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class SpecialSubject implements java.io.Serializable {

    private static final long serialVersionUID = -2468929513432015076L;

    private int subjectId;
    /**
     * 标题
     */
    private String subjectName;
    /**
     * 简短标题
     */
    private String shortName;

    /**
     * 所属栏目
     */
    private Programa programa;
    /**
     * 备注
     */
    private String description;
    /**
     * 是否推荐
     */
    private boolean recommend;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;

    /**
     * 排序号
     */
    private int sortOrder;

    /**
     * 文章数量
     */
    private int articleAmount;

    /**
     * 发布日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    /**
     * 排序时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;

    private Date createTime;

    private Set<Article> articles = new HashSet<>();


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Column(unique = true, nullable = false, length = 255)
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Column(nullable = false, length = 128)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROGRAMA_ID", nullable = false)
    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Column(nullable = true, length = 512)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    @Column(nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialSubject", cascade = CascadeType.REMOVE)
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Column(nullable = false)
    public int getArticleAmount() {
        return articleAmount;
    }

    public void setArticleAmount(int articleAmount) {
        this.articleAmount = articleAmount;
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

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
