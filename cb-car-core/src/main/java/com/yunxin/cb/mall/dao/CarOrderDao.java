package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汽车订单表 数据库处理类
 */
@Mapper
public interface CarOrderDao extends BaseDao<CarOrder,Long> {
	
}
