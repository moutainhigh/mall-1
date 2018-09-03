package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.mapper.DeliveryAddressMapper;
import com.yunxin.cb.mall.service.DeliveryAddressService;
import com.yunxin.cb.mall.vo.DeliveryAddressVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.yunxin.cb.orm.CustomerContextHolder.getCustomerId;

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
    @Transactional(rollbackFor = Exception.class)
    public void insert(DeliveryAddressVO deliveryAddressVO) throws Exception {
        //判断当前是否有默认收货地址
        deliveryAddressVO.setCustomerId(getCustomerId());
        DeliveryAddress  deliveryAddr = deliveryAddressMapper.selectDefaultByCustomerId(getCustomerId());
        if(deliveryAddr == null){
            DeliveryAddress deliveryAddress = new DeliveryAddress();
            BeanUtils.copyProperties(deliveryAddress, deliveryAddressVO);
            deliveryAddress.setDefaultAddress(true);
            deliveryAddressMapper.insert(deliveryAddress);
        }else{//判断新增地址是否为默认收货地址
            if(deliveryAddressVO.getDefaultAddress() == deliveryAddr.getDefaultAddress()){
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                BeanUtils.copyProperties(deliveryAddress, deliveryAddressVO);
                deliveryAddr.setDefaultAddress(false);
                deliveryAddressMapper.updateByPrimaryKey(deliveryAddr);
                deliveryAddressMapper.insert(deliveryAddress);
            }else {
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                BeanUtils.copyProperties(deliveryAddress, deliveryAddressVO);
                deliveryAddress.setDefaultAddress(false);
                deliveryAddressMapper.insert(deliveryAddress);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(Integer addressId)throws Exception {
        DeliveryAddress  deliveryAddr = deliveryAddressMapper.selectByPrimaryKey(addressId,getCustomerId());
        //删除的是默认地址
        if(deliveryAddr.getDefaultAddress()){
            deliveryAddressMapper.deleteByPrimaryKey(addressId);
            List<DeliveryAddress> list = deliveryAddressMapper.selectByCustomerId(getCustomerId());
            //需要把其中一个地址改为默认
            if(list.size()>0){
                DeliveryAddress address = list.get(0);
                address.setDefaultAddress(true);
                deliveryAddressMapper.updateByPrimaryKey(address);
            }
        }else{
            deliveryAddressMapper.deleteByPrimaryKey(addressId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByPrimaryKey(int addressId,DeliveryAddressVO deliveryAddressVO)throws Exception {
        deliveryAddressVO.setCustomerId(getCustomerId());
        DeliveryAddress  addr = deliveryAddressMapper.selectByPrimaryKey(addressId,getCustomerId());
        //判断当前地址是否是默认地址
        if(addr.getDefaultAddress()){
            DeliveryAddress deliveryAddress = new DeliveryAddress();
            BeanUtils.copyProperties(deliveryAddress, deliveryAddressVO);
            deliveryAddress.setAddressId(addressId);
            deliveryAddress.setDefaultAddress(true);
            deliveryAddressMapper.updateByPrimaryKey(deliveryAddress);
        }else {
            //判断修改的地址是否为默认地址
            if(deliveryAddressVO.getDefaultAddress()){
                DeliveryAddress  deliveryAddr = deliveryAddressMapper.selectDefaultByCustomerId(getCustomerId());
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                BeanUtils.copyProperties(deliveryAddress, deliveryAddressVO);
                deliveryAddress.setAddressId(addressId);
                deliveryAddress.setDefaultAddress(true);
                deliveryAddr.setDefaultAddress(false);
                deliveryAddressMapper.updateByPrimaryKey(deliveryAddr);
                deliveryAddressMapper.updateByPrimaryKey(deliveryAddress);
            }else{
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                BeanUtils.copyProperties(deliveryAddress, deliveryAddressVO);
                deliveryAddress.setAddressId(addressId);
                deliveryAddressMapper.updateByPrimaryKey(deliveryAddress);
            }
        }
    }
    @Override
    public DeliveryAddress selectDefaultByCustomerId(Integer customerId) {
        return deliveryAddressMapper.selectDefaultByCustomerId(customerId);
    }
}
