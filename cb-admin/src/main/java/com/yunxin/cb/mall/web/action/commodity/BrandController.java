package com.yunxin.cb.mall.web.action.commodity;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.entity.meta.AttachmentState;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.cb.mall.service.IBrandService;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author keymean
 */
@Controller
@RequestMapping(value = "/commodity")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class BrandController {

    @Resource
    private IBrandService brandService;
    @Resource
    private ICategoryService categoryService;
    @Resource
    private MessageSource messageSource;
    @Resource
    private IAttachmentService  attachmentService;

    @RequestMapping(value = "brands")
    public String brands(ModelMap modelMap) {
        return "commodity/brands";
    }

    /**
     * brand列表分页
     *
     * @param brandQuery
     * @return
     */
    @RequestMapping(value = "pageBrands", method = RequestMethod.POST)
    @ResponseBody
    public Page<Brand> pageBrands(@RequestBody PageSpecification<Brand> brandQuery) {
        return brandService.pageBrands(brandQuery);
    }

    /**
     *
     * @param brandQuery
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "choosePagedBrand",method = RequestMethod.POST)
    public Page<Brand> choosePagedCommodities(@RequestBody PageSpecification<Brand> brandQuery, HttpServletRequest request) {
        Page<Brand> page = brandService.pageBrands(brandQuery);
        return page;
    }
    /**
     * 跳转到brand新增页面
     *
     * @param brand
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "toAddBrand", method = RequestMethod.GET)
    public String toAddBrand(@ModelAttribute("brand") Brand brand, ModelMap modelMap) {
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "commodity/addBrand";
    }

    /**
     * 保存新增数据，上传图片
     *
     * @param brand
     * @return
     */
    @RequestMapping(value = "addBrand", method = RequestMethod.POST)
    public String addBrand(@ModelAttribute("brand") Brand brand,BindingResult result,ModelMap modelMap,Locale locale,HttpServletRequest request) {
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                brand.setPicPath(imgurl[0].split(",")[0]);
                brandService.addBrand(brand);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.BRAND,brand.getBrandId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.BRAND,brand.getBrandId(),imgpath);
                }
            }
        } catch (EntityExistException e) {
            result.addError(new FieldError("brand", "brandName", brand.getBrandEnName(), true, null, null,
                    messageSource.getMessage("brand_brandName_repeat", null, locale)));
            return toAddBrand(brand, modelMap);
        }
        return "redirect:../common/success.do?reurl=commodity/brands.do";
    }

    /**
     * 跳转到品牌修改页面
     *
     * @param brandId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "toEditBrand", method = RequestMethod.GET)
    public String toEditBrand(@RequestParam("brandId") int brandId,ModelMap modelMap) {
        Brand brand = brandService.getBrandById(brandId);
        modelMap.addAttribute("brand", brand);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.BRAND,brand.getBrandId());
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return "commodity/editBrand";
    }

    /**
     * 保存品牌修改后的数据，并跳转到品牌列表页面
     *
     * @param brand
     * @param request
     * @return
     */
    @RequestMapping(value = "editBrand", method = RequestMethod.POST)
    public String editBrand(@ModelAttribute("brand") Brand brand,BindingResult result,HttpServletRequest request,ModelMap modelMap,Locale locale) {
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                brand.setPicPath(imgurl[0].split(",")[0]);
                brandService.updateBrand(brand);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.BRAND,brand.getBrandId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.BRAND,brand.getBrandId(),imgpath);
                }
            }
        } catch (EntityExistException e) {
            result.addError(new FieldError("brand", "brandName", brand.getBrandEnName(), true, null, null,
                    messageSource.getMessage("brand_brandName_repeat", null, locale)));
            return toEditBrand(brand.getBrandId(), modelMap);
        }
        return "redirect:../common/success.do?reurl=commodity/brands.do";
    }

    /**
     * 根据品牌id删除品牌
     *
     * @param brandId
     * @return
     */
    @RequestMapping(value = "removeBrandById", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeBrandById(@RequestParam("brandId") int brandId) {
        try{
            brandService.removeBrandById(brandId);
            //把图片删除掉
            attachmentService.deleteAttachmentPictures(ObjectType.BRAND, brandId);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * 品牌详情
     *
     * @param brandId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "brandDetail", method = RequestMethod.GET)
    public String brandDetail(@RequestParam("brandId") int brandId, ModelMap modelMap) {
        modelMap.addAttribute("brand", brandService.getBrandById(brandId));
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.BRAND,brandId);
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return "commodity/brandDetail";
    }

    @RequestMapping(value = "enableBrandById", method = RequestMethod.GET)
    @ResponseBody
    public String enableBrandById(@RequestParam("brandId") int brandId, @RequestParam("enabled") boolean enabled) {
        try {
            brandService.enableBrandById(brandId, enabled);
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

    @RequestMapping(value = "checkBrandNoAndBrandName", method = RequestMethod.GET)
    @ResponseBody
    public Object checkBrandNoAndBrandName(@RequestParam("brandNo") String brandNo, @RequestParam("brandName") String brandName) {
        Brand brand = new Brand();
        brand.setBrandNo(brandNo);
        brand.setBrandName(brandName);
        String result = brandService.checkBrandNoAndBrandName(brand);
        return result;
    }
}
