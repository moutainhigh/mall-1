package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.requestvo.CarUsedCarReqVO;
import com.yunxin.cb.vo.responsevo.CarSystemVO;
import com.yunxin.cb.vo.responsevo.CarUsedCarVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(description = "二手车管理Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarUsedCarController extends BaseResource {

    @ApiOperation(value = "查询当前用户的评估记录")
    @ApiImplicitParams({
    })
    @GetMapping(value = "usedcar/list")
    @ApiVersion(1)
    public ResponseResult<List<CarUsedCarVO>> getUsedCar() {
        List<CarUsedCarVO> listVo = new ArrayList<>();
        CarUsedCarVO c = new CarUsedCarVO();
        c.setId(1);
        c.setReModelName("迈腾 2016款 1.8TSI 智享领先型");
        c.setOnCardsTime(new Date());
        c.setKilometre(2000.00);
        c.setOnCardsCity("东莞");
        c.setOverhaul(0);
        c.setReferencePrice(new BigDecimal(16000));
        c.setActualAssessPrice(new BigDecimal(13000));
        listVo.add(c);
        return new ResponseResult(listVo);
    }

    @ApiOperation(value = "新增评估记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyMobile", value = "手机号", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "uname", value = "试驾人", required = true, paramType = "post", dataType = "String")
    })
    @PostMapping(value = "usercar/add")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult addUsedCar(@RequestBody CarUsedCarReqVO usedCarVO) {
        CarUsedCarVO c = new CarUsedCarVO();
        c.setId(1);
        c.setReModelName("迈腾 2016款 1.8TSI 智享领先型");
        c.setOnCardsTime(new Date());
        c.setKilometre(2000.00);
        c.setOnCardsCity("东莞");
        c.setOverhaul(0);
        c.setReferencePrice(new BigDecimal(16000));
        c.setReferenceRange("15.03-16.32");

        List<CarSystemVO> sysList = new ArrayList<CarSystemVO>();
        CarSystemVO sysVO = new CarSystemVO();
        sysVO.setId(1);
        sysVO.setSysName("宝马（进口）7系");
        sysVO.setPicPath("http://mistra.beijing-hyundai.com.cn/pc/images/waiguan0.jpg");
        sysVO.setCategory("中型SUV");
        sysVO.setMixMonery(new BigDecimal(200000));
        sysVO.setMaxMonery(new BigDecimal(400000));
        c.setRecommandList(sysList);
        return new ResponseResult(c);
    }
}
