package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.FinacialExpectBill;
import com.yunxin.cb.mall.entity.FinacialLiabilitiesBill;
import com.yunxin.cb.mall.service.FinacialExpectBillService;
import com.yunxin.cb.mall.service.FinacialLiabilitiesBillService;
import com.yunxin.cb.mall.vo.FinacialExpectBillVO;
import com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO;
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
import java.util.List;

@Api(description = "预期收益交易记录")
@RestController
@RequestMapping(value = "/{version}/rb/liabilities")
public class FinacialLiabilitiesBillResource extends BaseResource {

    @Resource
    private FinacialLiabilitiesBillService finacialLiabilitiesBillService;

    private static final Log log = LogFactory.getLog(FinacialLiabilitiesBillResource.class);

    @ApiOperation(value = "获取负债交易信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")
    })
    @ApiVersion(1)
    @PostMapping(value = "get")
    public ResponseResult<PageFinder<FinacialLiabilitiesBillVO>> get(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        try {
            Query q = new Query(pageNo, pageSize);
            FinacialLiabilitiesBill fbill=new FinacialLiabilitiesBill();
            fbill.setCustomerId(getCustomerId());
            q.setData(fbill);
            PageFinder<FinacialLiabilitiesBill> pageFinder=finacialLiabilitiesBillService.page(q);
            PageFinder<FinacialLiabilitiesBillVO> page=FinacialLiabilitiesBillVO.dOconvertVOPage(pageFinder);
            return new ResponseResult(page);
        } catch (Exception e) {
            log.info("get failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    @ApiOperation(value = "添加负债交易信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "add")
    public ResponseResult<FinacialLiabilitiesBillVO> add(@RequestBody FinacialLiabilitiesBillVO vo){
        try {
            finacialLiabilitiesBillService.addFinacialLiabilitiesBill(vo);
            return new ResponseResult(vo);
        } catch (Exception e) {
            log.info("get failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }
}
