package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.PriceSection;
import com.yunxin.cb.mall.service.IPriceService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.PriceSection;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.service.IPriceService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by gonglei on 16/1/31.
 */
@Controller
@RequestMapping(value = "/commodity")
public class PriceSectionController {


    @Resource
    private IPriceService priceService;


    @RequestMapping(value = "priceSections")
    public String priceSection(ModelMap modelMap) {
        return "commodity/priceSections";
    }

    @RequestMapping(value = "pagePriceSections", method = RequestMethod.POST)
    @ResponseBody
    public Page<PriceSection> pagePriceSections(@RequestBody PageSpecification<PriceSection> query) {
        return priceService.pagePriceSections(query);
    }

    @RequestMapping(value = "toAddPriceSection", method = RequestMethod.GET)
    public String toAddPriceSection(@ModelAttribute("priceSection") PriceSection priceSection, ModelMap modelMap) {
        return "commodity/addPriceSection";
    }

    @RequestMapping(value = "addPriceSection", method = RequestMethod.POST)
    public String addPriceSection(@ModelAttribute("priceSection") PriceSection priceSection, ModelMap modelMap,BindingResult result) {
        try {
            priceService.addPriceSection(priceSection);
        } catch (EntityExistException e) {
            result.addError(new FieldError("priceSection", "endPrice", priceSection.getEndPrice(), true, null, null,"该价格段已存在"));
            return toAddPriceSection(priceSection, modelMap);
        }
        return "redirect:../common/success.do?reurl=commodity/priceSections.do";
    }

    @RequestMapping(value = "toEditPriceSection", method = RequestMethod.GET)
    public String toEditPriceSection(@RequestParam("sectionId") int sectionId, ModelMap modelMap) {
        modelMap.addAttribute("priceSection", priceService.getPriceSectionById(sectionId));
        return "commodity/editPriceSection";
    }

    @RequestMapping(value = "editPriceSection", method = RequestMethod.POST)
    public String editPriceSection(@Valid @ModelAttribute("priceSection") PriceSection priceSection, ModelMap modelMap, BindingResult result) {
        try {
            priceService.updatePriceSection(priceSection);
        } catch (EntityExistException e) {
            result.addError(new FieldError("priceSection", "endPrice", priceSection.getEndPrice(), true, null, null, "该价格段已存在"));
            return toEditPriceSection(priceSection.getSectionId(), modelMap);
        }
        return "redirect:../common/success.do?reurl=commodity/priceSections.do";
    }

    @RequestMapping(value = "removePriceSectionById", method = RequestMethod.GET)
    @ResponseBody
    public boolean removePriceSectionById(@RequestParam("sectionId") int sectionId) {
        try{
            priceService.removePriceSectionById(sectionId);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
