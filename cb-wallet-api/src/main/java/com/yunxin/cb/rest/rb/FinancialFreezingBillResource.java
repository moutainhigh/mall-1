package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.FinancialFreezingBill;
import com.yunxin.cb.mall.service.FinancialFreezingBillService;
import com.yunxin.cb.mall.vo.FinancialFreezingBillVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "冻结金额交易记录")
@RestController
@RequestMapping(value = "/{version}/rb/expectbill")
public class FinancialFreezingBillResource extends BaseResource {

    @Resource
    private FinancialFreezingBillService financialFreezingBillService;

    private static final Log log = LogFactory.getLog(FiaciaWalletResource.class);

    @ApiOperation(value = "获取预期收益交易信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")
    })
    @ApiVersion(1)
    @PostMapping(value = "get")
    public ResponseResult<PageFinder<FinancialFreezingBillVO>> get(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        try {
            Query q = new Query(pageNo, pageSize);
            FinancialFreezingBill fbill = new FinancialFreezingBill();
            fbill.setCustomerId(getCustomerId());
            q.setData(fbill);
            PageFinder<FinancialFreezingBill> pageFinder = financialFreezingBillService.page(q);
            PageFinder<FinancialFreezingBillVO> page = FinancialFreezingBillVO.dOconvertVOPage(pageFinder);
            return new ResponseResult(page);
        } catch (Exception e) {
            log.info("get failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    @ApiOperation(value = "添加预期收益交易信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "add")
    public ResponseResult<FinancialFreezingBillVO> add(@RequestBody FinancialFreezingBillVO vo){
        try {
            financialFreezingBillService.addFinancialFreezingBill(vo);
            return new ResponseResult(vo);
        } catch (Exception e) {
            log.info("get failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }
}
