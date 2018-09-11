package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.responsevo.CarBrandListVO;
import com.yunxin.cb.vo.responsevo.CarBrandVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(description = "汽车品牌Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarBrandController extends BaseResource {

    @ApiOperation(value = "查询所有品牌 V1 (返回集合两个:热门品牌[cityList]和品牌列表[hotCityList]")
    @ApiImplicitParams({
    })
    @GetMapping(value = "carbrand/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CarBrandListVO>> getCarBrand() {
        CarBrandListVO listVO = new CarBrandListVO();

        List<CarBrandVO> brandVOList = new ArrayList<>();
        CarBrandVO brandVO = new CarBrandVO();
        brandVO.setId(1);
        brandVO.setBrandName("宝马");
        brandVO.setBrandEnName("BMW");
        brandVO.setInitial("B");
        brandVO.setPicPath("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=462e8a6d0eb30f242197e451a9fcba26/d62a6059252dd42a159320a7023b5bb5c9eab827.jpg");
        brandVOList.add(brandVO);
        listVO.setAllBrandList(brandVOList);

        List<CarBrandVO> hostList = new ArrayList<>();
        CarBrandVO hotBrand = new CarBrandVO();
        hotBrand.setId(1);
        hotBrand.setBrandName("宝马");
        hotBrand.setBrandEnName("BMW");
        hotBrand.setInitial("B");
        hotBrand.setPicPath("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=462e8a6d0eb30f242197e451a9fcba26/d62a6059252dd42a159320a7023b5bb5c9eab827.jpg");
        hostList.add(hotBrand);
        listVO.setHotBrandList(hostList);
        return new ResponseResult(listVO);
    }


}
