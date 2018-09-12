package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: hfy
 * @Date: 2018/9/10 14:30
 * @Description: 品牌精选VO
 */
@ApiModel(value="品牌精选VO",description="品牌精选VO对象 BrandChoiceVO")
public class BrandChoiceVO implements Serializable {
    private static final long serialVersionUID = -8515455308081734286L;
    /**
     * 商品列表
     */
    @ApiModelProperty(value="商品列表",name="commodityList",example="商品列表")
    private List<RecommendationVO> commodityList;

    /**
     * 大图url
     */
    @ApiModelProperty(value = "大图url",name = "imageUrl",example = "http://test.resource.999shuijingqiu.com/BRAND/1532746048362")
    private String imageUrl;

    public List<RecommendationVO> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<RecommendationVO> commodityList) {
        this.commodityList = commodityList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
