package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarHomeModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汽车之家的车型,与车系相关 数据库处理类
 */
@Mapper
public interface CarHomeModelDao extends BaseDao<CarHomeModel,Integer> {
	
}
