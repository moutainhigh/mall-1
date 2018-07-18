package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterGroupService;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterService;
import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import com.yunxin.cb.insurance.service.IInsuranceProductService;
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
import java.util.*;

/**
* @Description:    事项控制器
* @Author:         likang
* @CreateDate:     2018/7/17 21:16
*/
@Controller
@RequestMapping(value = "/insuranceInformedMatter")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class InsuranceInformedMatterController {

    @Resource
    private IInsuranceInformedMatterService insuranceInformedMatterService;
    @Resource
    private IInsuranceInformedMatterGroupService insuranceInformedMatterGroupService;
    @Resource
    private IInsuranceProductService insuranceProductService;

    /**
     * 跳转到事项页面
     * @author      likang
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:17
     */
    @RequestMapping(value = "insuranceInformedMatters")
    public String insuranceInformedMatters(ModelMap modelMap) {
        return "insuranceinformedmatter/insuranceinformedmatters";
    }


    /**
     * 事项分页列表
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceInformedMatter>
     * @exception
     * @date        2018/7/17 21:17
     */
    @RequestMapping(value = "pageInsuranceInformedMatter",method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceInformedMatter> pageInsuranceInformedMatter(@RequestBody PageSpecification<InsuranceInformedMatter> query) {
        return insuranceInformedMatterService.pageInsuranceInformedMatter(query);
    }

    /**
     * 事项分页列表
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceInformedMatter>
     * @exception
     * @date        2018/7/17 21:17
     */
    @RequestMapping(value = "pageaddMatter",method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceInformedMatter> pageaddMatter(@RequestBody PageSpecification<InsuranceInformedMatter> query,@RequestParam("prodId") String prodId) {
//        List<InsuranceInformedMatter> list = insuranceInformedMatterService.pageaddMatter(query).getContent();
//        int pros=Integer.parseInt(prodId);
//        InsuranceProduct insuranceProduct = insuranceProductService.getInsuranceProductById(pros);
//        Set<InsuranceInformedMatter> insuranceInformedMatters = insuranceProduct.getInsuranceInformedMatters();
//        Set<Integer> setId=new HashSet<Integer>();
//        /**
//         * 筛选出已经存在的事项，(后期再优化)
//         */
//        for (InsuranceInformedMatter matters:insuranceInformedMatters) {
//            setId.add(matters.getMatterId());
//        }
//        for (InsuranceInformedMatter insuranceInformedMatter : list) {
//            if(!setId.contains(insuranceInformedMatter.getMatterId())){
//                list.remove(insuranceInformedMatter);
//            }
//        }
        return insuranceInformedMatterService.pageaddMatter(query);
    }

    /**
     * 跳转到事项添加页面
     * @author      likang
     * @param insuranceInformedMatter
    * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:18
     */
    @RequestMapping(value = "toAddMatter",method = RequestMethod.GET)
    public String toAddMatter(@ModelAttribute("InsuranceInformedMatter") InsuranceInformedMatter insuranceInformedMatter, ModelMap modelMap){
        modelMap.addAttribute("insuranceInformedMatter",insuranceInformedMatter);
        modelMap.addAttribute("groups",insuranceInformedMatterGroupService.findList(1));
        return "insuranceinformedmatter/addinsuranceinformedmatter";
    }

    /**
     * 跳转到事项修改页面
     * @author      likang
     * @param matterId
    * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:19
     */
    @RequestMapping(value = "toEditMatter",method = RequestMethod.GET)
    public String toEditMatter(@RequestParam("matterId") int matterId, ModelMap modelMap){
        InsuranceInformedMatter insuranceInformedMatter=insuranceInformedMatterService.getInsuranceInformedMatter(matterId);
        modelMap.addAttribute("insuranceInformedMatter",insuranceInformedMatter);
        modelMap.addAttribute("metterType",insuranceInformedMatter.getMatterType());
        if(null!=insuranceInformedMatter.getMatterGroup()){
            modelMap.addAttribute("groupId",insuranceInformedMatter.getMatterGroup().getGroupId());
        }else{
            modelMap.addAttribute("groupId",0);
        }
        modelMap.addAttribute("groups",insuranceInformedMatterGroupService.findList(1));
        return "insuranceinformedmatter/editinsuranceinformedmatter";
    }

    /**
     * 方法实现说明
     * @author      likang
     * @param insuranceInformedMatter
    * @param groupId
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:19
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
     * 修改事项
     * @author      likang
     * @param insuranceInformedMatter
    * @param groupId
    * @param matterType
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:21
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
     * 通过id删除事项
     * @author      likang
     * @param matterId
     * @return      boolean
     * @exception
     * @date        2018/7/17 21:22
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
