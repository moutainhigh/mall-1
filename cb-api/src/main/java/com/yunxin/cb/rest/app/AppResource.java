package com.yunxin.cb.rest.app;

import com.yunxin.cb.mall.entity.meta.UploadType;
import com.yunxin.cb.vo.AppCheckUpdate;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.yunxin.cb.meta.Result.FAILURE;
import static com.yunxin.cb.meta.Result.SUCCESS;

/**
 * app更新接口
 * create by tanggangyi
 */
@Api(description = "app更新接口")
@RestController
@RequestMapping(value = "/app")
public class AppResource {

    @ApiOperation(value = "检查更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "os", value = "系统（android,ios）", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "checkUpdate/{os}")
    public ResponseResult checkUpdate(@PathVariable(value = "os") String os) {
        return new ResponseResult(SUCCESS, new AppCheckUpdate());
    }
}
