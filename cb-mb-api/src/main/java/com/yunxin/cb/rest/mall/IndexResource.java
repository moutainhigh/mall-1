package com.yunxin.cb.rest.mall;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(description = "商城首页")
@RestController
@RequestMapping(value = "/mall/index")
public class IndexResource extends BaseResource {
    @Resource
    private AdvertisementService advertisementService;
    @Resource
    private BrandService brandService;
    @Resource
    private HomeFloorService homeFloorService;
    @Resource
    private FloorBrandService floorBrandService;
    @Resource
    private FloorCategoryService floorCategoryService;
    @Resource
    private FloorCommodityService floorCommodityService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private CommodityService commodityService;


    @ApiOperation(value = "商城首页")
    @GetMapping(value = "getIndex")
    public ResponseResult index(){
        //获取banner
        List<Advertisement> aList = advertisementService.selectAll();
        //获取楼层信息
        List<HomeFloor> hList = homeFloorService.selectByEnabledAll();
        List<Brand> bList = new ArrayList<Brand>();
        List<Category> cList = new ArrayList<Category>();
        List<Commodity> cdyList = new ArrayList<Commodity>();
        Index index = new Index();
        if(hList.size()>0){
            for(int i=0;i<hList.size();i++){
                HomeFloor homeFloor = hList.get(i);
                Integer floorId = homeFloor.getFloorId();
                List<FloorBrand> fbList = floorBrandService.selectByFloorId(floorId);
                //获取品牌
                if(fbList.size()>0){
                    for(int j=0;j<fbList.size();j++){
                        FloorBrand floorBrand = fbList.get(j);
                        Integer brandId = floorBrand.getBrandId();
                        Brand brand = brandService.selectByPrimaryKey(brandId);
                        bList.add(brand);
                    }
                }
                List<FloorCategory> fcyList = floorCategoryService.selectByFloorId(floorId);
                //获取分类
                if(fcyList.size()>0){
                    for(int k=0;k<fcyList.size();k++){
                        FloorCategory floorCategory = fcyList.get(k);
                        Integer categoryId = floorCategory.getCategoryId();
                        Category category = categoryService.selectByPrimaryKey(categoryId);
                        cList.add(category);
                    }
                }
                List<FloorCommodity> fcList = floorCommodityService.selectByFloorId(floorId);
                //获取商品
                if(fcList.size()>0){
                    for(int n=0;n<fcList.size();n++){
                        FloorCommodity floorCommodity = fcList.get(n);
                        Integer commodityId = floorCommodity.getCommodityId();
                        Commodity commodity = commodityService.selectByPrimaryKey(commodityId);
                        cdyList.add(commodity);
                    }
                }
            }
        }
        index.setAdList(aList);
        index.setBrandList(bList);
        index.setCategoryList(cList);
        index.setCommodityList(cdyList);
        return new ResponseResult(index);
    }
}