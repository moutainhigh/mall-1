package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Reimbursement;
import com.yunxin.cb.mall.entity.ReimbursementQuery;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.entity.meta.ReimbursementState;
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
public class ReimbursementQueryResource extends BaseResource {
    @Resource
    private ReimbursementQueryService reimbursementQueryService;
    @ApiOperation(value = "可报账列表 V1")
    @PostMapping(value = "getReimbursement")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")})
    public ResponseResult<PageFinder<ReimbursementVO>> getReimbursement(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        try {
            ReimbursementQuery reimbursementQuery = new ReimbursementQuery();
            Query q = new Query(pageNo, pageSize);
            reimbursementQuery.setCustomerId(getCustomerId());
            reimbursementQuery.setOrderState(OrderState.SUCCESS);
            reimbursementQuery.setReimbursementState(ReimbursementState.NOT_PASS_THROUGH);
            reimbursementQuery.setReimbursement_state(ReimbursementState.CANCEL_REIMBURSEMENT);
            q.setData(reimbursementQuery);
            PageFinder<ReimbursementVO> pageFinder = reimbursementQueryService.pageReimbursementQuery(q);
            return new ResponseResult(pageFinder);
        } catch (Exception e) {
            logger.error("getReimbursement failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
    @ApiOperation(value = "报账 V1")
    @PostMapping(value = "addReimbursement")
    @ApiVersion(1)
    @ApiImplicitParams({
    })
    public ResponseResult<ReimbursementSuccessVO> addReimbursement(@RequestBody List<AddReimbursementRequestVO> list){
        ReimbursementSuccessVO reimbursementSuccessVO;
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
    public ResponseResult<PageFinder<AlreadyReimbursementVO>> getAlreadyReimbursement(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        try {
            Reimbursement reimbursementQuery = new Reimbursement();
            Query q = new Query(pageNo, pageSize);
            reimbursementQuery.setCustomerId(getCustomerId());
            q.setData(reimbursementQuery);
            PageFinder<AlreadyReimbursementVO> pageFinder = reimbursementQueryService.selectAlreadyReimbursementQuery(q);
            return new ResponseResult(pageFinder);
        } catch (Exception e) {
            logger.error("getAlreadyReimbursement failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "已报账详情 V1")
    @GetMapping(value = "getAlreadyReimbursementDetail/{reimbursementId}")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reimbursementId", value = "报账ID", required = true, paramType = "path", dataType = "int")
    })
    public ResponseResult<AlreadyReimbursementVO> getAlreadyReimbursementDetail(@PathVariable(value = "reimbursementId") int reimbursementId){
        try {
            AlreadyReimbursementVO alreadyReimbursementVO = reimbursementQueryService.selectAlreadyReimbursementDetail(reimbursementId,getCustomerId());
            return new ResponseResult(alreadyReimbursementVO);
        } catch (Exception e) {
            logger.error("getAlreadyReimbursementDetail failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "取消报账 V1")
    @GetMapping(value = "cancelReimbursement/{reimbursementId}")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reimbursementId", value = "取消报账", required = true, paramType = "path", dataType = "int")
    })
    public ResponseResult cancelReimbursement(@PathVariable(value = "reimbursementId") int reimbursementId){
        try {
            Reimbursement reimbursement = reimbursementQueryService.selectByReimbursmentIdAndCustomer(reimbursementId,getCustomerId());
            if(reimbursement.getOrderState().ordinal()== ReimbursementState.FINANCE_IN_APPROVAL.ordinal()){
                reimbursement.setOrderState(ReimbursementState.CANCEL_REIMBURSEMENT);
                reimbursementQueryService.cancelReimbursement(reimbursement);
                return new ResponseResult(Result.SUCCESS);
            }else{
                return new ResponseResult(Result.FAILURE,"该报账订单不能取消");
            }
        } catch (Exception e) {
            logger.error("cancelReimbursement failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "报账成功列表 V1")
    @PostMapping(value = "getCompleteReimbursement")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")})
    public ResponseResult<CompleteReimbursementVO> getCompleteReimbursement(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        try {
            Reimbursement reimbursementQuery = new Reimbursement();
            Query q = new Query(pageNo, pageSize);
            reimbursementQuery.setCustomerId(getCustomerId());
            reimbursementQuery.setOrderState(ReimbursementState.ALREADY_TO_ACCOUNT);
            q.setData(reimbursementQuery);
            PageFinder<CompleteReimbursementVO> pageFinder = reimbursementQueryService.selectCompleteReimbursement(q);
            return new ResponseResult(pageFinder);
        } catch (Exception e) {
            logger.error("getCompleteReimbursement failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "报账成功详情 V1")
    @GetMapping(value = "getCompleteReimbursementDetail/{reimbursementId}")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reimbursementId", value = "报账ID", required = true, paramType = "path", dataType = "int")
    })
    public ResponseResult<CompleteReimbursementDetailVO> getCompleteReimbursementDetail(@PathVariable(value = "reimbursementId") int reimbursementId){
        try {
            CompleteReimbursementDetailVO completeReimbursementDetailVO = reimbursementQueryService.getCompleteReimbursementDetail(reimbursementId,getCustomerId());
            return new ResponseResult(completeReimbursementDetailVO);
        } catch (Exception e) {
            logger.error("getCompleteReimbursementDetail failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
}

