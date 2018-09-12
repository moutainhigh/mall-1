package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.cb.mall.entity.meta.AdvertisementType;
import com.yunxin.cb.mall.entity.meta.FloorType;
import com.yunxin.cb.mall.vo.*;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api(description = "商城首页接口")
@RestController
@RequestMapping(value = "/{version}/yx_mall/index")
public class IndexResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(IndexResource.class);

    @ApiOperation(value = "云信商城首页 V1")
    @GetMapping(value = "getIndex")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<FloorVO>> index(){

        List<AdvertisementVO> advertisements = new ArrayList<>();
        AdvertisementVO advertisement = new AdvertisementVO(1,"http://test.resource.999shuijingqiu.com/CATEGORY/1533262659906","第一张广告",
                "http://test.resource.999shuijingqiu.com/CATEGORY/1533262659906","http://test.resource.999shuijingqiu.com/xxxx",
                AdvertisementType.PHOTO_AND_TEXT, AdvertisementPlace.HOME);
        advertisements.add(advertisement);

        List<CategoryVO> categories = new ArrayList<>();
        CategoryVO category = new CategoryVO();
        category.setCategoryId(123);
        category.setCategoryName("牛奶分类");
        category.setDescription("这是牛奶分类");
        category.setHighestPrice(new BigDecimal(20000));
        category.setIconPath("http://test.resource.999shuijingqiu.com/CATEGORY/1533262659906");
        category.setLowestPrice(new BigDecimal(1));
        category.setShortName("牛奶");
        categories.add(category);

        List<RecommendationVO> recommendations = new ArrayList<>();
        RecommendationVO recommendation = new RecommendationVO(1,"光明牛奶","http://test.resource.999shuijingqiu.com/CATEGORY/1533262692465",25.00d);
        recommendations.add(recommendation);


        List<BrandChoiceVO> brandChoices = new ArrayList<>();
        BrandChoiceVO brandChoice = new BrandChoiceVO();
        brandChoice.setImageUrl("http://test.resource.999shuijingqiu.com/CATEGORY/1533262692465");
        brandChoice.setCommodityList(recommendations);
        brandChoices.add(brandChoice);

        FloorVO<AdvertisementVO> bannerFloor = new FloorVO();
        bannerFloor.setFloorType(FloorType.BANNER);
        bannerFloor.setList(advertisements);


        FloorVO<CategoryVO> categoryFloor = new FloorVO();
        categoryFloor.setFloorType(FloorType.CATEGORY);
        categoryFloor.setList(categories);

        FloorVO<RecommendationVO> recommendationFloor = new FloorVO();
        recommendationFloor.setFloorType(FloorType.RECOMMENDATION);
        recommendationFloor.setList(recommendations);

        FloorVO<BrandChoiceVO> brandChoiceFloor = new FloorVO();
        brandChoiceFloor.setFloorType(FloorType.BRAND_CHOICE);
        brandChoiceFloor.setList(brandChoices);

        List<FloorVO> result = new ArrayList<>();
        result.add(bannerFloor);
        result.add(categoryFloor);
        result.add(recommendationFloor);
        result.add(brandChoiceFloor);

        return new ResponseResult(result);
    }

}