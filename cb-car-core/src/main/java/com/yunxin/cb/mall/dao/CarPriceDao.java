package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarPrice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车型价格信息 数据库处理类
 */
@Mapper
public interface CarPriceDao extends BaseDao<CarPrice,Integer> {
	
}
