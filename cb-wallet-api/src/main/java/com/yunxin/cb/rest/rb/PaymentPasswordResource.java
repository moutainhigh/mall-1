package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.mall.vo.PaymentPasswordVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @title:  支付密码设置接口（暂不用，砍掉了）
 * @auther: gws
 * @date: 2018/8/8 14:28
 */
@ApiIgnore
@Api(description = "支付密码设置接口")
@RestController
@RequestMapping(value = "/{version}/rb/paymentPassword")
public class PaymentPasswordResource extends BaseResource {

    @Resource
    private CustomerService customerService;


    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams({
    })
    @GetMapping(value = "getCustomerInfo")
    @ApiVersion(1)
    public ResponseResult<PaymentPasswordVO> getCustomerInfo() {
        Customer customer = customerService.getCustomerById(getCustomerId());
        PaymentPasswordVO paymentPasswordVO = new PaymentPasswordVO();
        paymentPasswordVO.setAccountName(customer.getAccountName());
        paymentPasswordVO.setRealName(customer.getRealName());
        return new ResponseResult(paymentPasswordVO);
    }

    @ApiOperation(value = "用户信息验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "realName", value = "真实姓名", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "customerCardNo", value = "证件号码后6未数", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "mobileVaildCode", value = "手机验证码", required = true, paramType = "form", dataType = "String")
    })
    @PostMapping(value = "checkCustomerInfo")
    @ApiVersion(1)
    public ResponseResult checkCustomerInfo(@RequestBody PaymentPasswordVO paymentPasswordVO) {
        logger.info("paymentPasswordVO:" + paymentPasswordVO.toString());
        Customer customer = customerService.getCustomerById(getCustomerId());
        //校验证件号码后6位数
        if (customer != null && customer.getCustomerCardNo() != null && customer.getCustomerCardNo().length() > 6) {
            String customerCardNo = customer.getCustomerCardNo();
            String carNo6 = customerCardNo.substring(customerCardNo.length()-6, customerCardNo.length());
            if (!carNo6.equals(paymentPasswordVO.getCustomerCardNo())) {
                return new ResponseResult(Result.FAILURE, "证件号不一致");
            }
            String realName = paymentPasswordVO.getRealName();
            if (realName == null || !realName.equals(customer.getRealName())) {
                return new ResponseResult(Result.FAILURE, "姓名不一致");
            }
        } else {
            return new ResponseResult(Result.FAILURE, "用户不存在");
        }
        //校验验证码
        String checkStr = verificationCode(customer.getMobile(), paymentPasswordVO.getMobileVaildCode());
        if (checkStr != null) {
            return new ResponseResult(Result.FAILURE, checkStr);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "交易密码验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paymentPassword", value = "交易密码", required = true, paramType = "form", dataType = "string")
    })
    @PostMapping(value = "checkPwd")
    @ApiVersion(1)
    public ResponseResult checkPwd(@RequestParam(value = "paymentPassword") String paymentPassword) {
        Customer customer = customerService.getCustomerById(getCustomerId());
        if (customer == null || !paymentPassword.equals(customer.getPaymentPassword())) {
            return new ResponseResult(Result.FAILURE, "您输入的交易密码不正确");
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "更新交易密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paymentPassword1", value = "第一次交易密码", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "paymentPassword2", value = "第二次交易密码", required = true, paramType = "form", dataType = "string")
    })
    @PutMapping(value = "update")
    @ApiVersion(1)
    public ResponseResult updatePaymentPassword(@RequestParam(value = "paymentPassword1") String paymentPassword1,
            @RequestParam(value = "paymentPassword2") String paymentPassword2) {
        //密码一致更新交易密码，带上用户id
        if (!paymentPassword1.equals(paymentPassword2)) {
            return new ResponseResult(Result.FAILURE, "两次密码不一致");
        }
        //更新交易密码
        customerService.updatePaymentPasswordByCustomerId(getCustomerId(), paymentPassword1);
        return new ResponseResult(Result.SUCCESS);
    }


}