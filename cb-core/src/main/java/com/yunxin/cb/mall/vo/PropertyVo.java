package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.AttributeType;

public class PropertyVo {


    /**
     * 属性ID
     */
    private int propId;

    /**
     * 属性名
     */
    private String propName;

    /**
     * 属性编码
     */
    private String propCode;

    /**
     * 属性类型（多选，单选，输入）PROP_TYPE
     */
    private AttributeType attributeType;


    public int getPropId() {
        return propId;
    }

    public void setPropId(int propId) {
        this.propId = propId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropCode() {
        return propCode;
    }

    public void setPropCode(String propCode) {
        this.propCode = propCode;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }


}
