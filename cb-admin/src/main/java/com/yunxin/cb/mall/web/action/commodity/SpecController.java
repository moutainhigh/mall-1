package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.Spec;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.Spec;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author z001075
 */
@Controller
@RequestMapping(value = "/commodity")
public class SpecController {

    @Resource
    private ICommodityService commodityService;

    @Resource
    private ICatalogService catalogService;


    @Resource
    private MessageSource messageSource;


    @RequestMapping(method = RequestMethod.GET)
    public String catalogSpecs(@RequestParam("catalogId") int catalogId, @ModelAttribute("spec") Spec spec, ModelMap modelMap) {
        Catalog catalog = catalogService.getCategoryById(catalogId);
        modelMap.addAttribute("catalog", catalog);
        spec.setCatalog(catalog);
        List<Spec> specs = commodityService.getSpecsByCatalogId(catalogId);
        modelMap.addAttribute("specs", specs);
        TreeViewItem catalogItem = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogItem", catalogItem);
        return "commodity/catalogSpecs";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addSpec(@Valid @ModelAttribute("spec") Spec spec, BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return catalogSpecs(spec.getCatalog().getCatalogId(), spec, modelMap);
        }
        try {
            spec = commodityService.addSpec(spec);
        } catch (EntityExistException e) {
            e.printStackTrace();
            result.addError(new FieldError("spec", "specName", spec.getSpecName(), true, null, null,
                    messageSource.getMessage(e.getMessage(), null, locale)));
            return "redirect:../common/failure.do?reurl=commodity/catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId() + "&msgTitle=添加规格失败&msgContent=" + e.getMessage();
        }
        return "redirect:catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editSpec(@ModelAttribute("spec") Spec spec, BindingResult result, ModelMap modelMap) {
        try {
            spec = commodityService.updateSpec(spec);
        } catch (EntityExistException e) {
            e.printStackTrace();
        }
        return "redirect:catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Spec> getSpecsByCatalogId(@RequestParam("catalogId") int catalogId) {
        return commodityService.getSpecsByCatalogId(catalogId);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public boolean removeSpecById(@RequestParam("specId") int specId) {
        try {
            commodityService.removeSpecById(specId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
