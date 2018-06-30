package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.query.DeliveryAddressQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author x001393
 */
public interface IAddressService {

    public DeliveryAddress addDeliveryAddress(DeliveryAddress deliveryAddress);

    public DeliveryAddress updateDeliveryAddress(DeliveryAddress deliveryAddress);

    public DeliveryAddress getDeliveryAddressById(int deliveryAddressId);

    public void removeDeliveryAddressById(int deliveryAddressId);


    public List<DeliveryAddress> getAllAddressesByCustomerId(int customerId);

    public Page<DeliveryAddress> pageDeliveryAddresses(DeliveryAddressQuery query);

    /***
     * 查询客户是否具有默认地址
     * @param customerId
     * @return
     */
    public DeliveryAddress queryIsDefaultAddressByCustomerId(int customerId);

    /***
     * 设置客户默认收货地址
     * @param customerId
     * @param deliveryAddressId
     * @throws NullPointerException
     */
    public void setDefaultAddrByCustomerIdAndAddrId(int customerId, int deliveryAddressId) throws NullPointerException;


}
