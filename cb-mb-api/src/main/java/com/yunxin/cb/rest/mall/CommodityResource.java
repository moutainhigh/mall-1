package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Attribute;
import com.yunxin.cb.mall.entity.AttributeGroup;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.Profile;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.ProfileState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.mall.service.ProfileService;
import com.yunxin.cb.mall.vo.AttributeGroupVO;
import com.yunxin.cb.mall.vo.CommodityVo;
import com.yunxin.cb.mall.vo.SellerVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import javax.annotation.Resource;
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
public class CommodityResource extends BaseResource {

    @Resource
    private CommodityService commodityService;

    @Resource
    private ProfileService profileService;

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
            logger.error("Exception is "+e);
        }
        return new ResponseResult(commodityVo);
    }

    /**
     * @title: 通过商品ID查询所有货品属性
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/20 17:59
     */
    @ApiOperation(value = "通过商品ID查询所有货品属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int")})
    @GetMapping(value = "getProductsByCommodityId/{commodityId}")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<AttributeGroupVO>> getProductsByCommodityId(@PathVariable int commodityId) {
        //查询商品下面所有货品
        List<Product> products = commodityService.getProductsByCommodityId(commodityId,ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        //商品属性集合
        Set<Attribute> attributes = new HashSet<>();
        products.stream().forEach(p -> {
            //遍历所有货品，取出所有货品属性
            p.getProductAttributes().stream().forEach(pa -> attributes.add(pa.getAttribute()));
        });
        //根据属性里面的属性组对象进行对象分组
        Map<AttributeGroup, List<Attribute>> attributeGroups = attributes.stream().collect(
                Collectors.groupingBy(Attribute::getAttributeGroup));
        //属性组集合
        List<AttributeGroup> groups=new ArrayList<>();
        //迭代对象分组，将属性与属性组关联
        for (AttributeGroup attributeGroup : attributeGroups.keySet()) {
            attributeGroup.setAttributes(new TreeSet<>());
            //此处需实现属性实体中Comparable的compareTo排序方法
            attributeGroup.getAttributes().addAll(attributeGroups.get(attributeGroup));
            groups.add(attributeGroup);
        }
        //转换成VO返回(需实现VO中Comparable的compareTo排序方法)
        return new ResponseResult(AttributeGroupVO.convertVO(groups));
    }

    /**
     * @title: 获取所有商家的地区编码及名称
     * @param: []
     * @return: com.yunxin.cb.vo.ResponseResult<java.util.List<com.yunxin.cb.mall.vo.SellerVo>>
     * @auther: eleven
     * @date: 2018/8/16 16:30
     */
    @ApiOperation(value = "获取所有商家的地区编码及名称")
    @ApiImplicitParams({
    })
    @GetMapping(value = "getAllSellerAddress")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<SellerVo>> getAllSellerAddress() {
        return new ResponseResult(commodityService.getAllSellerAddress());
    }

    @ApiOperation(value = "热门城市")
    @GetMapping(value = "hotCity")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<String> hotCity() {
        ResponseResult result=new ResponseResult(Result.FAILURE);
        try {
            StringBuilder sb = new StringBuilder();
            Profile profile = profileService.getProfileByName(ProfileState.HOT_CITY.name());
            if (profile != null && StringUtils.isNotBlank(profile.getFileValue())) {
                sb.append("{");
                String [] hotSearchArr = profile.getFileValue().split(",");
                for (String city:hotSearchArr){
                    String [] cityArr = city.split("&");
                    sb.append("\"city\":").append(cityArr[0]);
                    sb.append(",\"code\":").append(cityArr[1]);
                }
                sb.append("}");
            }
            result.setResult(Result.SUCCESS);
            result.setMessage(sb.toString());
        } catch (Exception e) {
            logger.info("hotCity failed", e);
        }
        return result;
    }

}
