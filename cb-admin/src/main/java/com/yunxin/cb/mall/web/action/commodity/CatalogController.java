package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.mall.web.util.BuildCommonURL;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/commodity")
public class CatalogController {

    @Resource
    private ICatalogService catalogService;

    @Resource
    private MessageSource messageSource;


    @RequestMapping(method = RequestMethod.GET)
    public String catalogs(ModelMap modelMap, HttpServletRequest request) {
        List<Catalog> catalogs = catalogService.getAllCatalogs();
        catalogs.stream().forEach(p -> {
            if (p.getCatalogId() == p.getParentCatalog().getCatalogId()) {
                p.setParentCatalog(null);
            }
            p.setSpecs(null);
            p.setCatalogAttributeGroups(null);
            p.setCatalogs(null);
        });
        modelMap.addAttribute("catalogs", catalogs);
        return "commodity/catalogs";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toAddCatalog(@ModelAttribute("catalog") Catalog catalog, ModelMap modelMap) {
        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        return "commodity/addCatalog";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addCatalog(@Valid @ModelAttribute("catalog") Catalog catalog,
                             BindingResult result, ModelMap modelMap, HttpServletRequest request, Locale locale) {
        if (result.hasErrors()) {
            return toAddCatalog(catalog, modelMap);
        }
        try {
            catalogService.addCatalog(catalog);
        } catch (EntityExistException e) {
            e.printStackTrace();
            result.addError(new FieldError("catalog", "catalogCode", catalog.getCatalogCode(), true, null, null,
                    messageSource.getMessage(e.getMessage(), null, locale)));
            return BuildCommonURL.buildFailureURL("commodity/toAddCatalog.do", "新增商品分类失败", e.getMessage());
        } catch (CommonException e) {
            e.printStackTrace();
            result.addError(new FieldError("catalog", "catalogCode", catalog.getCatalogCode(), true, null, null,
                    messageSource.getMessage(e.getMessage(), null, locale)));
            return BuildCommonURL.buildFailureURL("commodity/toAddCatalog.do", "新增商品分类失败", e.getMessage());
        }
        return BuildCommonURL.buildSuccessURL("commodity/catalogs.do");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toEditCatalog(@RequestParam("catalogId") int catalogId,
                                ModelMap modelMap, HttpServletRequest request) {
        Catalog catalog = catalogService.getCategoryById(catalogId);
        modelMap.addAttribute("catalog", catalog);
        return toEditCatalog(catalog, modelMap);
    }

    private String toEditCatalog(Catalog catalog, ModelMap modelMap) {
        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        return "commodity/editCatalog";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editCatalog(@Valid @ModelAttribute("catalog") Catalog catalog,
                              BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toEditCatalog(catalog, modelMap);
        }
        try {
            catalogService.updateCatalog(catalog);
        } catch (EntityExistException e) {
            e.printStackTrace();
            result.addError(new FieldError("catalog", "catalogCode", catalog.getCatalogCode(), true, null, null,
                    messageSource.getMessage(e.getMessage(), null, locale)));
            return BuildCommonURL.buildFailureURL("commodity/toEditCatalog.do?catalogId="+catalog.getCatalogId(), "修改商品分类失败", e.getMessage());
        }
        return BuildCommonURL.buildSuccessURL("commodity/catalogs.do");
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public boolean removeCatalogById(@RequestParam("catalogId") int catalogId) {
        try {
            catalogService.removeCategoryById(catalogId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String enableCatalogById(@RequestParam("catalogId") int catalogId, @RequestParam("enabled") boolean enabled) {
        try {
            catalogService.enableCatalogById(catalogId, enabled);
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }
}
