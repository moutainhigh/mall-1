package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.vo.AfterSaleCommodityVO;
import com.yunxin.cb.mall.vo.AfterSaleServeDetailVO;
import com.yunxin.cb.mall.vo.ApplyAfterSaleRequestVO;
import com.yunxin.cb.mall.vo.FillLogisticInfoRequestVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: hfy
 * @Date: 2018/9/10 15:14
 * @Description: 售后服务接口
 */

@Api(description = "售后服务接口")
@RestController
@RequestMapping(value = "/{version}/yx_mall/afterSale")
public class AfterSaleServeResource extends BaseResource {

    private static Logger logger = LoggerFactory.getLogger(AfterSaleServeResource.class);
    private static  List<AfterSaleServeDetailVO> afterSaleServeDetails;
    private static  AfterSaleServeDetailVO afterSaleServeDetailVO;
    static {
        AfterSaleCommodityVO afterSaleCommodity = new AfterSaleCommodityVO(1,"光明牛奶","http://test.resource.999shuijingqiu.com/CATEGORY/1533262659906",25.0,100);
        List<AfterSaleCommodityVO> AfterSaleCommodities = new ArrayList<>();
        AfterSaleCommodities.add(afterSaleCommodity);
        afterSaleServeDetailVO = new AfterSaleServeDetailVO(1,3213123,"牛奶商家",1,AfterSaleCommodities,1,new Date(),
                new Date(),1,"没有原因","不想说明",null,null,null,null,null);
        afterSaleServeDetails = new ArrayList<>();
        afterSaleServeDetails.add(afterSaleServeDetailVO);
    }




    @ApiOperation(value = "查询售后服务列表 V1")
    @ApiImplicitParams({
    })
    @GetMapping
    @ApiVersion(1)
    public ResponseResult<PageFinder<AfterSaleServeDetailVO>> getAfterSaleServeList(Query query) {
        PageFinder<AfterSaleServeDetailVO> page = new PageFinder<AfterSaleServeDetailVO>();

        page.setData(afterSaleServeDetails);
        page.setRowCount(200);
        page.setPageCount(20);
        page.setHasPrevious(true);
        page.setHasNext(true);
        page.setPageNo(query.getPageNo());
        page.setPageSize(query.getPageSize());
        return new ResponseResult(page);
    }

    @ApiOperation(value = "根据售后服务id查询售后服务详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "afterSaleId", value = "售后服务ID", required = true, paramType = "path", dataType = "Integer")})
    @ApiVersion(1)
    @GetMapping(value = "{afterSaleId}")
    public ResponseResult<AfterSaleServeDetailVO> getAfterSaleServe(@PathVariable(value = "afterSaleId") int afterSaleId) {

        return new ResponseResult(afterSaleServeDetailVO);
    }

    @ApiOperation(value = "添加售后申请")
    @ApiImplicitParams({
    })
    @PostMapping(value = "addAfterSaleServe")
    @ApiVersion(1)
    public ResponseResult addAfterSaleServe(@Validated @RequestBody ApplyAfterSaleRequestVO applyAfterSaleRequestVO) {
        System.out.println(applyAfterSaleRequestVO.toString());

        return new ResponseResult(Result.SUCCESS, "添加成功");
    }

    @ApiOperation(value = "取消售后服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "afterSaleId", value = "售后服务ID", required = true, paramType = "path", dataType = "Integer")})
    @PutMapping(value = "cancelAfterSaleServe/{afterSaleId}")
    @ApiVersion(1)
    public ResponseResult cancelAfterSaleServe(@PathVariable(value = "afterSaleId") int afterSaleId) {
        System.out.println(afterSaleId);
        return new ResponseResult(Result.SUCCESS, "取消成功");
    }

    @ApiOperation(value = "删除售后服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "afterSaleId", value = "售后服务ID", required = true, paramType = "path", dataType = "Integer")})
    @DeleteMapping(value = "{afterSaleId}")
    @ApiVersion(1)
    public ResponseResult deleteAfterSaleServe(@PathVariable(value = "afterSaleId") int afterSaleId) {
        System.out.println(afterSaleId);
        return new ResponseResult(Result.SUCCESS, "删除成功");
    }

    @ApiOperation(value = "填写单号")
    @ApiImplicitParams({
    })
    @PutMapping(value = "fillLogisticInfo/{afterSaleId}")
    @ApiVersion(1)
    public ResponseResult fillLogisticInfo(@PathVariable(value = "afterSaleId") int afterSaleId,
                                           @Validated @RequestBody FillLogisticInfoRequestVO fillLogisticInfoRequestVO) {
        System.out.println(fillLogisticInfoRequestVO.toString());
        BeanUtils.copyProperties(fillLogisticInfoRequestVO,afterSaleServeDetailVO);
        System.out.println("afterSaleId:" + afterSaleId);
        return new ResponseResult(Result.SUCCESS, "填写单号成功");
    }
}
