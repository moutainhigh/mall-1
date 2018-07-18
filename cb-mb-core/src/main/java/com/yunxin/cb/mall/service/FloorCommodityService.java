package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FloorCommodity;

import java.util.List;

public interface FloorCommodityService {
    /**
     * 根据FloorId查询
     * @param floorId
     * @return
     */
    List<FloorCommodity> selectByFloorId(Integer floorId);

}
