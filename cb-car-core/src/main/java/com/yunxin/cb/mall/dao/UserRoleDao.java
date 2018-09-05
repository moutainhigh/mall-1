package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserRole 数据库处理类
 */
@Mapper
public interface UserRoleDao extends BaseDao<UserRole,Integer> {
	
}
