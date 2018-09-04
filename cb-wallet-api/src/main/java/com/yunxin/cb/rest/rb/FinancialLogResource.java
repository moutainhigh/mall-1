package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.FinancialLogService;
import com.yunxin.cb.mall.vo.FinancialLogDataVO;
import com.yunxin.cb.mall.vo.FinancialLogDetailVO;
import com.yunxin.cb.mall.vo.FinancialLogRequestVO;
import com.yunxin.cb.mall.vo.FinancialLogVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @title: 账单接口
 * @auther: eleven
 * @date: 2018/8/9 17:09
 */
@Api(description = "账单接口")
@RestController
@RequestMapping(value = "/{version}/rb/financialLog")
public class FinancialLogResource extends BaseResource {

    @Resource
    private FinancialLogService financialLogService;

    @ApiOperation(value = "获取用户账单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "yearMonth", value = "年月", required = true, paramType = "post", dataType = "string")})
    @PostMapping(value = "getCustomerFinancialLog")
    @ApiVersion(1)
    public ResponseResult<FinancialLogDataVO> getCustomerFinancialLog(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize,
                                                                   @RequestParam(value = "yearMonth") String yearMonth){
        Query q = new Query(pageNo, pageSize);
        FinancialLogRequestVO vo=new FinancialLogRequestVO();
        vo.setCustomerId(getCustomerId());
        vo.setYearMonth(yearMonth);
        q.setData(vo);
        PageFinder<FinancialLogDataVO> page = financialLogService.pageFinancialLog(q);
        return new ResponseResult(page);
    }
    @ApiOperation(value = "获取用户账单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "logId", value = "获取用户账单详情", required = true, paramType = "path", dataType = "int")})
    @GetMapping(value = "getCustomerFinancialLogDetail/{logId}")
    @ApiVersion(1)
    public ResponseResult<FinancialLogDetailVO> getCustomerFinancialLogDetail(@PathVariable(value = "logId") int logId){
        FinancialLogDetailVO financialLogDetailVO = financialLogService.getCustomerFinancialLogDetail(logId,getCustomerId());
        return new ResponseResult(financialLogDetailVO);
    }
}
