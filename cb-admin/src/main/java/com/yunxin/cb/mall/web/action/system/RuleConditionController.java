package com.yunxin.cb.mall.web.action.system;

import com.yunxin.cb.mall.entity.RuleCondition;
import com.yunxin.cb.mall.service.IRuleConditionService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.RuleCondition;
import com.yunxin.cb.mall.service.IRuleConditionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by gonglei on 16/2/11.
 */
@Controller
@RequestMapping(value = "/system")
public class RuleConditionController {

    @Resource
    private IRuleConditionService ruleConditionService;

    @RequestMapping(value="ruleConditions")
    public String ruleConditions(ModelMap modelMap,HttpServletRequest request){
        return "system/ruleConditions";
    }

    @RequestMapping(value="pageRuleConditions",method= RequestMethod.POST)
    @ResponseBody
    public Page<RuleCondition> pageRuleConditions(@RequestBody PageSpecification<RuleCondition> query, HttpServletRequest request){
        return ruleConditionService.pageRuleConditions(query);

    }

    @RequestMapping(value = "toAddRuleCondition", method = RequestMethod.GET)
    public String toAddRuleCondition(@ModelAttribute("ruleCondition") RuleCondition ruleCondition, ModelMap modelMap) {
        return "system/addRuleCondition";
    }

    @RequestMapping(value = "addRuleCondition",method = RequestMethod.POST)
    public String addRuleCondition(@ModelAttribute("ruleCondition") RuleCondition ruleCondition, ModelMap modelMap,BindingResult result, Locale locale){
        try {
            ruleConditionService.addRuleCondition(ruleCondition);
        } catch (EntityExistException e) {
            result.addError(new FieldError("ruleCondition", "ruleCode", ruleCondition.getRuleCode(), true, null, null,
                    e.getMessage()));
            return toAddRuleCondition(ruleCondition, modelMap);
        }
        return "redirect:../common/success.do?reurl=system/ruleConditions.do";
    }

    @RequestMapping(value="toEditRuleCondition", method = RequestMethod.GET)
    public String toEditRuleCondition(@RequestParam("ruleId") int ruleId,ModelMap modelMap){
        RuleCondition ruleCondition=ruleConditionService.getRuleConditionById(ruleId);
        modelMap.addAttribute("ruleCondition",ruleCondition);
        return "system/editRuleCondition";
    }

    @RequestMapping(value = "editRuleCondition", method = RequestMethod.POST)
    public String editRuleCondition(@Valid @ModelAttribute("ruleCondition") RuleCondition ruleCondition,ModelMap modelMap,BindingResult result) {
        try {
            ruleConditionService.updateRuleCondition(ruleCondition);
        } catch (EntityExistException e) {
            result.addError(new FieldError("ruleCondition", "ruleCode", ruleCondition.getRuleCode(), true, null, null,
                    e.getMessage()));
            return toEditRuleCondition(ruleCondition.getRuleId(), modelMap);
        }
        return "redirect:../common/success.do?reurl=system/ruleConditions.do";
    }

    @RequestMapping(value = "removeRuleConditionById", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeRuleConditionById(@RequestParam("ruleId") int ruleId,HttpServletRequest request) {
        try{
            ruleConditionService.removeRuleConditionById(ruleId);
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
