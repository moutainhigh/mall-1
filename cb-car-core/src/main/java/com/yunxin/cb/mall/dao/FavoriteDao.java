package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * Favorite 数据库处理类
 */
@Mapper
public interface FavoriteDao extends BaseDao<Favorite,Integer> {
	
}
