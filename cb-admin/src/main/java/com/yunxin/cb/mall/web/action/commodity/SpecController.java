package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.Spec;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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


    @Resource
    private MessageSource messageSource;


    @RequestMapping(value = "catalogSpecs",method = RequestMethod.GET)
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

    @RequestMapping(value = "addSpec",method = RequestMethod.POST)
    public String addSpec(@Valid @ModelAttribute("spec") Spec spec, BindingResult result, ModelMap modelMap, HttpServletRequest request) {
        if (result.hasErrors()) {
            return catalogSpecs(spec.getCatalog().getCatalogId(), spec, modelMap);
        }
        try {
            spec = commodityService.addSpec(spec);
        } catch (EntityExistException e) {
            logger.error("添加商品规格名称错误",e);
            request.setAttribute("msgTitle","商品规格名称已存在，添加失败！");
            //return "redirect:../common/failure.do?reurl=commodity/catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId() + "&msgTitle=商品规格名称已存在，添加失败！&msgContent=" + e.getMessage();
            return "redirect:../common/failure.do?reurl=commodity/catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId() + "&msgContent=" + e.getMessage();
        }
        return "redirect:catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId();
    }

    @RequestMapping(value = "editSpec",method = RequestMethod.POST)
    public String editSpec(@ModelAttribute("spec") Spec spec, BindingResult result, ModelMap modelMap) {
        try {
            spec = commodityService.updateSpec(spec);
        } catch (EntityExistException e) {
            logger.error("修改商品规格名称错误",e);
        }
        return "redirect:catalogSpecs.do?catalogId=" + spec.getCatalog().getCatalogId();
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
            commodityService.removeSpecById(specId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
