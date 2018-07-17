package com.yunxin.cb.rest.mall;


import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description = "商城商品搜索接口")
@RestController
@RequestMapping(value = "/mall/search")
@SessionAttributes("customerId")
public class SearchResource extends BaseResource {


    @ApiOperation(value = "商品分类搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchVo", value = "搜索对象", required = true, paramType = "post", dataType = "SearchVo")
    })

    @PostMapping(value = "categorySearch")
    public ResponseResult categorySearch(@RequestParam(value = "searchVo") SearchVo searchVo){
       return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "post", dataType = "String")
    })
    @PostMapping(value = "keywordSearch")
    public ResponseResult keywordSearch(@RequestParam(value = "keyword") String keyword){
        return new ResponseResult(Result.SUCCESS);
    }

}
