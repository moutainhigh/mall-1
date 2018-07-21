package com.yunxin.cb.rest.mall;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @title: 商城商品接口
 * @auther: eleven
 * @date: 2018/7/17 18:29
 */
@Api(description = "商城商品接口")
@RestController
@RequestMapping(value = "/mall/commodity")
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
            @ApiImplicitParam(name = "productId", value = "货品ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "getCommdityDetail")
    public ResponseResult getCommdityDetail(@RequestParam int productId){
        int customerId=getCustomerId();
        Map map=commodityService.getCommdityDetail(productId,customerId);
        Commodity commodity=(Commodity)map.get("commodity");
        //通过商品编码规则取商品所有图片
        File imageDir = new File(servletContext.getRealPath("/images/commodity/" + commodity.getCommodityCode()));
        String[] images = imageDir.list(new FilenameFilter() {//过滤图片后缀
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("jpg")) {
                    return true;
                }
                return false;
            }
        });
        Set<String> imageSet = new HashSet<>();
        if (images != null) {
            for (String image : images) {
                imageSet.add("commodity/" + commodity.getCommodityCode() + "/" + image.substring(0, image.indexOf("_")));
            }
            }
        map.put("imageSet", imageSet);//商品图片
        return new ResponseResult(map);
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
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "getProductsByCommodityId")
    public ResponseResult getProductsByCommodityId(@RequestParam int commodityId){
        List<Product> products=commodityService.getProductsByCommodityId(commodityId);
        return new ResponseResult(products);
    }
}
