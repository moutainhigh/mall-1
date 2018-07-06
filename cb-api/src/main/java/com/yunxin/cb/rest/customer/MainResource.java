package com.yunxin.cb.rest.customer;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.meta.SendType;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.sms.SmsHelper;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
import com.yunxin.core.util.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Resource
    private ICustomerService customerService;

    @ApiOperation(value ="用户注册")
    @PostMapping(value = "register")
    public ResponseResult register(@RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(Result.FAILURE,"注册失败");
        }
        return new ResponseResult(Result.SUCCESS, customer);
    }

    @ApiOperation(value ="用户名密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "账号(可以为账号名称和手机号)", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form", dataType = "String")
    })
    @PostMapping(value = "loginByPwd")
    public ResponseResult loginByPwd(@RequestParam String accountName, @RequestParam String password) {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value ="手机号验证码登录")
    @PostMapping(value = "loginByCode")
    public ResponseResult loginByCode(@RequestParam String accountName, @RequestParam String code) {
        return new ResponseResult(Result.SUCCESS);
    }


    @ApiOperation(value ="发送验证码")
    @PostMapping("sendMobileValidCode/{sendType}/{mobile}")
    public ResponseResult sendMobileValidCode(@PathVariable SendType sendType, @PathVariable String mobile, HttpServletRequest request, HttpSession session) {
        ResponseResult responseResult = new ResponseResult(Result.FAILURE);
        //send to mobile
        boolean existMobile = customerService.getCustomerByMobile(mobile) != null ? true : false;
        boolean isSend = false;
        //判断时间，1分钟只允许发送一次
        VerificationCode verificationCode = (VerificationCode) session.getAttribute(mobile);
        if(verificationCode != null && (System.currentTimeMillis() - verificationCode.getSendTime()) < 60000){
            responseResult.setMessage("发送过于频繁，请稍后在试！");
            return responseResult;
        }
        switch (sendType) {
            case FORGET_PASSWORD:
            case LOGIN:
                isSend = existMobile;
                if (!existMobile) {
                    responseResult.setMessage("手机号不存在！");
                }
                break;
            case REGISTER:
            case CHANGE_MOBILE:
                isSend = !existMobile;
                if (existMobile) {
                    responseResult.setMessage("手机号已存在！");
                }
                break;
        }
        if (isSend) {
            try {
                String randomCode = CommonUtils.randomString(6, CommonUtils.RANDRULE.RAND_NUMBER);
                 boolean sendState = SmsHelper.sendMobileValidCode(getIpAddr(request), randomCode, mobile);
                if (sendState) {
                    responseResult.setResult(Result.SUCCESS);
                    VerificationCode mobileCode = new VerificationCode(mobile, randomCode, System.currentTimeMillis());
                    session.setAttribute(mobile, mobileCode);
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
}
