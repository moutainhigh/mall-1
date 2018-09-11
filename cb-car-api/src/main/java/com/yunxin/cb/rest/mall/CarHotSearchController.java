package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.CarOpenCityService;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(description = "热门搜索Api")
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarHotSearchController extends BaseResource {
    @Resource
    private CarOpenCityService carOpenCityService;

    @ApiOperation(value = "热门搜索 V1 (返回数组)")
    @ApiImplicitParams({
    })
    @GetMapping(value = "hotsearch/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<String>> getHotSearchList() {
        List<String> listVo = new ArrayList<>();
        listVo.add("卡罗拉");
        listVo.add("思域");
        listVo.add("迈腾");
        listVo.add("凯美瑞");
        listVo.add("雷克萨斯CT");
        return new ResponseResult(listVo);
    }


}
