package com.yunxin.cb.mall.web.action.operation;

import com.yunxin.cb.mall.entity.SpecFilter;
import com.yunxin.cb.mall.service.ISpecFilterService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/operation")
public class SpecFilterController {

    @Resource
    private ISpecFilterService iSpecFilterService;

    @GetMapping(value = "specFilters")
    public String specFilters() {
        return "operation/specFilters";
    }

    @RequestMapping(value = "pageSpecFilters", method = RequestMethod.POST)
    @ResponseBody
    public Page<SpecFilter> pageSpecFilters(@RequestBody PageSpecification<SpecFilter> query) {
        Page<SpecFilter> pageFilterItem = iSpecFilterService.pageSpecFilters(query);
        return pageFilterItem;
    }

    @RequestMapping(value = "toAddSpecFilter", method = RequestMethod.GET)
    public String toAddSpecFilter(@ModelAttribute("filter") SpecFilter specFilter, ModelMap modelMap) {
        return "operation/addSpecFilter";
    }

    @RequestMapping(value = "addSpecFilter", method = RequestMethod.POST)
    public String addSpecFilter(@ModelAttribute("filter") SpecFilter specFilter, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return toAddSpecFilter(specFilter, modelMap);
        }
        try {
            iSpecFilterService.addSpecFilter(specFilter);
            return "redirect:../common/success.do?reurl=operation/specFilters.do";
        } catch (Exception e) {
            return "redirect:../common/simplefailure.do?reurl=operation/specFilters.do";
        }
    }

    @RequestMapping(value = "toEditSpecFilter", method = RequestMethod.GET)
    public String toEditSpecFilter(@RequestParam("filterId") Integer filterId, ModelMap modelMap) {
        SpecFilter specFilter = iSpecFilterService.getSpecFilterById(filterId);
        modelMap.addAttribute("filter", specFilter);
        return toEditSpecFilter(modelMap);
    }

    @RequestMapping(value = "specFilterDetail", method = RequestMethod.GET)
    public String specFilterDetail(@RequestParam("filterId") Integer filterId, ModelMap modelMap) {
        SpecFilter specFilter = iSpecFilterService.getSpecFilterById(filterId);
        modelMap.addAttribute("filter", specFilter);
        return "operation/specFilterDetail";
    }


    private String toEditSpecFilter(ModelMap modelMap) {
        return "operation/editSpecFilter";
    }

    @RequestMapping(value = "editSpecFilter", method = RequestMethod.POST)
    public String editSpecFilter(@ModelAttribute("filter") SpecFilter specFilter, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return toEditSpecFilter(modelMap);
        }
        try {
            iSpecFilterService.updateSpecFilter(specFilter);
            return "redirect:../common/success.do?reurl=operation/specFilters.do";
        } catch (Exception e) {
            return "redirect:../common/simplefailure.do?reurl=operation/specFilters.do";
        }
    }


    @RequestMapping(value = "removeSpecFilterById", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeSpecFilterById(@RequestParam("filterId") Integer filterId) {
        try {
            iSpecFilterService.removeSpecFilterById(filterId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
