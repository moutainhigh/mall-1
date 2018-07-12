package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
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


    @RequestMapping(value = "insuranceInformedMatters")
    public String insuranceordercodes(ModelMap modelMap) {
        return "insuranceInformedMatter/insuranceInformedMatters";
    }

    /**
     * InsuranceInformedMatter分页
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceInformedMatter> pageInsuranceOrderCode(@RequestBody PageSpecification<InsuranceInformedMatter> query) {
        return insuranceInformedMatterService.pageInsuranceInformedMatter(query);
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toAdd(){
            return "insuranceInformedMatter/addmetter";
    }

    /**
     *
     * @param metterId
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toEdit(@RequestParam("metterId") int metterId, ModelMap modelMap){
        InsuranceInformedMatter insuranceInformedMatter=insuranceInformedMatterService.getInsuranceInformedMatter(metterId);
        modelMap.addAttribute("insuranceInformedMatter",insuranceInformedMatter);
        return "insuranceInformedMatter/editmetter";
    }
}
