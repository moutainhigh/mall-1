package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.service.FavoriteService;
import com.yunxin.cb.mall.vo.CommodityVo;
import com.yunxin.cb.mall.vo.FavoriteVo;
import com.yunxin.cb.rest.BaseResource;
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
import java.util.ArrayList;
import java.util.List;

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
    public ResponseResult<FavoriteVo> getCustomerFavorite(Query q){
        PageFinder<FavoriteVo> page=new PageFinder<>();
        Favorite favorite=new Favorite();
        favorite.setCustomerId(getCustomerId());
        q.setData(favorite);
        PageFinder<Favorite> pageFinder=favoriteService.pageCustomerFavorites(q);
        if(pageFinder.getData().size()>0){
            try {
            List<Favorite> list = pageFinder.getData();
            List<FavoriteVo> volist = new ArrayList<>();
            for (Favorite fa:list){
                CommodityVo commodityVo=new CommodityVo();
                BeanUtils.copyProperties(commodityVo,fa.getCommodity());
                FavoriteVo favoriteVo=new FavoriteVo();
                BeanUtils.copyProperties(favoriteVo,fa);
                favoriteVo.setCommodityVo(commodityVo);
                volist.add(favoriteVo);
            }
            page.setData(volist);
            page.setRowCount(pageFinder.getRowCount());
            page.setPageCount(pageFinder.getPageCount());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return new ResponseResult(pageFinder);
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
            return new ResponseResult(false);//收藏夹不存在
        }else{
            return new ResponseResult(true);//收藏夹已存在
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
    public ResponseResult addFavorite(@RequestBody FavoriteVo favoriteVo) {
        try {
            logger.info("input Parameter favoriteVo:" + favoriteVo.toString());
            Favorite favorite = new Favorite();
            BeanUtils.copyProperties(favorite, favoriteVo);
            favorite.setCustomerId(getCustomerId());
            int result = favoriteService.addFavorite(favorite);
            if (result > 0) {
                return new ResponseResult(true);//成功
            } else {
                return new ResponseResult(false);//失败
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new ResponseResult(true);//成功
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
            @ApiImplicitParam(name = "favoriteId", value = "收藏夹ID", required = true, paramType = "path", dataType = "int")})
    @DeleteMapping(value = "delFavorite/{favoriteId}")
    @ApiVersion(1)
    public ResponseResult delFavorite(@PathVariable(value = "favoriteId") int favoriteId){
        int result=favoriteService.removeFavorite(favoriteId);
        if(result>0){
            return new ResponseResult(true);//成功
        }else{
            return new ResponseResult(false);//失败
        }
    }
}