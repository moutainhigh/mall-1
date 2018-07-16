package com.yunxin.cb.mall.web.action.operation;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.ProductEvaluateReply;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.mall.service.IEvaluateService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.ProductEvaluateReply;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.mall.service.IEvaluateService;
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
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/operation")
public class ProductEvaluateController {

    @Resource
    private ICommodityService commodityService;

    @Resource
    private IEvaluateService commodityEvaluateService;

    @Resource
    private ILogsService systemsServicelog;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "productEvaluates",method = RequestMethod.GET)
    public String productEvaluates() {
        return "operation/productEvaluates";
    }

    @RequestMapping(value = "pageProductEvaluates",method = RequestMethod.POST)
    @ResponseBody
    public Page<ProductEvaluate> pageProductEvaluates(@RequestBody PageSpecification<ProductEvaluate> query) {
        Page<ProductEvaluate> pages = commodityEvaluateService.pageProductEvaluates(query);
        return pages;
    }

    @RequestMapping(value = "toAddProductEvaluate",method = RequestMethod.GET)
    public String toAddProductEvaluate(@ModelAttribute("productEvaluate") ProductEvaluate productEvaluate,
                                       ModelMap modelMap) {
        return "operation/addEvaluate";
    }

    @RequestMapping(value = "addProductEvaluate",method = RequestMethod.POST)
    public String addProductEvaluate(@Valid @ModelAttribute("productEvaluate") ProductEvaluate productEvaluate, ModelMap modelMap, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return toAddProductEvaluate(productEvaluate, modelMap);
        }
        try {
            commodityEvaluateService.addProductEvaluate(productEvaluate);
            return "redirect:../common/success.do?reurl=evaluate/productEvaluates.do";
        } catch (Exception e) {
            result.addError(new FieldError("productEvaluate", "productName", productEvaluate.getCommodity().getCommodityName(), true, null, null,
                    messageSource.getMessage("productName", null, locale)));
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=evaluate/productEvaluates.do";
        }
    }

    @RequestMapping(value = "toEditProductEvaluate",method = RequestMethod.GET)
    public String toEditProductEvaluate(@RequestParam("evaluateId") int evaluateId, ModelMap modelMap) {
        modelMap.addAttribute("productEvaluate", commodityEvaluateService.getEvaluateById(evaluateId));
        return "operation/addEvaluate";
    }

    @RequestMapping(value = "editProductEvaluate",method = RequestMethod.POST)
    public String editProductEvaluate(@Valid @ModelAttribute("productEvaluate") ProductEvaluate productEvaluate, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return productEvaluates();
        }
        try {
            commodityEvaluateService.updateProductEvaluate(productEvaluate);
            return "redirect:../common/success.do?reurl=evaluate/productEvaluates.do";
        } catch (Exception e) {
            result.addError(new FieldError("productEvaluate", "productName", productEvaluate.getCommodity().getCommodityName(), true, null, null,
                    messageSource.getMessage("storeName", null, locale)));
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=evaluate/productEvaluates.do";
        }
    }

    @RequestMapping(value = "removeProductEvaluateById",method = RequestMethod.GET)
    @ResponseBody
    public int removeProductEvaluateById(@RequestParam("evaluateId") int evaluateId) {
        commodityEvaluateService.removeProductEvaluateById(evaluateId);
        return evaluateId;
    }

    @RequestMapping(value = "evaluateDetails",method = RequestMethod.GET)
    public String evaluateDetails(@RequestParam("evaluateId") int evaluateId, ModelMap modelMap) {
        modelMap.addAttribute("evaluate", commodityEvaluateService.getEvaluateById(evaluateId));
        modelMap.addAttribute("productEvaluateReplies", commodityEvaluateService.getProductEvaluateReplysByEvaluateId(evaluateId));
        return "operation/evaluateDetails";
    }


    @RequestMapping(value = "productEvaluateReply",method = RequestMethod.POST)
    @ResponseBody
    public boolean productEvaluateReply(@RequestParam("evaluateId") int evaluateId,@RequestParam("replyContent") String replyContent,HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute(SecurityConstants.LOGIN_SESSION);
            ProductEvaluateReply evaluateReply=new ProductEvaluateReply();
            ProductEvaluate productEvaluate=new ProductEvaluate();
            productEvaluate.setEvaluateId(evaluateId);
            evaluateReply.setProductEvaluate(productEvaluate);
            evaluateReply.setUser(user);
            evaluateReply.setReplyContent(replyContent);
            commodityEvaluateService.addProductEvaluateReply(evaluateReply);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @RequestMapping(value = "removeProductEvaluateReplyById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeProductEvaluateReplyById(@RequestParam("replyId") int replyId) {
        try {
            commodityEvaluateService.removeProductEvaluateReplyById(replyId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @RequestMapping(value = "removeCommodityEvaluateById", method = RequestMethod.GET)
    @ResponseBody
    public String removeCommodityEvaluateById(@RequestParam("evaId") int evaId
            , HttpServletRequest request) {
        commodityEvaluateService.removeCommodityEvaluateById(evaId);
        //增加日志
        systemsServicelog.log(request, "删除了的商品评价！evaId=" + evaId);
        return "success";
    }

}

