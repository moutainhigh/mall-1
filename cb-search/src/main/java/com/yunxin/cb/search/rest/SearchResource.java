package com.yunxin.cb.search.rest;


import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.service.CommodityService;
import com.yunxin.cb.search.vo.*;
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
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "商城商品搜索接口")
@RestController
@RequestMapping(value = "/mall/search")
public class SearchResource extends BaseResource {

    private static final Logger logger = LoggerFactory.getLogger(SearchResource.class);

    @Resource
    private CommodityService commodityService;


    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "返回行数", required = true, paramType = "post", dataType = "int")
    })
    @PostMapping(value = "keywordSearch/{page}/{size}")
    public ResponseResult<SearchResultVo> keywordSearch(@RequestParam(value = "keyword") String keyword, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        try {
            Page<Commodity> result = commodityService.keywordSearch(keyword, PageRequest.of(page, size));
            PageFinder<CommodityVO> pageFinder = new PageFinder<>();
            Map<String, List<Object>> condition = new HashMap<>();
            SearchResultVo searchResultVo = new SearchResultVo();
            dealResult(page, result, pageFinder, condition, searchResultVo);
            return new ResponseResult(searchResultVo);
        } catch (Exception e) {
            logger.info("keywordSearch excption", e);
            return new ResponseResult(Result.FAILURE);
        }
    }



    @ApiOperation(value = "分类搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "返回行数", required = true, paramType = "post", dataType = "int")
    })
    @PostMapping(value = "categorySearch/{page}/{size}")
    public ResponseResult<SearchResultVo> categorySearch(@RequestBody SearchVo searchVo, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        try {
            Page<Commodity> result = commodityService.categorySearch(searchVo, PageRequest.of(page, size));
            PageFinder<CommodityVO> pageFinder = new PageFinder<>();
            Map<String, List<Object>> condition = new HashMap<>();
            SearchResultVo searchResultVo = new SearchResultVo();
            dealResult(page, result, pageFinder, condition, searchResultVo);
            return new ResponseResult(searchResultVo);
        } catch (Exception e) {
            logger.info("categorySearch excption", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "添加对象到ES")
    @ApiImplicitParams({
    })
    @PostMapping(value = "commodity")
    public ResponseResult addCommodity(@RequestBody CommodityVO commodityVO) {
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityVO, commodity);
        commodityService.addCommodity(commodity);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "删除ES对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int"),
    })
    @DeleteMapping(value = "commodity/{commodityId}")
    public ResponseResult removeCommodity(@PathVariable int commodityId) {
        commodityService.deleteById(commodityId);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "批量添加对象到ES")
    @ApiImplicitParams({
    })
    @PostMapping(value = "bulkIndex")
    public ResponseResult bulkIndex(@RequestBody List<CommodityVO> voList) {
        try {
            List<Commodity> list = new ArrayList();
            for (CommodityVO commodityVO : voList) {
                Commodity commodity = new Commodity();
                BeanUtils.copyProperties(commodityVO, commodity);
                list.add(commodity);
            }
            commodityService.bulkIndex(list);
        } catch (Exception e) {
            logger.info("bulkIndex failled", e);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    private void dealResult( int page, Page<Commodity> result, PageFinder<CommodityVO> pageFinder, Map<String, List<Object>> condition, SearchResultVo searchResultVo) {
        if (result.getTotalElements() > 0) {
            List<Commodity> list = result.getContent();
            List<CommodityVO> voList = new ArrayList<>();
            for (Commodity commodity : list) {
                CommodityVO commodityVO = new CommodityVO();
                BeanUtils.copyProperties(commodity, commodityVO);
                voList.add(commodityVO);
                for (CommoditySpec commoditySpec : commodity.getCommoditySpecs()) {
                    List<Object> values = condition.get(commoditySpec.getSpecName());
                    if (values == null || values.size() == 0) {
                        values = new ArrayList<>();
                        values.add(commoditySpec.getValue());
                    } else {
                        values.add(commoditySpec.getValue());
                    }
                    condition.put(commoditySpec.getSpecName(), values);
                }
            }
            pageFinder.setData(voList);
            pageFinder.setPageCount(result.getTotalPages());
            pageFinder.setRowCount(result.getNumber());
            pageFinder.setPageNo(page);
            searchResultVo.setCondition(condition);
            searchResultVo.setPageFinder(pageFinder);
        }
    }


    @ApiOperation(value = "更新ES对象")
    @PutMapping(value = "commodity")
    public ResponseResult updateCommodity(@RequestBody CommodityVO commodityVO){
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityVO, commodity);
        commodityService.updateCommodity(commodity);
        return new ResponseResult(Result.SUCCESS);
    }

}
