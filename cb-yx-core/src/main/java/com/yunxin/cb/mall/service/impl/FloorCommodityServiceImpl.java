package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FloorCommodity;
import com.yunxin.cb.mall.mapper.FloorCommodityMapper;
import com.yunxin.cb.mall.service.FloorCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorCommodityServiceImpl implements FloorCommodityService {
    @Autowired
    private FloorCommodityMapper floorCommodityMapper;
    @Override
    public List<FloorCommodity> selectByFloorId(Integer floorId) {
        return floorCommodityMapper.selectByFloorId(floorId);
    }
}
