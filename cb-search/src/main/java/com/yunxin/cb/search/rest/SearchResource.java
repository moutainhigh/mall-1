package com.yunxin.cb.search.rest;


import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.service.CommodityService;
import com.yunxin.cb.search.vo.CommodityVO;
import com.yunxin.cb.search.vo.ResponseResult;
import com.yunxin.cb.search.vo.SearchVo;
import com.yunxin.cb.search.vo.meta.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "商城商品搜索接口")
@RestController
@RequestMapping(value = "/mall/search")
public class SearchResource extends BaseResource {

    private static final Logger logger = LoggerFactory.getLogger(SearchResource.class);

    @Resource
    private CommodityService commodityService;


    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "post", dataType = "String")
    })
    @PostMapping(value = "keywordSearch")
    public ResponseResult<Page<Commodity>> keywordSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        try {
            Page<Commodity> result = commodityService.keywordSearch(keyword, PageRequest.of(page, size));
            return new ResponseResult(result);
        } catch (Exception e) {
            logger.info("keywordSearch excption",e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @PostMapping(value = "categorySearch")
    public ResponseResult categorySearch(@RequestBody SearchVo searchVo,@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        try {
            Page<Commodity> result = commodityService.categorySearch(searchVo,PageRequest.of(page, size));
            return new ResponseResult(result);
        } catch (Exception e) {
            logger.info("categorySearch excption",e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @PostMapping(value = "addCommodity")
    public ResponseResult addCommodity(@RequestBody CommodityVO commodityVO) {
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityVO, commodity);
        commodityService.addCommodity(commodity);
        return new ResponseResult(Result.SUCCESS);
    }

    @PostMapping(value = "removeCommodity")
    public ResponseResult removeCommodity(@RequestParam(value = "commodityId") int commodityId) {
        commodityService.deleteById(commodityId);
        return new ResponseResult(Result.SUCCESS);
    }


}
