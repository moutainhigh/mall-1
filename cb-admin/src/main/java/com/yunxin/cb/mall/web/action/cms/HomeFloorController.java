package com.yunxin.cb.mall.web.action.cms;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.*;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.ImageConverter;
import com.yunxin.cb.mall.entity.HomeFloor;
import com.yunxin.cb.mall.entity.meta.FloorLayout;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.ImageConverter;
import com.yunxin.cb.mall.entity.HomeFloor;
import com.yunxin.cb.mall.entity.meta.FloorLayout;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.service.IFloorService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.mall.web.action.MediaPather;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
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
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

//import com.yunxin.cb.mall.entity.meta.FreeRepairCleanRecordType;

/**
 * @author k001389
 */
@Controller
@RequestMapping(value = "/cms")
public class HomeFloorController implements ServletContextAware {
    @Resource
    private IFloorService floorService;

    @Resource
    private ICategoryService categoryService;

    private ServletContext servletContext;

    @Resource
    private IAttachmentService attachmentService;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "homeFloors", method = RequestMethod.GET)
    public String homeFloors(ModelMap modelMap) {
        return "cms/homeFloors";
    }

    @RequestMapping(value = "pageHomeFloors", method = RequestMethod.POST)
    @ResponseBody
    public Page<HomeFloor> pageHomeFloors(@RequestBody PageSpecification<HomeFloor> query) {
        Page<HomeFloor> page = floorService.pageServiceRulesForClean(query);
        return page;
    }

    @RequestMapping(value = "toAddHomeFloor", method = RequestMethod.GET)
    public String toAddHomeFloor(@ModelAttribute("homeFloor") HomeFloor homeFloor, ModelMap modelMap) {
        homeFloor.setEnabled(true);
        homeFloor.setFloorLayout(FloorLayout.HORIZONTAL);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "cms/addHomeFloor";
    }

    @RequestMapping(value = "addHomeFloor", method = RequestMethod.POST)
    public String addHomeFloor(@ModelAttribute HomeFloor homeFloor, HttpServletRequest request) {
        String[] imgurl = request.getParameterValues("imgurl");
        String[] imgurl1 = request.getParameterValues("imgurl1");
        if(imgurl.length>0&&imgurl.length>0){
            homeFloor.setIconPath(imgurl[0].split(",")[0]);
            homeFloor.setImagePath(imgurl1[0].split(",")[0]);
            homeFloor=floorService.addHomeFloor(homeFloor);
            //保存图片路径
            attachmentService.deleteAttachmentPictures(ObjectType.HOMEFLOORICO,homeFloor.getFloorId());
            for (String imgpath:imgurl) {
                attachmentService.addAttachmentPictures(ObjectType.HOMEFLOORICO,homeFloor.getFloorId(),imgpath);
            }
            //保存图片路径
            attachmentService.deleteAttachmentPictures(ObjectType.HOMEFLOORPROPAGANDA,homeFloor.getFloorId());
            for (String imgpath:imgurl1) {
                attachmentService.addAttachmentPictures(ObjectType.HOMEFLOORPROPAGANDA,homeFloor.getFloorId(),imgpath);
            }
        }
        return "redirect:../common/success.do?reurl=cms/homeFloors.do";
    }


    @RequestMapping(value = "toEditHomeFloor", method = RequestMethod.GET)
    public String toEditHomeFloor(@RequestParam("floorId") int floorId, ModelMap modelMap, Locale locale) {
        HomeFloor homeFloor = floorService.getHomeFloorFetchAllById(floorId);
        modelMap.addAttribute("homeFloor", homeFloor);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.HOMEFLOORICO,floorId);
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        List<Attachment> listAttachment1=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.HOMEFLOORPROPAGANDA,floorId);
        modelMap.addAttribute("listAttachment1",JSON.toJSON(listAttachment1));
        return "cms/editHomeFloor";
    }

    @RequestMapping(value = "editHomeFloor", method = RequestMethod.POST)
    public String editHomeFloor(@Valid @ModelAttribute("homeFloor") HomeFloor homeFloor,HttpServletRequest request,
                                BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toEditHomeFloor(homeFloor.getFloorId(), modelMap, locale);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            String[] imgurl1 = request.getParameterValues("imgurl1");
            if(imgurl.length>0&&imgurl.length>0){
                homeFloor.setIconPath(imgurl[0].split(",")[0]);
                homeFloor.setImagePath(imgurl1[0].split(",")[0]);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.HOMEFLOORICO,homeFloor.getFloorId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.HOMEFLOORICO,homeFloor.getFloorId(),imgpath);
                }
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.HOMEFLOORPROPAGANDA,homeFloor.getFloorId());
                for (String imgpath:imgurl1) {
                    attachmentService.addAttachmentPictures(ObjectType.HOMEFLOORPROPAGANDA,homeFloor.getFloorId(),imgpath);
                }
            }
            HomeFloor homeFloor1 = floorService.updateHomeFloor(homeFloor);
        } catch (EntityExistException e) {
            result.addError(new FieldError("homeFloor", "floorName", homeFloor.getFloorName(), true, null, null,e.getMessage()));
            return toEditHomeFloor(homeFloor.getFloorId(), modelMap, locale);
        }
        return "redirect:../common/success.do?reurl=cms/homeFloors.do";

    }

    @RequestMapping(value = "removeHomeFloorById", method = RequestMethod.GET)
    @ResponseBody
    public int removeHomeFloorById(@RequestParam("floorId") int floorId, HttpServletRequest request) {
        try {
            attachmentService.deleteAttachmentPictures(ObjectType.HOMEFLOORICO,floorId);
            floorService.removeHomeFloorById(floorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return floorId;
    }

    @RequestMapping(value = "enableHomeFloorById", method = RequestMethod.GET)
    @ResponseBody
    public String enableHomeFloorById(@RequestParam("floorId") int floorId, @RequestParam("enabled") boolean enabled) {
        try {
            floorService.enableHomeFloorById(floorId, enabled);
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

}
