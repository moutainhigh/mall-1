package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.mall.service.ProductReturnService;
import com.yunxin.cb.mall.vo.ProductReturnDetailVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ProductReturnApplyVO;
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
import java.util.List;

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
    public ResponseResult pageProductReturn(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize,
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
    public ResponseResult listProductReturn(@RequestParam(value = "orderId", required = false) Integer orderId){
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
    public ResponseResult getProductReturn(@PathVariable Integer productReturnId){
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
