/**
 *
 */
package com.yunxin.cb.system.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.system.meta.PushStatus;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "message")
@DynamicInsert
@DynamicUpdate
public class Message implements java.io.Serializable {

    /***/
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @ApiModelProperty(value="消息ID",name="messageId",example="消息ID")
    private int messageId;
    /**
     * 消息推送标题
     */
    @ApiModelProperty(value="消息推送标题",name="pushTitle",example="消息推送标题")
    private String pushTitle;

    /**
     * 消息摘要
     */
    @ApiModelProperty(value="消息摘要(文字)",name="messageDigest",example="消息摘要（文字）")
    private String messageDigest;

    /**
     * 消息摘要
     */
    @ApiModelProperty(value="消息摘要图片（路径）",name="digestPic",example="消息摘要图片（路径）")
    private String digestPic;

    /**
     * 消息内容
     */
    @ApiModelProperty(value="消息内容",name="messageContent",example="消息内容")
    private String messageContent;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-08-10 14:38:30")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 推送状态
     */
    @ApiModelProperty(value="推送状态",name="pushStatus",example="推送状态：0/1")
    private PushStatus pushStatus;

    /**
     * 推送时间
     */
    @ApiModelProperty(value="推送时间",name="pushTime",example="2018-08-10 14:38:30")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pushTime;

    /**
     * 消息发送人
     */
    @ApiModelProperty(value="消息发送人",name="messageDespatcher",example="消息发送人")
    private String messageDespatcher;

    public Message() {
    }

    @Id
    @DocumentId  /*以字段id作为搜索引擎文档id*/
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0, name = "message_id")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Column(nullable = true , name = "push_title")
    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    @Column(nullable = true,name = "message_content")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 128, nullable = false, unique = true, name = "push_status")
    @Enumerated(value = EnumType.ORDINAL)//如果枚举值为int类型，则结果封装时按照枚举类中的元素下标进行取值/封装
    public PushStatus getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(PushStatus pushStatus) {
        this.pushStatus = pushStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(name = "push_time")
    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    @Column(nullable = true, length = 4098, name = "message_digest")
    public String getMessageDigest() {
        return messageDigest;
    }

    public void setMessageDigest(String messageDigest) {
        this.messageDigest = messageDigest;
    }

    @Column(length = 4098, name = "digest_pic")
    public String getDigestPic() {
        return digestPic;
    }

    public void setDigestPic(String digestPic) {
        this.digestPic = digestPic;
    }

    @Column(nullable = true , name = "message_despatcher")
    public String getMessageDespatcher() {
        return messageDespatcher;
    }

    public void setMessageDespatcher(String messageDespatcher) {
        this.messageDespatcher = messageDespatcher;
    }
}
