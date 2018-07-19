package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(description = "商城订单支付接口")
@RestController
@RequestMapping(value = "/mall/pay")
public class PayResource extends BaseResource {

    @ApiOperation(value = "根据订单id选择支付方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payVo", value = "支付Vo", required = true, paramType = "post", dataType = "Object")
    })
    @PostMapping(value = "payOrder")
    public ResponseResult payOrder(@RequestBody Object  payVo){
        return new ResponseResult(null);
    }

}
