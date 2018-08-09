package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @title:  信用额度接口
 * @auther: gws
 * @date: 2018/8/8 14:28
 */
@Api(description = "信用额度接口")
@RestController
@RequestMapping(value = "/{version}/rb/creditAmount")
public class CreditAmountResource extends BaseResource {

    @Resource
    private CustomerService customerService;

    
    @ApiOperation(value = "获取信用额度信息")
    @ApiImplicitParams({
    })
    @GetMapping()
    @ApiVersion(1)
    public ResponseResult getCreditAmountInfo() {
        //Customer customer = customerService.getCustomerById(getCustomerId());
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "获取信用额度借款配置以及银行卡等借款信息")
    @ApiImplicitParams({
    })
    @GetMapping()
    @ApiVersion(1)
    public ResponseResult getLoanInfo() {
        //Customer customer = customerService.getCustomerById(getCustomerId());
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "提交借款")
    @ApiImplicitParams({
    })
    @PostMapping(value = "submitLoan")
    @ApiVersion(1)
    public ResponseResult submitLoan() {
        //Customer customer = customerService.getCustomerById(CustomerId());
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "额度明细")
    @ApiImplicitParams({
    })
    @GetMapping(value = "Quotas")
    @ApiVersion(1)
    public ResponseResult Quotas() {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "借款明细")
    @ApiImplicitParams({
    })
    @GetMapping(value = "loanDetails")
    @ApiVersion(1)
    public ResponseResult checkPwd() {
        return new ResponseResult(Result.SUCCESS);
    }


}