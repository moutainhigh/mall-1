package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.Brand;
import org.apache.ibatis.annotations.Mapper;

/**
 * Brand 数据库处理类
 */
@Mapper
public interface BrandDao extends BaseDao<Brand,Integer> {

}
