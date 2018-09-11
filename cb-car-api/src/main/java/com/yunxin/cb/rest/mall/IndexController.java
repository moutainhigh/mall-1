package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.cb.mall.entity.meta.AdvertisementType;
import com.yunxin.cb.vo.responsevo.*;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(description = "商城首页")
@RestController
@RequestMapping(value = "/{version}/mall/index")
public class IndexController extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @ApiOperation(value = "商城首页 V1")
    @GetMapping(value = "getIndex")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<IndexVO> index(){
        try{
            //获取首页banner
            List<CarAdvertisements> firstList = new ArrayList<CarAdvertisements>();
            List<CarAdvertisementsVO> homeList = firstList.stream()
                    .map(adm -> {
                        CarAdvertisementsVO adVO = new CarAdvertisementsVO();
                        BeanUtils.copyProperties(adVO, adm);
                        return adVO;
                    }).collect(Collectors.toList());
            CarAdvertisementsVO ad = new CarAdvertisementsVO();
            ad.setPicPath("http://mistra.beijing-hyundai.com.cn/pc/images/waiguan0.jpg");
            ad.setJumpType(1);
            ad.setAdverType(AdvertisementType.PTHOTO_AND_TEXT);
            ad.setPosition(AdvertisementPlace.HOME);
            ad.setSysId(1);

            homeList.add(ad);
            //获取中部banner
            List<CarAdvertisements> secondList = new ArrayList<CarAdvertisements>();
            List<CarAdvertisementsVO> middleList = secondList.stream()
                    .map(adm -> {
                        CarAdvertisementsVO adVO = new CarAdvertisementsVO();
                        BeanUtils.copyProperties(adVO, adm);
                        return adVO;
                    }).collect(Collectors.toList());
            CarAdvertisementsVO ad1 = new CarAdvertisementsVO();
            ad1.setPicPath("http://mistra.beijing-hyundai.com.cn/pc/images/waiguan0.jpg");
            ad1.setJumpType(1);
            ad1.setAdverType(AdvertisementType.PTHOTO_AND_TEXT);
            ad1.setPosition(AdvertisementPlace.HOME);
            ad1.setSysId(1);
            middleList.add(ad1);
            //获取热门品牌
            List<CarBrand> brandList = new ArrayList<CarBrand>();
            List<CarBrandVO> brandVOList = brandList.stream()
                    .map(brand -> {
                        CarBrandVO brandVO = new CarBrandVO();
                        BeanUtils.copyProperties(brandVO, brand);
                        return brandVO;
                    }).collect(Collectors.toList());
            CarBrandVO hotBrand = new CarBrandVO();
            hotBrand.setId(1);
            hotBrand.setBrandName("宝马");
            hotBrand.setBrandEnName("BMW");
            hotBrand.setInitial("B");
            hotBrand.setPicPath("http://mistra.beijing-hyundai.com.cn/pc/images/waiguan0.jpg");
            brandVOList.add(hotBrand);

            //获取主打车系
            List<CarSystem> mainCarList = new ArrayList<CarSystem>();
            List<CarSystemVO> mainCarVOList = mainCarList.stream()
                    .map(carSystem -> {
                        CarSystemVO carSystemVO = new CarSystemVO();
                        BeanUtils.copyProperties(carSystemVO, carSystem);
                        return carSystemVO;
                    }).collect(Collectors.toList());
            CarSystemVO c = new CarSystemVO();
            c.setId(1);
            c.setSysName("宝马（进口）7系");
            c.setPicPath("http://mistra.beijing-hyundai.com.cn/pc/images/waiguan0.jpg");
            c.setCategory("中型SUV");
            c.setMainCar(1);
            c.setMixMonery(new BigDecimal(200000));
            c.setMaxMonery(new BigDecimal(400000));
            mainCarVOList.add(c);
            //品类配置--第四层
            List<CarBaseData> dataList = new ArrayList<CarBaseData>();
            List<CarBaseDataVO> dataVOList = dataList.stream()
                    .map(data -> {
                        CarBaseDataVO dataVO = new CarBaseDataVO();
                        BeanUtils.copyProperties(dataVO, data);
                        return dataVO;
                    }).collect(Collectors.toList());
            CarBaseDataVO bdVO = new CarBaseDataVO();
            bdVO.setId(1);
            bdVO.setBaseDataName("新能源");
            bdVO.setBaseDataCode("code0001");
            bdVO.setRemark("节能环保实惠");
            bdVO.setPicPath("http://mistra.beijing-hyundai.com.cn/pc/images/waiguan0.jpg");
            dataVOList.add(bdVO);

            //热门车系
            List<CarSystem> hotCarList = new ArrayList<CarSystem>();
            List<CarSystemVO> hotCarVOList = hotCarList.stream()
                    .map(carSystem -> {
                        CarSystemVO carSystemVO = new CarSystemVO();
                        BeanUtils.copyProperties(carSystemVO, carSystem);
                        return carSystemVO;
                    }).collect(Collectors.toList());

            CarSystemVO c1 = new CarSystemVO();
            c1.setId(1);
            c1.setSysName("宝马（进口）7系");
            c1.setPicPath("http://mistra.beijing-hyundai.com.cn/pc/images/waiguan0.jpg");
            c1.setCategory("中型SUV");
            c1.setIsHot(1);
            c1.setMixMonery(new BigDecimal(200000));
            c1.setMaxMonery(new BigDecimal(400000));
            hotCarVOList.add(c1);

            IndexVO indexVO = new IndexVO();
            indexVO.setHomeList(homeList);
            indexVO.setMilldeList(middleList);
            indexVO.setBrandList(brandVOList);
            indexVO.setMainCarList(mainCarVOList);
            indexVO.setCategoryList(dataVOList);
            indexVO.setHotCarList(hotCarVOList);
            return new ResponseResult(indexVO);
        }catch (Exception e){
            logger.info("indexResource failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
}