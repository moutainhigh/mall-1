package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.responsevo.CarBaseDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@Api(description = "参数配置Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarBaseDataController extends BaseResource {

    @ApiOperation(value = "查询车型参数配置 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId", value = "车型ID", required = true, paramType = "path", dataType = "Integer")
    })
    @GetMapping(value = "spec/{carId}")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CarBaseDataVO>> getSpec(@PathVariable(value = "carId") int carId) {
        List<CarBaseDataVO> listVo = new ArrayList<>();
        CarBaseDataVO c = new CarBaseDataVO();
        c.setBaseDataName("厂商");
        c.setBaseDataValue("宝马（进口）");
        listVo.add(c);
        return new ResponseResult(listVo);
    }


}
