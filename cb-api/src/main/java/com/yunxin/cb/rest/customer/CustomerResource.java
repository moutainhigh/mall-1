package com.yunxin.cb.rest.customer;

import com.yunxin.cb.im.RongCloudService;
import com.yunxin.cb.insurance.meta.GratitudeType;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.cb.mall.vo.CustomerInfoVo;
import com.yunxin.cb.mall.vo.CustomerMatchsVo;
import com.yunxin.cb.mall.vo.CustomerUpdateVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.redis.RedisService;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.service.ICustomerFriendRequestService;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

    @Resource
    private RedisService redisService;

    @ApiOperation(value = "我的好友")
    @GetMapping(value = "myFriends")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "get", dataType = "int")})
    public ResponseResult myFriends() {
        return new ResponseResult(customerService.getFriendByCustomerId(getCustomerId()));
    }

    @ApiOperation(value = "通过手机号搜索好友")
    @PostMapping(value = "queryFriend")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")})
    public ResponseResult queryFriend(@RequestParam("mobile") String mobile) {
        Customer friend = customerService.getCustomerByMobile(mobile);
        if (friend != null) {
            int customerId = getCustomerId();
            friend.setFriend(customerService.isFriend(customerId, friend.getCustomerId()) || friend.getCustomerId() == customerId);
            CustomerFriend customerFriend= customerService.getFriend(customerId,friend.getCustomerId());
            if(null!=customerFriend) {
                friend.setState(customerFriend.getState().name());
                friend.setAliasName(customerFriend.getAliasName());
            }
            return new ResponseResult(friend);
        }
        return new ResponseResult(Result.FAILURE, "未找到相关好友");
    }
    @ApiOperation(value = "通过用户名查询用户")
    @GetMapping(value = "queryByAccountName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "用户名", required = true, paramType = "get", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "get", dataType = "int")})
    public ResponseResult queryByAccountName(@RequestParam("accountName") String accountName) {
        Customer customer = customerService.getAccountName(accountName);
        if (customer != null) {
            int customerId = getCustomerId();
            customer.setFriend(customerService.isFriend(customerId, customer.getCustomerId()) || customer.getCustomerId() == customerId);
            CustomerFriend customerFriend= customerService.getFriend(customerId,customer.getCustomerId());
            if(null!=customerFriend) {
                customer.setState(customerFriend.getState().name());
                customer.setAliasName(customerFriend.getAliasName());
            }
            return new ResponseResult(customer);
        }
        return new ResponseResult(Result.FAILURE, "未找到用户信息");
    }

    @ApiOperation(value = "添加好友")
    @PostMapping(value = "addFriend")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")})
    public ResponseResult addFriend(@RequestParam("mobile") String mobile) {
        try {
            Customer myself = customerService.getCustomerById(getCustomerId());
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
     * @return com.yunxin.cb.vo.ResponseResult
     * @throws
     * @author likang
     * @date 2018/7/18 20:03
     */
    @ApiOperation(value = "根据邀请添加好友ID查询所有添加记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    @GetMapping(value = "getCustomerFriendRequestList")
    public ResponseResult getCustomerFriendRequestList() {
        return new ResponseResult(customerFriendRequestService.getCustomerFriendRequestByFriendId(getCustomerId()));
    }

    @ApiOperation(value = "添加好友通知")
    @PostMapping(value = "addFriendNotice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "friendId", value = "好友ID", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "requestMessage", value = "通知消息", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult addFriendNoitce(@RequestParam("friendId") String friendId, @RequestParam("requestMessage") String requestMessage) {
        try {
            Customer myself = customerService.getCustomerById(getCustomerId());
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult updateFriendsProfile(@RequestBody CustomerFriend customerFriend) {
        return new ResponseResult(customerService.updateFriendsProfile(customerFriend));
    }
    @ApiOperation(value = "删除好友申请通知")
    @DeleteMapping(value = "removeFriendRequest/{friendId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "friendId", value = "好友ID", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult removeFriendRequest(@PathVariable int friendId){
        try{
            customerFriendRequestService.deleteCustomerFriendRequestById(friendId,getCustomerId());
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseResult(Result.FAILURE,"删除失败");
        }
        return new ResponseResult(Result.SUCCESS);
    }
    @ApiOperation(value = "删除好友")
    @DeleteMapping(value = "removeFriend/{friendId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "friendId", value = "好友ID", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult removeFriend(@PathVariable int friendId){
        try{
            int customerId = getCustomerId();
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "avatar", value = "头像地址", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult updateAvatar(@RequestParam("avatar") String avatar) throws Exception {
        Customer customer = customerService.updateAvatar(getCustomerId(), avatar);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户昵称")
    @PostMapping(value = "updateNickName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", value = "用户昵称", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult updateNickName(@RequestParam("nickName") String nickName) throws Exception {
        Customer customer = customerService.updateNickName(getCustomerId(), nickName);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户性别")
    @PostMapping(value = "updateSex")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sex", value = "用户性别", required = true, paramType = "post", dataType = "boolean"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult updateSex(@RequestParam("sex") boolean sex) {
        Customer customer = customerService.updateSex(getCustomerId(), sex);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改用户所在地")
    @PostMapping(value = "updateAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "所在省", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "city", value = "所在市", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "district", value = "所在区", required = false, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "用户地址", required = false, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult updateAddress(@RequestParam("province") String province, @RequestParam("city") String city,
                                        @RequestParam(value = "district", required = false) String district, @RequestParam(value = "address", required = false) String address) {
        Customer customer = customerService.updateAddress(getCustomerId(), province, city, district, address);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "修改手机号")

    @PostMapping(value = "updateMobile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moblie", value = "手机号", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult updateMobile(String moblie, String code) {
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) redisService.getVerificationCode(moblie);
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
        Customer customer = customerService.updateMobile(getCustomerId(), moblie);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "查询客户基本信息")
    @PostMapping(value = "getCustomerInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult getCustomerInfo() throws Exception{
        Customer customer = customerService.getCustomerById(getCustomerId());
        CustomerInfoVo customerInfoVo = new CustomerInfoVo();
        BeanUtils.copyProperties(customerInfoVo, customer);
        return new ResponseResult(customerInfoVo);
    }

    @ApiOperation(value = "提交反馈")
    @PostMapping(value = "addFeedback")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult addFeedback(@RequestBody Feedback feedback) {
        Customer customer = new Customer();
        customer.setCustomerId(getCustomerId());
        feedback.setCustomer(customer);
        feedback.setCreateTime(new Date());
        return new ResponseResult(feedbackService.addFeedback(feedback));
    }

    @ApiOperation(value = "添加黑名单")
    @GetMapping(value = "addBlacklist/{friendId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "friendId", value = "朋友ID", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "get", dataType = "int")
    })
    public ResponseResult addBlacklist(@PathVariable int friendId) {
        try {
            customerService.addBlacklist(friendId, getCustomerId());
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.error("addBlacklist failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        }
    }


    @ApiOperation(value = "移除黑名单")
    @GetMapping(value = "removeBlacklist/{friendId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "friendId", value = "朋友ID", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "get", dataType = "int")
    })
    public ResponseResult removeBlacklist(@PathVariable int friendId) {
        try {
            customerService.removeBlacklist(friendId, getCustomerId());
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.error("removeBlacklist failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        }
    }

    @ApiOperation(value = "获取黑名单")
    @GetMapping(value = "getBlacklist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "get", dataType = "int")
    })
    public ResponseResult getBlacklist() {
        try {
            List<CustomerFriend> blackList = customerService.getBlacklist(getCustomerId());
            return new ResponseResult(blackList);
        } catch (Exception e) {
            logger.error("getBlacklist failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        }
    }


    @ApiOperation(value = "用户点赞")
    @PostMapping(value = "praise")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult praise() {
        try{
            if(customerService.customerPraise(getCustomerId()))
                return new ResponseResult(Result.SUCCESS,"感恩成功");
            else
                return new ResponseResult(Result.FAILURE,"请先购买保险才能感恩推荐人");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(Result.FAILURE,"服务器异常,请稍后重试");
        }

    }

    @ApiOperation(value = "查询点赞用户")
    @PostMapping(value = "getPraiseCustomer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult getPraiseCustomer() {
        return new ResponseResult(customerService.getPraiseCustomers(getCustomerId()));
    }
    @ApiOperation(value = "更新用户信息")
    @PostMapping(value = "updateCustomer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "post", dataType = "int")
    })
    public ResponseResult  updateCustomer(@RequestBody CustomerUpdateVo customerUpdateVo){
        try{
            return new ResponseResult(customerService.updateCustomerMsg(getCustomerId(),customerUpdateVo));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseResult(Result.FAILURE,"服务器异常");
        }
    }
    @ApiOperation(value = "匹配通讯录")
    @PostMapping(value = "matchAddressBook")
    public ResponseResult matchAddressBook(@RequestBody CustomerMatchsVo[] customerMatchsVo){
            return new ResponseResult( customerService.matchAddressBook(customerMatchsVo));
    }

    @ApiOperation(value = "获取感恩统计")
    @GetMapping(value = "getGratitude")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "get", dataType = "int")
    })
    public ResponseResult getGratitude() {
        try {
            return new ResponseResult(customerService.findCustomerGratitude(getCustomerId()));
        } catch (Exception e) {
            logger.error("getGratitude failed", e);
            return new ResponseResult(Result.FAILURE, "服务器开小差，请稍后重试");
        }
    }
    @ApiOperation(value = "获取感恩列表")
    @GetMapping(value = "getGratitudeData/{gratitudeType}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "get", dataType = "int")
    })
    public ResponseResult getGratitudeData(@PathVariable GratitudeType gratitudeType) {
        try {
            return new ResponseResult(customerService.findCustomerGratitudeData(getCustomerId(),gratitudeType));
        } catch (Exception e) {
            logger.error("getGratitudeData failed", e);
            return new ResponseResult(Result.FAILURE, "服务器开小差，请稍后重试");
        }
    }
    @ApiOperation(value = "我的个人统计")
    @GetMapping(value = "getInterpersonal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "get", dataType = "int")
    })
    public ResponseResult getInterpersonal() {
        try {
            return new ResponseResult(customerService.getInterpersonal(getCustomerId()));
        } catch (Exception e) {
            logger.error("getGratitudeData failed", e);
            return new ResponseResult(Result.FAILURE, "服务器开小差，请稍后重试");
        }
    }
//    @ApiOperation(value = "创建群组")
//    @PostMapping(value = "matchAddressBook")
//    public ResponseResult matchAddressBook(@RequestBody CustomerMatchsVo[] customerMatchsVo){
//        return new ResponseResult( customerService.matchAddressBook(customerMatchsVo));
//    }

}
