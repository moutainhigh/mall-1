package com.yunxin.cb.mall.web.action.seller;

import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.service.ISellerService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.service.ISellerService;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by chenxing on 2016/1/31.
 */
@Controller
@RequestMapping(value = "/seller")
public class SellerController {


    @Resource
    private ISellerService sellerService;

    @Resource
    private ISecurityService userService;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "sellers",method = RequestMethod.GET)
    public String sellers() {
        return "seller/sellers";
    }


    @RequestMapping(value = "pageSellers",method = RequestMethod.POST)
    @ResponseBody
    public Page<Seller> pageSellers(@RequestBody PageSpecification<Seller> query) {
        Page<Seller> page = sellerService.pageSellers(query);
        return page;
    }

    @RequestMapping(value = "toAddSeller",method = RequestMethod.GET)
    public String toAddSeller(@ModelAttribute("seller") Seller seller, ModelMap modelMap) {
        return "seller/addSeller";
    }

    @RequestMapping(value = "addSeller",method = RequestMethod.POST)
    public String addSeller(@Valid @ModelAttribute("seller") Seller seller, BindingResult result, ModelMap modelMap, HttpServletRequest request, Locale locale) {
        if (result.hasErrors()) {
            return toAddSeller(seller, modelMap);
        }
        try {
            sellerService.addSeller(seller);
            return "redirect:../common/success.do?reurl=seller/sellers.do";
        } catch (EntityExistException e) {
            result.addError(new FieldError("seller", "sellerName", seller.getSellerName(), true, null, null,
                    messageSource.getMessage(e.getMessage(), null, locale)));
            return toAddSeller(seller, modelMap);
        }
    }

    @RequestMapping(value = "toEditSeller",method = RequestMethod.GET)
    public String toEditSeller(@RequestParam("sellerId") int sellerId, ModelMap modelMap) {
        modelMap.addAttribute("seller", sellerService.getSellerById(sellerId));
        return "seller/editSeller";
    }

    @RequestMapping(value = "editSeller",method = RequestMethod.POST)
    public String editSeller(@Valid @ModelAttribute("seller") Seller seller, BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return sellers();
        }
        try {
            sellerService.updateSeller(seller);
            return "redirect:../common/success.do?reurl=seller/sellers.do";
        } catch (EntityExistException e) {
            result.addError(new FieldError("seller", "sellerName", seller.getSellerName(), true, null, null,
                    messageSource.getMessage(e.getMessage(), null, locale)));
            return toEditSeller(seller.getSellerId(), modelMap);
        }
    }

    @RequestMapping(value = "sellerDetail",method = RequestMethod.GET)
    public String sellerDetail(@RequestParam("sellerId") int sellerId, ModelMap modelMap) {
        modelMap.addAttribute("seller", sellerService.getSellerById(sellerId));
        return "seller/sellerDetail";
    }

    @RequestMapping(value = "enableSellerById",method = RequestMethod.GET)
    @ResponseBody
    public int enableSellerById(@RequestParam("sellerId") int sellerId, @RequestParam("enabled") boolean enabled) {
        sellerService.enableSellerById(sellerId, enabled);
        return sellerId;
    }


    @RequestMapping(value = "removeSellerById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeSellerById(@RequestParam("sellerId") int sellerId) {
        try {
            sellerService.removeSellerById(sellerId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}
