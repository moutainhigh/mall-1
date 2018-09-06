package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarTestDrive;
import org.apache.ibatis.annotations.Mapper;

/**
 * 试驾信息 数据库处理类
 */
@Mapper
public interface CarTestDriveDao extends BaseDao<CarTestDrive,Integer> {
	
}
