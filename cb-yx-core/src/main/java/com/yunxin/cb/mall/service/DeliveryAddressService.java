package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.vo.DeliveryAddressVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
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

    DeliveryAddress selectByPrimaryKey(Integer addressId,Integer customerId);
    /**
     * 新增收货地址
     */
    void insert(DeliveryAddressVO deliveryAddressVO) throws Exception;
    /**
     * 删除收货地址
     */
    void deleteByPrimaryKey(Integer addressId)throws Exception;
    /**
     * 更新收货地址
     */
    void updateByPrimaryKey(int addressId,DeliveryAddressVO deliveryAddressVO)throws Exception;

    /**
     * 查询用户默认收货地址
     * @param customerId
     * @return
     */
    DeliveryAddress selectDefaultByCustomerId(Integer customerId);
}
