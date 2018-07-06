package com.yunxin.cb.rest.customer;

import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "用户接口")
@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

    private static Logger logger = LoggerFactory.getLogger(CustomerResource.class);
    @Resource
    private ICustomerService customerService;


    @ApiOperation(value ="我的好友")
    @PostMapping(value = "myFriends/{customerId}")
    public ResponseResult myFriends(@PathVariable int customerId) {

        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value ="添加好友")
    @PostMapping(value = "addFriend")
    public ResponseResult addFriend(@RequestBody CustomerFriend customerFriend) {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value ="删除好友")
    @PostMapping(value = "removeFriend/{customerId}/{friendId}")
    public ResponseResult removeFriend(@PathVariable int customerId, @PathVariable int friendId) {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value ="修改密码")
    @PostMapping(value = "updatePwd/{customerId}")
    public ResponseResult updatePwd(@PathVariable int customerId, @RequestParam String code, @RequestParam String newPwd) {
        return new ResponseResult(Result.SUCCESS);
    }

}
