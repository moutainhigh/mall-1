package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarUsedCar;
import org.apache.ibatis.annotations.Mapper;

/**
 * 二手车管理 数据库处理类
 */
@Mapper
public interface CarUsedCarDao extends BaseDao<CarUsedCar,Integer> {
	
}
