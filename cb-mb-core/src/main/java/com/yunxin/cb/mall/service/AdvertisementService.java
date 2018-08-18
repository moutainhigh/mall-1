package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;

import java.util.List;

public interface AdvertisementService {

    /**
     * 查询banner
     */
    List<Advertisement> selectByPlace(String advertisementPlace,Boolean enabled);

    /**
     *
     * @param
     * @param enabled
     * @return
     */
    List<Advertisement> select(Boolean enabled);

    /**
     * 查询banner
     */
    List<Advertisement> selectByAdvertisementPlace(AdvertisementPlace advertisementPlace,Boolean enabled);

    /**
     * 根据ID查询
     */
    Advertisement selectByAdvertId(int advertId);
}
