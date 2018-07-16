package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterGroupService;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterService;
import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import com.yunxin.cb.mall.web.util.ExcelUtils;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Date;
import java.util.List;

/**
 * @author likang
 */
@Controller
@RequestMapping(value = "/insuranceInformedMatter")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class InsuranceInformedMatterController {

    @Resource
    private IInsuranceInformedMatterService insuranceInformedMatterService;
    @Resource
    private IInsuranceInformedMatterGroupService insuranceInformedMatterGroupService;

    @RequestMapping(value = "insuranceInformedMatters")
    public String insuranceordercodes(ModelMap modelMap) {
        return "insuranceinformedmatter/insuranceinformedmatters";
    }

    /**
     * InsuranceInformedMatter分页
     *
     * @param
     * @return
     */
    @RequestMapping(value = "pageInsuranceInformedMatter",method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceInformedMatter> pageInsuranceInformedMatter(@RequestBody PageSpecification<InsuranceInformedMatter> query) {
        return insuranceInformedMatterService.pageInsuranceInformedMatter(query);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "toAddMatter",method = RequestMethod.GET)
    public String toAddMatter(@ModelAttribute("InsuranceInformedMatter") InsuranceInformedMatter insuranceInformedMatter, ModelMap modelMap){
        modelMap.addAttribute("insuranceInformedMatter",insuranceInformedMatter);
        modelMap.addAttribute("groups",insuranceInformedMatterGroupService.findList(1));
        return "insuranceinformedmatter/addinsuranceinformedmatter";
    }

    /**
     *
     * @param matterId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "toEditMatter",method = RequestMethod.GET)
    public String toEditMatter(@RequestParam("matterId") int matterId, ModelMap modelMap){
        InsuranceInformedMatter insuranceInformedMatter=insuranceInformedMatterService.getInsuranceInformedMatter(matterId);
        modelMap.addAttribute("insuranceInformedMatter",insuranceInformedMatter);
        modelMap.addAttribute("metterType",insuranceInformedMatter.getMatterType());
        modelMap.addAttribute("groupId",insuranceInformedMatter.getMatterGroup().getGroupId());
        modelMap.addAttribute("groups",insuranceInformedMatterGroupService.findList(1));
        return "insuranceinformedmatter/editinsuranceinformedmatter";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "addInsuranceInformedMatter",method = RequestMethod.POST)
    public String addInsuranceInformedMatter(@ModelAttribute("insuranceInformedMatter") InsuranceInformedMatter insuranceInformedMatter,@ModelAttribute("groupId")int groupId) {
        insuranceInformedMatter.setCreateTime(new Date());
        if(groupId>0){
            InsuranceInformedMatterGroup group=new InsuranceInformedMatterGroup();
            group.setGroupId(groupId);
            insuranceInformedMatter.setMatterGroup(group);
        }
        insuranceInformedMatterService.addInsuranceInformedMatter(insuranceInformedMatter);
        return "redirect:../common/success.do?reurl=insuranceInformedMatter/insuranceInformedMatters.do";
    }

    /**
     *
     * @param insuranceInformedMatter
     * @return
     */
    @RequestMapping(value = "updateinsuranceInformedMatter",method = RequestMethod.POST)
    public String updateinsuranceInformedMatter(@ModelAttribute("insuranceInformedMatter") InsuranceInformedMatter insuranceInformedMatter,@ModelAttribute("groupId")int groupId,@ModelAttribute("matterType")int matterType) {
        if(groupId>0){
            InsuranceInformedMatterGroup group=new InsuranceInformedMatterGroup();
            group.setGroupId(groupId);
            insuranceInformedMatter.setMatterGroup(group);
        }
        insuranceInformedMatter.setMatterType(matterType);
        insuranceInformedMatterService.update(insuranceInformedMatter);
        return "redirect:../common/success.do?reurl=insuranceInformedMatter/insuranceInformedMatters.do";
    }

    /**
     *
     * @param matterId
     * @return
     */
    @RequestMapping(value = "removeById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeById(@RequestParam("matterId") int matterId) {
        try{
            insuranceInformedMatterService.removeById(matterId);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
