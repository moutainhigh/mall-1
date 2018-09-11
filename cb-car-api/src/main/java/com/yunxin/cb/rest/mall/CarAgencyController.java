package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.responsevo.CarAgencyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(description = "代理商门店Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarAgencyController extends BaseResource {

    @ApiOperation(value = "查询代理商门店 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId", value = "车型ID", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "query", dataType = "Integer")
    })
    @PostMapping(value = "agency/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CarAgencyVO>> getAgencyList(@RequestParam(value = "carId") int carId,
                                                         @RequestParam(value = "pageNo") int pageNo,@RequestParam(value = "pageSize") int pageSize) {
        List<CarAgencyVO> listVo = new ArrayList<>();
        CarAgencyVO carAgencyVO = new CarAgencyVO();
        carAgencyVO.setId(2);
        carAgencyVO.setAgencyName("深圳宝泰行宝马店");
        carAgencyVO.setDetailAddress("深圳福田生命保险大厦");
        carAgencyVO.setPositionX(3.33);
        carAgencyVO.setPositionY(4.44);
        listVo.add(carAgencyVO);
        return new ResponseResult(listVo);
    }


}
