package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(description = "商城退货接口")
@RestController
@RequestMapping(value = "/mall/goodsReturn")
public class GoodsReturnResource extends BaseResource {

    @ApiOperation(value = "退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsReturn", value = "退货申请对象", required = true, paramType = "post", dataType = "Object")
    })
    @PostMapping(value = "saveGoodsReturn")
    public ResponseResult saveGoodsReturn(@RequestBody Object goodsReturn){
        return new ResponseResult(null);
    }

    @ApiOperation(value = "退货查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsReturn", value = "退货查询对象", required = true, paramType = "post", dataType = "Object")
    })
    @PostMapping(value = "getGoodsReturn")
    public ResponseResult getGoodsReturn(@RequestBody Object goodsReturn){
        return new ResponseResult(null);
    }


}
