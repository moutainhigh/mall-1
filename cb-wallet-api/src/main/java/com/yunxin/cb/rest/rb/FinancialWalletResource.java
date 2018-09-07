package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.mall.service.FinancialWalletService;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "我的钱包")
@RestController
@RequestMapping(value = "/{version}/rb/wallet")
public class FinancialWalletResource extends BaseResource {

    @Resource
    private FinancialWalletService financialWalletService;

    @Resource
    private CustomerService customerService;


    @ApiOperation(value = "获取钱包信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "get")
    public ResponseResult<FinancialWalletVO> get() {
        try {
            Customer customer = customerService.getCustomerById(getCustomerId());
            if (customer == null) {
                return new ResponseResult<>(Result.FAILURE, "未获取到用户信息");
            }
            FinancialWalletVO vo = financialWalletService.getFinancialWalletByCustomerId(customer.getCustomerId());
            return new ResponseResult<>(vo);
        } catch (Exception e) {
            logger.info("get failed", e);
        }
        return new ResponseResult<>(Result.FAILURE);
    }

}
