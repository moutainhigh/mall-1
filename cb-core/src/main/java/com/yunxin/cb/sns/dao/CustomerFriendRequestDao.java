package com.yunxin.cb.sns.dao;

import com.yunxin.cb.sns.entity.CustomerFriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by gonglei on 16/3/15.
 */
public interface CustomerFriendRequestDao extends JpaRepository<CustomerFriendRequest, Integer>, JpaSpecificationExecutor<CustomerFriendRequest> {

     @Query("select cr from CustomerFriendRequest cr left join fetch cr.friendCustomer fc left join fetch cr.customer c where fc.customerId = ?1 ")
     public List<CustomerFriendRequest> getCustomerFriendRequestByFriendId(int friendId);
     @Query("select cr from CustomerFriendRequest cr left join fetch cr.friendCustomer fc left join fetch cr.customer c where fc.customerId = ?1 and c.customerId = ?2 and cr.state=0")
     public List<CustomerFriendRequest> getCustomerFriendRequestByFriendIdAndCustomerId(int friendId,int customerId);

     @Modifying
     @Query("update CustomerFriendRequest cr set cr.state = ?3 where cr.friendCustomer.customerId=?1 and cr.customer.customerId=?2")
     public void updateCustomerFriendRequestState(int friendId,int customerId,int state);
     @Modifying
     @Query("delete from CustomerFriendRequest cr where cr.friendCustomer.customerId=?1 and cr.customer.customerId=?2")
     public void deleteCustomerFriendRequest(int friendId,int customerId);

}
