package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.FinacialExpectBillService;
import com.yunxin.cb.mall.vo.FinacialExpectBillVO;
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
@RequestMapping(value = "/{version}/rb/expectbill")
public class FinacialExpectBillResource {

    @Resource
    private FinacialExpectBillService finacialExpectBillService;

    private static final Log log = LogFactory.getLog(FiaciaWalletResource.class);

    @ApiOperation(value = "获取预期收益交易信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "get/{customerId}")
    public ResponseResult<FinacialExpectBillVO> get(@PathVariable Integer customerId){
        try {
            List<FinacialExpectBillVO> listVo = finacialExpectBillService.getFinacialExpectBillByCustomerId(customerId);
            return new ResponseResult(listVo);
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
    public ResponseResult<FinacialExpectBillVO> add(@RequestBody FinacialExpectBillVO vo){
        try {
            finacialExpectBillService.addFinacialExpectBill(vo);
            return new ResponseResult(vo);
        } catch (Exception e) {
            log.info("get failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }
}
