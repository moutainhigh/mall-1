package com.yunxin.cb.rest.mall;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @title: 商城商品接口
 * @auther: eleven
 * @date: 2018/7/17 18:29
 */
@Api(description = "商城商品接口")
@RestController
@RequestMapping(value = "/mall/commodity")
public class CommodityResource extends BaseResource {

    @Resource
    private CommodityService commodityService;

    /**
     * @title: 通过商品ID查询商品详情
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:29
     */
    @ApiOperation(value = "通过商品ID查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "getCommdity")
    public ResponseResult getCommdity(@RequestParam int commodityId){
        Commodity commodity=commodityService.selectCommodityDetailById(commodityId);
        return new ResponseResult(commodity);
    }
}
