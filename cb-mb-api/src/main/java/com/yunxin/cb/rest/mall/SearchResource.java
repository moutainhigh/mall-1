package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.restful.RestfulFactory;
import com.yunxin.cb.mall.service.SearchRestService;
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


    @ApiOperation(value = "商品分类搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchVo", value = "搜索对象", required = true, paramType = "post", dataType = "SearchVo")
    })

    @PostMapping(value = "categorySearch")
    @IgnoreAuthentication
    public ResponseResult categorySearch(@RequestBody SearchVo searchVo, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size)throws Exception{
        SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
        Call<ResponseResult> call = restService.categorySearch(searchVo,page,size);
        ResponseResult result = call.execute().body();
        return result;
    }

    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "post", dataType = "String")
    })
    @PostMapping(value = "keywordSearch")
    @IgnoreAuthentication
    public ResponseResult keywordSearch(@RequestParam(value = "keyword") String keyword,@RequestParam(value = "page") int page,@RequestParam(value = "size") int size)throws Exception{
        SearchRestService restService = RestfulFactory.getInstance().getSearchRestService();
        Call<ResponseResult> call = restService.keywordSearch(keyword,page,size);
        ResponseResult result = call.execute().body();
        return result;
    }

}
