package com.yunxin.cb.rest.customer;

import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.jwt.JwtUtil;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.meta.CustomerType;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.vo.CustomerVo;
import com.yunxin.cb.mall.vo.RecommendCustomerVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.meta.SendType;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.sms.SmsHelper;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
import com.yunxin.core.util.CommonUtils;
import com.yunxin.core.util.IdGenerate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(description = "用户接口")
@RestController
@CrossOrigin
@RequestMapping(value = "/noAuth")
public class MainResource extends BaseResource {

    private static Logger logger = LoggerFactory.getLogger(MainResource.class);

    @Value("${application.default.avatarUrl}")
    private String avatarUrl;

    @Resource
    private ICustomerService customerService;


//    @ApiOperation(value = "用户注册")
//    @PostMapping(value = "register")
//    public ResponseResult register(@RequestParam String mobile, @RequestParam String pwd, @RequestParam(required = false) String recommendMobile, @RequestParam String code) {
//        try {
//            //校验验证码
//            VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(mobile);
//            //验证码不存在
//            if (verificationCode == null){
//                return new ResponseResult(Result.FAILURE, "验证码不存在");
//            }
//            //验证码超过5分钟，失效
//            if ((System.currentTimeMillis() - verificationCode.getSendTime()) > 300000) {
//                return new ResponseResult(Result.FAILURE, "验证码失效");
//            }
//            //验证码错误
//            if (!verificationCode.getCode().equals(code)) {
//                return new ResponseResult(Result.FAILURE, "验证码错误");
//            }
//            //验证推荐人手机号
//            Customer recommendCustomer = customerService.getCustomerByMobile(recommendMobile);
//            if(StringUtils.isNotBlank(recommendMobile)){
//                if(recommendCustomer == null){
//                    return new ResponseResult(Result.FAILURE, "邀请码不存在");
//                }
//            }
//            Customer customer = new Customer();
//            customer.setAccountName(mobile);
//            customer.setMobile(mobile);
//            customer.setPassword(pwd);
//            customer.setAvatarUrl(avatarUrl);
//            customer.setCustomerType(CustomerType.PLATFORM_SELF);
//            customer.setEnabled(true);
//            if(recommendCustomer != null){
//                customer.setRecommendCustomer(recommendCustomer);
//            }
//            customer = customerService.addCustomer(customer);
//            String token = JwtUtil.generateToken(customer.getCustomerId(), customer.getMobile());
//            customer.setToken(token);
//            return new ResponseResult(customer);
//        } catch (Exception e) {
//            logger.error("用户注册异常", e);
//        }
//        return new ResponseResult(Result.FAILURE, "注册失败");
//    }

