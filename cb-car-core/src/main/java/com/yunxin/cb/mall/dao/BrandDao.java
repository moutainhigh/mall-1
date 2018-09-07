package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Brand 数据库处理类
 */
@Mapper
public interface BrandDao extends BaseDao<Brand,Integer> {

    List<Brand> queryAll2(Query q);
	
}
