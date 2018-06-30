package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.core.persistence.PageSpecification;

/**
 * @author x001393
 */
public class DeliveryAddressQuery extends PageSpecification<DeliveryAddress> {

    private int customerId;

    private Customer customer;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
