package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FloorAdvert;
import com.yunxin.cb.mall.mapper.FloorAdvertMapper;
import com.yunxin.cb.mall.service.FloorAdvertService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FloorAdvertServiceImpl implements FloorAdvertService {
    @Resource
    private FloorAdvertMapper floorAdvertMapper;
    @Override
    public List<FloorAdvert> selectByFloorId(Integer floorId) {
        return floorAdvertMapper.selectByFloorId(floorId);
    }
}
