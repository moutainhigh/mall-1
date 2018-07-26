package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.mapper.DeliveryAddressMapper;
import com.yunxin.cb.mall.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService{
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;

    @Override
    public List<DeliveryAddress> selectByCustomerId(Integer customerId) {
        return deliveryAddressMapper.selectByCustomerId(customerId);
    }

    @Override
    public DeliveryAddress selectByPrimaryKey(Integer addressId,Integer customerId) {
        return deliveryAddressMapper.selectByPrimaryKey(addressId,customerId);
    }

    @Override
    public int insert(DeliveryAddress record) {
        return deliveryAddressMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer addressId) {
        return deliveryAddressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public int updateByPrimaryKey(DeliveryAddress record) {
        return deliveryAddressMapper.updateByPrimaryKey(record);
    }

    @Override
    public DeliveryAddress selectDefaultByCustomerId(Integer customerId) {
        return deliveryAddressMapper.selectDefaultByCustomerId(customerId);
    }
}
