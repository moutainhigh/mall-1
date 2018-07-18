package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.mapper.CommodityMapper;
import com.yunxin.cb.mall.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Override
    public Commodity selectByPrimaryKey(Integer commodityId) {
        return commodityMapper.selectByPrimaryKey(commodityId);
    }
}
