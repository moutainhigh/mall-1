package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {

    /**
     * 查询banner
     */
    List<Advertisement> selectByPlace(Integer advertisementPlace);
}
