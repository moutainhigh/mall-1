package com.yunxin.cb.mall.web.action.operation;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.Activity;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ActivityState;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.Activity;
import com.yunxin.cb.mall.entity.meta.ActivityState;
import com.yunxin.cb.mall.service.*;
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
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/operation")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class ActivityController implements ServletContextAware {

    @Resource
    private IActivityService activityService;
    @Resource
    private ICategoryService categoryService;

    @Resource
    private IRuleConditionService ruleConditionService;
    @Resource
    private IAttachmentService attachmentService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "activities",method = RequestMethod.GET)
    private String activities(ModelMap modelMap) {
        return "operation/activities";
    }

    @RequestMapping(value = "pageActivities",method = RequestMethod.POST)
    @ResponseBody
    public Page<Activity> pageActivities(@RequestBody PageSpecification activityQuery, ModelMap modelMap) {

        Page<Activity> activityPage =  activityService.pageActivities(activityQuery);
        return activityPage;
    }

    @RequestMapping(value = "toAddActivity",method = RequestMethod.GET)
    public String toAddActivity(@ModelAttribute("activity") Activity activity, ModelMap modelMap) {
        modelMap.put("activityRules", ruleConditionService.getRuleConditionsLikeCode("ACTIVITY_"));
        modelMap.addAttribute("categoryTree", categoryService.getAllCategories());
        return "operation/addActivity";
    }

    @RequestMapping(value = "addActivity",method = RequestMethod.POST)
    public String addActivity(@Valid @ModelAttribute("activity") Activity activity, BindingResult result, Locale locale, HttpServletRequest request) {
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                activity.setPicPath(imgurl[0].split(",")[0]);
                Activity activityDb = activityService.addActivity(activity);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.ACTIVITY,activityDb.getActivityId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.ACTIVITY,activityDb.getActivityId(),imgpath);
                }
            }

        } catch (EntityExistException e) {
            e.printStackTrace();
            result.addError(new FieldError("activity", "activityName", activity.getActivityName(), true, null, null,e.getMessage()));
        }
        return "redirect:../common/success.do?reurl=operation/activities.do";

    }


    @RequestMapping(value = "removeActivityById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeActivityById(@RequestParam("activityId") int activityId, HttpServletRequest request) {
        try {
            activityService.removeActivityById(activityId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping(value = "toEditActivity",method = RequestMethod.GET)
    public String toEditActivity(@RequestParam("activityId") int activityId, ModelMap modelMap) {
        modelMap.put("activityRules", ruleConditionService.getRuleConditionsLikeCode("ACTIVITY_"));
        modelMap.addAttribute("activity", activityService.findByActivityId(activityId));
        modelMap.addAttribute("categoryTree", categoryService.getAllCategories());
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.ACTIVITY,activityId);
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return "operation/editActivity";
    }

    @RequestMapping(value = "editActivity",method = RequestMethod.POST)
    public String editActivity(@Valid @ModelAttribute("activity") Activity activity, BindingResult result, Locale locale, HttpServletRequest request, ModelMap modelMap) {
        if (result.hasErrors()) {
            return toEditActivity(activity.getActivityId(), modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                activity.setPicPath(imgurl[0].split(",")[0]);
                Activity activityDb = activityService.updateActivity(activity);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.ACTIVITY,activityDb.getActivityId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.ACTIVITY,activityDb.getActivityId(),imgpath);
                }
            }
        } catch (EntityExistException e) {
            e.printStackTrace();
            result.addError(new FieldError("activity", "activityName", activity.getActivityName(), true, null, null,e.getMessage()));
        }
        return "redirect:../common/success.do?reurl=operation/activities.do";
    }


    @RequestMapping(value = "updateActivityStatus",method = RequestMethod.GET)
    @ResponseBody
    public String updateActivityStatus(@RequestParam("activityId") int activityId,
                                       @RequestParam("status") ActivityState status, HttpServletRequest request) {
        activityService.updateActivityStatus(activityId, status);
        //增加系统日志
//		systemsService.log(request,"修改了活动"+activityService.findByActivityId(activityId).getActivityName()+"的活动状态！");
        return "success";
    }


    /**
     * 活动详情页面
     *
     * @param activityId
     * @param request
     * @return
     */
    @RequestMapping(value = "activityDetails", method = RequestMethod.GET)
    public String activityDetails(@RequestParam("activityId") int activityId, HttpServletRequest request, ModelMap modelMap) {
//        Activity activity = activityService.findByActivityId(activityId);
//        modelMap.addAttribute("activity",activity);
//		List<ActivityScope> activityScopes = activityService.findByActivityId(activityId);
//
//		modelMap.addAttribute("activityScopes",activityScopes);

//		ActivityRuleScope scope = activityScopes.get(0).getActivityRule().getScope();
//		if(scope.equals(ActivityRuleScope.COMMODITY)){
//                Map<Commodity,ActivityScope> scopeList = new HashMap<Commodity,ActivityScope>();
//                for(ActivityScope activityScope:activityScopes){
//                    Commodity commodity = commodityService.findByCommodity_CommodityId(activityScope.getActivityScopeValue());
//                    scopeList.put(commodity,activityScope);
//                }
//                modelMap.addAttribute("scopeList",scopeList);
//
//		}else if(scope.equals(ActivityRuleScope.BRAND)){
//			Map<Brand,Float> scopeList = new HashMap<Brand,Float>();
//			for(ActivityScope activityScope:activityScopes){
//				Brand brand = brandService.getBrandById(activityScope.getActivityScopeValue());
//				scopeList.put(brand,activityScope.getValue());
//			}
//			modelMap.addAttribute("scopeList",scopeList);
//
//		}else if(scope.equals(ActivityRuleScope.CATEGORG)){
//			Map<Catalog,Float> scopeList = new HashMap<Catalog,Float>();
//			for(ActivityScope activityScope:activityScopes){
//				Catalog category = catalogService.findOne(activityScope.getActivityScopeValue());
//				scopeList.put(category,activityScope.getValue());
//			}
//			modelMap.addAttribute("scopeList",scopeList);
//		}else if(scope.equals(ActivityRuleScope.ALL)) {
//            Map<String,Float> scopeList = new HashMap<String,Float>();
//            scopeList.put("全站",activityScopes.get(0).getValue());
//            modelMap.addAttribute("scopeList",scopeList);
//        } else if(scope.equals(ActivityRuleScope.PRICE)) {
//            ActivityScope activityScope = activityScopes.get(0);
//            Map<Commodity,ActivityScope> scopeList = new HashMap<Commodity,ActivityScope>();
//            List<Commodity> commodities = commodityService.findBySellPriceBetween(activityScope.getActivityScopeValue(),activityScope.getHighPrice());
//            for(Commodity commodity : commodities) {
//                scopeList.put(commodity,activityScope);
//            }
//            modelMap.addAttribute("scopeList",scopeList);
//        }
        return "operation/activityDetails";
    }


    @RequestMapping(value = "activityRules")
    private String activityRules(ModelMap modelMap) {
        return "operation/activityRules";
    }

    @ResponseBody
    @RequestMapping(value = "effectOrDiscontinueActivity",method = RequestMethod.GET)
    public boolean effectOrDiscontinueActivity(@RequestParam("activityId") int activityId, @RequestParam("activityState") ActivityState activityState) {
        try {
            return activityService.effectOrDiscontinueActivity(activityId, activityState);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "toActivityCommodities",method = RequestMethod.GET)
    public String toActivityCommodities(@RequestParam("activityId") int activityId, ModelMap modelMap) {
        modelMap.addAttribute("activity", activityService.findByActivityId(activityId));

        modelMap.addAttribute("activityCommodities", activityService.getActivityCommoditiesByActivityId(activityId));
        return "operation/activityCommoditys";
    }

    @RequestMapping(value = "addActivityCommodities",method = RequestMethod.POST)
    public String addActivityCommodities(@RequestParam("activityId") int activityId, @RequestParam("selectedCommodityId") int[] selectedCommodityId, @RequestParam("limitAmountSize") int[] limitAmountSize) {
        try {
            boolean flag = activityService.addActivityCommodities(activityId, selectedCommodityId, limitAmountSize);
            if (flag) {
                return "redirect:../common/success.do?reurl=operation/toActivityCommodities.do?activityId=" + activityId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:../common/failure.do?reurl=operation/toActivityCommodities.do?activityId=" + activityId;
    }
}
