package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.FinacialWalletService;
import com.yunxin.cb.mall.vo.FinacialWalletVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "钱包接口")
@RestController
@RequestMapping(value = "/{version}/rb/wallet")
public class FiaciaWalletResource {

    @Resource
    private FinacialWalletService finacialWalletService;

    private static final Log log = LogFactory.getLog(FiaciaWalletResource.class);

    @ApiOperation(value = "添加钱包信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "expectedAmount", value = "预期收益（买的保险额的一半）", required = true, paramType = "path", dataType = "string")
    })
    @ApiVersion(1)
    @PostMapping(value = "add")
    public ResponseResult<FinacialWalletVO> add(@RequestBody FinacialWalletVO vo){
        try {
            vo = finacialWalletService.addFinaciaWallet(vo);
            return new ResponseResult(vo);
        } catch (Exception e) {
            log.info("add failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    @ApiOperation(value = "更新钱包信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @PostMapping(value = "update")
    public ResponseResult<FinacialWalletVO> update(@RequestBody FinacialWalletVO vo){
        try {
            vo = finacialWalletService.updateFinacialWallet(vo);
            return new ResponseResult(vo);
        } catch (Exception e) {
            log.info("update failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    @ApiOperation(value = "获取钱包信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "get/{customerId}")
    public ResponseResult<FinacialWalletVO> get(@PathVariable Integer customerId){
        try {
            FinacialWalletVO vo=finacialWalletService.getFinacialWalletByCustomerId(customerId);
            return new ResponseResult(vo);
        } catch (Exception e) {
            log.info("get failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }
}
