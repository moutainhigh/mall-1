package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.web.vo.ResponseResult;
import com.yunxin.cb.mall.web.vo.ResultType;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.util.IdGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/commodity")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Resource
    private IProductService productService;

    @Resource
    private ICommodityService commodityService;

    @Resource
    private ILogsService systemLogsService;

    @Resource
    private IAttributeService attributeService;

    @Resource
    private IStoreService storeService;

    @Resource
    private MessageSource messageSource;
    @Resource
    private ICatalogService catalogService;

    @RequestMapping(value = "editProducts")
    public String editProducts(@RequestParam("commodityId") int commodityId, @ModelAttribute("product") Product product, ModelMap modelMap) {
        Commodity commodity = commodityService.getCommodityDetailById(commodityId);
        List<Product> products = productService.getProductsFetchAllByCommodityId(commodityId);
        modelMap.addAttribute("commodity", commodity);
        modelMap.addAttribute("products", products);
        product.setCommodity(commodity);
        product.setProductNo(IdGenerate.genProductID());

        List<AttributeGroup> attributeGroups = attributeService.getAttributeGroupsByCommodityId(commodity.getCommodityId());
        modelMap.addAttribute("attributeGroups", attributeGroups);

        List<CatalogAttributeGroup> catalogAttributeGroups = attributeService.getAttributeGroupsByCatalogId(commodity.getCatalog().getCatalogId());
        modelMap.addAttribute("catalogAttributeGroups", catalogAttributeGroups);

        List<Store> stores = storeService.getAllStores();
        modelMap.addAttribute("stores", stores);

        //S 获得一级分类对象  2018-08-14    LXC
        Catalog oneLevelCatalog = catalogService.findOneLevelCatalogByCatalogCode(commodity.getCatalog().getCatalogCode());
        modelMap.addAttribute("oneLevelCatalog",oneLevelCatalog);
        //E
        return "commodity/editProducts";
    }

    @RequestMapping( value = "addCommodityAttributeGroups",method = RequestMethod.POST)
    public String addCommodityAttributeGroups(@RequestParam("commodityId") int commodityId,@RequestParam("groupId") int[] groupId) {
        if (groupId!=null&&groupId.length>0){
            try{
                attributeService.addCommodityAttributeGroups(commodityId,groupId);
            }catch (EntityExistException e){
                e.printStackTrace();
            }
        }
        return "redirect:editProducts.do?commodityId=" + commodityId;
    }


    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product, BindingResult result, ModelMap modelMap, Locale locale, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            editProducts(product.getCommodity().getCommodityId(), product, modelMap);
        }
        int[] attributeIds = product.getAttributeIds();
        if (attributeIds == null || attributeIds.length == 0) {
            FieldError fieldError = new FieldError("product", "attributeIds", attributeIds, true, null, null,
                    messageSource.getMessage("product_attributeIds_empty", null, locale));
            result.addError(fieldError);
            return editProducts(product.getCommodity().getCommodityId(), product,  modelMap);
        }
        List<Integer> groupIds = attributeService.findGroupIdsByAttributeIdIn(attributeIds);
        int size = groupIds.stream().collect(Collectors.groupingBy(p -> p.intValue())).size();
        if (size < attributeIds.length) {
            FieldError fieldError = new FieldError("product", "attributeIds", attributeIds, true, null, null,
                    messageSource.getMessage("product_attributeIds_repeat", null, locale));
            result.addError(fieldError);
            return editProducts(product.getCommodity().getCommodityId(), product, modelMap);
        }
        Commodity commodity = commodityService.getCommodityDetailById(product.getCommodity().getCommodityId());
        product.setDefaultPicPath(commodity.getDefaultPicPath());
        product = productService.addProduct(product);
        redirectAttributes.addFlashAttribute("oppMsg","货品新增成功！");
        return "redirect:editProducts.do?commodityId=" + product.getCommodity().getCommodityId();
    }

    @RequestMapping(value = "updateProductStatus", method = RequestMethod.GET)
    @ResponseBody
    public String updateProductStatus(@RequestParam("productId") int productId,
                                      @RequestParam("status") ProductState status, HttpServletRequest request) {
        Product product = productService.getProductById(productId);
        Commodity commodity = product.getCommodity();
        //只有商品审核通过 其下货品才可以进行其他操作
//        if(commodity.getStatus()== CommodityState.AUDITED){
        /**货品未上架，申请下架，提示不允许**/
//        if (product.getProductState() != ProductState.ON && status == ProductState.OFF) {
//            return "wait";
//        }
        productService.updateProductStatusById(productId, status);
        //添加系统日志
        systemLogsService.log(request, "更改了货品状态,productId=" + productId);
        return "success";
//        }else{
//            return "failure";
//        }
    }


    @RequestMapping(value = "toEditProduct",method = RequestMethod.GET)
    public String toEditProduct(@RequestParam("productId") int productId, ModelMap modelMap) {
        Product p = productService.getProductDetailById(productId);
        modelMap.addAttribute("product", p);
        Commodity commodity = commodityService.getCommodityDetailById(p.getCommodity().getCommodityId());
        modelMap.addAttribute("commodity", commodity);
//        List<AttributeGroup> attributeGroups = attributeService.getAttributeGroupsByCommodityId(commodity.getCommodityId());
//        modelMap.addAttribute("attributeGroups", attributeGroups);
        List<ProductAttribute> productAttributes = attributeService.findProductAttributeValues(productId);
        modelMap.addAttribute("productAttributes", productAttributes);
        List<Store> stores = storeService.getAllStores();
        modelMap.addAttribute("stores", stores);
        //S 获得一级分类对象  2018-08-14    LXC
        Catalog oneLevelCatalog = catalogService.findOneLevelCatalogByCatalogCode(commodity.getCatalog().getCatalogCode());
        modelMap.addAttribute("oneLevelCatalog",oneLevelCatalog);
        //E
        return "commodity/editProduct";
    }

    @RequestMapping(value = "editProduct", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("product") Product product, BindingResult result ) {
        int commodityId=product.getCommodity().getCommodityId();
        Commodity commodity = commodityService.getCommodityDetailById(product.getCommodity().getCommodityId());
        product.setDefaultPicPath(commodity.getDefaultPicPath());
        productService.updateProduct(product);
        return "redirect:editProducts.do?commodityId="+commodityId;
    }

    @RequestMapping( value = "removeProductById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult removeProductById(@RequestParam("productId") int productId) {
        ResponseResult responseResult = new ResponseResult();
        try {
            productService.removeProductById(productId);
            responseResult.setResultType(ResultType.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseResult.setResultType(ResultType.FAILURE);
        }
        return responseResult;
    }

    /**
     * @title: 设置默认货品
     * @param: [productId]
     * @return: com.yunxin.cb.mall.web.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/30 16:47
     */
    @RequestMapping( value = "defaultProductById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult defaultProductById(@RequestParam("productId") int productId,@RequestParam("commodityId") int commodityId) {
        ResponseResult responseResult = new ResponseResult();
        try {
            Product product=productService.getProductById(productId);
            if (product.getCommodity().getPublishState() == PublishState.DOWN_SHELVES){
                commodityService.updateCommodityStatus(product,commodityId);
                responseResult.setResultType(ResultType.SUCCESS);
            }else {
                responseResult.setResultType(ResultType.FAILURE);
                responseResult.setData("请先下架商品");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseResult.setResultType(ResultType.FAILURE);
        }
        return responseResult;
    }



    @RequestMapping(value = "product", method = RequestMethod.GET)
    public String product(@RequestParam("productId") int productId, ModelMap modelMap) {
        try {
            Product p = productService.getProductDetailById(productId);
            modelMap.addAttribute("product", p);
            Commodity commodity = commodityService.getCommodityDetailById(p.getCommodity().getCommodityId());
            modelMap.addAttribute("commodity", commodity);
//			Map<String, List<AttributeValue>> map = propertyService.initProps(commodity.getAttributes());
//			List<ProductAttribute> pplist=propertyService.findProductProptyValues(p.getProductId());
//			if(LogicUtils.isNotNullAndEmpty(pplist)){
//				String values="";
//				for(ProductAttribute pp:pplist){
////					values+=pp.getPropertyValue()+",";
//				}
//				modelMap.addAttribute("propValues",values);
//			}
//			modelMap.addAttribute("propMap",map);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/productDetail";
    }

    @ResponseBody
    @RequestMapping(value = "upOrDownShelvesProduct",method = RequestMethod.GET)
    public boolean upOrDownShelvesProduct(@RequestParam("productId") int productId, @RequestParam("publishState") PublishState publishState) {
        return productService.upOrDownShelvesProduct(productId, publishState);
    }

}
