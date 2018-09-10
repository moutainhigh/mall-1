package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FloorCategory;

import java.util.List;

public interface FloorCategoryService {
    /**
     * 根据FloorId查询
     * @param floorId
     * @return
     */
    List<FloorCategory> selectByFloorId(Integer floorId);
}
