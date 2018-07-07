package com.yunxin.cb.rest.customer;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.entity.CustomerFriendId;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(description = "用户接口")
@RestController
@RequestMapping(value = "/customer")
@SessionAttributes("customerId")
public class CustomerResource extends BaseResource {

    @Resource
    private ICustomerService customerService;


    @ApiOperation(value = "我的好友")
    @GetMapping(value = "myFriends")
    public ResponseResult myFriends(@ModelAttribute("customerId") int customerId) {
        return new ResponseResult(customerService.getFriendByCustomerId(customerId));
    }

    @ApiOperation(value = "通过手机号搜索好友")
    @PostMapping(value = "queryFriend")
    public ResponseResult queryFriend(@RequestParam("mobile") String mobile, @ModelAttribute("customerId") int customerId) {
        Customer friend = customerService.getCustomerByMobile(mobile);
        if (friend != null) {
            friend.setFriend(customerService.isFriend(customerId, friend.getCustomerId()) || friend.getCustomerId() == customerId);
            return new ResponseResult(friend);
        }
        return new ResponseResult(Result.FAILURE, "未找到相关好友");
    }

    @ApiOperation(value = "添加好友")
    @PostMapping(value = "addFriend")
    public ResponseResult addFriend(@RequestParam("mobile") String mobile, @ModelAttribute("customerId") int customerId) {
        Customer myself = customerService.getCustomerById(customerId);
        Customer customer = customerService.getCustomerByMobile(mobile);

        if (customer == null) {
            return new ResponseResult(Result.FAILURE, "您所添加的用户不存在");
        }

        CustomerFriend customerFriend = new CustomerFriend();
        CustomerFriendId customerFriendId = new CustomerFriendId();
        customerFriendId.setCustomerId(customerId);
        customerFriendId.setFriendId(customer.getCustomerId());
        customerFriend.setId(customerFriendId);
        customerFriend.setCustomer(myself);
        customerFriend.setFriend(customer);
        customerFriend.setCreateTime(new Date());
        customerService.addFriend(customerFriend);

        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "删除好友")
    @DeleteMapping(value = "removeFriend/{friendId}")
    public ResponseResult removeFriend(@PathVariable int friendId, @ModelAttribute("customerId") int customerId) {
        CustomerFriendId customerFriendId = new CustomerFriendId();
        customerFriendId.setCustomerId(customerId);
        customerFriendId.setFriendId(friendId);
        customerService.delFriendById(customerFriendId);
        return new ResponseResult(Result.SUCCESS);
    }
}
