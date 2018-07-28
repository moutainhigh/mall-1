package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.ProductAttribute;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseResult getProductsByCommodityId(@PathVariable int commodityId) {
        Map<String, Object> firstFloor = new HashMap<String, Object>();//第一层
        Map<String, Object> twoFloor = new HashMap<String, Object>();//第二层
        Map<String, Object> threeFloor = new HashMap<String, Object>();//第三层
        Map<String, Object> fourFloor = new HashMap<String, Object>();//第四层
        Map<String, Object> fiveFloor = new HashMap<String, Object>();//第五层
        Map<String, Object> sixValue = new HashMap<String, Object>();
//        Map<String, Object> sixFloor = new HashMap<String, Object>();//第六层
        String treeKey="";
        String fourKey="";
        String fiveKey="";
        String sixKey="";
        List<Product> products = commodityService.getProductsByCommodityId(commodityId);
        for (Product pro : products) {
            List<ProductAttribute> attributeList = pro.getProductAttributes();
            for (int i = 0; i < attributeList.size(); i++) {
                //获取第一层和第二层的key
                String oneKey=attributeList.get(0).getAttribute().getAttributeGroup().getGroupName();
                String twoKey=attributeList.get(0).getAttribute().getAttributeName();
                if (i == 0) {
                    //取value---map，没有就实例化
                    twoFloor= (Map<String, Object>) firstFloor.get(oneKey)==null?new HashMap<String, Object>():(Map<String, Object>) firstFloor.get(oneKey);
                    threeFloor = (Map<String, Object>) twoFloor.get(twoKey)==null?new HashMap<String, Object>():(Map<String, Object>) twoFloor.get(twoKey);
                    //第二层Key取AttributeName，Value放第三层
                    twoFloor.put(twoKey, threeFloor);
                    //第一层Key取GroupName，Value放第二层
                    firstFloor.put(oneKey, twoFloor);
                } else if (i == 1) {
                    //获取key
                    treeKey = attributeList.get(1).getAttribute().getAttributeGroup().getGroupName();
                    fourKey = attributeList.get(1).getAttribute().getAttributeName();
                    twoFloor= (Map<String, Object>) firstFloor.get(oneKey);
                    threeFloor = (Map<String, Object>) twoFloor.get(twoKey);
                    //取value---map，没有就实例化
                    fourFloor= (Map<String, Object>)threeFloor.get(treeKey)==null?new HashMap<String, Object>():(Map<String, Object>) threeFloor.get(treeKey);
                    fiveFloor = (Map<String, Object>)fourFloor.get(fourKey)==null?new HashMap<String, Object>():(Map<String, Object>) fourFloor.get(fourKey);
//                    sixFloor = (Map<String, Object>)fiveFloor.get(fiveKey)==null?new HashMap<String, Object>():(Map<String, Object>) fiveFloor.get(fiveKey);
                    //第四层key放AttributeName
                    fourFloor.put(attributeList.get(1).getAttribute().getAttributeName(),fiveFloor);
                    //第三层key放GroupName
                    threeFloor.put(treeKey, fourFloor);
                } else if (i == 2) {
                    //获取key
                    fiveKey= attributeList.get(2).getAttribute().getAttributeGroup().getGroupName();
                    sixKey = attributeList.get(2).getAttribute().getAttributeName();
                    twoFloor= (Map<String, Object>) firstFloor.get(oneKey);
                    threeFloor = (Map<String, Object>) twoFloor.get(twoKey);
                    fourFloor= (Map<String, Object>)threeFloor.get(treeKey);
                    sixValue=fiveFloor.get(fiveKey)==null?new HashMap<String, Object>():(Map<String, Object>) fiveFloor.get(fiveKey);
                    //第六层放AttributeName
                    sixValue.put(sixKey,pro.getProductId());
                    //第五层key放GroupName
                    fiveFloor.put(fiveKey,sixValue);
                }

            }
        }
        return new ResponseResult(firstFloor);
    }
}
