package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.cb.mall.mapper.AdvertisementMapper;
import com.yunxin.cb.mall.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public List<Advertisement> selectByPlace(String advertisementPlace,Boolean enabled) {
        return advertisementMapper.selectByPlace(advertisementPlace,enabled);
    }


    @Override
    public List<Advertisement> select(Boolean enabled) {
        return advertisementMapper.select(enabled);
    }

    @Override
    public List<Advertisement> selectByAdvertisementPlace(AdvertisementPlace advertisementPlace, Boolean enabled) {
        return advertisementMapper.selectByAdvertisementPlace(advertisementPlace,enabled);
    }

    @Override
    public Advertisement selectByAdvertId(int advertId) {
        return advertisementMapper.selectByPrimaryKey(advertId);
    }
}
