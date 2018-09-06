package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.CarOrderReason;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汽车订单(取消/换货/退款/退款审核)原因 数据库处理类
 */
@Mapper
public interface CarOrderReasonDao extends BaseDao<CarOrderReason,Long> {
	
}
