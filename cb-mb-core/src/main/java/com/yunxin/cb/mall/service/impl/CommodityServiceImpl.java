package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.mapper.CommodityMapper;
import com.yunxin.cb.mall.service.CommodityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @title: 商品接口实现类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    private static final Log log = LogFactory.getLog(CommodityServiceImpl.class);

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public Commodity selectByPrimaryKey(int commodityId) {
        return commodityMapper.selectByPrimaryKey(commodityId);
    }
}
