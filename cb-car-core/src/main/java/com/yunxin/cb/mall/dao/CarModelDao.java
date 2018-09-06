package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车型信息 数据库处理类
 */
@Mapper
public interface CarModelDao extends BaseDao<CarModel,Integer> {
	
}
