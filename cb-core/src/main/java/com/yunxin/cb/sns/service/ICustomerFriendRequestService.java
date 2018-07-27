package com.yunxin.cb.sns.service;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.sns.entity.CustomerFriendRequest;

import java.util.List;

/**
 * Created by gonglei on 16/3/15.
 */
public interface ICustomerFriendRequestService {

    public List<CustomerFriendRequest> getCustomerFriendRequestByFriendId(int friendId);

    public void updateCustomerFriendRequestState(int friendId,int customerId,int state);

    /**
     * 删除好友请求通知
     * @param
     */
    public void deleteCustomerFriendRequestById(int friendId,int customerId);

    public CustomerFriendRequest addCustomerFriendRequest(Customer customer,Customer friendCustomer,String requestMessage);
}
