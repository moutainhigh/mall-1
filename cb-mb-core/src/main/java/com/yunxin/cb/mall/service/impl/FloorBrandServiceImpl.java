package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FloorBrand;
import com.yunxin.cb.mall.mapper.FloorBrandMapper;
import com.yunxin.cb.mall.service.FloorBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorBrandServiceImpl implements FloorBrandService {
    @Autowired
    private FloorBrandMapper floorBrandMapper;
    @Override
    public List<FloorBrand> selectByFloorId(Integer floorId) {
        return floorBrandMapper.selectByFloorId(floorId);
    }
}
