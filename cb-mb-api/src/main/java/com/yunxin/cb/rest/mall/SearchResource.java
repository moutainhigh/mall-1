package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.restful.RestfulFactory;
import com.yunxin.cb.mall.restful.meta.Result;
import com.yunxin.cb.mall.service.SearchRestService;
import com.yunxin.cb.mall.vo.SearchResultVo;
import com.yunxin.cb.mall.vo.SearchVo;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;

@Api(description = "商城商品搜索接口")
@RestController
@RequestMapping(value = "/mall/search")
public class SearchResource extends BaseResource {

    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "返回行数", required = true, paramType = "post", dataType = "int")
    })
    @PostMapping(value = "keywordSearch/{page}/{size}")
    @IgnoreAuthentication
    public ResponseResult<SearchResultVo> keywordSearch(@RequestParam(value = "keyword") String keyword, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        try {
            SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
            Call<ResponseResult<SearchResultVo>> call = restService.keywordSearch(keyword, page, size);
            ResponseResult result = call.execute().body();
            return result;
        } catch (Exception e) {
            logger.info("categorySearch failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "分类搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "返回行数", required = true, paramType = "post", dataType = "int")
    })
    @PostMapping(value = "categorySearch/{page}/{size}")
    @IgnoreAuthentication
    public ResponseResult<SearchResultVo> categorySearch(@RequestBody SearchVo searchVo, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        try {
            SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
            Call<ResponseResult<SearchResultVo>> call = restService.categorySearch(searchVo, page, size);
            ResponseResult result = call.execute().body();
            return result;
        } catch (Exception e) {
            logger.info("categorySearch failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }



}
