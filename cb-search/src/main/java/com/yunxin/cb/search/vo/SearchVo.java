package com.yunxin.cb.search.vo;


import com.yunxin.cb.search.vo.meta.SortBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.Set;

@ApiModel(value="商品搜索对象",description="商品搜索对象 SearchVo")
public class SearchVo implements java.io.Serializable {

    private static final long serialVersionUID = -3123560903203859821L;

    @ApiModelProperty(value="品牌ID",name="brandId",example="1")
    private int brandId;

    @ApiModelProperty(value="分类ID",name="categoryId",example="1")
    private int categoryId;

    @ApiModelProperty(value="商家ID",name="sellerId",example="1")
    private int sellerId;

    @ApiModelProperty(value="最低销售价",name="lowestPrice",example="50")
    private int lowestPrice;

    @ApiModelProperty(value="最高销售价",name="highestPrice",example="500")
    private int highestPrice;

    @ApiModelProperty(value="价格段",name="priceSection",example="价格段")
    private PriceSection priceSection;

    @ApiModelProperty(value="商品筛选属性",name="commoditySpecs",example="商品筛选属性")
    private Set<CommoditySpec> commoditySpecs = new HashSet<>();

    @ApiModelProperty(value="排序字段",name="sortBy",example="排序字段")
    private SortBy sortBy;

    @ApiModelProperty(value="排序方向，枚举:升序or降序",name="direction",example="ASC|DESC")
    private Sort.Direction direction;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(int highestPrice) {
        this.highestPrice = highestPrice;
    }

    public PriceSection getPriceSection() {
        return priceSection;
    }

    public void setPriceSection(PriceSection priceSection) {
        this.priceSection = priceSection;
    }

    public Set<CommoditySpec> getCommoditySpecs() {
        return commoditySpecs;
    }

    public void setCommoditySpecs(Set<CommoditySpec> commoditySpecs) {
        this.commoditySpecs = commoditySpecs;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
