package com.yunxin.cb.mall.vo;

import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.util.PinyinUtils;
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
     * 分类名称
     */
    @ApiModelProperty(value="分类名称",name="categoryName",example="分类名称")
    private String categoryName;

    /**
     * 简写名称
     */
    @ApiModelProperty(value="简写名称",name="shortName",example="简写名称")
    private String shortName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        if(LogicUtils.isNotNull(categoryName)){
            this.shortName=PinyinUtils.getAlpha(this.categoryName.substring(0,1));
        }
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
