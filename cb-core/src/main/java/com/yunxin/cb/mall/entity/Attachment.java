/*
 * Powered By [microlink-framework]
 * Web Site: http://www.microlinktech.net
 * Since 2015 - 2017
 */


package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.AttachmentState;
import com.yunxin.cb.mall.entity.meta.FileType;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.core.web.json.deserializer.JsonTimestampDeserializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 附件
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 附件Id
     */
    private int attachId;
    /**
     * 业务对象类型
     */
    @NotNull
    private ObjectType objectType;
    /**
     * 业务对象ID
     */
    private int objectId;
    /**
     * 业务应用场景编码
     * 如：指标ID等于1：commodity:1 or 指标ID等于1评论等于1：commodity:1:sku:1
     */
    @NotBlank
    @Length(max = 255)
    private String businessScenario;
    /**
     * 访问路径
     */
    @NotBlank
    @Length(max = 512)
    private String filePath;
    /**
     * 附件名称
     */
    @NotBlank
    @Length(max = 255)
    private String fileName;
    /**
     * 文件类型(文档.压缩.图片,视频,其他)
     */
    @NotNull
    private FileType fileType;
    /**
     * 文件大小
     */
    private int fileSize;
    /**
     * 文件存储系统标识
     */
    @NotBlank
    @Length(max = 32)
    private String fsGuid;
    /**
     * 上传时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 上传人ID
     */
    private int staffId;
    /**
     * 上传人
     */
    @NotBlank
    @Length(max = 32)
    private String staffName;
    /**
     * 状态
     */
    @NotNull
    private AttachmentState state;
    /**
     * 描述
     */
    @Length(max = 1024)
    private String description;
    //columns END


    public Attachment() {
    }

    public Attachment(
            int attachId
    ) {
        this.attachId = attachId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, length = 10)
    public int getAttachId() {
        return this.attachId;
    }

    public void setAttachId(int attachId) {
        this.attachId = attachId;
    }

    @Column(unique = false, nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    public ObjectType getObjectType() {
        return this.objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    @Column(unique = false, nullable = false, length = 10)
    public int getObjectId() {
        return this.objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    @Column(unique = false, nullable = false, length = 255)
    public String getBusinessScenario() {
        return this.businessScenario;
    }

    public void setBusinessScenario(String businessScenario) {
        this.businessScenario = businessScenario;
    }

    @Column(unique = false, nullable = false, length = 512)
    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Column(unique = false, nullable = false, length = 255)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(unique = false, nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    public FileType getFileType() {
        return this.fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Column(unique = false, nullable = false, length = 10)
    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Column(unique = false, nullable = false, length = 32)
    public String getFsGuid() {
        return this.fsGuid;
    }

    public void setFsGuid(String fsGuid) {
        this.fsGuid = fsGuid;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    @Column(unique = false, nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(unique = false, nullable = false, length = 10)
    public int getStaffId() {
        return this.staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Column(unique = false, nullable = false, length = 32)
    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(unique = false, nullable = true, length = 32)
    @Enumerated(EnumType.STRING)
    public AttachmentState getState() {
        return this.state;
    }

    public void setState(AttachmentState state) {
        this.state = state;
    }

    @Column(unique = false, nullable = true, length = 1024)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}