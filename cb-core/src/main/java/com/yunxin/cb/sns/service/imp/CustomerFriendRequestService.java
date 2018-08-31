package com.yunxin.cb.sns.service.imp;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.sns.dao.CustomerFriendRequestDao;
import com.yunxin.cb.sns.entity.CustomerFriendRequest;
import com.yunxin.cb.sns.meta.CustomerFriendRequestState;
import com.yunxin.cb.sns.service.ICustomerFriendRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @Description:    发起添加好友请求记录service
* @Author:         likang
* @CreateDate:     2018/7/18 17:53
*/
@Service
@Transactional
public class CustomerFriendRequestService implements ICustomerFriendRequestService {
    @Resource
    private CustomerFriendRequestDao customerFriendRequestDao;
    /**
     * 根据friendId查询所有请求
     * @author      likang
     * @param friendId 邀请添加好友ID
     * @return      java.util.List<com.yunxin.cb.sns.entity.CustomerFriendRequest>
     * @exception
     * @date        2018/7/18 17:54
     */
    public List<CustomerFriendRequest> getCustomerFriendRequestByFriendId(int friendId){
        return customerFriendRequestDao.getCustomerFriendRequestByFriendId(friendId,false);
    }

    /**
     * 更新添加好友请求的请求状态
     * @author      likang
     * @param friendId 邀请添加好友ID
    * @param customerId 请求添加ID
    * @param state 状态
     * @return      void
     * @exception
     * @date        2018/7/18 17:55
     */
    public void updateCustomerFriendRequestState(int friendId,int customerId,int state){
        customerFriendRequestDao.updateCustomerFriendRequestState(friendId,customerId,state);
    }

    @Override
    public void deleteCustomerFriendRequestById(int friendId,int customerId) {
        customerFriendRequestDao.deleteCustomerFriendRequest(friendId,customerId);
    }

    /**
     * 方法实现说明
     * @author      likang
     * @param customer
    * @param friendCustomer
    * @param requestMessage
     * @return      com.yunxin.cb.sns.entity.CustomerFriendRequest
     * @exception
     * @date        2018/7/18 18:04
     */
    public CustomerFriendRequest addCustomerFriendRequest(Customer customer, Customer friendCustomer, String requestMessage){
        List<CustomerFriendRequest> CustomerFriendRequest=customerFriendRequestDao.getCustomerFriendRequestByFriendIdAndCustomerId(friendCustomer.getCustomerId(),customer.getCustomerId());

        CustomerFriendRequest cfr=new CustomerFriendRequest();
        cfr.setCreateTime(new Date());
        cfr.setCustomer(customer);
        cfr.setFriendCustomer(friendCustomer);
        cfr.setRequestMessage(requestMessage);
        if(null!=CustomerFriendRequest&&CustomerFriendRequest.size()>0){

            for(CustomerFriendRequest CustomerFriendRequests:CustomerFriendRequest){
                CustomerFriendRequest customerFriendRequest= customerFriendRequestDao.findOne(CustomerFriendRequests.getRequestId());
                customerFriendRequest.setCreateTime(new Date());
            }

        }else {
            //默认新请求
            cfr.setState(CustomerFriendRequestState.NEWREQUEST.getState());
            customerFriendRequestDao.save(cfr);
        }
        return cfr;
    }
}
