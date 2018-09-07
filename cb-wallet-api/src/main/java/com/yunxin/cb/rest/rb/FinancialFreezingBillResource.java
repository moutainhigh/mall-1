package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.FinancialFreezingBill;
import com.yunxin.cb.mall.service.FinancialFreezingBillService;
import com.yunxin.cb.mall.vo.FinancialFreezingBillVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "冻结金额-交易")
@RestController
@RequestMapping(value = "/{version}/rb/freezingBill")
public class FinancialFreezingBillResource extends BaseResource {

    @Resource
    private FinancialFreezingBillService financialFreezingBillService;

    private static final Log log = LogFactory.getLog(FinancialWalletResource.class);

    @ApiOperation(value = "获取冻结金额交易信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")
    })
    @ApiVersion(1)
    @GetMapping(value = "page")
    public ResponseResult<PageFinder<FinancialFreezingBillVO>> get(@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        try {
            PageFinder<FinancialFreezingBill> pageFinder = financialFreezingBillService.page(getCustomerId(), pageNo, pageSize);
            PageFinder<FinancialFreezingBillVO> page = FinancialFreezingBillVO.dOconvertVOPage(pageFinder);
            return new ResponseResult<>(page);
        } catch (Exception e) {
            log.info("get failed", e);
        }
        return new ResponseResult<>(Result.FAILURE);
    }

}
