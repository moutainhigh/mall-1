package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.security.SecurityConstants;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
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
import java.io.FilenameFilter;
import java.util.*;

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
    private ISellerService sellerService;

    @Resource
    private IPriceService priceService;


    @Resource
    private IAttributeService attributeService;

    @Resource
    private ICatalogService catalogService;

    @Resource
    private IProductService productService;

    @Resource
    private MessageSource messageSource;

    private ServletContext servletContext;

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
    public String addCommodity(@Valid @ModelAttribute("commodity") Commodity commodity,HttpSession session, BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toAddCommodity(commodity, modelMap);
        }
        try {
            String[] imagePath = commodity.getImagePath();
            Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
            commodity.setSeller(seller);
            commodity = commodityService.addCommodity(commodity);
            CommodityImageConverter imageConverter = new CommodityImageConverter(commodity, imagePath, servletContext);
            imageConverter.compress();
            commodityService.updateDefaultPicPath(commodity.getCommodityId(), imageConverter.getDefaultImagePath());
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
        List<CommoditySpec> currentSpecs = commodityService.getCommoditySpecsByCommodityId(commodityId);
        modelMap.addAttribute("currentSpecs", currentSpecs);

        File imageDir = new File(servletContext.getRealPath("/images/commodity/" + commodity.getCommodityCode()));
        String[] images = imageDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("jpg")) {
                    return true;
                }
                return false;
            }
        });
        if (images != null) {
            Set<String> imageSet = new HashSet<>();
            for (String image : images) {
                imageSet.add("commodity/" + commodity.getCommodityCode() + "/" + image.substring(0, image.indexOf("_")));
            }
            modelMap.addAttribute("imageSet", imageSet);
        }
        return "commodity/editCommodity";
    }

    @RequestMapping(value = "editCommodity", method = RequestMethod.POST)
    public String editCommodity(@Valid @ModelAttribute("commodity") Commodity commodity, BindingResult result, ModelMap modelMap, Locale locale) {
        try {
            String[] imagePath = commodity.getImagePath();
            commodity = commodityService.updateCommodity(commodity,servletContext.getRealPath("/images/commodity/"));
            CommodityImageConverter imageConverter = new CommodityImageConverter(commodity, imagePath, servletContext);
            imageConverter.compress();
            if (LogicUtils.isNullOrEmpty(imageConverter.getDefaultImagePath())) {
                commodityService.updateDefaultPicPath(commodity.getCommodityId(), commodity.getDefaultPicPath());
            } else {
                commodityService.updateDefaultPicPath(commodity.getCommodityId(), imageConverter.getDefaultImagePath());
            }

        } catch (EntityExistException e) {
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
        return commodityService.upOrDownShelvesCommodity(commodityId, publishState);
    }

    @RequestMapping(value = "commodityDetail",method = RequestMethod.GET)
    public String commodityDetail(@RequestParam("commodityId") int commodityId, ModelMap modelMap) throws Exception {
        Commodity commodity = commodityService.getCommodityDetailById(commodityId);
        modelMap.addAttribute("commodity", commodity);
        modelMap.addAttribute("products", productService.getProductsFetchAllByCommodityId(commodity.getCommodityId()));
        modelMap.addAttribute("commoditySpecs", commodityService.getCommoditySpecsByCommodity(commodity));
        File imageDir = new File(servletContext.getRealPath("/images/commodity/" + commodity.getCommodityCode()));
        String[] images = imageDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("jpg")) {
                    return true;
                }
                return false;
            }
        });
        if (images != null) {
            Set<String> imageSet = new HashSet<>();
            for (String image : images) {
                imageSet.add("commodity/" + commodity.getCommodityCode() + "/" + image.substring(0, image.indexOf("_")));
            }
            modelMap.addAttribute("imageSet", imageSet);
        }
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

