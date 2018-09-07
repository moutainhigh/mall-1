package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.CarOpenCityService;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.responsevo.CarOrderListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api(description = "汽车商城订单Api")
@Validated
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarOrderController extends BaseResource {
    @Resource
    private CarOpenCityService carOpenCityService;

    @ApiOperation(value = "查询用户汽车商城订单 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", defaultValue = "1", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", defaultValue = "20", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "appOrderState", value = "前端订单状态(1未支付,2待收货,3已完成,4已取消)", paramType = "query", dataType = "Integer")
    })
    @GetMapping(value = "order/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<CarOrderListVO>> getOpenCity(@RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "20") @Min(value = 10,message = "条数不能小于10") @Max(value = 30,message = "条数不能超过30") Integer pageSize,
            @Min(value = 1,message = "订单状态不正确") @Max(value = 4,message = "订单状态不正确") Integer appOrderState) {
        List<CarOrderListVO> listVo = new ArrayList<>();
        CarOrderListVO c = new CarOrderListVO();
        c.setId(1l);
        c.setAppOrderState(1);
        c.setAppPaymentState(1);
        c.setBuyType(1);
        c.setCarAgencyLiftId(1);
        c.setCarAgencyLiftName("深圳上通汽车4S店");
        c.setCarColor("白色");
        c.setCarImageUrl("http://pb9sg55i7.bkt.clouddn.com/HOMEFLOORICO/1532574926181");
        c.setCarModelId(1);
        c.setCarName("宝马");
        c.setOrderCode("201809070001");
        c.setTotalNum(1);
        c.setTotalPrice(new BigDecimal(112000));
        listVo.add(c);
        return new ResponseResult(listVo);
    }


}
