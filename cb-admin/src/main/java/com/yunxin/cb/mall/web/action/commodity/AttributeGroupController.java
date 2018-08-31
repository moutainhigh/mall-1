package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.AttributeGroup;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.service.IAttributeService;
import com.yunxin.cb.mall.web.vo.ResponseResult;
import com.yunxin.cb.mall.web.vo.ResultType;
import com.yunxin.core.exception.EntityExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author z001075
 */
@Controller
@RequestMapping(value = "/commodity")
public class AttributeGroupController implements ServletContextAware {
    private Logger logger = LoggerFactory.getLogger(AttributeGroupController.class);
    @Resource
    private IAttributeService attributeService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @RequestMapping(value = "toAddAttributeGroup", method = RequestMethod.GET)
    public String toAddAttributeGroup(@RequestParam("commodityId") int commodityId, @ModelAttribute("attributeGroup") AttributeGroup attributeGroup, ModelMap modelMap) {
        attributeGroup.setCommodity(new Commodity(commodityId));
        return "commodity/addAttributeGroup";
    }

    @RequestMapping(value = "addAttributeGroup", method = RequestMethod.POST)
    public String addAttributeGroup(@Valid @ModelAttribute("attributeGroup") AttributeGroup attributeGroup, BindingResult result, ModelMap modelMap) throws IOException {
        if (result.hasErrors()) {
            return toAddAttributeGroup(attributeGroup.getCommodity().getCommodityId(), attributeGroup, modelMap);
        }
        try {
            attributeService.addAttributeGroup(attributeGroup);
        } catch (EntityExistException e) {
            e.printStackTrace();
        }
        return "redirect:editProducts.do?commodityId=" + attributeGroup.getCommodity().getCommodityId();
    }


    @RequestMapping(value = "toEditAttributeGroup", method = RequestMethod.GET)
    public String toEditAttributeGroup(@RequestParam("groupId") int groupId, ModelMap modelMap) {
        AttributeGroup attributeGroup = attributeService.getAttributeGroupById(groupId);
        modelMap.addAttribute("attributeGroup", attributeGroup);
        return toEditAttributeGroup(attributeGroup, modelMap);
    }

    private String toEditAttributeGroup(AttributeGroup attributeGroup, ModelMap modelMap) {
        return "commodity/editAttributeGroup";
    }

    @RequestMapping(value = "editAttributeGroup", method = RequestMethod.POST)
    public String editAttributeGroup(@Valid @ModelAttribute("attributeGroup") AttributeGroup attributeGroup, BindingResult result,
                                     ModelMap modelMap) throws IOException {
        if (result.hasErrors()) {
            return toEditAttributeGroup(attributeGroup, modelMap);
        }
        try {
            attributeService.updateAttributeGroup(attributeGroup);
        } catch (EntityExistException e) {
            e.printStackTrace();
        }
        return "redirect:editProducts.do?commodityId=" + attributeGroup.getCommodity().getCommodityId();
    }

    @RequestMapping(value = "removeAttributeGroupById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult removeAttributeGroupById(@RequestParam("groupId") int groupId) {
        ResponseResult responseResult = new ResponseResult();
        try {
            attributeService.removeAttributeGroupById(groupId);
            responseResult.setResultType(ResultType.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseResult.setResultType(ResultType.FAILURE);
        }
        return responseResult;
    }


}

