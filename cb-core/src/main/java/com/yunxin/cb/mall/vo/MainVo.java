package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.cb.mall.entity.HomeFloor;

import java.util.List;

/**
 * pad调用那个返回的商城首页VO
 * Created by moxin on 2016-04-18-0018.
 */
public class MainVo {

    private List<HomeFloor> floors;

    private List<Advertisement> advertisements;

    public List<HomeFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<HomeFloor> floors) {
        this.floors = floors;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
