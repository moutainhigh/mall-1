package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarSetUpdateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设置中心修改记录 数据库处理类
 */
@Mapper
public interface CarSetUpdateLogDao extends BaseDao<CarSetUpdateLog,Integer> {
	
}
