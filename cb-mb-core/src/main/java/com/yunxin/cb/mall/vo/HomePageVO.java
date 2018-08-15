package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@ApiModel(value="商城首页",description="商城首页VO对象 HomePageVO")
public class HomePageVO implements Serializable {
    @ApiModelProperty(value="楼层信息",name="homeFloorVOList",example="1")
    private List<HomeFloorVO> homeFloorVOList;
    @ApiModelProperty(value="banner",name="advertisementMap",example="1")
    private Map<String, List<AdvertisementVO>> advertisementMap;

    public List<HomeFloorVO> getHomeFloorVOList() {
        return homeFloorVOList;
    }

    public void setHomeFloorVOList(List<HomeFloorVO> homeFloorVOList) {
        this.homeFloorVOList = homeFloorVOList;
    }

    public Map<String, List<AdvertisementVO>> getAdvertisementMap() {
        return advertisementMap;
    }

    public void setAdvertisementMap(Map<String, List<AdvertisementVO>> advertisementMap) {
        this.advertisementMap = advertisementMap;
    }
}
