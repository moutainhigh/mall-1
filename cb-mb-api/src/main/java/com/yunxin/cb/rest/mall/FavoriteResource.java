package com.yunxin.cb.rest.mall;

import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.service.FavoriteService;
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

/**
 * @title:  商城收藏夹接口
 * @auther: eleven
 * @date: 2018/7/17 18:28
 */
@Api(description = "商城收藏夹接口")
@RestController
@RequestMapping(value = "/mall/favorite")
@SessionAttributes("customerId")
public class FavoriteResource extends BaseResource {

    @Resource
    private FavoriteService favoriteService;

    /**
     * @title: 获取用户收藏夹
     * @param: [customerId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:28
     */
    @ApiOperation(value = "获取用户收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "getCustomerFavorite")
    public ResponseResult getCustomerFavorite(Query q,@ModelAttribute("customerId") int customerId){
        Favorite favorite=new Favorite();
        favorite.setCustomerId(customerId);
        q.setQ(favorite);
        PageFinder<Favorite> pageFinder=favoriteService.pageCustomerFavorites(q);
        return new ResponseResult(pageFinder);
    }

    @ApiOperation(value = "商品是否存在收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "findByCustomerAndCommodity")
    public ResponseResult findByCustomerAndCommodity(Favorite favorite,@ModelAttribute("customerId") int customerId) {
        favorite.setCustomerId(customerId);
        favorite=favoriteService.findByCustomerAndCommodity(favorite);
        if(favorite==null){
            return new ResponseResult(Result.SUCCESS);
        }else{
            return new ResponseResult(Result.FAILURE);
        }
    }

    /**
     * @title: 商品添加收藏夹
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品添加收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "addFavorite")
    public ResponseResult addFavorite(Favorite favorite,@ModelAttribute("customerId") int customerId){
        favorite.setCustomerId(customerId);
        favorite=favoriteService.addFavorite(favorite);
        return new ResponseResult(favorite);
    }

    /**
     * @title: 商品移出收藏夹
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品移出收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "favoriteId", value = "收藏夹ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "delFavorite")
    public ResponseResult delFavorite(@ModelAttribute("favoriteId") int favoriteId){
        favoriteService.removeFavorite(favoriteId);
        return new ResponseResult(Result.SUCCESS);
    }
}