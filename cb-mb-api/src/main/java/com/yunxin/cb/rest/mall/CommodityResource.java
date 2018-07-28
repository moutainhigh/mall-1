package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Attribute;
import com.yunxin.cb.mall.entity.AttributeGroup;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.mall.vo.CommodityVo;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @title: 商城商品接口
 * @auther: eleven
 * @date: 2018/7/17 18:29
 */
@Api(description = "商城商品接口")
@RestController
@RequestMapping(value = "/{version}/mall/commodity")
public class CommodityResource extends BaseResource implements ServletContextAware {

    @Resource
    private CommodityService commodityService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * @title: 通过货品ID查询商品详情
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:29
     */
    @ApiOperation(value = "通过货品ID查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "货品ID", required = true, paramType = "path", dataType = "int")})
    @GetMapping(value = "getCommdityDetail/{productId}")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<CommodityVo> getCommdityDetail(@PathVariable int productId){
        CommodityVo commodityVo= null;
        try {
            int customerId=getCustomerId();
            commodityVo = commodityService.getCommdityDetail(productId,customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(commodityVo);
    }

    /**
     * @title: 通过商品ID查询所有货品
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/20 17:59
     */
    @ApiOperation(value = "通过商品ID查询所有货品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int")})
    @GetMapping(value = "getProductsByCommodityId/{commodityId}")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<AttributeGroup>> getProductsByCommodityId(@PathVariable int commodityId) {
        //List<Product> products = productService.getProductsFetchAllByCommodityId(commodityId, PublishState.UP_SHELVES);
        List<Product> products = commodityService.getProductsByCommodityId(commodityId,ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        Set<Attribute> attributes = new HashSet<>();
        products.stream().forEach(p -> {
            p.getProductAttributes().stream().forEach(pa -> attributes.add(pa.getAttribute()));
        });
        Map<AttributeGroup, List<Attribute>> attributeGroups = attributes.stream().collect(
                Collectors.groupingBy(Attribute::getAttributeGroup));
        List<AttributeGroup> groups=new ArrayList<>();
        for (AttributeGroup attributeGroup : attributeGroups.keySet()) {
            attributeGroup.setAttributes(new TreeSet<>());
            attributeGroup.getAttributes().addAll(attributeGroups.get(attributeGroup));
            groups.add(attributeGroup);
        }
        return new ResponseResult(groups);
    }

}
