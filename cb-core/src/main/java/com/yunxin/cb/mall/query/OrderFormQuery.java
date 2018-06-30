/**
 *
 */
package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.core.persistence.PageSpecification;

/**
 * @author gonglei
 */
public class OrderFormQuery extends PageSpecification<Order> {


    private Customer customer;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
