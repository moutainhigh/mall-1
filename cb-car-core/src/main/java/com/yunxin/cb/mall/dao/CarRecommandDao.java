package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarRecommand;
import org.apache.ibatis.annotations.Mapper;

/**
 * 推荐管理 数据库处理类
 */
@Mapper
public interface CarRecommandDao extends BaseDao<CarRecommand,Integer> {
	
}
