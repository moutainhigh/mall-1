package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarSystem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汽车车系 数据库处理类
 */
@Mapper
public interface CarSystemDao extends BaseDao<CarSystem,Integer> {
	
}
