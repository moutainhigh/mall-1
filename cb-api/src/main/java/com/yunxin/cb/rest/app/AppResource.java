package com.yunxin.cb.rest.app;

import com.yunxin.cb.system.MobileOSType;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.yunxin.cb.meta.Result.SUCCESS;

/**
 * app更新接口
 * create by tanggangyi
 */
@Api(description = "app更新接口")
@RestController
@RequestMapping(value = "/app")
public class AppResource {

    @Resource
    private IProfileService profileService;

    @ApiOperation(value = "检查更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "os", value = "系统（android,ios）", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "checkUpdate/{os}")
    public ResponseResult checkUpdate(@PathVariable(value = "os") MobileOSType os) {
        return new ResponseResult(SUCCESS, profileService.getAppCheckUpdate(os));
    }
}
