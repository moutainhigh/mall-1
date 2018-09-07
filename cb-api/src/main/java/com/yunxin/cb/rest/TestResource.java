package com.yunxin.cb.rest;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.FinancialWallet;
import com.yunxin.cb.mall.service.imp.FinancialWalletService;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.math.BigDecimal;

/**
* @Description:    获取分享配置信息
* @Author:         likang
* @CreateDate:     2018/7/28 10:32
*/
@Api(description = "获取分享配置信息")
@RestController
@RequestMapping(value = "/test")
public class TestResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(TestResource.class);
    @Resource
    FinancialWalletService F;

    @ApiOperation(value = "获取分享配置信息")
    @ApiImplicitParams({
    })
    @GetMapping(value = "add")
    public ResponseResult<FinancialWallet> getShareInfo() {
        Customer c = new Customer();
        c.setCustomerId(305);
        FinancialWallet fw=new FinancialWallet(c);
        fw.setWalletId(4);
        fw.setCreditAmount(new BigDecimal(100));
        fw=F.addFinaciaWallet(fw,0,new BigDecimal(100));
        return new ResponseResult(fw);
    }
}
