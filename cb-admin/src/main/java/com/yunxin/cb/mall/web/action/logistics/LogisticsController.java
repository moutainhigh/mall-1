package com.yunxin.cb.mall.web.action.logistics;

import com.yunxin.cb.mall.entity.Logistic;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.service.ILogisticsService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.Logistic;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.service.ILogisticsService;
import com.yunxin.cb.security.SecurityConstants;
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
import java.util.List;
import java.util.Locale;

/**
 * Created by sheh on 14-11-21.
 */
@Controller
@RequestMapping("/logistic")
public class LogisticsController {
    @Resource
    private ILogisticsService logisticsService;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String logistics() {
        return "logistic/logistics";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<Logistic> pageLogistics(@RequestBody PageSpecification<Logistic> query) {
        Page<Logistic> page = logisticsService.pageLogistics(query);
        return page;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toAddLogistic(@ModelAttribute("logistic") Logistic logistic) {
        return "logistic/addLogistic";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addLogistic(@Valid @ModelAttribute("logistic") Logistic logistic, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return toAddLogistic(logistic);
        }
        try {
            Seller seller = (Seller)request.getSession().getAttribute(SecurityConstants.LOGIN_SELLER);
            logistic.setSeller(seller);
            logisticsService.addLogistics(logistic);
        } catch (EntityExistException e) {
            result.addError(new FieldError("logistic", "logisticName", logistic.getLogisticName(), true, null, null,e.getMessage()));
            return toAddLogistic(logistic);
        }
        return "redirect:../common/success.do?reurl=logistic/logistics.do";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toEditLogistic(@RequestParam("logisticId") int logisticsId, ModelMap modelMap) {
        Logistic logistic = logisticsService.findById(logisticsId);
        modelMap.addAttribute("logistic", logistic);
        return "logistic/editLogistic";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editLogistic(@Valid @ModelAttribute("logistic") Logistic logistic, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return logistics();
        }
        try {
            logisticsService.updateLogistics(logistic);
        } catch (EntityExistException e) {
            result.addError(new FieldError("logistic", "logisticName", logistic.getLogisticName(), true, null, null,e.getMessage()));
            return toEditLogistic(logistic.getLogisticId(),modelMap);
        }
        return "redirect:../common/success.do?reurl=logistic/logistics.do";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public boolean removeLogisticById(@RequestParam("logisticId") int logisticId) {
        try {
            logisticsService.removeLogisticById(logisticId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String updateLogisticStatusById(@RequestParam("enable") boolean enable, @RequestParam("logisticId") int logisticsId, HttpServletRequest request) {
        Logistic logistic = logisticsService.findById(logisticsId);
        logistic.setEnabled(enable);
        try {
            logisticsService.updateLogistics(logistic);
            return "redirect:../common/success.do?reurl=logistic/logistics.do";
        } catch (EntityExistException e) {
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=logistic/logistics.do";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<Logistic> findLogisticsByEnable(){
        return logisticsService.findByEnable(true);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public boolean enableLogisticById(@RequestParam("logisticId") int logisticId, @RequestParam("enabled") boolean enabled) {
        try {
            logisticsService.enableLogisticById(logisticId, enabled);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
