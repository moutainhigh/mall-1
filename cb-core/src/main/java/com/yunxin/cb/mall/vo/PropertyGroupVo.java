package com.yunxin.cb.mall.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertyGroupVo {

    private int groupId;
    /**
     * 属性组名称
     */
    private String groupName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否为规格属性
     */
    private boolean spec;
    /**
     * 属性组类型 1：商品属性 2：定制属性
     */
    private int groupType;
    /**
     * 属性
     */
    private List<PropertyVo> propertis = new ArrayList<PropertyVo>();

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isSpec() {
        return spec;
    }

    public void setSpec(boolean spec) {
        this.spec = spec;
    }

    public List<PropertyVo> getPropertis() {
        return propertis;
    }

    public void setPropertis(List<PropertyVo> propertis) {
        this.propertis = propertis;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }


}
