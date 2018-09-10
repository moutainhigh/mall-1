package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FloorBrand;

import java.util.List;

public interface FloorBrandService {
    /**
     * 根据FloorId查询
     * @param floorId
     * @return
     */
    List<FloorBrand> selectByFloorId(Integer floorId);
}
