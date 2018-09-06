package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarOpenCity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 已开通城市表 数据库处理类
 */
@Mapper
public interface CarOpenCityDao extends BaseDao<CarOpenCity,Integer> {
	
}
