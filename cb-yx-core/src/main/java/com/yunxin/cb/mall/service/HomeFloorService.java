package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.HomeFloor;

import java.util.List;

public interface HomeFloorService {
    /**
     * 查询楼层
     * @return
     */
    List<HomeFloor> selectByEnabledAll();
}
