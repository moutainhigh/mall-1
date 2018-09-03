package com.yunxin.cb.rest.rb;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.mall.service.FinacialRepaymentService;
import com.yunxin.cb.mall.vo.FinacialRepaymentVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Api(description = "还款信息")
@RestController
@RequestMapping(value = "/{version}/rb/repayment")
public class FinacialRepaymentResource extends BaseResource {


    @Resource
    private FinacialRepaymentService finacialRepaymentService;

    @Resource
    private CustomerService customerService;

    @ApiOperation(value = "还款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repayAmount", value = "还款金", required = true, paramType = "path", dataType = "string")
    })
    @ApiVersion(1)
    @PostMapping(value = "repayAmount")
    public ResponseResult<FinacialRepaymentVO> repayAmount(@RequestBody BigDecimal repayAmount){
        try {
            Customer customer = customerService.getCustomerById(getCustomerId());
            if (customer == null) {
                return new ResponseResult(Result.FAILURE, "未获取到用户信息");
            }
            finacialRepaymentService.add(repayAmount,customer.getCustomerId());
            return new ResponseResult("还款成功");
        } catch (Exception e) {
            logger.info("add failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

}
