package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.meta.CarOrderBuyType;
import com.yunxin.cb.mall.service.CarOpenCityService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.responsevo.CarOrderDetailVo;
import com.yunxin.cb.vo.responsevo.CarOrderListVO;
import com.yunxin.cb.vo.responsevo.CarPaySimpleOrderlVo;
import com.yunxin.cb.vo.responsevo.CarPerpOrderDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(description = "汽车商城订单Api")
@Validated
@RestController
@RequestMapping(value = "/{version}/mall")
public class CarOrderController extends BaseResource {
    @Resource
    private CarOpenCityService carOpenCityService;

//    @ApiOperation(value = "查询用户汽车商城订单列表 V1")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNo", value = "当前页数", defaultValue = "1", paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize", value = "每页行数", defaultValue = "20", paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "appOrderState", value = "前端订单状态(1未支付,2待收货,3已完成,4已取消)", paramType = "query", dataType = "Integer")
//    })
//    @GetMapping(value = "order/list")
//    @ApiVersion(1)
//    @IgnoreAuthentication   //TODO  联调时应该去掉
//    public ResponseResult<List<CarOrderListVO>> getOrderList(@RequestParam(defaultValue = "1") @Min(value = 1) Integer pageNo,
//            @RequestParam(defaultValue = "20") @Min(value = 10) @Max(value = 30) Integer pageSize,
//            @Min(value = 1) @Max(value = 4) Integer appOrderState) {
//        List<CarOrderListVO> listVo = new ArrayList<>();
//        CarOrderListVO c = new CarOrderListVO();
//        c.setId(1l);
//        c.setAppOrderState(appOrderState != null ? appOrderState : 1);
//        c.setAppPaymentState(1);
//        c.setBuyType(1);
//        c.setCarAgencyLiftId(1);
//        c.setCarAgencyLiftName("深圳上通汽车4S店");
//        c.setCarColor("白色");
//        c.setCarImageUrl("http://pb9sg55i7.bkt.clouddn.com/HOMEFLOORICO/1532574926181");
//        c.setCarModelId(1);
//        c.setCarName("宝马");
//        c.setOrderCode("201809070001");
//        c.setTotalNum(1);
//        c.setTotalPrice(new BigDecimal(112000));
//        listVo.add(c);
//        return new ResponseResult(listVo);
//    }


