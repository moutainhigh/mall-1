/**
 *
 */
package com.yunxin.cb.system.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.system.meta.PushStatus;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.DocumentId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 消息
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Message implements java.io.Serializable {

    /***/
    private static final long serialVersionUID = -3113613325145218113L;

    /**
     * 消息ID
     */
    private int messageId;
    /**
     * 消息推送标题
     */
    private String pushTitle;

    /**
     * 消息摘要（文字）
     */
    private String messageDigest;

    /**
     * 摘要图片（路径）
     */
    private String digestPic;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 推送状态
     */
    private PushStatus pushStatus;

    /**
     * 推送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pushTime;

    public Message() {
    }

    @Id
    @DocumentId  /*以字段id作为搜索引擎文档id*/
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    @Column(nullable = true, length = 4098)
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 128, nullable = false, unique = true)
    @Enumerated(value = EnumType.ORDINAL)//如果枚举值为int类型，则结果封装时按照枚举类中的元素下标进行取值/封装
    public PushStatus getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(PushStatus pushStatus) {
        this.pushStatus = pushStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    @Column(nullable = true, length = 4098)
    public String getMessageDigest() {
        return messageDigest;
    }

    public void setMessageDigest(String messageDigest) {
        this.messageDigest = messageDigest;
    }

    @Column(length = 200)
    public String getDigestPic() {
        return digestPic;
    }

    public void setDigestPic(String digestPic) {
        this.digestPic = digestPic;
    }
}
