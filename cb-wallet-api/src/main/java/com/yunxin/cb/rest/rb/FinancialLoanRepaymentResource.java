package com.yunxin.cb.rest.rb;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.mall.service.FinancialLoanRepaymentService;
import com.yunxin.cb.mall.service.FinancialLoanService;
import com.yunxin.cb.mall.vo.FinancialLoanVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api(description = "我的负债-还款")
@RestController
@RequestMapping(value = "/{version}/rb/repayment")
public class FinancialLoanRepaymentResource extends BaseResource {

    @Resource
    private FinancialLoanRepaymentService financialLoanRepaymentService;
    @Resource
    private CustomerService customerService;
    @Resource
    private FinancialLoanService financialLoanService;

    @ApiOperation(value = "未还清借款列表")
    @ApiImplicitParams({
    })
    @GetMapping(value = "getRepayingLoans")
    @ApiVersion(1)
    public ResponseResult<List<FinancialLoanVO>> getRepayingLoans() {
        try {
            List<FinancialLoan> loans = financialLoanService.getByCustomerRepaying(getCustomerId());
            List<FinancialLoanVO> volist = new ArrayList<>();
            loans.forEach(financialLoan -> {
                FinancialLoanVO vo = new FinancialLoanVO();
                BeanUtils.copyProperties(financialLoan, vo);
                volist.add(vo);
            });
            return new ResponseResult<>(volist);
        } catch (Exception e) {
            logger.info("getRepayingLoans failed", e);
            return new ResponseResult<>(Result.FAILURE);
        }
    }

    @ApiOperation(value = "还款test-后台测试用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repayAmount", value = "还款金", required = true, paramType = "path", dataType = "string")
    })
    @ApiVersion(1)
    @GetMapping(value = "repayAmount")
    public ResponseResult repayAmount(@RequestParam BigDecimal repayAmount) {
        try {
            financialLoanRepaymentService.repay(repayAmount, getCustomerId());
//            financialLoanRepaymentService.autoRepay(repayAmount, getCustomerId(),
//                    FinancialLoanRepayment.Type.INSURANCE_REPAYMENT,"BX88888888");
            return new ResponseResult<>(Result.SUCCESS);
        } catch (Exception e) {
            logger.info("repayAmount failed", e);
        }
        return new ResponseResult<>(Result.FAILURE);
    }

}
