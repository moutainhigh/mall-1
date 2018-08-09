package com.yunxin.cb.rest.rb;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.FinacialRepaymentService;
import com.yunxin.cb.mall.vo.FinacialRepaymentVO;
import com.yunxin.cb.mall.vo.FinacialWalletVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "还款信息")
@RestController
@RequestMapping(value = "/{version}/rb/repayment")
public class FinacialRepaymentResource {

    private static final Log log = LogFactory.getLog(FinacialRepaymentResource.class);

    @Resource
    private FinacialRepaymentService finacialRepaymentService;

    @ApiOperation(value = "还款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "repayAmount", value = "还款金", required = true, paramType = "path", dataType = "string")
    })
    @ApiVersion(1)
    @PostMapping(value = "add")
    public ResponseResult<FinacialRepaymentVO> add(@RequestBody FinacialRepaymentVO vo){
        try {
            vo = finacialRepaymentService.add(vo);
            return new ResponseResult(vo);
        } catch (Exception e) {
            log.info("add failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

}
