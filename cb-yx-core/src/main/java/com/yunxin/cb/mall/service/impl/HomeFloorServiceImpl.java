package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.HomeFloor;
import com.yunxin.cb.mall.mapper.HomeFloorMapper;
import com.yunxin.cb.mall.service.HomeFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeFloorServiceImpl implements HomeFloorService {
    @Autowired
    private HomeFloorMapper homeFloorMapper;
    @Override
    public List<HomeFloor> selectByEnabledAll() {
        return homeFloorMapper.selectByEnabledAll();
    }
}
