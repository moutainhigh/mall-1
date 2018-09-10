package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.responsevo.CarAgencyVO;
import com.yunxin.cb.vo.responsevo.CarModelVO;
import com.yunxin.cb.vo.responsevo.CarSetFocusVO;
import com.yunxin.cb.vo.responsevo.CarSystemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api(description = "汽车车系Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarSystemController extends BaseResource {

    @ApiOperation(value = "按条件查询车系 V1")
    @ApiImplicitParams({
    })
    @GetMapping(value = "carsystem/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CarSystemVO>> getCarSystemList() {
        List<CarSystemVO> listVo = new ArrayList<CarSystemVO>();
        CarSystemVO c = new CarSystemVO();
        c.setId(1);
        c.setSysName("宝马（进口）7系");
        c.setPicPath("http://mistra.beijing-hyundai.com.cn/pc/images/waiguan0.jpg");
        c.setCategory("中型SUV");
        c.setMixMonery(new BigDecimal(200000));
        c.setMaxMonery(new BigDecimal(400000));

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

        List<CarSetFocusVO> focusList = new ArrayList<>();
        CarSetFocusVO setFocusVO = new CarSetFocusVO();
        setFocusVO.setSetName("全款购车比例");
        setFocusVO.setSetType("全款购车");
        setFocusVO.setSetValue("100%");
        focusList.add(setFocusVO);

        CarSetFocusVO setFocusVO1 = new CarSetFocusVO();
        setFocusVO1.setSetName("置换购车超过返还比例");
        setFocusVO1.setSetType("置换购车");
        setFocusVO1.setSetValue("70%");
        focusList.add(setFocusVO1);

        CarSetFocusVO setFocusVO2 = new CarSetFocusVO();
        setFocusVO2.setSetName("置换购车比例");
        setFocusVO2.setSetType("置换购车");
        setFocusVO2.setSetValue("30%");
        focusList.add(setFocusVO2);

        CarSetFocusVO setFocusVO3 = new CarSetFocusVO();
        setFocusVO3.setSetName( "置换手续费");
        setFocusVO3.setSetType("置换购车");
        setFocusVO3.setSetValue("20000");
        focusList.add(setFocusVO3);

        CarSetFocusVO setFocusVO4 = new CarSetFocusVO();
        setFocusVO4.setSetName( "夸品牌置换");
        setFocusVO4.setSetType("置换购车");
        setFocusVO4.setSetValue("20000");
        focusList.add(setFocusVO4);

        car.setCarSetFocusVO(focusList);
        c.setCarModelVO(car);
        listVo.add(c);
        return new ResponseResult(listVo);
    }


}
