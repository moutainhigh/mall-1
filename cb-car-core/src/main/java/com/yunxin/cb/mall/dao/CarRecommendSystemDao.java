package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarRecommendSystem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 推荐车系中间表 数据库处理类
 */
@Mapper
public interface CarRecommendSystemDao extends BaseDao<CarRecommendSystem,Integer> {
	
}
