package com.yunxin.cb.vo;

import java.util.List;

public class AttributeVo {

    /**
     * 层次
     */
    private Integer layer;
    /**
     * 属性名
     */
    private String attributeName;

    /**
     * 属性Id
     */
    private String attributeId;

    /**
     * 组Id
     */
    private String groupId;
    /**
     * 组名
     */
    private String groupName;


    /**
     * 下层节点
     */
    private List<AttributeVo> items;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<AttributeVo> getItems() {
        return items;
    }

    public void setItems(List<AttributeVo> items) {
        this.items = items;
    }
}
