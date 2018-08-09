package com.yunxin.cb.mall.entity;

import java.io.Serializable;

/**
 * @title: 配置文件实体类
 * @auther: eleven
 * @date: 2018/8/8 15:59
 */
public class Profile implements Serializable {
    private static final long serialVersionUID = 919642410860076127L;
    /** ID */
    private Integer fileId;

    /** 名称 */
    private String profileName;

    /** 值 */
    private String fileValue;

    /** 是否是图片 */
    private Integer isPicture;

    /** 备注 */
    private String remarks;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName == null ? null : profileName.trim();
    }

    public String getFileValue() {
        return fileValue;
    }

    public void setFileValue(String fileValue) {
        this.fileValue = fileValue == null ? null : fileValue.trim();
    }

    public Integer getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(Integer isPicture) {
        this.isPicture = isPicture;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}