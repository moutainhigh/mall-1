package com.yunxin.cb.mall.web.action.logistics;

import com.yunxin.cb.config.ProvinceConfig;
import com.yunxin.cb.mall.entity.Logistic;
import com.yunxin.cb.mall.entity.LogisticPrice;
import com.yunxin.cb.mall.service.ILogisticsService;
import com.yunxin.cb.bean.Province;
import com.yunxin.cb.config.ProvinceConfig;
import com.yunxin.cb.mall.entity.Logistic;
import com.yunxin.cb.mall.entity.LogisticPrice;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.service.ILogisticsService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by sheh on 14-11-21.
 */
@Controller
@RequestMapping("/logistic")
public class LogisticPriceController {
    @Resource
    private ILogisticsService logisticsService;

    @Resource
    private MessageSource messageSource;

    @Resource
    private ProvinceConfig provinceConfig;


    @RequestMapping(method = RequestMethod.GET)
    public String logisticPrice(@RequestParam("logisticId") int logisticId, ModelMap modelMap) {
        Logistic logistic = logisticsService.findById(logisticId);
        modelMap.addAttribute("logistic", logistic);
        List<LogisticPrice> prices = logisticsService.findLogisticPricesByLogisticId(logisticId);
        modelMap.addAttribute("prices", prices);
        return "logistic/logisticPrice";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toAddLogisticPrice(@RequestParam("logisticId") int logisticId, @ModelAttribute("logisticPrice") LogisticPrice logisticPrice, ModelMap modelMap) {
        logisticPrice.setLogistic(new Logistic(logisticId));
        List<Province> provinces = provinceConfig.getProvinces();
        modelMap.addAttribute("provinces", provinces);
        return toAddLogisticPrice(logisticPrice);
    }

    public String toAddLogisticPrice(LogisticPrice logisticPrice) {
        return "logistic/addLogisticPrice";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addLogisticPrice(@Valid @ModelAttribute("logisticPrice") LogisticPrice logisticPrice, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return toAddLogisticPrice(logisticPrice);
        }
        try {
            logisticsService.addLogisticPrice(logisticPrice);
            return "redirect:../common/success.do?reurl=logistic/logisticPrice.do?logisticId=" + logisticPrice.getLogistic().getLogisticId();
        } catch (EntityExistException e) {
            e.printStackTrace();
            return "redirect:../common/failure.do?reurl=logistic/logisticPrice.do?logisticId=" + logisticPrice.getLogistic().getLogisticId();
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public String toEditLogisticPrice(@RequestParam("priceId") int priceId,@RequestParam("logisticId") int logisticId, ModelMap modelMap) {
        LogisticPrice logisticPrice = logisticsService.getLogisticPriceById(priceId);

        logisticPrice.setLogistic(new Logistic(logisticId));
        modelMap.addAttribute("logisticPrice", logisticPrice);
        List<Province> provinces = provinceConfig.getProvinces();
        modelMap.addAttribute("provinces", provinces);
        return toEditLogisticPrice(logisticPrice);
    }

    private String toEditLogisticPrice(LogisticPrice logisticPrice) {
        return "logistic/editLogisticPrice";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editLogisticPrice(@Valid @ModelAttribute("logisticPrice") LogisticPrice logisticPrice, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return toEditLogisticPrice(logisticPrice);
        }
        int logisticId= logisticPrice.getLogistic().getLogisticId();
        try {
            logisticsService.updateLogisticPrice(logisticPrice);
            return "redirect:../common/success.do?reurl=logistic/logisticPrice.do?logisticId=" + logisticId;
        } catch (EntityExistException e) {
            return "redirect:../common/failure.do?reurl=logistic/logisticPrice.do?logisticId=" + logisticId;
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public boolean removeLogisticPriceById(@RequestParam("priceId") int priceId) {
        try {
            logisticsService.removeLogisticPrice(priceId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