    @ApiOperation(value = "用户注册验证")
    @PostMapping(value = "registerFirst")
    public ResponseResult registerFirst(@RequestBody CustomerVo customerVo){
        try {
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(customerVo.getMobile());
        //验证码不存在
        if (verificationCode == null){
            return new ResponseResult(Result.FAILURE, "验证码不存在");
        }
        //验证码超过5分钟，失效
        if ((System.currentTimeMillis() - verificationCode.getSendTime()) > 300000) {
            return new ResponseResult(Result.FAILURE, "验证码失效");
        }
        //验证码错误
        if (!verificationCode.getCode().equals(customerVo.getCode())) {
            return new ResponseResult(Result.FAILURE, "验证码错误");
        }

        //邀请码或者手机号码
        String invitationCode=customerVo.getInvitationCode();
        if(StringUtils.isEmpty(invitationCode)){
                return new ResponseResult(Result.FAILURE, "邀请码或推荐人不能为空");
        }
        //验证推荐人手机号
        Customer recommendCustomer = customerService.getCustomerByInvitationCode(invitationCode);
        if(StringUtils.isNotBlank(invitationCode)){
            if(recommendCustomer == null){
                return new ResponseResult(Result.FAILURE, "邀请码或推荐人不存在");
            }
        }
        } catch (Exception e) {
            logger.error("用户注册异常", e);
            return new ResponseResult(Result.FAILURE, "用户注册验证异常");
        }
        String random=String.valueOf(System.currentTimeMillis());
        CachedUtil.getInstance().setContext(customerVo.getMobile()+customerVo.getMobile(),random);
        return new ResponseResult(random);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "register")
    public ResponseResult register(@RequestBody CustomerVo customerVo){
        try {
            logger.info("register-random"+customerVo.getRandom());
            if(!StringUtils.isNotBlank(customerVo.getRandom()))
                return new ResponseResult(Result.FAILURE, "注册失败");
            else{
               String random=(String)CachedUtil.getInstance().getContext(customerVo.getMobile()+customerVo.getMobile());
                logger.info("register-randomNext"+random);
               if(!customerVo.getRandom().equals(random))
                   return new ResponseResult(Result.FAILURE, "注册失败");
            }
            String invitationCode=customerVo.getInvitationCode();
            Customer recommendCustomer = customerService.getCustomerByInvitationCode(invitationCode);
            Customer customer = new Customer();
            //随机数
//            IdWorker idWorker = IdWorker.getFlowIdWorkerInstance();
            customer.setAccountName(IdGenerate.generateRandomStr(10));
            customer.setMobile(customerVo.getMobile());
            customer.setPassword(customerVo.getPwd());
            customer.setAvatarUrl(avatarUrl);
            customer.setCustomerType(CustomerType.PLATFORM_SELF);
            customer.setEnabled(true);
            customer.setRealName(customerVo.getRealName());
            customer.setNickName(customer.getRealName());
            customer.setCardType(customerVo.getCardType());
            customer.setCustomerCardNo(customerVo.getCustomerCardNo());
            customer.setCardPositiveImg(customerVo.getCardPositiveImg());
            customer.setCardNegativeImg(customerVo.getCardNegativeImg());
            customer.setBankCardImg(customerVo.getBankCardImg());

            if(recommendCustomer != null){
                customer.setRecommendCustomer(recommendCustomer);
                Customer customerCode=customerService.generateCode(recommendCustomer.getInvitationCode());
                if(customerCode!=null){
                    customer.setLevelCode(customerCode.getLevelCode());
                    customer.setCustomerLevel(customerCode.getCustomerLevel());
                    customer.setInvitationCode(customerCode.getInvitationCode());
                }
            }
            customer = customerService.addCustomer(customer);
            String token = JwtUtil.generateToken(customer.getCustomerId(), customer.getMobile());
            customer.setToken(token);
            CachedUtil.getInstance().removeContext(customerVo.getMobile()+customerVo.getMobile());
            return new ResponseResult(customer);
        } catch (Exception e) {
            logger.error("用户注册异常", e);
        }
        return new ResponseResult(Result.FAILURE, "注册失败");
    }

