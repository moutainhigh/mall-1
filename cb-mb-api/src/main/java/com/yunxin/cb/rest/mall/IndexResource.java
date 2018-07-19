package com.yunxin.cb.rest.mall;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.FloorInfo;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private CategoryService categoryService;

    @ApiOperation(value = "商城首页")
    @GetMapping(value = "getIndex")
    public ResponseResult index(){
        //获取首页banner
        Integer homePlace = 0;
        List<Advertisement> homeList = advertisementService.selectByPlace(homePlace);
        //获取中部banner
        int middlePlace = 1;
        List<Advertisement> middleList = advertisementService.selectByPlace(middlePlace);
        //获取楼层信息
        List<HomeFloor> hList = homeFloorService.selectByEnabledAll();
        Index index = new Index();
        FloorInfo floorInfoTwo = new FloorInfo();
        FloorInfo floorInfoThree = new FloorInfo();
        FloorInfo floorInfoFive = new FloorInfo();
        if(hList.size()>0){
            for(int i=0;i<hList.size();i++){
                List<Brand> brandList = new ArrayList<>();
                List<Category> categoryThreeList = new ArrayList<>();
                List<Category> categoryFiveList = new ArrayList<>();
                HomeFloor homeFloor = hList.get(i);
                Integer floorId = homeFloor.getFloorId();
                //获取品牌
                if(homeFloor.getSortOrder() == 2){
                    List<FloorBrand> fbList = floorBrandService.selectByFloorId(floorId);
                    for(int j=0;j<fbList.size();j++){
                        FloorBrand floorBrand = fbList.get(j);
                        Integer brandId = floorBrand.getBrandId();
                        Brand brand = brandService.selectByPrimaryKey(brandId);
                        brandList.add(brand);
                    }
                    floorInfoTwo.setBrandList(brandList);
                }
                //获取第三层分类
                if(homeFloor.getSortOrder() == 3){
                    List<FloorCategory> fcyList = floorCategoryService.selectByFloorId(floorId);
                    for(int k=0;k<fcyList.size();k++){
                        FloorCategory floorCategory = fcyList.get(k);
                        Integer categoryId = floorCategory.getCategoryId();
                        Category category = categoryService.selectByPrimaryKey(categoryId);
                        categoryThreeList.add(category);
                    }
                    floorInfoThree.setCategoryThreeList(categoryThreeList);
                }
                //获取第五层分类
                if(homeFloor.getSortOrder() == 5){
                    List<FloorCategory> fcy_List = floorCategoryService.selectByFloorId(floorId);
                    for(int h=0;h<fcy_List.size();h++){
                        FloorCategory floor_Category = fcy_List.get(h);
                        Integer categoryId = floor_Category.getCategoryId();
                        Category cate_gory = categoryService.selectByPrimaryKey(categoryId);
                        categoryFiveList.add(cate_gory);
                    }
                    floorInfoFive.setCategoryFiveList(categoryFiveList);
                }
                //List<FloorCommodity> fcList = floorCommodityService.selectByFloorId(floorId);
            }
        }
        index.setHomeList(homeList);
        index.setMilldeList(middleList);
        index.setBrand(floorInfoTwo);
        index.setCategoryThree(floorInfoThree);
        index.setCategoryFive(floorInfoFive);
        return new ResponseResult(index);
    }
}