package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @title: 属性VO
 * @auther: eleven
 * @date: 2018/7/28 17:24
 */
@ApiModel(value="属性VO",description="属性VO对象 AttributeGroup")
public class AttributeVO implements java.io.Serializable, Comparable {

    private static final long serialVersionUID = -3847918277378475479L;

    /** 属性ID */
    @ApiModelProperty(value="属性ID",name="attributeId",example="1")
    private Integer attributeId;

    /** 属性名 */
    @ApiModelProperty(value="属性名",name="attributeName",example="白色")
    private String attributeName;

    /** 属性图片路径 */
    @ApiModelProperty(value="属性图片路径",name="imagePath",example="att.jpg")
    private String imagePath;

    /** 排序 */
    @ApiModelProperty(value="排序",name="sortOrder",example="1")
    private short sortOrder;

    /** 属性组id */
    @ApiModelProperty(value="属性组id",name="groupId",example="1")
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
        this.attributeName = attributeName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(short sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "AttributeVO{" +
                "attributeId=" + attributeId +
                ", attributeName='" + attributeName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", sortOrder=" + sortOrder +
                ", groupId=" + groupId +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        AttributeVO o1 = (AttributeVO) o;
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

        AttributeVO attribute = (AttributeVO) o;

        return attributeId == attribute.attributeId;

    }

    @Override
    public int hashCode() {
        return attributeId;
    }
}
