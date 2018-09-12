package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单确认购买接口
 *
 * @param
 * @author gws
 * @date 2018/9/10 09:41
 * @return
 */
@Api(description = "订单确认购买接口")
@Validated
@RestController
@RequestMapping(value = "{version}/mall")
public class BuyResource extends BaseResource {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "立即购买")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "货品id", required = true, paramType = "form", dataType = "Integer"),
            @ApiImplicitParam(name = "buyNum", value = "购买数量", required = false, defaultValue = "1", paramType = "form", dataType = "Integer")})
    @ApiVersion(1)
    @PostMapping(value = "tempOrder/buy")
    public ResponseResult<TempOrderVO> buy(
            @NotNull(message = "货品id不能为空")
            @RequestParam(value = "productId") Integer productId,
            @RequestParam(value = "buyNum", defaultValue = "1") Integer buyNum) {
        TempOrderVO tempOrderVO = new TempOrderVO();
        tempOrderVO.setFeeToTal(new BigDecimal("600"));
        TempOrderDeliveryAddressVO deliveryAddressVO = new TempOrderDeliveryAddressVO();
        deliveryAddressVO.setAddressId(1);
        deliveryAddressVO.setProvince("110000");
        deliveryAddressVO.setCity("110100");
        deliveryAddressVO.setDistrict("110109");
        deliveryAddressVO.setConsigneeAddress("深圳市福田区金田路富德生命保险大厦507室");
        deliveryAddressVO.setConsigneeName("张三");
        deliveryAddressVO.setConsigneeMobile("13598969958");
        tempOrderVO.setDeliveryAddressVO(deliveryAddressVO);
        List<TempOrderSellerVO> tempOrderSellerListVO = new ArrayList<TempOrderSellerVO>();
        TempOrderSellerVO tempOrderSellerVO = new TempOrderSellerVO();
        tempOrderSellerVO.setDeliveryFeeTotal(new BigDecimal("10"));
        tempOrderSellerVO.setItemNum(2);
        tempOrderSellerVO.setPrice(new BigDecimal("600"));
        tempOrderSellerVO.setSellerId(1);
        tempOrderSellerVO.setSellerName("商家1");
        List<TempOrderItemVO> tempOrderItemListVO = new ArrayList<TempOrderItemVO>();
        TempOrderItemVO TempOrderItemVO = new TempOrderItemVO();
        TempOrderItemVO.setBuyNum(2);
        TempOrderItemVO.setCommodityId(1);
        TempOrderItemVO.setCommodityName("商品1");
        TempOrderItemVO.setCommodityTitle("商品1标题");
        TempOrderItemVO.setDefaultPicPath("http://test.resource.999shuijingqiu.com/COMMODITY/1534574506619");
        TempOrderItemVO.setProductId(1);
        TempOrderItemVO.setProductName("颜色：黄色&档位：手动挡");
        TempOrderItemVO.setProductNo("111111111");
        TempOrderItemVO.setSalePrice(300f);
        tempOrderItemListVO.add(TempOrderItemVO);
        tempOrderSellerVO.setTempOrderItemListVO(tempOrderItemListVO);
        tempOrderSellerListVO.add(tempOrderSellerVO);
        tempOrderVO.setTempOrderSellerListVO(tempOrderSellerListVO);
        return new ResponseResult(tempOrderVO);
    }

//    @ApiOperation(value = "购物车去结算")
//    @ApiImplicitParams({
//    })
//    @ApiVersion(1)
//    @PostMapping(value = "tempOrder/settlement")
//    public ResponseResult<TempOrderVO> settlement(
//            @Validated @RequestBody SettlementVO settlementVO) {
//        //清除购物车购买的商品
//        return new ResponseResult(new TempOrderVO());
//    }

    @ApiOperation(value = "购物车去结算")
    @ApiImplicitParams({
            })
    @ApiVersion(1)
    @PostMapping(value = "tempOrder/settlement")
    public ResponseResult<TempOrderVO> settlement(
            @RequestBody List<UpdateShoppingCartVO> shoppingCarts) {
        //清除购物车购买的商品
        TempOrderVO tempOrderVO = new TempOrderVO();
        tempOrderVO.setFeeToTal(new BigDecimal("600"));
        TempOrderDeliveryAddressVO deliveryAddressVO = new TempOrderDeliveryAddressVO();
        deliveryAddressVO.setAddressId(1);
        deliveryAddressVO.setProvince("110000");
        deliveryAddressVO.setCity("110100");
        deliveryAddressVO.setDistrict("110109");
        deliveryAddressVO.setConsigneeAddress("深圳市福田区金田路富德生命保险大厦507室");
        deliveryAddressVO.setConsigneeName("张三");
        deliveryAddressVO.setConsigneeMobile("13598969958");
        tempOrderVO.setDeliveryAddressVO(deliveryAddressVO);
        List<TempOrderSellerVO> tempOrderSellerListVO = new ArrayList<TempOrderSellerVO>();
        TempOrderSellerVO tempOrderSellerVO = new TempOrderSellerVO();
        tempOrderSellerVO.setDeliveryFeeTotal(new BigDecimal("10"));
        tempOrderSellerVO.setItemNum(2);
        tempOrderSellerVO.setPrice(new BigDecimal("600"));
        tempOrderSellerVO.setSellerId(1);
        tempOrderSellerVO.setSellerName("商家1");
        List<TempOrderItemVO> tempOrderItemListVO = new ArrayList<TempOrderItemVO>();
        TempOrderItemVO TempOrderItemVO = new TempOrderItemVO();
        TempOrderItemVO.setBuyNum(2);
        TempOrderItemVO.setCommodityId(1);
        TempOrderItemVO.setCommodityName("商品1");
        TempOrderItemVO.setCommodityTitle("商品1标题");
        TempOrderItemVO.setDefaultPicPath("http://test.resource.999shuijingqiu.com/COMMODITY/1534574506619");
        TempOrderItemVO.setProductId(1);
        TempOrderItemVO.setProductName("颜色：黄色&档位：手动挡");
        TempOrderItemVO.setProductNo("111111111");
        TempOrderItemVO.setSalePrice(300f);
        tempOrderItemListVO.add(TempOrderItemVO);
        tempOrderSellerVO.setTempOrderItemListVO(tempOrderItemListVO);
        tempOrderSellerListVO.add(tempOrderSellerVO);
        tempOrderVO.setTempOrderSellerListVO(tempOrderSellerListVO);
        return new ResponseResult(tempOrderVO);
    }

    @ApiOperation(value = "订单确认")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @PostMapping(value = "tempOrder/submitOrder")
    public ResponseResult addOrder(@Validated @RequestBody SettlementVO settlementVO){
        return new ResponseResult(Result.SUCCESS);
    }
}
