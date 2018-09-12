package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.requestvo.CarTestDriveReqVO;
import com.yunxin.cb.vo.responsevo.CarOpenCityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(description = "试驾信息Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarTestDriveController extends BaseResource {

    @ApiOperation(value = "试驾信息 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "driveName", value = "试驾人", required = true, paramType = "post", dataType = "String")
    })
    @PostMapping(value = "drive/add")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CarOpenCityVO>> addDrive(@RequestBody CarTestDriveReqVO driveVO) {

        return new ResponseResult(Result.SUCCESS);
    }


}
