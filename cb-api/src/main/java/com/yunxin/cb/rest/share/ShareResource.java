package com.yunxin.cb.rest.share;

import com.yunxin.cb.system.MobileOSType;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.cb.system.vo.ShareInfo;
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
* @Description:    获取分享配置信息
* @Author:         likang
* @CreateDate:     2018/7/28 10:32
*/
@Api(description = "获取分享配置信息")
@RestController
@RequestMapping(value = "/share")
public class ShareResource {

    @Resource
    private IProfileService profileService;

    @ApiOperation(value = "获取分享配置信息")
    @ApiImplicitParams({
    })
    @GetMapping(value = "getShareInfo")
    public ResponseResult<ShareInfo> getShareInfo() {
        return new ResponseResult(SUCCESS, profileService.getShareInfo());
    }
}
