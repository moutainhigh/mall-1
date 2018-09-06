package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarAdvertisements;
import org.apache.ibatis.annotations.Mapper;

/**
 * 广告 数据库处理类
 */
@Mapper
public interface CarAdvertisementsDao extends BaseDao<CarAdvertisements,Integer> {
	
}
