package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;

import java.util.List;

public interface AdvertisementService {

    /**
     * 查询banner
     */
    List<Advertisement> selectByPlace(AdvertisementPlace advertisementPlace,Boolean enabled);
}
