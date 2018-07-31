package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.service.FavoriteService;
import com.yunxin.cb.mall.vo.FavoriteVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * @title:  商城收藏夹接口
 * @auther: eleven
 * @date: 2018/7/17 18:28
 */
@Api(description = "商城收藏夹接口")
@RestController
@RequestMapping(value = "/{version}/mall/favorite")
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
    @ApiVersion(1)
    public ResponseResult<PageFinder<FavoriteVo>> getCustomerFavorite(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        Query q = new Query(pageNo, pageSize);
        Favorite favorite=new Favorite();
        favorite.setCustomerId(getCustomerId());
        q.setData(favorite);
        PageFinder<Favorite> pageFinder=favoriteService.pageCustomerFavorites(q);
        PageFinder<FavoriteVo> page=FavoriteVo.dOconvertVOPage(pageFinder);
        return new ResponseResult(page);
    }

    @ApiOperation(value = "商品是否存在收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int")})
    @GetMapping(value = "findByCustomerAndCommodity/{commodityId}")
    @ApiVersion(1)
    public ResponseResult findByCustomerAndCommodity(@PathVariable(value = "commodityId") int commodityId) {
        Favorite favorite=new Favorite();
        favorite.setCommodityId(commodityId);
        favorite.setCustomerId(getCustomerId());
        favorite=favoriteService.findByCustomerAndCommodity(favorite);
        if(favorite==null){
            return new ResponseResult(Result.FAILURE);//收藏夹不存在
        }else{
            return new ResponseResult(Result.SUCCESS);//收藏夹已存在
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
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "salePrice", value = "销售价", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "addFavorite")
    @ApiVersion(1)
    public ResponseResult<FavoriteVo> addFavorite(@RequestBody FavoriteVo favoriteVo) {
        try {
            logger.info("input Parameter favoriteVo:" + favoriteVo.toString());
            Favorite favorite = new Favorite();
            BeanUtils.copyProperties(favorite, favoriteVo);
            favorite.setCustomerId(getCustomerId());
            favorite = favoriteService.addFavorite(favorite);
            if (favorite != null) {
                BeanUtils.copyProperties(favoriteVo, favorite);
                return new ResponseResult(favoriteVo);//成功
            } else {
                return new ResponseResult(Result.FAILURE);//失败
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new ResponseResult(Result.SUCCESS);//成功
    }

    /**
     * @title: 商品移出收藏夹
     * @param: [favoriteId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品移出收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "favoriteId", value = "收藏夹ID", required = true, paramType = "path", dataType = "Integer")})
    @DeleteMapping(value = "delFavorite/{favoriteId}")
    @ApiVersion(1)
    public ResponseResult delFavorite(@PathVariable(value = "favoriteId") Integer favoriteId){
        if(LogicUtils.isNull(favoriteId)){
            return new ResponseResult(Result.FAILURE,"参数为空");//失败
        }
        int result=favoriteService.removeFavorite(favoriteId);
        if(result>0){
            return new ResponseResult(Result.SUCCESS);//成功
        }else{
            return new ResponseResult(Result.FAILURE);//失败
        }
    }

    /**
     * @title: 商品移出收藏夹(批量)
     * @param: [favoriteIds]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品移出收藏夹(批量)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "favoriteIds", value = "收藏夹id,根据“,”拆分", required = true, paramType = "path", dataType = "String")})
    @DeleteMapping(value = "delFavorites/{favoriteIds}")
    @ApiVersion(1)
    public ResponseResult delFavorites(@PathVariable(value = "favoriteIds") String favoriteIds){
        if(LogicUtils.isNullOrEmpty(favoriteIds)){
            return new ResponseResult(Result.FAILURE,"参数为空");//失败
        }
        int result=favoriteService.removeFavoriteBatch(favoriteIds.split(","));
        if(result>0){
            return new ResponseResult(Result.SUCCESS);//成功
        }else{
            return new ResponseResult(Result.FAILURE);//失败
        }
    }
}