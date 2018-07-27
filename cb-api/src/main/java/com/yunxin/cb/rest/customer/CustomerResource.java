package com.yunxin.cb.rest.customer;

import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.im.RongCloudService;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.cb.mall.vo.CustomerInfoVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.service.ICustomerFriendRequestService;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Api(description = "用户接口")
@RestController
@RequestMapping(value = "/customer")
@SessionAttributes("customerId")
public class CustomerResource extends BaseResource {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ICustomerService customerService;

    @Resource
    private IFeedbackService feedbackService;

    @Resource
    private RongCloudService rongCloudService;

    @Resource
    private ICustomerFriendRequestService customerFriendRequestService;

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
            CustomerFriend customerFriend= customerService.getFriend(customerId,friend.getCustomerId());
            if(null!=customerFriend)
                friend.setState(customerFriend.getState().name());
            return new ResponseResult(friend);
        }
        return new ResponseResult(Result.FAILURE, "未找到相关好友");
    }
    @ApiOperation(value = "通过用户名查询用户")
    @GetMapping(value = "queryByAccountName")
    public ResponseResult queryByAccountName(@RequestParam("accountName") String accountName,@ModelAttribute("customerId") int customerId) {
        Customer customer = customerService.getAccountName(accountName);
        if (customer != null) {
            customer.setFriend(customerService.isFriend(customerId, customer.getCustomerId()) || customer.getCustomerId() == customerId);
            CustomerFriend customerFriend= customerService.getFriend(customerId,customer.getCustomerId());
            if(null!=customerFriend)
                customer.setState(customerFriend.getState().name());
            return new ResponseResult(customer);
        }
        return new ResponseResult(Result.FAILURE, "未找到用户信息");
    }

    @ApiOperation(value = "添加好友")
    @PostMapping(value = "addFriend")
    public ResponseResult addFriend(@RequestParam("mobile") String mobile, @ModelAttribute("customerId") int customerId) {
        try {
            Customer myself = customerService.getCustomerById(customerId);
            Customer customer = customerService.getCustomerByMobile(mobile);
            if (customer == null) {
                return new ResponseResult(Result.FAILURE, "您所添加的用户不存在");
            }
            customerService.addTwoWayFriend(customer,myself);
            return new ResponseResult(Result.SUCCESS);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseResult(Result.FAILURE,"好友添加失败");
        }
    }



    /**
     * 根据邀请添加好友ID查询所有添加记录
     *
     * @param customerId
     * @return com.yunxin.cb.vo.ResponseResult
     * @throws
     * @author likang
     * @date 2018/7/18 20:03
     */
    @ApiOperation(value = "根据邀请添加好友ID查询所有添加记录")
    @ApiImplicitParams({

    })
    @GetMapping(value = "getCustomerFriendRequestList")
    public ResponseResult getCustomerFriendRequestList(@ModelAttribute("customerId") int customerId) {
        return new ResponseResult(customerFriendRequestService.getCustomerFriendRequestByFriendId(customerId));
    }

    @ApiOperation(value = "添加好友通知")
    @PostMapping(value = "addFriendNotice")
    public ResponseResult addFriendNoitce(@RequestParam("friendId") String friendId, @RequestParam("requestMessage") String requestMessage, @ModelAttribute("customerId") int customerId) {
        try {
            Customer myself = customerService.getCustomerById(customerId);
            Customer friend = customerService.findByAccountName(friendId);

            if (friend == null) {
                return new ResponseResult(Result.FAILURE, "您所添加的用户不存在");
            }
            rongCloudService.sendMessage(myself, friend, requestMessage);
            //添加好友请求记录
            customerFriendRequestService.addCustomerFriendRequest(myself, friend, requestMessage);
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.error("addFriendNotice failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        }
    }

    @ApiOperation(value = "修改好友备注")
    @PostMapping(value = "updateFriendsProfile")
    public ResponseResult updateFriendsProfile(@RequestBody CustomerFriend customerFriend, @ModelAttribute("customerId") int customerId) {
        return new ResponseResult(customerService.updateFriendsProfile(customerFriend));
    }
    @ApiOperation(value = "删除好友申请通知")
    @DeleteMapping(value = "removeFriendRequest/{friendId}")
    public ResponseResult removeFriendRequest(@PathVariable int friendId, @ModelAttribute("customerId") int customerId){
        try{
            customerFriendRequestService.deleteCustomerFriendRequestById(friendId,customerId);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseResult(Result.FAILURE,"删除失败");
        }
        return new ResponseResult(Result.SUCCESS);
    }
    @ApiOperation(value = "删除好友")
    @DeleteMapping(value = "removeFriend/{friendId}")
    public ResponseResult removeFriend(@PathVariable int friendId, @ModelAttribute("customerId") int customerId){
        try{
            customerService.delFriendById(customerId, friendId);
            customerFriendRequestService.deleteCustomerFriendRequestById(friendId,customerId);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseResult(Result.FAILURE,"好友删除失败");
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping(value = "updateAvatar")
    public ResponseResult updateAvatar(@RequestParam("avatar") String avatar, @ModelAttribute("customerId") int customerId) throws Exception {
        Customer customer = customerService.updateAvatar(customerId, avatar);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户昵称")
    @PostMapping(value = "updateNickName")
    public ResponseResult updateNickName(@RequestParam("nickName") String nickName, @ModelAttribute("customerId") int customerId) throws Exception {
        Customer customer = customerService.updateNickName(customerId, nickName);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户性别")
    @PostMapping(value = "updateSex")
    public ResponseResult updateSex(@RequestParam("sex") boolean sex, @ModelAttribute("customerId") int customerId) {
        Customer customer = customerService.updateSex(customerId, sex);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户所在地")
    @PostMapping(value = "updateAddress")
    public ResponseResult updateAddress(@RequestParam("province") String province, @RequestParam("city") String city,
                                        @RequestParam(value = "district", required = false) String district, @RequestParam(value = "address", required = false) String address, @ModelAttribute("customerId") int customerId) {
        Customer customer = customerService.updateAddress(customerId, province, city, district, address);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改手机号")
    @PostMapping(value = "updateMobile")
    public ResponseResult updateMobile(String moblie, String code, @ModelAttribute("customerId") int customerId) {
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(moblie);
        //验证码不存在
        if (verificationCode == null) {
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

    @ApiOperation(value = "查询客户基本信息")
    @PostMapping(value = "getCustomerInfo")
    public ResponseResult getCustomerInfo(@ModelAttribute("customerId") int customerId) throws Exception{
        Customer customer = customerService.getCustomerById(customerId);
        CustomerInfoVo customerInfoVo = new CustomerInfoVo();
        BeanUtils.copyProperties(customerInfoVo, customer);
        return new ResponseResult(customerInfoVo);
    }

    @ApiOperation(value = "提交反馈")
    @PostMapping(value = "addFeedback")
    public ResponseResult addFeedback(@RequestBody Feedback feedback, @ModelAttribute("customerId") int customerId) {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        feedback.setCustomer(customer);
        feedback.setCreateTime(new Date());
        return new ResponseResult(feedbackService.addFeedback(feedback));
    }

    @ApiOperation(value = "添加黑名单")
    @GetMapping(value = "addBlacklist/{friendId}")
    public ResponseResult addBlacklist(@PathVariable int friendId, @ModelAttribute("customerId") int customerId) {
        try {
            customerService.addBlacklist(friendId, customerId);
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.error("addBlacklist failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        }
    }


    @ApiOperation(value = "移除黑名单")
    @GetMapping(value = "removeBlacklist/{friendId}")
    public ResponseResult removeBlacklist(@PathVariable int friendId, @ModelAttribute("customerId") int customerId) {
        try {
            customerService.removeBlacklist(friendId, customerId);
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.error("removeBlacklist failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        }
    }

    @ApiOperation(value = "获取黑名单")
    @GetMapping(value = "getBlacklist")
    public ResponseResult getBlacklist(@ModelAttribute("customerId") int customerId) {
        try {
            List<CustomerFriend> blackList = customerService.getBlacklist(customerId);
            return new ResponseResult(blackList);
        } catch (Exception e) {
            logger.error("getBlacklist failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        }
    }


    @ApiOperation(value = "用户点赞")
    @PostMapping(value = "praise")
    public ResponseResult praise(@ModelAttribute("customerId") int customerId) {
        try{
            if(customerService.customerPraise(customerId))
                return new ResponseResult(Result.SUCCESS,"感恩成功");
            else
                return new ResponseResult(Result.FAILURE,"请先购买保险才能感恩推荐人");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseResult(Result.FAILURE,"服务器异常,请稍后重试");
        }

    }

    @ApiOperation(value = "查询点赞用户")
    @PostMapping(value = "getPraiseCustomer")
    public ResponseResult getPraiseCustomer(@ModelAttribute("customerId") int customerId) {
        return new ResponseResult(customerService.getPraiseCustomers(customerId));
    }


}
