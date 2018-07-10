package com.yunxin.cb.rest.customer;

import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.entity.CustomerFriendId;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
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

    @Resource
    private IFeedbackService feedbackService;


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

        //双向加好友
        customerFriend = new CustomerFriend();
        customerFriendId = new CustomerFriendId();
        customerFriendId.setCustomerId(customer.getCustomerId());
        customerFriendId.setFriendId(customerId);
        customerFriend.setId(customerFriendId);
        customerFriend.setCustomer(customer);
        customerFriend.setFriend(myself);
        customerFriend.setCreateTime(new Date());
        customerService.addFriend(customerFriend);


        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改好友备注")
    @GetMapping(value = "updateFriendsProfile")
    public ResponseResult updateFriendsProfile(CustomerFriend customerFriend ,@ModelAttribute("customerId") int customerId) {
        return new ResponseResult(customerService.updateFriendsProfile(customerFriend));
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

    @ApiOperation(value = "修改用户头像")
    @PostMapping(value = "updateAvatar")
    public ResponseResult updateAvatar(@RequestParam("avatar") String avatar, @ModelAttribute("customerId") int customerId) {
        Customer customer = customerService.updateAvatar(customerId, avatar);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户昵称")
    @PostMapping(value = "updateNickName")
    public ResponseResult updateNickName(@RequestParam("nickName") String nickName, @ModelAttribute("customerId") int customerId) {
        Customer customer = customerService.updateNickName(customerId, nickName);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户昵称")
    @PostMapping(value = "updateSex")
    public ResponseResult updateSex(@RequestParam("sex") boolean sex, @ModelAttribute("customerId") int customerId) {
        Customer customer = customerService.updateSex(customerId, sex);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户所在地")
    @PostMapping(value = "updateAddress")
    public ResponseResult updateAddress(@RequestParam("province") String province,@RequestParam("city") String city,
                                        @RequestParam("district") String district,@RequestParam(value = "address", required = false) String address, @ModelAttribute("customerId") int customerId) {
        Customer customer = customerService.updateAddress(customerId, province, city, district, address);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改手机号")
    @PostMapping(value = "updateMobile")
    public ResponseResult updateMobile(String moblie,String code, @ModelAttribute("customerId") int customerId) {
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(moblie);
        //验证码不存在
        if (verificationCode == null){
            return new ResponseResult(Result.FAILURE, "验证码不存在");
        }
        //验证码超过5分钟，失效
        if ((System.currentTimeMillis() - verificationCode.getSendTime()) > 300000) {
            return new ResponseResult(Result.FAILURE, "验证码失效");
        }
        //验证码错误
        if (!verificationCode.getCode().equals(code)) {
            return new ResponseResult(Result.FAILURE, "验证码错误");
        }
        Customer customer = customerService.updateMobile(customerId, moblie);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "提交反馈")
    @PostMapping(value = "addFeedback")
    public ResponseResult addFeedback(Feedback feedback, @ModelAttribute("customerId") int customerId) {

        return new ResponseResult(feedbackService.addFeedback(feedback));
    }

}
