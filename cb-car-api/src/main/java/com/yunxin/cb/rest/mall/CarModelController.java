package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.responsevo.CarAgencyVO;
import com.yunxin.cb.vo.responsevo.CarModelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api(description = "汽车车型Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarModelController extends BaseResource {

    @ApiOperation(value = "查询车系下所有车型 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carSystemId", value = "车系ID", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "query", dataType = "Integer")
    })
    @PostMapping(value = "carmodel/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CarModelVO>> getCarList(@RequestParam(value = "carSystemId") int carSystemId,
            @RequestParam(value = "pageNo") int pageNo,@RequestParam(value = "pageSize") int pageSize) {
        List<CarModelVO> listVo = new ArrayList<>();
        CarModelVO car = new CarModelVO();
        car.setId(11);
        car.setModelName("2018款 730Li 领先型 M运动套装");
        car.setGroupName("2.0升 涡轮增压 258马力");
        car.setMonery(new BigDecimal(230000.00));

        CarAgencyVO carAgencyVO = new CarAgencyVO();
        carAgencyVO.setId(2);
        carAgencyVO.setAgencyName("深圳宝泰行宝马店");
        carAgencyVO.setDetailAddress("深圳福田生命保险大厦");
        carAgencyVO.setPositionX(3.33);
        carAgencyVO.setPositionY(4.44);
        car.setCarAgencyVO(carAgencyVO);
        listVo.add(car);
        return new ResponseResult(listVo);
    }


}
