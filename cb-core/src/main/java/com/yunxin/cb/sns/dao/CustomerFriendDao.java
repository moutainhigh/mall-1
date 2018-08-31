package com.yunxin.cb.sns.dao;

import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.entity.CustomerFriendId;
import com.yunxin.cb.sns.meta.CustomerFriendState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by gonglei on 16/3/15.
 */
public interface CustomerFriendDao extends JpaRepository<CustomerFriend, CustomerFriendId>, JpaSpecificationExecutor<CustomerFriend> {

    @Query("select cf from CustomerFriend cf left join fetch cf.friend left join fetch cf.customer where cf.customer.customerId = ?1")
    List<CustomerFriend> findCustomerFriendByCustomerCustomerId(int customerId);

    @Query("select cf from CustomerFriend cf left join fetch cf.friend left join fetch cf.customer c where cf.customer.customerId = ?1 and c.ynDelete=?2 and cf.state=?3")
    List<CustomerFriend> findCustomerFriendByCustomerCustomerIdAndState(int customerId,boolean ynDelete,CustomerFriendState state);

}
