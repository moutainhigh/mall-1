package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarBaseData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础数据表 数据库处理类
 */
@Mapper
public interface CarBaseDataDao extends BaseDao<CarBaseData,Integer> {
	
}
