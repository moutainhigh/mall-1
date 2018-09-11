package com.yunxin.cb.vo.responsevo;

import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.cb.mall.entity.meta.AdvertisementType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="banner",description="bannerVO对象 CarAdvertisementsVO")
public class CarAdvertisementsVO implements java.io.Serializable{
    private static final long serialVersionUID = -4757351429057292609L;
    /**
     *
     */
    @ApiModelProperty(value="图片id",name="advertId",example="1")
    private Integer advertId;

    @ApiModelProperty(value="图片路径",name="picPath",example="xxxx.png")
    private String picPath;

    @ApiModelProperty(value="标题",name="title",example="让生活更美好")
    private String title;

    @ApiModelProperty(value="广告类型",name="adverType",example="枚举")
    private AdvertisementType adverType;

    @ApiModelProperty(value="广告位置",name="position",example="枚举")
    private AdvertisementPlace position;

    @ApiModelProperty(value="跳转类型",name="jumpType",example="（0：不跳转：1：内部；2：外部）")
    private Integer jumpType;

    @ApiModelProperty(value="广告URL",name="jumpUrl",example="www.baidu.com")
    private String jumpUrl;

    @ApiModelProperty(value="品牌",name="carBrandVO",example="品牌")
    private CarBrandVO carBrandVO;

    @ApiModelProperty(value="车系ID",name="sysId",example="1")
    private Integer sysId;

    @ApiModelProperty(value="内容",name="content",example="内容")
    private String content;

    public Integer getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Integer advertId) {
        this.advertId = advertId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AdvertisementType getAdverType() {
        return adverType;
    }

    public void setAdverType(AdvertisementType adverType) {
        this.adverType = adverType;
    }

    public AdvertisementPlace getPosition() {
        return position;
    }

    public void setPosition(AdvertisementPlace position) {
        this.position = position;
    }

    public Integer getJumpType() {
        return jumpType;
    }

    public void setJumpType(Integer jumpType) {
        this.jumpType = jumpType;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public CarBrandVO getCarBrandVO() {
        return carBrandVO;
    }

    public void setCarBrandVO(CarBrandVO carBrandVO) {
        this.carBrandVO = carBrandVO;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
