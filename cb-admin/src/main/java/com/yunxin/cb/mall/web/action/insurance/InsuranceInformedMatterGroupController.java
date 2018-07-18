package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterGroupService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
* @Description:    事项组控制器
* @Author:         likang
* @CreateDate:     2018/7/17 21:09
*/
@Controller
@RequestMapping(value = "/insuranceInformedGroup")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class InsuranceInformedMatterGroupController {

    @Resource
    private IInsuranceInformedMatterGroupService insuranceInformedMatterGroupService;

    /**
     * 跳转到事项组页面
     * @author      likang
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:13
     */
    @RequestMapping(value = "insuranceInformedGroups")
    public String insuranceInformedGroups(ModelMap modelMap) {
        return "insuranceinformedgroup/insuranceInformedGroups";
    }

    /**
     * 事项组分页列表
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup>
     * @exception
     * @date        2018/7/17 21:13
     */
    @RequestMapping(value = "pageInsuranceInformedMatterGroup",method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceInformedMatterGroup> pageInsuranceInformedMatterGroup(@RequestBody PageSpecification<InsuranceInformedMatterGroup> query) {
        return insuranceInformedMatterGroupService.pageInsuranceInformedMatterGroup(query);
    }

    /**
     * 跳转到事项组添加页面
     * @author      likang
     * @param insuranceInformedMatterGroup
    * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:14
     */
    @RequestMapping(value = "toAddGroup",method = RequestMethod.GET)
    public String toAddGroup(@ModelAttribute("InsuranceInformedMatterGroup") InsuranceInformedMatterGroup insuranceInformedMatterGroup, ModelMap modelMap){
        modelMap.addAttribute("insuranceInformedMatterGroup",insuranceInformedMatterGroup);
        return "insuranceinformedgroup/addinsuranceInformedGroup";
    }

    /**
     * 跳转到事项组修改页面
     * @author      likang
     * @param groupId
    * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:14
     */
    @RequestMapping(value = "toEditGroup",method = RequestMethod.GET)
    public String toEditGroup(@RequestParam("groupId") int groupId, ModelMap modelMap){
        InsuranceInformedMatterGroup insuranceInformedMatterGroup=insuranceInformedMatterGroupService.getInsuranceInformedMatterGroup(groupId);
        modelMap.addAttribute("insuranceInformedMatterGroup",insuranceInformedMatterGroup);
        return "insuranceinformedgroup/editinsuranceInformedGroup";
    }

    /**
     * 添加事项组
     * @author      likang
     * @param insuranceInformedMatterGroup
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:15
     */
    @RequestMapping(value = "addinsuranceInformedMatterGroup",method = RequestMethod.POST)
    public String addinsuranceInformedMatterGroup(@ModelAttribute("InsuranceInformedMatterGroup") InsuranceInformedMatterGroup insuranceInformedMatterGroup) {
        insuranceInformedMatterGroup.setEnabled(0);
        insuranceInformedMatterGroup.setCreateTime(new Date());
        insuranceInformedMatterGroupService.addInsuranceInformedMatterGroup(insuranceInformedMatterGroup);
        return "redirect:../common/success.do?reurl=insuranceInformedMatterGroup/insuranceInformedMatterGroups.do";
    }

    /**
     * 更新事项组
     * @author      likang
     * @param insuranceInformedMatterGroup
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:15
     */
    @RequestMapping(value = "updateinsuranceInformedMatterGroup",method = RequestMethod.POST)
    public String updateinsuranceInformedMatterGroup(@ModelAttribute("InsuranceInformedMatterGroup") InsuranceInformedMatterGroup insuranceInformedMatterGroup) {
        insuranceInformedMatterGroupService.update(insuranceInformedMatterGroup);
        return "redirect:../common/success.do?reurl=insuranceInformedMatterGroup/insuranceInformedMatterGroups.do";
    }

    /**
     * 根据id删除事项组
     * @author      likang
     * @param groupId
     * @return      boolean
     * @exception
     * @date        2018/7/17 21:16
     */
    @RequestMapping(value = "removeById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeById(@RequestParam("groupId") int groupId) {
        try{
            insuranceInformedMatterGroupService.removeById(groupId);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
