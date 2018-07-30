package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.mall.entity.meta.ReturnReason;
import com.yunxin.cb.mall.service.ProductReturnService;
import com.yunxin.cb.mall.vo.ProductReturnApplyDataVO;
import com.yunxin.cb.mall.vo.ProductReturnApplyVO;
import com.yunxin.cb.mall.vo.ProductReturnDetailVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商城退货接口
* @author gws
* @date 2018/7/24 20:01
* @param
* @return
*/
@Api(description = "商城退货接口")
@RestController
@RequestMapping(value = "{version}/mall")
public class ProductReturnResource extends BaseResource {

    private static Logger logger = LoggerFactory.getLogger(ProductReturnResource.class);
    @Resource
    private ProductReturnService productReturnService;

    @ApiOperation(value = "退货申请页数据获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, paramType = "path", dataType = "int")})
    @ApiVersion(1)
    @PostMapping(value = "productReturn/apply/{orderId}")
    public ResponseResult<ProductReturnApplyDataVO> getProductReturnData(@PathVariable(value = "orderId")int orderId) throws Exception{
        ProductReturnApplyDataVO productReturnApplyDataVO = new ProductReturnApplyDataVO();
        Order order = productReturnService.checkProductReturnApply(orderId, getCustomerId());
        Map returnReason = new HashMap();//退货原因
        for (ReturnReason reason : ReturnReason.values()){
            returnReason.put(reason, reason.toString());
        }
        productReturnApplyDataVO.setReturnReason(returnReason);
        productReturnApplyDataVO.setOrderId(order.getOrderId());
        productReturnApplyDataVO.setReturnMobile(order.getConsigneeMobile());
        productReturnApplyDataVO.setReturnName(order.getConsigneeName());
        return new ResponseResult(productReturnApplyDataVO);
    }

    @ApiOperation(value = "退货申请")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @PostMapping(value = "productReturn")
    public ResponseResult addProductReturn(@RequestBody ProductReturnApplyVO productReturnApplyVO) throws Exception{
        logger.info("productReturnApplyVO:" + productReturnApplyVO.toString());
        ProductReturn productReturn = new ProductReturn();
        try {
            BeanUtils.copyProperties(productReturn, productReturnApplyVO);
            productReturn.setCustomerId(getCustomerId());
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        productReturnService.applyOrderProductReturn(productReturn);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "退货分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "orderId", value = "订单id", paramType = "post", dataType = "Integer")})
    @ApiVersion(1)
    @PostMapping(value = "productReturn/pageList")
    public ResponseResult<PageFinder<ProductReturnDetailVO>> pageProductReturn(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize,
                                            @RequestParam(value = "orderId", required = false) Integer orderId){
        Query q = new Query(pageNo, pageSize);
        ProductReturn productReturn = new ProductReturn();
        productReturn.setCustomerId(getCustomerId());
        productReturn.setOrderId(orderId);
        q.setData(productReturn);
        PageFinder<ProductReturn> pageFinder =  productReturnService.pageProductReturn(q);
        PageFinder<ProductReturnDetailVO> page = null;
        try {
            page = ProductReturnDetailVO.dOconvertVOPage(pageFinder);
        } catch (Exception e) {
            logger.info("pageListProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(page);
    }

    @ApiOperation(value = "退货列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", paramType = "post", dataType = "Integer")})
    @ApiVersion(1)
    @PostMapping(value = "productReturn/list")
    public ResponseResult<List<ProductReturnDetailVO>> listProductReturn(@RequestParam(value = "orderId", required = false) Integer orderId){
        Query q = new Query();
        ProductReturn productReturn = new ProductReturn();
        productReturn.setCustomerId(getCustomerId());
        productReturn.setOrderId(orderId);
        q.setData(productReturn);
        List<ProductReturn> listProductReturn =  productReturnService.listProductReturn(q);
        List<ProductReturnDetailVO> list = null;
        try {
            list = ProductReturnDetailVO.dOconvertVOList(listProductReturn);
        } catch (Exception e) {
            logger.info("listProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(list);
    }

    @ApiOperation(value = "退货详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productReturnId", value = "退货id", required = true, paramType = "path", dataType = "int")})
    @ApiVersion(1)
    @GetMapping(value = "productReturn/{productReturnId}")
    public ResponseResult<ProductReturnDetailVO> getProductReturn(@PathVariable Integer productReturnId){
        ProductReturn productReturn = productReturnService.getProductReturn(productReturnId, getCustomerId());
        ProductReturnDetailVO productReturnDetailVO = null;
        try {
            productReturnDetailVO =  ProductReturnDetailVO.dOconvertVO(productReturn);
        } catch (Exception e) {
            logger.info("getProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(productReturnDetailVO);
    }


}
