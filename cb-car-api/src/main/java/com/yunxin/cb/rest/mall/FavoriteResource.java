package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.service.FavoriteService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
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
    @IgnoreAuthentication
    public ResponseResult<PageFinder<Favorite>> getCustomerFavorite(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        Query q = new Query(pageNo, pageSize);
        Favorite favorite=new Favorite();
        favorite.setCustomerId(1);
        q.setData(favorite);
        PageFinder<Favorite> pageFinder=favoriteService.getFavoritePagedList(q);
        return new ResponseResult(pageFinder);
    }


}