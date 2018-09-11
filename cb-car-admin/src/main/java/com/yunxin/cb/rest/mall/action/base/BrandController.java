package com.yunxin.cb.rest.mall.action.base;

import com.yunxin.cb.common.utils.PageSpecification;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.entity.CarBrand;
import com.yunxin.cb.mall.service.CarBrandService;
import com.yunxin.cb.rest.mall.action.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
* @Description:
* @Author:         likang
* @CreateDate:     2018/9/10 10:47
*/
@Controller
@RequestMapping("brand")
public class BrandController extends BaseController {


    @Resource
    private CarBrandService brandService;

    @RequestMapping("brands.do")
    public String brands(HttpServletRequest request, HttpServletResponse response) {
        return "brand/brands";
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public PageFinder<CarBrand> pageBrands(@RequestBody PageSpecification<CarBrand> ps) {
        Query q = new Query(ps.getPage(),ps.getPageSize());
        PageFinder<CarBrand> page =  brandService.getCarBrandPagedList(q);
        return page;
    }

    @RequestMapping("addPage.do")
    public String addPage(CarBrand carBrand, ModelMap modelMap) {
        modelMap.addAttribute("carBrand", carBrand);
        return "brand/addbrand";
    }

    @RequestMapping(value = "addBrand", method = RequestMethod.POST)
    public String addBrand(@ModelAttribute("brand") CarBrand carBrand, BindingResult result, ModelMap modelMap, Locale locale, HttpServletRequest request) {
//            String[] imgurl = request.getParameterValues("imgurl");
//            if(imgurl.length>0){
//                brand.setPicPath(imgurl[0].split(",")[0]);
//                brandService.addBrand(brand);
//                //保存图片路径
//                attachmentService.deleteAttachmentPictures(ObjectType.BRAND,brand.getBrandId());
//                for (String imgpath:imgurl) {
//                    attachmentService.addAttachmentPictures(ObjectType.BRAND,brand.getBrandId(),imgpath);
//                }
//            }
        try {
            carBrand.setBrandNo("123");
            brandService.addCarBrand(carBrand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:../common/success.do?reurl=brand/brands.do";
    }
}
