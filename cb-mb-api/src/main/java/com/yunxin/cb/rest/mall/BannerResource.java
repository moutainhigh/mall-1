package com.yunxin.cb.rest.mall;

import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@Api(description = "首页banner接口")
@RestController
@RequestMapping(value = "/mall/banner")
@SessionAttributes("customerId")
public class BannerResource extends BaseResource {
    @ApiOperation(value = "首页banner列表")
    @GetMapping(value = "getBanner")
    public ResponseResult getBanner(){
        return new ResponseResult(Result.SUCCESS);
    }
}
