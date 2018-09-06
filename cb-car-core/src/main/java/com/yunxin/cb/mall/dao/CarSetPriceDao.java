package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarSetPrice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 价格段置换 数据库处理类
 */
@Mapper
public interface CarSetPriceDao extends BaseDao<CarSetPrice,Integer> {
	
}
