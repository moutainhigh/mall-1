package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FloorAdvert;

import java.util.List;

public interface FloorAdvertService {

    /**
     * 根据FloorId查询
     * @param floorId
     * @return
     */
    List<FloorAdvert> selectByFloorId(Integer floorId);
}
