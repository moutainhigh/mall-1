package com.yunxin.cb.mall.entity;

/**
 * @title: 属性实体类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
public class Attribute {
    /** 属性ID */
    private Integer attributeId;

    /** 属性名 */
    private String attributeName;

    /** 属性图片路径 */
    private String imagePath;

    /** 排序 */
    private Short sortOrder;

    /** 属性组id */
    private Integer groupId;

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName == null ? null : attributeName.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}