package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Commodity;

public interface CommodityService {
    /**
     * 根据ID查询
     * @param commodityId
     * @return
     */
    Commodity selectByPrimaryKey(Integer commodityId);
}
