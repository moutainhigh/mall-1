package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.ArticleType;
import com.yunxin.cb.mall.entity.meta.Unit;
import com.yunxin.core.web.json.deserializer.JsonTimestampDeserializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Aidy_He on 16/9/22.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Fridge implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int fridgeId;

    /**
     * 会员
     */
    private Customer customer;

    /**
     * 物品类型
     */
    private ArticleType articleType;

    /**
     * 物品名称
     */
    private String articleName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 保质期
     */
    private Date shelfLife;

    /**
     * 单位
     */
    private Unit unit;

    /**
     * 重量
     */
    private int weight;

    /**
     * 备注
     */
    private String remark;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    @Column(nullable = false, length = 32)
    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Column(precision = 4)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
