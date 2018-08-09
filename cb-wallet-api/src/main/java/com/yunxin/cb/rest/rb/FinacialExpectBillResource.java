package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.entity.FinacialExpectBill;
import com.yunxin.cb.mall.service.FinacialExpectBillService;
import com.yunxin.cb.mall.vo.FavoriteVo;
import com.yunxin.cb.mall.vo.FinacialExpectBillVO;
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
@RequestMapping(value = "/{version}/rb/expectbill")
public class FinacialExpectBillResource extends BaseResource {

    @Resource
    private FinacialExpectBillService finacialExpectBillService;

    private static final Log log = LogFactory.getLog(FiaciaWalletResource.class);

    @ApiOperation(value = "获取预期收益交易信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")
    })
    @ApiVersion(1)
    @PostMapping(value = "get")
    public ResponseResult<PageFinder<FinacialExpectBillVO>> get(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        try {
            Query q = new Query(pageNo, pageSize);
            FinacialExpectBill fbill=new FinacialExpectBill();
            fbill.setCustomerId(getCustomerId());
            q.setData(fbill);
            PageFinder<FinacialExpectBill> pageFinder=finacialExpectBillService.page(q);
            PageFinder<FinacialExpectBillVO> page=FinacialExpectBillVO.dOconvertVOPage(pageFinder);
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
