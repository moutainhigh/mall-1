package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.ReimbursementQuery;
import com.yunxin.cb.mall.service.ReimbursementQueryService;
import com.yunxin.cb.mall.vo.*;
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
import java.util.List;

@Api(description = "报账")
@RestController
@RequestMapping(value = "/{version}/reimbursement")
public class ReimbursementQueryResource extends BaseResource {
    @Resource
    private ReimbursementQueryService reimbursementQueryService;
    @ApiOperation(value = "查询可报账列表 V1")
    @PostMapping(value = "getReimbursement")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")})
    public ResponseResult<PageFinder<ReimbursementVO>> getReimbursement(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        try {
            ReimbursementQuery reimbursementQuery = new ReimbursementQuery();
            Query q = new Query(pageNo, pageSize);
            reimbursementQuery.setCustomerId(306);
            q.setData(reimbursementQuery);
            PageFinder<ReimbursementVO> pageFinder = reimbursementQueryService.pageReimbursementQuery(q);
            return new ResponseResult(pageFinder);
        } catch (Exception e) {
            logger.error("pageListOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
    @ApiOperation(value = "报账 V1")
    @PostMapping(value = "addReimbursement")
    @ApiVersion(1)
    @ApiImplicitParams({
    })
    public ResponseResult<ReimbursementSuccessVO> addReimbursement(@RequestBody List<AddReimbursementRequestVO> list){
        ReimbursementSuccessVO reimbursementSuccessVO = new ReimbursementSuccessVO();
        try {
            reimbursementSuccessVO = reimbursementQueryService.addReimbursement(list);
        } catch (Exception e) {
            logger.error("addReimbursement failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(reimbursementSuccessVO);
    }

    @ApiOperation(value = "已报账列表 V1")
    @PostMapping(value = "getAlreadyReimbursement")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")})
    public ResponseResult<AlreadyReimbursementVO> getAlreadyReimbursement(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        AlreadyReimbursementVO alreadyReimbursementVO = new AlreadyReimbursementVO();
        return new ResponseResult(alreadyReimbursementVO);
    }

    @ApiOperation(value = "已报账详情 V1")
    @GetMapping(value = "getAlreadyReimbursementDetail/{reimbursementId}")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reimbursementId", value = "报账ID", required = true, paramType = "path", dataType = "int")
    })
    public ResponseResult<AlreadyReimbursementVO> getAlreadyReimbursementDetail(@PathVariable(value = "reimbursementId") int reimbursementId){
        AlreadyReimbursementVO alreadyReimbursementVO = new AlreadyReimbursementVO();
        return new ResponseResult(alreadyReimbursementVO);
    }

    @ApiOperation(value = "取消报账 V1")
    @GetMapping(value = "cancelReimbursement/{reimbursementId}")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reimbursementId", value = "取消报账", required = true, paramType = "path", dataType = "int")
    })
    public ResponseResult cancelReimbursement(@PathVariable(value = "reimbursementId") int reimbursementId){
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "报账成功列表 V1")
    @PostMapping(value = "getCompleteReimbursement")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")})
    public ResponseResult<CompleteReimbursementVO> getCompleteReimbursement(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        CompleteReimbursementVO completeReimbursementVO = new CompleteReimbursementVO();
        return new ResponseResult(completeReimbursementVO);
    }

    @ApiOperation(value = "报账成功详情 V1")
    @GetMapping(value = "getCompleteReimbursementDetail/{reimbursementId}")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reimbursementId", value = "报账ID", required = true, paramType = "path", dataType = "int")
    })
    public ResponseResult<CompleteReimbursementDetailVO> getCompleteReimbursementDetail(@PathVariable(value = "reimbursementId") int reimbursementId){
        CompleteReimbursementDetailVO completeReimbursementDetailVO = new CompleteReimbursementDetailVO();
        return new ResponseResult(completeReimbursementDetailVO);
    }
}

