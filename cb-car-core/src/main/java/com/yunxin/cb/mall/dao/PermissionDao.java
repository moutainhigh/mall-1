package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源权限 数据库处理类
 */
@Mapper
public interface PermissionDao extends BaseDao<Permission,Integer> {
	
}
