package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarHomeBrand;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汽车之家的品牌 数据库处理类
 */
@Mapper
public interface CarHomeBrandDao extends BaseDao<CarHomeBrand,Integer> {
	
}
