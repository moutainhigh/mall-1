package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.CatalogAttribute;
import com.yunxin.cb.mall.entity.CatalogAttributeGroup;
import com.yunxin.cb.mall.service.IAttributeService;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.ImageConverter;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.cb.mall.entity.CatalogAttribute;
import com.yunxin.cb.mall.entity.CatalogAttributeGroup;
import com.yunxin.cb.mall.service.IAttributeService;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.mall.web.action.MediaPather;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author z001075
 */
@Controller
@RequestMapping(value = "/commodity")
public class CatalogAttributeGroupController implements ServletContextAware {

    @Resource
    private IAttributeService attributeService;

    @Resource
    private ICatalogService catalogService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String catalogAttributeGroups(ModelMap modelMap) {
        TreeViewItem catalog = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogItem", catalog);
        return "commodity/catalogAttributeGroups";
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<CatalogAttributeGroup> pageCatalogAttributeGroups(@RequestBody PageSpecification<CatalogAttributeGroup> query) {
        Page<CatalogAttributeGroup> page = attributeService.pageCatalogAttributeGroups(query);
        return page;
    }

    @RequestMapping(value = "getAttributesByGroupId", method = RequestMethod.POST)
    @ResponseBody
    public List<CatalogAttribute> getAttributesByGroupId(@RequestBody PageSpecification pageRequest) {
        String groupId = (String) pageRequest.getData().get("groupId");
        return attributeService.findAttributeByGroupId(Integer.parseInt(groupId));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toAddCatalogAttributeGroup(@ModelAttribute("attributeGroup") CatalogAttributeGroup attributeGroup, BindingResult result, ModelMap modelMap) {
        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        return "commodity/addCatalogAttributeGroup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addCatalogAttributeGroup(@Valid @ModelAttribute("attributeGroup") CatalogAttributeGroup attributeGroup, BindingResult result, ModelMap modelMap) throws IOException {
        if (result.hasErrors()) {
            return toAddCatalogAttributeGroup(attributeGroup, result, modelMap);
        }
        try {
            if (attributeGroup.isShowAsImage()) {
                MediaPather.createPicSiteRealDir(servletContext, "attribute");
                String[] imagePath = attributeGroup.getImagePath();
                if (LogicUtils.isNotNullAndEmpty(imagePath)) {
                    for (int i = 0; i < imagePath.length; i++) {
                        String iPath = imagePath[i];
                        if (null != iPath) {
                            File imageFile = MediaPather.getPicStoreRealFile(servletContext, imagePath[i]);
                            ImageConverter imageConverter = new ImageConverter(imageFile);
                            imagePath[i] = "attribute/" + System.currentTimeMillis() + ".jpg";
                            imageConverter.compressJpg(50, 50, MediaPather.getPicSiteRealPath(servletContext, imagePath[i]));
                        }
                    }
                }
            }
            attributeService.addCatalogAttributeGroup(attributeGroup);
        } catch (EntityExistException e) {
            e.printStackTrace();
        }
        return "redirect:../common/success.do?reurl=commodity/catalogAttributeGroups.do";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String toEditCatalogAttributeGroup(@RequestParam("groupId") int groupId, ModelMap modelMap) {
        CatalogAttributeGroup attributeGroup = attributeService.getCatalogAttributeGroupById(groupId);
        modelMap.addAttribute("attributeGroup", attributeGroup);
        return toEditAttributeGroup(attributeGroup, modelMap);
    }

    private String toEditAttributeGroup(CatalogAttributeGroup attributeGroup, ModelMap modelMap) {
        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        return "commodity/editCatalogAttributeGroup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editCatalogAttributeGroup(@Valid @ModelAttribute("attributeGroup") CatalogAttributeGroup attributeGroup, BindingResult result,
                                            ModelMap modelMap) throws IOException {
        if (result.hasErrors()) {
            return toEditAttributeGroup(attributeGroup, modelMap);
        }
        try {
            if (attributeGroup.isShowAsImage()) {
                MediaPather.createPicSiteRealDir(servletContext, "attribute");
                String[] imagePath = attributeGroup.getImagePath();
                if (LogicUtils.isNotNullAndEmpty(imagePath)) {
                    for (int i = 0; i < imagePath.length; i++) {
                        if (LogicUtils.isNotNullAndEmpty(imagePath[i])) {
                            if (!imagePath[i].startsWith("attribute")) {
                                File imageFile = MediaPather.getPicStoreRealFile(servletContext, imagePath[i]);
                                ImageConverter imageConverter = new ImageConverter(imageFile);
                                imagePath[i] = "attribute/" + System.currentTimeMillis() + ".jpg";
                                imageConverter.compressJpg(50, 50, MediaPather.getPicSiteRealPath(servletContext, imagePath[i]));
                            }
                        }
                    }
                }
            }
            attributeService.updateCatalogAttributeGroup(attributeGroup);
        } catch (EntityExistException e) {
            e.printStackTrace();
            return toEditAttributeGroup(attributeGroup, modelMap);
        }
        return "redirect:../common/success.do?reurl=commodity/catalogAttributeGroups.do";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String removeCatalogAttributeGroup(@RequestParam("groupId") int groupId) {
        try {
            List<CatalogAttribute> catalogAttributes = attributeService.findAttributeByGroupId(groupId);
            List<String> imagePaths = new ArrayList<>();
            for (CatalogAttribute catalogAttribute : catalogAttributes) {
                String imgDirectory = MediaPather.getPicStoreRealPath(servletContext, catalogAttribute.getImagePath());
                imagePaths.add(imgDirectory);
            }
            attributeService.removeCatalogAttributeGroupById(groupId);
            //待删除动作执行成功后删除文件
            for (String imgPath : imagePaths) {
                File imgFilePath = new File(imgPath);
                if (imgFilePath.exists()) {
                    imgFilePath.delete();
                }
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CatalogAttributeGroup> getCatalogAttributeGroupsByCatalogId(@RequestParam("catalogId") int catalogId) {
        List<CatalogAttributeGroup> attributeGroups = attributeService.getAttributeGroupsByCatalogId(catalogId);
        return attributeGroups;
    }


}

