package com.yunxin.cb.mall.web.action.operation;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.util.ImageConverter;
import com.yunxin.cb.mall.entity.Category;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.mall.web.action.MediaPather;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/operation")
public class CategoryController implements ServletContextAware {

    @Resource
    private ICategoryService categoryService;

    @Resource
    private MessageSource messageSource;

    private ServletContext servletContext;


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "categories",method = RequestMethod.GET)
    public String categories(ModelMap modelMap, HttpServletRequest request) {
        List<Category> categories = categoryService.getAllCategories();
        categories.stream().forEach(p -> {
            if (p.getCategoryId() == p.getParentCategory().getCategoryId()) {
                p.setParentCategory(null);
            }
            p.setCategories(null);
            p.setActivities(null);
            p.setCommodityCategories(null);
            p.setPropertyFilters(null);
            p.setBrands(null);
        });
        modelMap.addAttribute("categories", categories);
        return "operation/categories";
    }

    @RequestMapping(value = "toAddCategory",method = RequestMethod.GET)
    public String toAddCategory(@ModelAttribute("category") Category category, ModelMap modelMap) {
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "operation/addCategory";
    }

    @RequestMapping(value = "addCategory",method = RequestMethod.POST)
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Locale locale,  ModelMap modelMap) {
        if (result.hasErrors()) {
            return toAddCategory(category, modelMap);
        }
        try {
            Category categoryDb = categoryService.addCategory(category);
            CommonImageConverter imageConverter = new CommonImageConverter(servletContext, category);
            imageConverter.compress();
            if(null!=imageConverter.getDefaultImagePath()){
                categoryService.updateIconPath(categoryDb.getCategoryId(), imageConverter.getDefaultImagePath());
            }
        } catch (EntityExistException e) {
            e.printStackTrace();
            result.addError(new FieldError("category", "categoryName", category.getCategoryName(), true, null, null,
                    messageSource.getMessage(e.getMessage(), null, locale)));
        }
        return "redirect:../common/success.do?reurl=operation/categories.do";
    }

    @RequestMapping(value = "toEditCategory",method = RequestMethod.GET)
    public String toEditCategory(@RequestParam("categoryId") int categoryId, ModelMap modelMap) {
        Category category = categoryService.getCategoryById(categoryId);
        modelMap.addAttribute("category", category);
        return toEditCategory(modelMap);
    }

    public String toEditCategory(ModelMap modelMap) {
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "operation/editCategory";
    }

    @RequestMapping(value = "editCategory",method = RequestMethod.POST)
    public String editCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,Locale locale,
                               ModelMap modelMap) {
        if (result.hasErrors()) {
            return toEditCategory(modelMap);
        }
        try {
            Category categoryDb = categoryService.updateCategory(category);
            // 用户重新上传了图片，即图片路径发生改变，则需要压缩图片
            if(null != categoryDb && !(categoryDb.getIconPath().equals(category.getIconPath())) ){
                CommonImageConverter imageConverter = new CommonImageConverter(servletContext, category);
                imageConverter.compress();
                categoryService.updateIconPath(categoryDb.getCategoryId(), imageConverter.getDefaultImagePath());
            }
        } catch (EntityExistException e) {
            e.printStackTrace();
            result.addError(new FieldError("category", "categoryName", category.getCategoryName(), true, null, null,
                    messageSource.getMessage(e.getMessage(), null, locale)));
        }
        return "redirect:../common/success.do?reurl=operation/categories.do";
    }

    @RequestMapping(value = "getCategoryById", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategoryById(@RequestParam("categoryId") int categoryId) {
        Category catalog = categoryService.getCategoryById(categoryId);
        return catalog;
    }


    @RequestMapping(value = "removeCategoryById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeCategoryById(@RequestParam("categoryId") int categoryId) {
        try {
            categoryService.removeCategoryById(categoryId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
