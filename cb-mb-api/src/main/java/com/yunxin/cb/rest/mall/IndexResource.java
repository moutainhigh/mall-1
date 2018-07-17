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

@Api(description = "商城首页")
@RestController
@RequestMapping(value = "/mall/index")
@SessionAttributes("customerId")
public class IndexResource extends BaseResource {
    @ApiOperation(value = "商城首页")
    @GetMapping(value = "getIndex")
    public ResponseResult index(){
        return new ResponseResult(Result.SUCCESS);
    }
}