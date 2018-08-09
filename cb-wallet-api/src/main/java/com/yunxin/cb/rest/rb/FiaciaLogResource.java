package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.FiaciaLogService;
import com.yunxin.cb.mall.vo.FiaciaLogDataVO;
import com.yunxin.cb.mall.vo.FiaciaLogVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @title: 账单接口
 * @auther: eleven
 * @date: 2018/8/9 17:09
 */
@Api(description = "账单接口")
@RestController
@RequestMapping(value = "/{version}/rb/fiacialog")
public class FiaciaLogResource extends BaseResource {

    @Resource
    private FiaciaLogService fiaciaLogService;

    @ApiOperation(value = "添加账单信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @PostMapping(value = "add")
    public ResponseResult add(@RequestBody FiaciaLogVO vo){
        try {
            vo.setCustomerId(getCustomerId());
            int result=fiaciaLogService.addFiaciaLog(vo);
            if(result>0){
                return new ResponseResult(Result.SUCCESS);
            }
        } catch (Exception e) {
            logger.info("add failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    @ApiOperation(value = "获取用户账单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "yearMonth", value = "年月", required = true, paramType = "post", dataType = "string")})
    @PostMapping(value = "getCustomerFiaciaLog")
    @ApiVersion(1)
    public ResponseResult<FiaciaLogDataVO> getCustomerFiaciaLog(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize,
                                                               @RequestParam(value = "yearMonth") String yearMonth){
        Query q = new Query(pageNo, pageSize);
        FiaciaLogVO vo=new FiaciaLogVO();
        vo.setCustomerId(getCustomerId());
        vo.setYearMonth(yearMonth);
        q.setData(vo);
        FiaciaLogDataVO fiaciaLogDataVO=fiaciaLogService.pageFiaciaLog(q);
        return new ResponseResult(fiaciaLogDataVO);
    }

}
