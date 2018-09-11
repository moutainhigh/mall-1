package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarHomeSeries;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汽车之家的车系,与品牌相关 数据库处理类
 */
@Mapper
public interface CarHomeSeriesDao extends BaseDao<CarHomeSeries,Integer> {
	
}
