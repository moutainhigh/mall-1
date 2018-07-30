package com.yunxin.cb.mall.entity;

import java.util.*;

/**
 * @title: 属性组实体类
 * @auther: eleven
 * @date: 2018/7/18 17:43
 */
public class AttributeGroup implements java.io.Serializable {
    private static final long serialVersionUID = -8474655123593357628L;
    /** 属性组id */
    private Integer groupId;

    /** 创建时间 */
    private Date createTime;

    /** 属性组名称 */
    private String groupName;

    /** 以图片方式显示 */
    private Boolean showAsImage;

    /** 商品id */
    private Integer commodityId;

    private Set<Attribute> attributes = new HashSet<>();

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Boolean getShowAsImage() {
        return showAsImage;
    }

    public void setShowAsImage(Boolean showAsImage) {
        this.showAsImage = showAsImage;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeGroup that = (AttributeGroup) o;

        return groupId == that.groupId;

    }

    @Override
    public int hashCode() {
        return groupId;
    }
}