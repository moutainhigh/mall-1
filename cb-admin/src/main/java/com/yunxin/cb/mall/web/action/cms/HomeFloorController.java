package com.yunxin.cb.mall.web.action.cms;

import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.ImageConverter;
import com.yunxin.cb.mall.entity.HomeFloor;
import com.yunxin.cb.mall.entity.meta.FloorLayout;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.service.IFloorService;
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
    private MessageSource messageSource;

    @Resource
    private ICategoryService categoryService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String homeFloors(ModelMap modelMap) {
        return "cms/homeFloors";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<HomeFloor> pageHomeFloors(@RequestBody PageSpecification<HomeFloor> query) {
        Page<HomeFloor> page = floorService.pageServiceRulesForClean(query);
        return page;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toAddHomeFloor(@ModelAttribute("homeFloor") HomeFloor homeFloor, ModelMap modelMap) {
        homeFloor.setEnabled(true);
        homeFloor.setFloorLayout(FloorLayout.HORIZONTAL);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "cms/addHomeFloor";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addHomeFloor(@ModelAttribute HomeFloor homeFloor, HttpServletRequest request) {
        compressImage(homeFloor);
        compressIcon(homeFloor);
        floorService.addHomeFloor(homeFloor);
        return "redirect:../common/success.do?reurl=cms/homeFloors.do";
    }

    private void compressImage(HomeFloor homeFloor) {
        try {
            MediaPather.createPicSiteRealDir(servletContext, "homeFloor");

            String imagePath = homeFloor.getImagePath();
            File imageFile = MediaPather.getPicStoreRealFile(servletContext, imagePath);
            ImageConverter imageConverter = new ImageConverter(imageFile);
            imagePath = "homeFloor/" + System.currentTimeMillis() + ".jpg";
            if (homeFloor.getFloorLayout() == FloorLayout.HORIZONTAL) {
                imageConverter.compressJpg(1270, 270, MediaPather.getPicSiteRealPath(servletContext, imagePath));
            } else {
                imageConverter.compressJpg(423, 611, MediaPather.getPicSiteRealPath(servletContext, imagePath));
            }
            homeFloor.setImagePath(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void compressIcon(HomeFloor homeFloor) {
        try {
            MediaPather.createPicSiteRealDir(servletContext, "homeFloor");
            String iconPath = homeFloor.getIconPath();
            File iconFile = MediaPather.getPicStoreRealFile(servletContext, iconPath);
            ImageConverter iconConverter = new ImageConverter(iconFile);
            iconPath = "homeFloor/" + System.currentTimeMillis() + ".png";
            iconConverter.compressPng(30, 30, MediaPather.getPicSiteRealPath(servletContext, iconPath));
            homeFloor.setIconPath(iconPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toEditHomeFloor(@RequestParam("floorId") int floorId, ModelMap modelMap, Locale locale) {
        HomeFloor homeFloor = floorService.getHomeFloorFetchAllById(floorId);
        modelMap.addAttribute("homeFloor", homeFloor);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "cms/editHomeFloor";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editHomeFloor(@Valid @ModelAttribute("homeFloor") HomeFloor homeFloor,
                                BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toEditHomeFloor(homeFloor.getFloorId(), modelMap, locale);
        }
        HomeFloor homeFloor1 = floorService.getHomeFloorById(homeFloor.getFloorId());
        if (!homeFloor1.getIconPath().equals(homeFloor.getIconPath())) {
            compressIcon(homeFloor);
        }
        if (!homeFloor1.getImagePath().equals(homeFloor.getImagePath())) {
            compressImage(homeFloor);
        }
        try {
            homeFloor1 = floorService.updateHomeFloor(homeFloor);
        } catch (EntityExistException e) {
            result.addError(new FieldError("homeFloor", "floorName", homeFloor.getFloorName(), true, null, null,e.getMessage()));
            return toEditHomeFloor(homeFloor.getFloorId(), modelMap, locale);
        }
        return "redirect:../common/success.do?reurl=cms/homeFloors.do";

    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public int removeHomeFloorById(@RequestParam("floorId") int floorId, HttpServletRequest request) {
        try {
            floorService.removeHomeFloorById(floorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return floorId;
    }

    @RequestMapping(method = RequestMethod.GET)
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
