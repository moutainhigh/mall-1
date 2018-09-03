package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.mall.service.FinancialWalletService;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Api(description = "钱包接口")
@RestController
@RequestMapping(value = "/{version}/rb/wallet")
public class FinancialWalletResource extends BaseResource {

    @Resource
    private FinancialWalletService financialWalletService;

    @Resource
    private CustomerService customerService;

//    @ApiOperation(value = "添加钱包信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "path", dataType = "string"),
//            @ApiImplicitParam(name = "expectedAmount", value = "预期收益（买的保险额的一半）", required = true, paramType = "path", dataType = "string")
//    })
//    @ApiVersion(1)
//    @PostMapping(value = "add")
//    public ResponseResult<FinancialWalletVO> add(@RequestBody FinancialWalletVO vo){
//        try {
//            vo = financialWalletService.addFinancialWallet(vo);
//            return new ResponseResult(vo);
//        } catch (Exception e) {
//            logger.error("add failed", e);
//        }
//        return new ResponseResult(Result.FAILURE);
//    }

//    @ApiOperation(value = "更新钱包信息")
//    @ApiImplicitParams({
//    })
//    @ApiVersion(1)
//    @PostMapping(value = "update")
//    public ResponseResult<FinancialWalletVO> update(@RequestBody FinancialWalletVO vo){
//        try {
//            vo = financialWalletService.updateFinancialWallet(vo);
//            return new ResponseResult(vo);
//        } catch (Exception e) {
//            log.info("update failed", e);
//        }
//        return new ResponseResult(Result.FAILURE);
//    }

    @ApiOperation(value = "获取钱包信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "get")
    public ResponseResult<FinancialWalletVO> get(){
        try {
            Customer customer = customerService.getCustomerById(getCustomerId());
            if (customer == null) {
                return new ResponseResult(Result.FAILURE, "未获取到用户信息");
            }
            FinancialWalletVO vo= financialWalletService.getFinancialWalletByCustomerId(customer.getCustomerId());
            return new ResponseResult(vo);
        } catch (Exception e) {
            logger.info("get failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    /**
     * @title: 处理用户返现接口（用于报账转账和保险返利转账）
     * @param: [customerId]
     * @return: com.yunxin.cb.vo.ResponseResult<com.yunxin.cb.mall.vo.FinancialWalletVO>
     * @auther: eleven
     * @date: 2018/8/8 19:34
     */
    @ApiOperation(value = "返现接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, paramType = "path", dataType = "bigdecimal"),
            @ApiImplicitParam(name = "remark", value = "预留字段（可填保单号）", required = true, paramType = "path", dataType = "string")
    })
    @ApiVersion(1)
    @PostMapping(value = "processCustomerMoney/{customerId}/{money}/{type}/{remark}")
    public com.yunxin.cb.mall.restful.ResponseResult processCustomerMoney(@PathVariable Integer customerId, @PathVariable BigDecimal money,
                                                                          @PathVariable WithdrawType type, @PathVariable String remark){
        com.yunxin.cb.mall.restful.ResponseResult result=new com.yunxin.cb.mall.restful.ResponseResult(Result.FAILURE);
        try {
            result= financialWalletService.processCustomerMoney(customerId,money,type,remark);
        } catch (Exception e) {
            logger.info("get failed", e);
        }
        return result;
    }
}