    @ApiOperation(value = "查询用户汽车商城订单详情 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode",required = true, value = "订单编号", paramType = "query", dataType = "String")
    })
    @GetMapping(value = "order/detail")
    @ApiVersion(1)
    @IgnoreAuthentication   //TODO  联调时应该去掉
    public ResponseResult<CarOrderDetailVo> getOrderDetail(@RequestParam(value="orderCode") String orderCode
            ,Integer appPaymentState) {
        CarOrderDetailVo c = new CarOrderDetailVo();
        c.setId(1l);
        c.setAppOrderState(1);
        c.setAppPaymentState(appPaymentState != null ? appPaymentState : 1);
        c.setBuyType(1);
        c.setCarAgencyLiftId(1);
        c.setCarAgencyLiftName("深圳上通汽车4S店");
        c.setCarAgencyLiftAddress("深圳市福田区福中一路1001号生命保险大厦(金田路与福中一路交汇处)");
        c.setDistanceLift(3000);
        c.setCarColor("天漠金 2.4L 自动");
        c.setCarImageUrl("http://pb9sg55i7.bkt.clouddn.com/HOMEFLOORICO/1532574926181");
        c.setCarModelId(1);
        c.setCarName("2018 款 240 TURBO 自动两驱舒适版");
        c.setOrderCode("201809070001");
        c.setTotalNum(1);
        c.setTotalPrice(new BigDecimal(15000));
        c.setActualAssessPrice(new BigDecimal(10000));
        c.setBuyCarName("小猫");
        c.setBuyCarPhone("13565985874");

        c.setCarAgencyAssessId(1);
        c.setCarAgencyAssessName("深圳上通汽车4S店");
        c.setCarAgencyAssessAddress("深圳市福田区福中一路1001号生命保险大厦(金田路与福中一路交汇处)");
        c.setCarDiscount("33.74万");
        c.setCountPrice("105.80万*30%+2万");
        c.setCreateTime(new Date());
        c.setDistanceAssess(2000);
        c.setEarnest(new BigDecimal(5000));
        c.setKilometre(12000d);
        c.setOnCardsCity("深圳");
        c.setOnCardsTime("2016年09月");
        c.setOverhaul(0);
        c.setPaymentType(1);
        c.setReferencePrice(new BigDecimal(159000));
        c.setTailMoney(new BigDecimal(50000));
        c.setUsedCarName("2008 款 240 TURBO 自动两驱舒适版");
        return new ResponseResult(c);
    }


    @ApiOperation(value = "取消订单原因 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode",required = true, value = "订单编号", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "reason", value = "原因", paramType = "form", dataType = "String")
    })
    @PostMapping(value = "order/updateOrderState")
    @ApiVersion(1)
    @IgnoreAuthentication   //TODO  联调时应该去掉
    public ResponseResult<Object> updateOrderState(String orderCode
            , String reason) {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "汽车商城预下单详情 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carModelId",required = true, value = "新车ID", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "carUsedId", value = "二手车信息ID", paramType = "query", dataType = "Integer")
    })
    @GetMapping(value = "order/Prepdetail")
    @ApiVersion(1)
    @IgnoreAuthentication   //TODO  联调时应该去掉
    public ResponseResult<CarPerpOrderDetailVo> getPrepOrderDetail(int carModelId,Integer carUsedId) {
        CarPerpOrderDetailVo c = new CarPerpOrderDetailVo();
        c.setBuyType(1);
        c.setCarAgencyLiftId(1);
        c.setCarModelId(1);
        c.setCarAgencyLiftName("深圳上通汽车4S店");
        c.setCarAgencyLiftAddress("深圳市福田区福中一路1001号生命保险大厦(金田路与福中一路交汇处)");
        c.setDistanceLift(3000);
        c.setCarColor("天漠金 2.4L 自动");
        c.setCarImageUrl("http://pb9sg55i7.bkt.clouddn.com/HOMEFLOORICO/1532574926181");
        c.setCarUsedId(1);
        c.setCarName("2018 款 240 TURBO 自动两驱舒适版");
        c.setTotalNum(1);
        c.setTotalPrice(new BigDecimal(15000));
        c.setCarDiscount("33.74万");
        c.setCountPrice("105.80万*30%+2万");
        c.setEarnest(new BigDecimal(5000));
        c.setKilometre(12000d);
        c.setOnCardsCity("深圳");
        c.setOnCardsTime("2016年09月");
        c.setOverhaul(0);
        c.setReferencePrice(new BigDecimal(159000));
        c.setUsedCarName("2008 款 240 TURBO 自动两驱舒适版");
        return new ResponseResult(c);
    }

    @ApiOperation(value = "汽车商城提交订单 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carModelId",required = true, value = "新车ID", paramType = "from", dataType = "Integer"),
            @ApiImplicitParam(name = "carUsedId", value = "二手车信息ID", paramType = "from", dataType = "Integer"),
            @ApiImplicitParam(name = "buyCarName",required = true, value = "购买人名字", paramType = "from", dataType = "String"),
            @ApiImplicitParam(name = "buyCarPhone",required = true, value = "购买人手机", paramType = "from", dataType = "String"),
            @ApiImplicitParam(name = "carNum",required = true, value = "车牌号", paramType = "from", dataType = "String"),
            @ApiImplicitParam(name = "buyType",required = true, value = "购买类型(1全款,2置换)", paramType = "from", dataType = "Integer")
    })

    @PostMapping(value = "order/addOrder")
    @ApiVersion(1)
    @IgnoreAuthentication   //TODO  联调时应该去掉
    public ResponseResult<CarPerpOrderDetailVo> addOrder(
            int carModelId, Integer carUsedId,
        @NotNull String buyCarName, @NotNull String buyCarPhone, @NotNull String carNum,
        @Min(value = 1) @Max(value = 2) int buyType) {
        if(buyType == CarOrderBuyType.EXCHANGE.getValue() && carUsedId == null){
            logger.error(CarOrderBuyType.getCarOrderBuyType().get(buyType)+"类型订单,二手车信息ID不能为null");
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "去支付订单页 V1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode",required = true, value = "订单编号", paramType = "form", dataType = "String")
    })
    @PostMapping(value = "order/toPayOrder")
    @ApiVersion(1)
    @IgnoreAuthentication   //TODO  联调时应该去掉
    public ResponseResult<CarPaySimpleOrderlVo> toPayOrder(@NotNull String orderCode) {
        CarPaySimpleOrderlVo c = new CarPaySimpleOrderlVo();
        c.setEarnest(new BigDecimal(5000));
        c.setOrderCode("45487987");
        c.setRemainingTime(14212325152l);
        c.setTransactionNum("3251148845412855");
        return new ResponseResult(c);
    }
}
