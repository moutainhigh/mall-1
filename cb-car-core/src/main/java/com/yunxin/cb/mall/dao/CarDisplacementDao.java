package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarDisplacement;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车系排量信息 数据库处理类
 */
@Mapper
public interface CarDisplacementDao extends BaseDao<CarDisplacement,Integer> {
	
}
