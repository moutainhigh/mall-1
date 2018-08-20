package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.Spec;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author z001075
 */
@Controller
@RequestMapping(value = "/commodity")
public class SpecController {

    private static Logger logger = LoggerFactory.getLogger(SpecController.class);

    @Resource
    private ICommodityService commodityService;

    @Resource
    private ICatalogService catalogService;

    @RequestMapping(value = "catalogSpecs",method = RequestMethod.GET)
    public String catalogSpecs(@RequestParam("catalogId") int catalogId, @ModelAttribute("spec") Spec spec, ModelMap modelMap) {
        Catalog catalog = catalogService.getCategoryById(catalogId);
        modelMap.addAttribute("catalog", catalog);
        spec.setCatalog(catalog);
        List<Spec> specs = commodityService.getSpecsByCatalogId(catalogId);
        modelMap.addAttribute("specs", specs);
        TreeViewItem catalogItem = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogItem", catalogItem);

        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        return "commodity/catalogSpecs";
    }

    @RequestMapping(value = "addSpec",method = RequestMethod.POST)
    public String addSpec(@Valid @ModelAttribute("spec") Spec spec, BindingResult result, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return catalogSpecs(spec.getCatalog().getCatalogId(), spec, modelMap);
        }
        try {
            spec = commodityService.addSpec(spec);
        } catch (EntityExistException e) {
            logger.error("商品规格名称已存在",e);
            redirectAttributes.addFlashAttribute("msgTitle","商品规格名称已存在，添加失败！");
            redirectAttributes.addFlashAttribute("msgContent",e.getMessage());
            return "redirect:../common/failure.do?reurl=commodity/catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId();
        }
        return "redirect:catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId();
    }

    @RequestMapping(value = "editSpec",method = RequestMethod.POST)
    public String editSpec(@ModelAttribute("spec") Spec spec, BindingResult result, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        try {
            spec = commodityService.updateSpec(spec);
        } catch (EntityExistException e) {
            logger.error("商品规格名称已存在",e);
            redirectAttributes.addFlashAttribute("msgTitle","商品规格名称已存在，修改失败！");
            return "redirect:../common/failure.do?reurl=commodity/catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId();
        }
        return "redirect:catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId();
    }

    @RequestMapping(value = "cloneSpec",method = RequestMethod.POST)
    public String cloneSpec(@RequestParam("cloneCatalogId") int cloneCatalogId, @RequestParam("catalogId") int catalogId) {
        commodityService.cloneSpec(cloneCatalogId, catalogId);
        return "redirect:catalogSpecs.do?catalogId=" + catalogId;
    }

    @ResponseBody
    @RequestMapping(value = "getSpecsByCatalogId",method = RequestMethod.GET)
    public List<Spec> getSpecsByCatalogId(@RequestParam("catalogId") int catalogId) {
        return commodityService.getSpecsByCatalogId(catalogId);
    }

    @ResponseBody
    @RequestMapping(value = "removeSpecById",method = RequestMethod.GET)
    public boolean removeSpecById(@RequestParam("specId") int specId) {
        try {
            int result=commodityService.removeSpecById(specId);
            return result>0?true:false;
        } catch (Exception e) {
            return false;
        }
    }


}
