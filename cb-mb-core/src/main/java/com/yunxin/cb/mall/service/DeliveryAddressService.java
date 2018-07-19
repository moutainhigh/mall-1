package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService {
    /**
     * 根据用户ID查询收货地址
     * @param customerId
     * @return
     */
    List<DeliveryAddress> selectByCustomerId(Integer customerId);
    /**
     * 根据地址ID查看收货详情
     * @param addressId
     * @return
     */
    DeliveryAddress selectByPrimaryKey(Integer addressId);
    /**
     * 新增收货地址
     */
    int insert(DeliveryAddress record);
    /**
     * 删除收货地址
     */
    int deleteByPrimaryKey(Integer addressId);
    /**
     * 更新收货地址
     */
    int updateByPrimaryKey(DeliveryAddress record);
}
