package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarBaseData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 基础数据表 数据库处理类
 */
@Mapper
public interface CarBaseDataDao extends BaseDao<CarBaseData,Integer> {

    /**
     * @title: 停用/启用
     * @param: [catalogId, enabled]
     * @return: int
     * @auther: eleven
     * @date: 2018/9/11 11:23
     */
    int enableBaseDataById(@Param("baseDataId") int baseDataId,@Param("enabled") boolean enabled);
}
