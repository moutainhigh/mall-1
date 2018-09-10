package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.CarOpenCityService;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.responsevo.CarOpenCityListVO;
import com.yunxin.cb.vo.responsevo.CarOpenCityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(description = "开通城市Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarOpenCityController extends BaseResource {
    @Resource
    private CarOpenCityService carOpenCityService;

    @ApiOperation(value = "查询所有开通城市 V1 (返回集合两个:热门城市[cityList]和城市列表[hotCityList]")
    @ApiImplicitParams({
    })
    @GetMapping(value = "opencity/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CarOpenCityVO>> getOpenCity() {
        List<CarOpenCityVO> listVo = new ArrayList<>();
        CarOpenCityVO c = new CarOpenCityVO();
        c.setId(1);
        c.setCityCode("100045");
        c.setCityName("深圳");
        c.setInitial("S");

        List<CarOpenCityVO> hot = new ArrayList<>();
        hot.add(c);
        listVo.add(c);
        CarOpenCityListVO carOpenCityListVO = new CarOpenCityListVO();
        carOpenCityListVO.setCityList(listVo);
        carOpenCityListVO.setHotCityList(hot);
        return new ResponseResult(carOpenCityListVO);
    }


}
