package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author x001393
 */
public interface DeliveryAddressDao extends JpaRepository<DeliveryAddress, Integer>, JpaSpecificationExecutor<DeliveryAddress> {

    public List<DeliveryAddress> findByCustomer_CustomerIdOrderByDefaultAddressDesc(int customerId);


    DeliveryAddress findTopByCustomer_CustomerIdAndDefaultAddress(int customerId, boolean defaultAddress);

    @Modifying
    @Query("update DeliveryAddress da set da.defaultAddress=false where da.customer.customerId=?1 and da.defaultAddress=true")
    public void setNonDefaultAddressByCustomerId(int customerId);

    @Modifying
    @Query("update DeliveryAddress d set d.defaultAddress=true where d.addressId=?1")
    public void setDefaultAddressByAddrId(int deliveryAddressId);

    //此处不可以left join fetch Customer，否则会报错
    @Query("select count(addr.addressId) from DeliveryAddress addr left join  addr.customer c where c.customerId=?1")
    long findByCustomerIdforTotalSize(int customerId);

}
