package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarBrand;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌 数据库处理类
 */
@Mapper
public interface CarBrandDao extends BaseDao<CarBrand,Integer> {
	
}
