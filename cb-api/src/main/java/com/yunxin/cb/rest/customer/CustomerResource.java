package com.yunxin.cb.rest.customer;

import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.entity.CustomerFriendId;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
import com.yunxin.core.exception.EntityExistException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(description = "用户接口")
@RestController
@RequestMapping(value = "/customer")
public class CustomerResource extends BaseResource {

    private static Logger logger = LoggerFactory.getLogger(CustomerResource.class);
    @Resource
    private ICustomerService customerService;


    @ApiOperation(value ="我的好友")
    @GetMapping(value = "myFriends")
    public ResponseResult myFriends() {
        int customerId=getCustomerId();
        return new ResponseResult( customerService.getFriendByCustomerId(customerId));
    }

    @ApiOperation(value ="添加好友")
    @PostMapping(value = "addFriend")
    public ResponseResult addFriend(@RequestBody String mobile) {
        int customerId=getCustomerId();
        Customer myself =customerService.getCustomerById(customerId);
        Customer customer =customerService.getCustomerByMobile(mobile);



        if(customer==null)
        {
            return new ResponseResult(Result.FAILURE,"您所添加的用户不存在");
        }

        CustomerFriend customerFriend=new CustomerFriend();
        CustomerFriendId customerFriendId=new CustomerFriendId ();
        customerFriendId.setCustomerId(customerId);
        customerFriendId.setFriendId(customer.getCustomerId());
        customerFriend.setId(customerFriendId);
        customerFriend.setCustomer(myself);
        customerFriend.setFriend(customer);
        customerFriend.setCreateTime(new Date());
        customerFriend =customerService.addFriend(customerFriend);

        return new ResponseResult(customerFriend);
    }

    @ApiOperation(value ="删除好友")
    @DeleteMapping(value = "removeFriend/{friendId}")
    public ResponseResult removeFriend( @PathVariable int friendId) {
        int customerId=getCustomerId();
        CustomerFriendId customerFriendId=new CustomerFriendId ();
        customerFriendId.setCustomerId(customerId);
        customerFriendId.setFriendId(friendId);
        customerService.delFriendById(customerFriendId);
        return new ResponseResult(Result.SUCCESS);
    }



}
