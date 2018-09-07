package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(description = "商城首页")
@RestController
@RequestMapping(value = "/{version}/mall/index")
public class IndexController extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @ApiOperation(value = "商城首页 V2")
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
            //获取中部banner
            List<CarAdvertisements> secondList = new ArrayList<CarAdvertisements>();
            List<CarAdvertisementsVO> middleList = secondList.stream()
                    .map(adm -> {
                        CarAdvertisementsVO adVO = new CarAdvertisementsVO();
                        BeanUtils.copyProperties(adVO, adm);
                        return adVO;
                    }).collect(Collectors.toList());
            //获取热门品牌
            List<CarBrand> brandList = new ArrayList<CarBrand>();
            List<CarBrandVO> brandVOList = brandList.stream()
                    .map(brand -> {
                        CarBrandVO brandVO = new CarBrandVO();
                        BeanUtils.copyProperties(brandVO, brand);
                        return brandVO;
                    }).collect(Collectors.toList());
            //获取主打车系
            List<CarSystem> mainCarList = new ArrayList<CarSystem>();
            List<CarSystemVO> mainCarVOList = mainCarList.stream()
                    .map(carSystem -> {
                        CarSystemVO carSystemVO = new CarSystemVO();
                        BeanUtils.copyProperties(carSystemVO, carSystem);
                        return carSystemVO;
                    }).collect(Collectors.toList());
            //品类配置--第四层
            List<CarBaseData> dataList = new ArrayList<CarBaseData>();
            List<CarBaseDataVO> dataVOList = dataList.stream()
                    .map(data -> {
                        CarBaseDataVO dataVO = new CarBaseDataVO();
                        BeanUtils.copyProperties(dataVO, data);
                        return dataVO;
                    }).collect(Collectors.toList());
            //热门车系
            List<CarSystem> hotCarList = new ArrayList<CarSystem>();
            List<CarSystemVO> hotCarVOList = hotCarList.stream()
                    .map(carSystem -> {
                        CarSystemVO carSystemVO = new CarSystemVO();
                        BeanUtils.copyProperties(carSystemVO, carSystem);
                        return carSystemVO;
                    }).collect(Collectors.toList());

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