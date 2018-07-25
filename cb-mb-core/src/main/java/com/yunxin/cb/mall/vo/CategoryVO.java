package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value="分类",description="分类VO对象 CategoryVO")
public class CategoryVO implements java.io.Serializable{
    private static final long serialVersionUID = 7902398986602388206L;

    /**
     *
     */
    @ApiModelProperty(value="分类ID",name="categoryId",example="1")
    private Integer categoryId;
    /**
     * 分类描述
     */
    @ApiModelProperty(value="分类描述",name="description",example="分类描述")
    private String description;
    /**
     * 图标路径
     */
    @ApiModelProperty(value="图标路径",name="iconPath",example="xxxx.png")
    private String iconPath;
    /**
     * 最低价格
     */
    @ApiModelProperty(value="最低价格",name="lowestPrice",example="9.99")
    private BigDecimal lowestPrice;
    /**
     * 最高价格
     */
    @ApiModelProperty(value="最高价格",name="highestPrice",example="99.99")
    private BigDecimal highestPrice;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(BigDecimal lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public BigDecimal getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(BigDecimal highestPrice) {
        this.highestPrice = highestPrice;
    }
}
