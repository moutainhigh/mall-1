package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(description = "商城订单支付接口")
@RestController
@RequestMapping(value = "/mall/pay")
@SessionAttributes("customerId")
public class PayResource extends BaseResource {

    @ApiOperation(value = "根据订单id选择支付方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "payType", value = "支付类型", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "payOrder/{orderId}-{payType}")
    public ResponseResult payOrder(@PathVariable(value = "orderId") int orderId, @PathVariable(value = "payType") int payType
            , @ModelAttribute("customerId") int customerId){
        return new ResponseResult(null);
    }

}
