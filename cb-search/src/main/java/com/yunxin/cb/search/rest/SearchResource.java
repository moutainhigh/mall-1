package com.yunxin.cb.search.rest;


import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.service.CommodityService;
import com.yunxin.cb.search.vo.*;
import com.yunxin.cb.search.vo.meta.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.geo.GeoPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Api(description = "商城商品搜索接口")
@RestController
@RequestMapping(value = "/mall/search")
public class SearchResource extends BaseResource {

    private static final Logger logger = LoggerFactory.getLogger(SearchResource.class);

    @Resource
    private CommodityService commodityService;


    @ApiOperation(value = "关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "返回行数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "lat", value = "地理纬度", paramType = "query", dataType = "Double"),
            @ApiImplicitParam(name = "lon", value = "地理经度", paramType = "query", dataType = "Double")
    })
    @PostMapping(value = "keywordSearch")
    public ResponseResult<SearchResultVo> keywordSearch(@RequestParam String keyword, @RequestParam int page, @RequestParam int size,
                                                        Double lat, Double lon) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            GeoPoint geoPoint = null;
            if (lat != null && lon != null) {
                geoPoint = new GeoPoint(lat, lon);
            }
            Page<Commodity> result = commodityService.keywordSearch(keyword, geoPoint, pageable);
            PageFinder<CommodityVO> pageFinder = new PageFinder<>();
            SearchResultVo searchResultVo = new SearchResultVo();
            dealResult(result, pageFinder, searchResultVo);
            return new ResponseResult(searchResultVo);
        } catch (Exception e) {
            logger.info("keywordSearch excption", e);
            return new ResponseResult(Result.FAILURE);
        }
    }



    @ApiOperation(value = "分类搜索")
    @ApiImplicitParams({
    })
    @PostMapping(value = "categorySearch")
    public ResponseResult<SearchResultVo> categorySearch(@RequestBody SearchVo searchVo) {
        try {
            Page<Commodity> result = commodityService.categorySearch(searchVo, PageRequest.of(searchVo.getPage(), searchVo.getSize()));
            PageFinder<CommodityVO> pageFinder = new PageFinder<>();
            SearchResultVo searchResultVo = new SearchResultVo();
            dealResult(result, pageFinder, searchResultVo);
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
        commodity.setId(String.valueOf(commodityVO.getCommodityId()));
        setLocation(commodity, commodityVO.getSeller());
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
            List<Commodity> list = new ArrayList<>();
            for (CommodityVO commodityVO : voList) {
                Commodity commodity = new Commodity();
                BeanUtils.copyProperties(commodityVO, commodity);
                commodity.setId(String.valueOf(commodityVO.getCommodityId()));
                setLocation(commodity, commodityVO.getSeller());
                list.add(commodity);
            }
            commodityService.bulkIndex(list);
        } catch (Exception e) {
            logger.info("bulkIndex failled", e);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    private void setLocation(Commodity commodity, Seller seller) {
        if (seller == null || StringUtils.isBlank(seller.getPositionX())
                || StringUtils.isBlank(seller.getPositionY())) {
            return;
        }
        commodity.setLocation(new org.springframework.data.elasticsearch.core.geo.GeoPoint(Double.valueOf(seller.getPositionY()),
                Double.valueOf(seller.getPositionX())));
    }

    private void dealResult(Page<Commodity> result, PageFinder<CommodityVO> pageFinder, SearchResultVo searchResultVo) {
        if (result.getTotalElements() > 0) {
            List<Commodity> list = result.getContent();
            List<CommodityVO> voList = new ArrayList<>();
            for (Commodity commodity : list) {
                CommodityVO commodityVO = new CommodityVO();
                BeanUtils.copyProperties(commodity, commodityVO);
                voList.add(commodityVO);
            }
            pageFinder.setData(voList);
            pageFinder.setPageCount(result.getTotalPages());
            pageFinder.setRowCount(result.getTotalElements());
            pageFinder.setPageNo(result.getNumber());
            pageFinder.setPageSize(result.getSize());
            searchResultVo.setPageFinder(pageFinder);
        }
    }


    @ApiOperation(value = "更新ES对象")
    @PutMapping(value = "commodity")
    public ResponseResult updateCommodity(@RequestBody CommodityVO commodityVO){
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityVO, commodity);
        commodity.setId(String.valueOf(commodityVO.getCommodityId()));
        setLocation(commodity, commodityVO.getSeller());
        commodityService.updateCommodity(commodity);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "查询ES对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int"),
    })
    @GetMapping(value = "commodity/{commodityId}")
    public ResponseResult<CommodityVO> selectByCommodityId(@PathVariable int commodityId) {
        Commodity commodity = commodityService.selectByCommodityId(commodityId);
        CommodityVO commodityVO = new CommodityVO();
        BeanUtils.copyProperties(commodity, commodityVO);
        return new ResponseResult(commodityVO);
    }

    /**
     * 查询所有ES中所有商品,查询条件
     */
    @ApiOperation(value = "查询ES对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", paramType = "query", dataType = "String"),
    })
    @GetMapping(value = "commodity")
    public ResponseResult getSearchParam(String keyword){
        try{

            CombinationVO combinationVO = commodityService.getCombination(keyword);
            return new ResponseResult<>(combinationVO);

//            List<Commodity> list = commodityService.findByAll();
//            Map<String, List<Object>> condition = new HashMap<>();
//            CombinationVO combinationVO = new CombinationVO();
//            List<PriceSection> priceSection = new ArrayList<>();
//            Set<Category> categories = new HashSet<>();
//            assemblingResult(list,combinationVO,condition,priceSection,categories);
//            return new ResponseResult(combinationVO);

        }catch (Exception e){
            logger.info("categorySearch excption", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    private void assemblingResult(List<Commodity> list,CombinationVO combinationVO,Map<String, List<Object>> condition,List<PriceSection> priceSection,Set<Category> categories){
        if(list.size()>0){
            for (Commodity commodity : list) {
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
                PriceSection price = commodity.getPriceSection();
                priceSection.add(price);
                for(Category category : commodity.getCategories()){
                    Category cate_gory = new Category();
                    BeanUtils.copyProperties(category, cate_gory);
                    categories.add(cate_gory);
                }
            }
            HashSet h = new HashSet(priceSection);
            priceSection.clear();
            priceSection.addAll(h);
            combinationVO.setCondition(condition);
            combinationVO.setPriceSection(priceSection);
            combinationVO.setCategories(categories);
        }
    }
}
