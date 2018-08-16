package com.yunxin.cb.mall.web.action.commodity;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/commodity")
public class CommodityController implements ServletContextAware {
    private Logger logger = LoggerFactory.getLogger(CommodityController.class);


    @Resource
    private ICommodityService commodityService;

    @Resource
    private IBrandService brandService;

    @Resource
    private IPriceService priceService;

    @Resource
    private ICatalogService catalogService;

    @Resource
    private IProductService productService;

    @Resource
    private MessageSource messageSource;

    private ServletContext servletContext;
    @Resource
    private IAttachmentService  attachmentService;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "commodities")
    public String commodities(HttpSession session,ModelMap modelMap) {
        Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
        modelMap.put("seller",seller);
        return "commodity/commodities";
    }

    @RequestMapping(value = "pageCommodities", method = RequestMethod.POST)
    @ResponseBody
    public Page<Commodity> pageCommodities(@RequestBody PageSpecification<Commodity> commodityQuery, HttpServletRequest request) {
        Page<Commodity> page = commodityService.pageCommodities(commodityQuery);
        return page;
    }


    @ResponseBody
    @RequestMapping(value = "choosePagedCommodities",method = RequestMethod.POST)
    public Page<Commodity> choosePagedCommodities(@RequestBody PageSpecification<Commodity> commodityQuery, HttpServletRequest request) {
        Page<Commodity> page = commodityService.choosePagedCommodities(commodityQuery);
        return page;
    }


    @RequestMapping(value = "toAddCommodity", method = RequestMethod.GET)
    public String toAddCommodity(@ModelAttribute("commodity") Commodity commodity, ModelMap modelMap, HttpServletRequest request) {
        return toAddCommodity(commodity, modelMap);
    }

    private String toAddCommodity(Commodity commodity, ModelMap modelMap) {
        List<Brand> brands = brandService.getAllBrands();
        modelMap.addAttribute("brands", brands);
//		List<CommoditySpec> commoditySpecs = commodityService.getCommoditySpecsByCatalogId(2);
//		modelMap.addAttribute("commoditySpecs", commoditySpecs);
        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        modelMap.addAttribute("priceSections", priceService.getAllPriceSections());
        return "commodity/addCommodity";
    }

    @RequestMapping(value = "addCommodity", method = RequestMethod.POST)
    public String addCommodity(@Valid @ModelAttribute("commodity") Commodity commodity,HttpSession session, BindingResult result, ModelMap modelMap, Locale locale,HttpServletRequest request) {
        if (result.hasErrors()) {
            return toAddCommodity(commodity, modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                commodity.setDefaultPicPath(imgurl[0].split(",")[0]);
                if(commodity.getSeller().getSellerId()==0){
                    Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
                    commodity.setSeller(seller);
                }
                commodity = commodityService.addCommodity(commodity);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.COMMODITY,commodity.getCommodityId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.COMMODITY,commodity.getCommodityId(),imgpath);
                }
            }

        } catch (EntityExistException e) {
            result.addError(new FieldError("commodity", "commodityName", commodity.getCommodityName(), true, null, null,
                    messageSource.getMessage("commodity_commodityName_repeat", null, locale)));
            return toAddCommodity(commodity, modelMap);
        }
        return "redirect:editProducts.do?commodityId=" + commodity.getCommodityId();
    }

    @RequestMapping(value = "toAddCommodityAttr", method = RequestMethod.GET)
    public String toAddCommodityAttr(int catalogId, ModelMap modelMap, HttpServletRequest request) {

        return "commodity/addCommodity";
    }

    @RequestMapping(value = "toEditCommodity", method = RequestMethod.GET)
    public String toEditCommodity(@RequestParam("commodityId") int commodityId, ModelMap modelMap, Locale locale) {
        List<Brand> brands = brandService.getAllBrands();
        modelMap.addAttribute("brands", brands);
        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        modelMap.addAttribute("priceSections", priceService.getAllPriceSections());
        Commodity commodity = commodityService.getCommodityDetailById(commodityId);
        modelMap.addAttribute("commodity", commodity);
        modelMap.addAttribute("seller", commodity.getSeller());
        List<CommoditySpec> currentSpecs = commodityService.getCommoditySpecsByCommodityId(commodityId);
        modelMap.addAttribute("currentSpecs", currentSpecs);
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.COMMODITY,commodity.getCommodityId());
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        //S 获得一级分类对象  2018-08-14    LXC
        Catalog oneLevelCatalog = catalogService.findOneLevelCatalogByCatalogCode(commodity.getCatalog().getCatalogCode());
        modelMap.addAttribute("oneLevelCatalog",oneLevelCatalog);
        //E
        return "commodity/editCommodity";
    }

    @RequestMapping(value = "editCommodity", method = RequestMethod.POST)
    public String editCommodity(@Valid @ModelAttribute("commodity") Commodity commodity,HttpSession session, BindingResult result, ModelMap modelMap, Locale locale,HttpServletRequest request) {
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                commodity.setDefaultPicPath(imgurl[0].split(",")[0]);
                if(commodity.getSeller().getSellerId()==0){
                    Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
                    commodity.setSeller(seller);
                }
                commodity = commodityService.updateCommodity(commodity);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.COMMODITY,commodity.getCommodityId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.COMMODITY,commodity.getCommodityId(),imgpath);
                }
            }
            commodityService.updateCommodityES(commodity.getCommodityId());

        } catch (Exception e) {
            result.addError(new FieldError("commodity", "commodityName", commodity.getCommodityName(), true, null, null,
                    messageSource.getMessage("commodity_commodityName_repeat", null, locale)));
            return toEditCommodity(commodity.getCommodityId(), modelMap, locale);
        }
        return "redirect:../common/success.do?reurl=commodity/commodities.do";
    }

    @RequestMapping(value = "removeCommodityById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeCommodityById(@RequestParam("commodityId") int commodityId, HttpServletRequest request) {
        try {
            Commodity commodity = commodityService.findByCommodityId(commodityId);
            String imgDirectory = servletContext.getRealPath("/images/commodity/" + commodity.getCommodityCode());
            commodityService.removeCommodityById(commodityId);
            File imageDir = new File(imgDirectory);
            if (imageDir.isDirectory()) {
                String[] children = imageDir.list();
                for (int i = 0; i < children.length; i++) {
                    new File(imageDir, children[i]).delete();
                }
                imageDir.delete();
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @ResponseBody
    @RequestMapping(value = "upOrDownShelvesCommodity",method = RequestMethod.GET)
    public boolean upOrDownShelvesCommodity(@RequestParam("commodityId") int commodityId, @RequestParam("publishState") PublishState publishState) {
        try{
            return commodityService.upOrDownShelvesCommodity(commodityId, publishState);
        }catch (Exception ex){
            logger.info(ex.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "commodityDetail",method = RequestMethod.GET)
    public String commodityDetail(@RequestParam("commodityId") int commodityId, ModelMap modelMap) throws Exception {
        Commodity commodity = commodityService.getCommodityDetailById(commodityId);
        modelMap.addAttribute("commodity", commodity);
        modelMap.addAttribute("products", productService.getProductsFetchAllByCommodityId(commodity.getCommodityId()));
        modelMap.addAttribute("commoditySpecs", commodityService.getCommoditySpecsByCommodity(commodity));
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.COMMODITY,commodity.getCommodityId());
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return "commodity/commodityDetail";
    }

    @RequestMapping(value = "findCommoditySaleVos")
    public String findCommoditySaleVos(ModelMap modelMap) {
//	List<CommoditySaleVo> commoditySaleVos = commodityService.findCommoditySaleCount();
//	modelMap.addAttribute("commoditySaleVos", commoditySaleVos);
        return "commodity/commoditySaleVos";
    }

    @RequestMapping(value = "getCombinationsByCommodityId",method = RequestMethod.GET)
    @ResponseBody
    public List<Combination> getCombinationsByCommodityId(@RequestParam("commodityId") int commodityId) {
        return commodityService.getCombinationCommoditiesByCommodityId(commodityId);
    }

    @RequestMapping(value = "toCombinations",method = RequestMethod.GET)
    public String toCombinations(@ModelAttribute("combination") Combination combination, @RequestParam("commodityId") int commodityId, ModelMap modelMap) {
        modelMap.addAttribute("commodity", commodityService.findByCommodityId(commodityId));
        modelMap.addAttribute("combinations", commodityService.getCombinationCommoditiesByCommodityId(commodityId));
        return "commodity/combinations";
    }

    @RequestMapping(value = "addCombinationCommodities",method = RequestMethod.POST)
    @ResponseBody
    public boolean addCombinationCommodities(@RequestParam("commodityId") int commodityId, @RequestParam("selectedCommodityId") int[] selectedCommodityId) {
        try {
            return commodityService.addCombinationCommodities(commodityId, selectedCommodityId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping(value = "removeCombinationById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeCombinationById(@RequestParam("combinationId") int combinationId) {
        try {
            commodityService.removeCombinationById(combinationId);
            //把图片删除掉
            attachmentService.deleteAttachmentPictures(ObjectType.COMMODITY, combinationId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @RequestMapping(value = "commodityAudit",method = RequestMethod.GET)
    @ResponseBody
    public boolean commodityAudit(@RequestParam("commodityId") int commodityId, @RequestParam("commodityState") CommodityState commodityState, @RequestParam("auditRemark") String auditRemark) {
        try {
            commodityService.commodityAudit(commodityId,commodityState,auditRemark);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * @Description:                根据分类id,返回一级分类的比例配置
     * @author: lxc
     * @param catalogId             分类id
     * @Return java.lang.String:
     * @DateTime: 2018/8/15 17:41
     */
    @ResponseBody
    @RequestMapping(value = "getOneLevelCatalog",method = RequestMethod.GET)
    public String getOneLevelCatalog(@RequestParam("catalogId") int catalogId) {
        Catalog one = catalogService.findOne(catalogId);
        if(one.getParentCatalogId() == 1){
            return one.getRatio().toString();
        }
        Catalog catalog = catalogService.findOneLevelCatalogByCatalogCode(one.getCatalogCode());
        return catalog.getRatio().toString();
    }

//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public boolean sendSms() {
//        try {
//            SmsHelper.sendCustomerRegisterSms(“234543”, “18898581118”);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//
//    }

}

