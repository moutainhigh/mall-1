package com.yunxin.cb.mall.web.action.operation;

import com.yunxin.cb.mall.entity.PropertyFilter;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.service.IFilterService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.PropertyFilter;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.service.IFilterService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/operation")
public class PropertyFilterController {


    @Resource
    private ICategoryService categoryService;


    @Resource
    private IFilterService filterService;


    @RequestMapping(  method = RequestMethod.GET)
    public String propertyFilters(ModelMap modelMap) {
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", categoryTree.getItems());
        return "operation/propertyFilters";
    }

    @RequestMapping(  method = RequestMethod.POST)
    @ResponseBody
    public Page<PropertyFilter> pagePropertyFilters(@RequestBody PageSpecification<PropertyFilter> query) {
        Page<PropertyFilter> pageFilterItem = filterService.pagePropertyFilters(query);
        return pageFilterItem;
    }

    @RequestMapping(  method = RequestMethod.GET)
    public String toAddPropertyFilter(@ModelAttribute("filter") PropertyFilter propertyFilter,ModelMap modelMap) {
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "operation/addPropertyFilter";
    }

    @RequestMapping( method = RequestMethod.POST)
    public String addPropertyFilter(@ModelAttribute("filter") PropertyFilter propertyFilter, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()){
            return toAddPropertyFilter(propertyFilter,modelMap);
        }
        try {
            filterService.addPropertyFileter(propertyFilter);
            return "redirect:../common/success.do?reurl=operation/propertyFilters.do";
        } catch (Exception e) {
            return "redirect:../common/simplefailure.do?reurl=operation/propertyFilters.do";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toEditPropertyFilter(@RequestParam("filterId") int filterId,  ModelMap modelMap) {
        PropertyFilter propertyFilter=filterService.getPropertyFilterById(filterId);
        modelMap.addAttribute("filter", propertyFilter);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return toEditPropertyFilter(modelMap);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String propertyFilterDetail(@RequestParam("filterId") int filterId,  ModelMap modelMap) {
        PropertyFilter propertyFilter=filterService.getPropertyFilterById(filterId);
        modelMap.addAttribute("filter", propertyFilter);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "operation/propertyFilterDetail";
    }


    private String toEditPropertyFilter(ModelMap modelMap) {
        return "operation/editPropertyFilter";
    }

    @RequestMapping( method = RequestMethod.POST)
    public String editPropertyFilter(@ModelAttribute("filter") PropertyFilter propertyFilter, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()){
            return toEditPropertyFilter(modelMap);
        }
        try {
            filterService.updatePropertyFilter(propertyFilter);
            return "redirect:../common/success.do?reurl=operation/propertyFilters.do";
        } catch (Exception e) {
            return "redirect:../common/simplefailure.do?reurl=operation/propertyFilters.do";
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public boolean removePropertyFilterById(@RequestParam("filterId") int filterId) {
        try{
            filterService.removePropertyFilterById(filterId);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
