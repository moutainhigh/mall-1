package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.LetterType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class SystemLetter implements Serializable {

    /**
     * id
     */
    private int letterId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 发布类型
     */
    private LetterType publishType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 发布状态
     */
    private boolean published;
    /**
     * 收件人
     */
    private String recipient;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    @Column(nullable = false, length = 128)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false, length = 3000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public LetterType getPublishType() {
        return publishType;
    }

    public void setPublishType(LetterType publishType) {
        this.publishType = publishType;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Column(nullable = false)
    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean publishStatus) {
        this.published = publishStatus;
    }

    @Column(length = 5000)
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
