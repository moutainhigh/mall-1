package com.yunxin.cb.mall.web.action.operation;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
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

    @Resource
    private IAttachmentService attachmentService;


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
    public String addCategory(@Valid @ModelAttribute("category") Category category,HttpServletRequest request, BindingResult result, Locale locale,  ModelMap modelMap) {
        if (result.hasErrors()) {
            return toAddCategory(category, modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                category.setIconPath(imgurl[0].split(",")[0]);
                Category categoryDb = categoryService.addCategory(category);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.CATEGORY,categoryDb.getCategoryId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.CATEGORY,categoryDb.getCategoryId(),imgpath);
                }
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
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.CATEGORY,categoryId);
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return toEditCategory(modelMap);
    }

    public String toEditCategory(ModelMap modelMap) {
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "operation/editCategory";
    }

    @RequestMapping(value = "editCategory",method = RequestMethod.POST)
    public String editCategory(@Valid @ModelAttribute("category") Category category,HttpServletRequest request, BindingResult result,Locale locale,
                               ModelMap modelMap) {
        if (result.hasErrors()) {
            return toEditCategory(modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                category.setIconPath(imgurl[0].split(",")[0]);
                Category categoryDb = categoryService.updateCategory(category);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.CATEGORY,categoryDb.getCategoryId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.CATEGORY,categoryDb.getCategoryId(),imgpath);
                }
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
