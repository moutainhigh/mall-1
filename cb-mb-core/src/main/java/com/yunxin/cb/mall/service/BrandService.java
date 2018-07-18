package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 根据楼层ID查询品牌
     * @param floorId
     * @return
     */
    Brand selectByPrimaryKey(Integer floorId);
}
