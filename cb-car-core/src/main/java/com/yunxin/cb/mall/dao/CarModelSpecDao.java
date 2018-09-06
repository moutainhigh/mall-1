package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarModelSpec;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汽车配置信息 数据库处理类
 */
@Mapper
public interface CarModelSpecDao extends BaseDao<CarModelSpec,Long> {
	
}