    @ApiOperation(value = "用户名密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "账号(可以为账号名称和手机号)", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form", dataType = "String")
    })
    @PostMapping(value = "loginByPwd")
    public ResponseResult<Customer> loginByPwd(@RequestParam String accountName, @RequestParam String password) {
        try {
            Customer customer = customerService.getCustomerByAccountNameAndPassword(accountName, password);
            if (customer != null) {
                String token = JwtUtil.generateToken(customer.getCustomerId(), customer.getMobile());
                customer.setToken(token);
                return new ResponseResult(customer);
            }else {
                return new ResponseResult(Result.FAILURE, "账号或密码错误");
            }
        } catch (Exception e) {
            logger.error("登录异常", e);
        }
        return new ResponseResult(Result.FAILURE, "登录失败");
    }

    @ApiOperation(value = "手机号验证码登录")
    @PostMapping(value = "loginByCode")
    public ResponseResult loginByCode(@RequestParam String mobile, @RequestParam String code) {
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(mobile);
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
        Customer customer = customerService.getCustomerByMobile(mobile);
        if (customer != null) {
            if (code != null && code.equals(verificationCode.getCode())) {
                return new ResponseResult(customer);
            }
        }
        return new ResponseResult(Result.FAILURE, "登陆失败");
    }

    @ApiOperation(value = "通过邀请码查询推荐人手机号和邀请人名称")
    @GetMapping(value = "recommendCustomer/{invitationCode}")
    public ResponseResult recommendCustomer(@PathVariable String invitationCode) {
        Customer customer = customerService.getCustomerByInvitationCode(invitationCode);
        RecommendCustomerVo recommendCustomerVo = new RecommendCustomerVo(customer);
        return new ResponseResult(recommendCustomerVo);
    }

    @ApiOperation(value ="修改密码")
    @PostMapping(value = "updatePwd/{mobile}")
    public ResponseResult updatePwd(@PathVariable String mobile, @RequestParam String code, @RequestParam String newPwd) {
        Customer customer = customerService.getCustomerByMobile(mobile);
        if(customer == null){
            return new ResponseResult(Result.FAILURE,"用户不存在");
        }
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(customer.getMobile());
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
        customerService.updatePassword(customer.getCustomerId(), newPwd);
        return new ResponseResult(Result.SUCCESS);
    }


    @ApiOperation(value = "发送验证码")
    @PostMapping("sendMobileValidCode/{sendType}/{mobile}")
    public ResponseResult sendMobileValidCode(@PathVariable SendType sendType, @PathVariable String mobile, HttpSession session, HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult(Result.FAILURE);
        //send to mobile
        boolean existMobile = customerService.getCustomerByMobile(mobile) != null ? true : false;
        boolean isSend = false;
        //判断时间，1分钟只允许发送一次
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(mobile);
        // session.getAttribute(mobile);
        if (verificationCode != null && (System.currentTimeMillis() - verificationCode.getSendTime()) < 60000) {
            responseResult.setMessage("发送过于频繁，请稍后再试！");
            return responseResult;
        }
        switch (sendType) {
            case FORGET_PASSWORD:
            case LOGIN:
                isSend = existMobile;
                if (!existMobile) {
                    responseResult.setMessage("该手机号未注册！");
                }
                break;
            case REGISTER:
            case CHANGE_MOBILE:
                isSend = !existMobile;
                if (existMobile) {
                    responseResult.setMessage("手机号已存在！");
                }
                break;
            case ORDER_CONFIRM:
                isSend = true;
                break;
        }
        if (isSend) {
            try {
                String randomCode = CommonUtils.randomString(6, CommonUtils.RANDRULE.RAND_NUMBER);
                boolean sendState = SmsHelper.sendMobileValidCode(getIpAddr(request), randomCode, mobile);
                if (sendState) {
                    responseResult.setResult(Result.SUCCESS);
                    VerificationCode mobileCode = new VerificationCode(mobile, randomCode, System.currentTimeMillis());
                    CachedUtil.getInstance().setContext(mobile, mobileCode);
                } else {
                    responseResult.setMessage("短信发送失败，请确认手机号或稍后再试!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                responseResult.setMessage("服务器出错！");
            }
        }
        return responseResult;
    }

    @ApiOperation(value ="重置密码")
    @PostMapping(value = "resetPwd/{mobile}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "用户手机号码", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "resetNewPwd", value = "新密码", required = true, paramType = "post", dataType = "String")
    })
    public ResponseResult resetPwd(@RequestParam String mobile, @RequestParam String code,@RequestParam String resetNewPwd) {
        Customer customer = customerService.getCustomerByMobile(mobile);
        if(customer == null){
            return new ResponseResult(Result.FAILURE,"用户不存在");
        }
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(customer.getMobile());
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
        customerService.updatePassword(customer.getCustomerId(), resetNewPwd);
        return new ResponseResult(Result.SUCCESS);
    }

}
