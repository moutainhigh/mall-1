package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.vo.AdvertisementVO;
import com.yunxin.cb.mall.vo.BrandVO;
import com.yunxin.cb.mall.vo.CategoryVO;
import com.yunxin.cb.mall.vo.IndexVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(description = "商城首页")
@RestController
@RequestMapping(value = "/{version}/mall/index")
public class IndexResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(IndexResource.class);
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

    @ApiOperation(value = "商城首页 V1")
    @GetMapping(value = "getIndex")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult index(){
        try{
            //获取首页banner
            List<Advertisement> firstList = advertisementService.selectByPlace(AdvertisementPlace.HOME,true);
            List<AdvertisementVO> homeList = new ArrayList<>();
            for(Advertisement adm : firstList){
                AdvertisementVO adVO = new AdvertisementVO();
                BeanUtils.copyProperties(adVO, adm);
                homeList.add(adVO);
            }
            //获取中部banner
            List<Advertisement> secondList = advertisementService.selectByPlace(AdvertisementPlace.MIDDLE,true);
            List<AdvertisementVO> middleList = new ArrayList<>();
            for(Advertisement advment : secondList){
                AdvertisementVO adVO = new AdvertisementVO();
                BeanUtils.copyProperties(adVO, advment);
                middleList.add(adVO);
            }
            //获取楼层信息
            List<HomeFloor> hList = homeFloorService.selectByEnabledAll();
            IndexVO indexVO = new IndexVO();
            List<BrandVO> brandList = new ArrayList<>();
            List<CategoryVO> categoryThreeList = new ArrayList<>();
            List<CategoryVO> categoryFiveList = new ArrayList<>();
            if(hList.size()>0){
                for(HomeFloor homeFloor :hList){
                    Integer floorId = homeFloor.getFloorId();
                    //获取品牌
                    if(homeFloor.getSortOrder() == 2){
                        List<FloorBrand> fbList = floorBrandService.selectByFloorId(floorId);
                        for(FloorBrand floorBrand :fbList){
                            Integer brandId = floorBrand.getBrandId();
                            Brand brand = brandService.selectByPrimaryKey(brandId);
                            BrandVO bVO = new BrandVO();
                            BeanUtils.copyProperties(bVO, brand);
                            brandList.add(bVO);
                        }
                    }
                    //获取第三层分类
                    if(homeFloor.getSortOrder() == 3){
                        List<FloorCategory> fcyList = floorCategoryService.selectByFloorId(floorId);
                        for(FloorCategory floorCategory : fcyList){
                            Integer categoryId = floorCategory.getCategoryId();
                            Category category = categoryService.selectByPrimaryKey(categoryId);
                            CategoryVO cVO = new CategoryVO();
                            BeanUtils.copyProperties(cVO, category);
                            categoryThreeList.add(cVO);
                        }
                    }
                    //获取第五层分类
                    if(homeFloor.getSortOrder() == 5){
                        List<FloorCategory> fcy_List = floorCategoryService.selectByFloorId(floorId);
                        for(FloorCategory floor_Category : fcy_List){
                            Integer categoryId = floor_Category.getCategoryId();
                            Category cate_gory = categoryService.selectByPrimaryKey(categoryId);
                            CategoryVO cVO = new CategoryVO();
                            BeanUtils.copyProperties(cVO, cate_gory);
                            categoryFiveList.add(cVO);
                        }
                    }
                }
            }
            indexVO.setBrandList(brandList);
            indexVO.setHomeList(homeList);
            indexVO.setMilldeList(middleList);
            indexVO.setCategoryThreeList(categoryThreeList);
            indexVO.setCategoryFiveList(categoryFiveList);
            return new ResponseResult(indexVO);
        }catch (Exception e){
            logger.info("indexResource failed", e);
            return new ResponseResult(Result.FAILURE);
        }

    }
}