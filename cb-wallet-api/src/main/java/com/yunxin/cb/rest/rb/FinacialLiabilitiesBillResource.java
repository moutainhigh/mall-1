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
import com.yunxin.cb.util.page.Query;
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

@Api(description = "负债交易记录")
@RestController
@RequestMapping(value = "/{version}/rb/liabilities")
public class FinacialLiabilitiesBillResource extends BaseResource {

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
    @GetMapping(value = "get")
    public ResponseResult<PageFinder<FinancialLoanBillVO>> get(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        try {
            Customer customer = customerService.getCustomerById(getCustomerId());
            if (customer == null) {
                return new ResponseResult<>(Result.FAILURE, "未获取到用户信息");
            }
            Query q = new Query(pageNo, pageSize);
            FinancialLoanBill fbill=new FinancialLoanBill();
            fbill.setCustomerId(customer.getCustomerId());
            q.setData(fbill);
            PageFinder<FinancialLoanBill> pageFinder= financialLoanBillService.page(q);
            PageFinder<FinancialLoanBillVO> page=FinancialLoanBillVO.dOconvertVOPage(pageFinder);
            return new ResponseResult(page);
        } catch (Exception e) {
            logger.error("get failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

//    @ApiOperation(value = "添加负债交易信息")
//    @ApiImplicitParams({
//    })
//    @ApiVersion(1)
//    @GetMapping(value = "add")
//    public ResponseResult<FinancialLoanBillVO> add(@RequestBody FinancialLoanBillVO vo){
//        try {
//            financialLoanBillService.addFinacialLiabilitiesBill(vo);
//            return new ResponseResult(vo);
//        } catch (Exception e) {
//            log.info("get failed", e);
//        }
//        return new ResponseResult(Result.FAILURE);
//    }
}
