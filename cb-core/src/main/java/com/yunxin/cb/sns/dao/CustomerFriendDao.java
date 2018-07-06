package com.yunxin.cb.sns.dao;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.entity.CustomerFriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by gonglei on 16/3/15.
 */
public interface CustomerFriendDao extends JpaRepository<CustomerFriend, CustomerFriendId>, JpaSpecificationExecutor<CustomerFriend> {

    @Query("select cf.friend from CustomerFriend cf  where cf.customer.customerId = ?1")
    List<Customer> findCustomerFriendByCustomerCustomerId(int customerId);
}
