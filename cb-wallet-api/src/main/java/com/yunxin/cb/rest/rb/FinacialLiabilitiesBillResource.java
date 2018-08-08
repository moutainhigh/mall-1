package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.FinacialExpectBillService;
import com.yunxin.cb.mall.service.FinacialLiabilitiesBillService;
import com.yunxin.cb.mall.vo.FinacialExpectBillVO;
import com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
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
public class FinacialLiabilitiesBillResource {

    @Resource
    private FinacialLiabilitiesBillService finacialLiabilitiesBillService;

    private static final Log log = LogFactory.getLog(FinacialLiabilitiesBillResource.class);

    @ApiOperation(value = "获取负债交易信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "get/{customerId}")
    public ResponseResult<FinacialLiabilitiesBillVO> get(@PathVariable Integer customerId){
        try {
            List<FinacialLiabilitiesBillVO> listVo = finacialLiabilitiesBillService.getFinacialLiabilitiesBillByCustomerId(customerId);
            return new ResponseResult(listVo);
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
