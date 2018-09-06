package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarAgency;
import org.apache.ibatis.annotations.Mapper;

/**
 * 代理商(卖家,商家) 数据库处理类
 */
@Mapper
public interface CarAgencyDao extends BaseDao<CarAgency,Integer> {
	
}
