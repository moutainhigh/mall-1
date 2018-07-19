package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Advertisement;
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
    public List<Advertisement> selectByPlace(Integer advertisementPlace) {
        return advertisementMapper.selectByPlace(advertisementPlace);
    }
}
