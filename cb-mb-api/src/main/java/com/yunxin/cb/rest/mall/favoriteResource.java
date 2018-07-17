package com.yunxin.cb.rest.mall;

import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @title:  商城收藏夹接口
 * @auther: eleven
 * @date: 2018/7/17 18:28
 */
@Api(description = "商城收藏夹接口")
@RestController
@RequestMapping(value = "/mall/favorite")
@SessionAttributes("customerId")
public class favoriteResource extends BaseResource {

    /**
     * @title: 获取用户收藏夹
     * @param: [customerId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:28
     */
    @ApiOperation(value = "获取用户收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "getCustomerFavorite")
    public ResponseResult getCustomerFavorite(@RequestParam(value = "customerId") int customerId){
        return new ResponseResult(null);
    }

    /**
     * @title: 商品添加收藏夹
     * @param: [commodityId, customerId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品添加收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "customerId", value = "客户ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "addFavorite")
    public ResponseResult addFavorite(@RequestParam(value = "commodityId") int commodityId,@RequestParam(value = "customerId") int customerId){
        return new ResponseResult(null);
    }

    /**
     * @title: 商品移出收藏夹
     * @param: [commodityId, customerId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品移出收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "customerId", value = "客户ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "delFavorite")
    public ResponseResult delFavorite(@RequestParam(value = "commodityId") int commodityId,@RequestParam(value = "customerId") int customerId){
        return new ResponseResult(null);
    }
}