package com.yunxin.cb.mall.entity;

/**
 * @title: 属性实体类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
public class Attribute implements java.io.Serializable, Comparable {

    private static final long serialVersionUID = 3560156621440619711L;
    /** 属性ID */
    private Integer attributeId;

    /** 属性名 */
    private String attributeName;

    /** 属性图片路径 */
    private String imagePath;

    /** 排序 */
    private short sortOrder;

    /** 属性组id */
    private Integer groupId;

    //属性组
    private AttributeGroup attributeGroup;

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public AttributeGroup getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(AttributeGroup attributeGroup) {
        this.attributeGroup = attributeGroup;
    }

    public short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(short sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compareTo(Object o) {
        Attribute o1 = (Attribute) o;
        if (sortOrder > o1.getSortOrder()) {
            return 1;
        } else if (sortOrder == o1.getSortOrder()) {
            return 0;
        } else {
            return -1;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        return attributeId == attribute.attributeId;

    }

    @Override
    public int hashCode() {
        return attributeId;
    }
}