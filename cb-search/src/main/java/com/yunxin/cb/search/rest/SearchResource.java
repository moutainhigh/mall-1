package com.yunxin.cb.search.rest;


import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.vo.ResponseResult;
import com.yunxin.cb.search.vo.meta.Result;
import com.yunxin.cb.search.service.CommodityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@Api(description = "商城商品搜索接口")
@RestController
@RequestMapping(value = "/mall/search")
public class SearchResource extends BaseResource {

    @Resource
    private CommodityService commodityService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "post", dataType = "String")
    })
    @PostMapping(value = "keywordSearch")
    public ResponseResult<Page<Commodity>> keywordSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())

                .build();
        Page<Commodity> pageable =elasticsearchTemplate.queryForPage(searchQuery,Commodity.class);
       return new ResponseResult(pageable);
    }

    @PostMapping(value = "addCommodity")
    public ResponseResult addCommodity(@RequestBody Commodity commodity){
        commodityService.addCommodity(commodity);
        return new ResponseResult(Result.SUCCESS);
    }



}
