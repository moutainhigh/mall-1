package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车接口
 *
 * @param
 * @author gws
 * @date 2018/9/10 09:41
 * @return
 */
@Api(description = "购物车接口")
@Validated
@RestController
@RequestMapping(value = "{version}/mall")
public class ShoppingCartResource extends BaseResource {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "获取我的购物车")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "shoppingCart")
    public ResponseResult<MyShoppingCartVO> getMyShoppingCart() {
        MyShoppingCartVO myShoppingCartVO = new MyShoppingCartVO();
        myShoppingCartVO.setTotalPrice(new BigDecimal("1000"));
        myShoppingCartVO.setCommodityNum(4);
        List<MyShoppingCartSellerVO> sellerListVO = new ArrayList<MyShoppingCartSellerVO>();
        MyShoppingCartSellerVO sellerVO1 = new MyShoppingCartSellerVO();
        sellerVO1.setSellerId(1);
        sellerVO1.setSellerName("商家1");
        List<MyShoppingCartProductVO> seller1ProductListVO = new ArrayList<MyShoppingCartProductVO>();
        MyShoppingCartProductVO seller1ProductVO1 = new MyShoppingCartProductVO();
        seller1ProductVO1.setCartId(1);
        seller1ProductVO1.setCommodityId(1);
        seller1ProductVO1.setCommodityName("商品1");
        seller1ProductVO1.setDefaultPicPath("http://test.resource.999shuijingqiu.com/COMMODITY/1534574506619");
        seller1ProductVO1.setProductId(1);
        seller1ProductVO1.setProductNum(1);
        seller1ProductVO1.setSalePrice(new BigDecimal("300"));
        seller1ProductListVO.add(seller1ProductVO1);
        MyShoppingCartProductVO seller1ProductVO2 = new MyShoppingCartProductVO();
        seller1ProductVO2.setCartId(2);
        seller1ProductVO2.setCommodityId(1);
        seller1ProductVO2.setCommodityName("商品1");
        seller1ProductVO2.setDefaultPicPath("http://test.resource.999shuijingqiu.com/COMMODITY/1534574506619");
        seller1ProductVO2.setProductId(2);
        seller1ProductVO2.setProductNum(1);
        seller1ProductVO2.setSalePrice(new BigDecimal("300"));
        seller1ProductListVO.add(seller1ProductVO2);
        sellerVO1.setProductListVO(seller1ProductListVO);
        sellerListVO.add(sellerVO1);

        MyShoppingCartSellerVO sellerVO2 = new MyShoppingCartSellerVO();
        sellerVO2.setSellerId(2);
        sellerVO2.setSellerName("商家2");
        List<MyShoppingCartProductVO> seller2ProductListVO = new ArrayList<MyShoppingCartProductVO>();
        MyShoppingCartProductVO seller2ProductVO1 = new MyShoppingCartProductVO();
        seller2ProductVO1.setCartId(3);
        seller2ProductVO1.setCommodityId(2);
        seller2ProductVO1.setCommodityName("商品2");
        seller2ProductVO1.setDefaultPicPath("http://test.resource.999shuijingqiu.com/COMMODITY/1534574506619");
        seller2ProductVO1.setProductId(3);
        seller2ProductVO1.setProductNum(2);
        seller2ProductVO1.setSalePrice(new BigDecimal("200"));
        seller2ProductListVO.add(seller2ProductVO1);
        sellerVO2.setProductListVO(seller2ProductListVO);
        sellerListVO.add(sellerVO2);
        myShoppingCartVO.setSellerListVO(sellerListVO);
        //根据商家区分
        //价格合计
        //货品状态
        //商品总数量
        return new ResponseResult(myShoppingCartVO);
    }

    @ApiOperation(value = "加入购物车")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "shoppingCart/add")
    public ResponseResult add(@Validated @RequestBody AddShoppingCartVO addShoppingCartVO) {
        //有重复的需要合并
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "购物车删除(批量)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shoppingCartIds", value = "购物车id集合", required = true, paramType = "json", dataType = "list<int>")})
    @PostMapping(value = "shoppingCart/bachDel")
    @ApiVersion(1)
    public ResponseResult bachDel(@RequestBody List<Integer> shoppingCartIds){
        ResponseResult result = new ResponseResult(Result.FAILURE);
        try {
            if(LogicUtils.isNullOrEmpty(shoppingCartIds)){
                return new ResponseResult(Result.FAILURE,"参数为空");//失败
            }
            //favoriteService.removeFavoriteBatch(favoriteIds, getCustomerId());
            result.setResult(Result.SUCCESS);
        }catch (Exception e){
            logger.error("delShoppingCarts Exception is "+e);
        }
        return result;
    }

    @ApiOperation(value = "修改购物车货品数量")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "shoppingCart/update")
    public ResponseResult update(@Validated @RequestBody UpdateShoppingCartVO updateShoppingCartVO) {
        //直接更新数量
        return new ResponseResult(Result.SUCCESS);
    }

//    @ApiOperation(value = "结算")
//    @ApiImplicitParams({
//    })
//    @PostMapping(value = "shoppingCart/settlement")
//    @ApiVersion(1)
//    public ResponseResult settlement(@Validated @RequestBody SettlementVO settlementVO){
//        ResponseResult result = new ResponseResult(Result.FAILURE);
//        //清除购物车购买的商品
//        return result;
//    }
}
