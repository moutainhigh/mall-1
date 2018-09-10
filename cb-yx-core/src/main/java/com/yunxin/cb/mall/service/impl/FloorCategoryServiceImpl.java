package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FloorCategory;
import com.yunxin.cb.mall.mapper.FloorCategoryMapper;
import com.yunxin.cb.mall.service.FloorCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorCategoryServiceImpl implements FloorCategoryService {
    @Autowired
    private FloorCategoryMapper floorCategoryMapper;
    @Override
    public List<FloorCategory> selectByFloorId(Integer floorId) {
        return floorCategoryMapper.selectByFloorId(floorId);
    }
}
