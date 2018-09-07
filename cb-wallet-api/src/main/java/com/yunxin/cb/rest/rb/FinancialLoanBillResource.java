package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.mall.service.FinancialLoanBillService;
import com.yunxin.cb.mall.vo.FinancialLoanBillVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "我的负债-负债交易")
@RestController
@RequestMapping(value = "/{version}/rb/loanBill")
public class FinancialLoanBillResource extends BaseResource {

    @Resource
    private FinancialLoanBillService financialLoanBillService;

    @Resource
    private CustomerService customerService;

    @ApiOperation(value = "获取负债交易信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")
    })
    @ApiVersion(1)
    @GetMapping(value = "page")
    public ResponseResult<PageFinder<FinancialLoanBillVO>> get(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        try {
            Customer customer = customerService.getCustomerById(getCustomerId());
            if (customer == null) {
                return new ResponseResult<>(Result.FAILURE, "未获取到用户信息");
            }
            PageFinder<FinancialLoanBill> pageFinder = financialLoanBillService.page(getCustomerId(), pageNo, pageSize);
            PageFinder<FinancialLoanBillVO> page = FinancialLoanBillVO.convertVOPage(pageFinder);
            return new ResponseResult<>(page);
        } catch (Exception e) {
            logger.error("get FinancialLoanBill page failed", e);
        }
        return new ResponseResult<>(Result.FAILURE);
    }

}
