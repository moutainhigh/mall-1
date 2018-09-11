package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value="基础数据",description="基础数据VO对象 CarBaseDataVO")
public class CarBaseDataVO implements java.io.Serializable{
    private static final long serialVersionUID = 3473099803823395986L;
    /**
     * 基础数据ID
     */
    @ApiModelProperty(value="基础数据ID",name="id",example="1")
    private Integer id;
    /**
     * 基础数据名称
     */
    @ApiModelProperty(value="基础数据名称",name="baseDataName",example="国别")
    private String baseDataName;
    /**
     * 基础数据编码
     */
    @ApiModelProperty(value="基础数据编码",name="baseDataName",example="0001")
    private String baseDataCode;
    /**
     * 基础数据值
     */
    @ApiModelProperty(value="基础数据值",name="baseDataValue",example="中国")
    private String baseDataValue;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注",name="remark",example="备注")
    private String remark;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序",name="sortOrder",example="1")
    private Integer sortOrder;
    /**
     * 图片路径
     */
    @ApiModelProperty(value="图片路径",name="picPath",example="www.baidu.com/xxx.jpg")
    private String picPath;
    /**
     * 父节点数据
     */
    @ApiModelProperty(value="父节点数据",name="chilrenList",example="父节点数据")
    private CarBaseDataVO parentNode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseDataName() {
        return baseDataName;
    }

    public void setBaseDataName(String baseDataName) {
        this.baseDataName = baseDataName;
    }

    public String getBaseDataCode() {
        return baseDataCode;
    }

    public void setBaseDataCode(String baseDataCode) {
        this.baseDataCode = baseDataCode;
    }

    public String getBaseDataValue() {
        return baseDataValue;
    }

    public void setBaseDataValue(String baseDataValue) {
        this.baseDataValue = baseDataValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public CarBaseDataVO getParentNode() {
        return parentNode;
    }

    public void setParentNode(CarBaseDataVO parentNode) {
        this.parentNode = parentNode;
    }
}
