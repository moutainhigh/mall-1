package com.yunxin.cb.mall.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 文件表
 * @auther: eleven
 * @date: 2018/7/25 10:26
 */
public class Attachment implements Serializable {
    private static final long serialVersionUID = 7169924732520516883L;
    /**  */
    private Integer attachId;

    /** 业务对象类型 */
    private String objectType;

    /** 业务对象ID */
    private Integer objectId;

    /** 业务应用场景编码 */
    private String businessScenario;

    /** 文件名ID */
    private String inputId;

    /** 访问路径 */
    private String filePath;

    /** 附件名称 */
    private String fileName;

    /** 文件类型 */
    private String fileType;

    /** 文件大小 */
    private Integer fileSize;

    /** 文件存储系统标识 */
    private String fsGuid;

    /** 上传时间 */
    private Date createTime;

    /** 上传人ID */
    private Integer staffId;

    /** 上传人 */
    private String staffName;

    /** 状态 */
    private String state;

    /** 描述 */
    private String description;

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getBusinessScenario() {
        return businessScenario;
    }

    public void setBusinessScenario(String businessScenario) {
        this.businessScenario = businessScenario == null ? null : businessScenario.trim();
    }

    public String getInputId() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId == null ? null : inputId.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFsGuid() {
        return fsGuid;
    }

    public void setFsGuid(String fsGuid) {
        this.fsGuid = fsGuid == null ? null : fsGuid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}