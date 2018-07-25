package com.yunxin.cb.search.rest;


import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.vo.ResponseResult;
import com.yunxin.cb.search.vo.meta.Result;
import com.yunxin.cb.search.service.CommodityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "商城商品搜索接口")
@RestController
@RequestMapping(value = "/mall/search")
public class SearchResource extends BaseResource {

    @Resource
    private CommodityService commodityService;


    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "post", dataType = "String")
    })
    @PostMapping(value = "keywordSearch")
    public ResponseResult<Page<Commodity>> keywordSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size){
       Page<Commodity> pager =  commodityService.search(keyword, new PageRequest(page, size));
       return new ResponseResult(pager.getContent());
    }

    @PostMapping(value = "addCommodity")
    public ResponseResult addCommodity(@RequestBody Commodity commodity){
        commodityService.addCommodity(commodity);
        return new ResponseResult(Result.SUCCESS);
    }

}
