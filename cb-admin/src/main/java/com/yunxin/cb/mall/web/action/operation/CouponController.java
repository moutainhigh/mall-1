package com.yunxin.cb.mall.web.action.operation;

import com.yunxin.cb.mall.entity.Coupon;
import com.yunxin.cb.mall.entity.CouponSchema;
import com.yunxin.cb.mall.entity.meta.CouponState;
import com.yunxin.cb.mall.service.ICouponService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.cb.mall.entity.Coupon;
import com.yunxin.cb.mall.entity.CouponSchema;
import com.yunxin.cb.mall.entity.meta.CouponState;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.service.ICouponService;
import com.yunxin.cb.security.SecurityConstants;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import com.yunxin.cb.mall.entity.meta.CouponRule;
//import com.yunxin.cb.mall.entity.meta.CouponRuleScope;
//import com.yunxin.cb.mall.entity.meta.CouponType;

/**
 * Created by k001389 on 2015/2/3.
 */
@Controller
@RequestMapping(value = "/operation")
@SessionAttributes({ SecurityConstants.LOGIN_SESSION })
public class CouponController {

    @Resource
    private ICouponService couponService;

    @RequestMapping(value = "coupons")
    public String coupons(ModelMap modelMap) {
       return "operation/coupons";
    }

    @RequestMapping(value = "pageCoupons", method = RequestMethod.POST)
    @ResponseBody
    public Page<Coupon> pageCoupons(ModelMap modelMap, PageSpecification<Coupon> query) {
        return couponService.pageCoupons(query);
    }

    @RequestMapping(value = "changeStatus", method = RequestMethod.GET)
    @ResponseBody
    public String changeStatus(@RequestParam("status")CouponState status, @RequestParam("couponId")int couponId) {
        return couponService.changeStatus(status,couponId);
    }

    @RequestMapping(value = "toAddCoupon",method = RequestMethod.GET)
    public String toAddCoupon(@ModelAttribute("coupon") Coupon coupon,ModelMap modelMap) {
//        modelMap.addAttribute("couponRules", CouponRule.values());
//        modelMap.addAttribute("couponTypes", CouponType.values());
//        modelMap.addAttribute("couponRuleScopes", CouponRuleScope.values());
        return "operation/addCoupon";
    }

    @RequestMapping(value="addCoupon",method= RequestMethod.POST)
    public String addCoupon(@ModelAttribute("coupon") Coupon coupon,HttpSession session)  {

        return "redirect:../common/success.do?reurl=operation/coupons.do";

    }


    @RequestMapping(value="couponDetails",method= RequestMethod.GET)
    public String couponDetails(@RequestParam("couponId")int couponId,ModelMap modelMap) {
        Coupon coupon = couponService.findCouponById(couponId);
        if(LogicUtils.isNotNull(coupon)) {
//            List<CouponScopeVo> couponScopeVos = couponService.getCouponScopeVoBycouponIdAndScope(couponId,coupon.getCouponRuleScope());
//            modelMap.addAttribute("coupon",coupon);
//            modelMap.addAttribute("couponScopeVos",couponScopeVos);
        }
//        Map<Coupon,List<CouponScopeVo>> couponListMap = couponService.getCouponScopeVoBycouponId(couponId);
//        modelMap.addAttribute("couponListMap",couponListMap);
        return "operation/couponDetails";
    }

    @RequestMapping(value = "removeCouponById",method = RequestMethod.GET)
    @ResponseBody
    public String removeCouponById(@RequestParam("couponId")int couponId,ModelMap modelMap) {
        return couponService.removeCouponById(couponId);
    }


    @RequestMapping(value = "couponSchemas")
    private String couponSchemas(ModelMap modelMap) {
        return "operation/couponSchemas";
    }

    @RequestMapping(value = "pageCouponSchemas", method = RequestMethod.POST)
    @ResponseBody
    public Page<CouponSchema> pageCouponSchemas(@RequestBody PageSpecification query, ModelMap modelMap) {

        return couponService.pageCouponSchemas(query);
    }

    @RequestMapping(value = "toAddCouponSchema")
    public String toAddActivity(@ModelAttribute("couponSchema") CouponSchema couponSchema,ModelMap modelMap) {
        return "operation/addCouponSchema";
    }

    @RequestMapping(value = "addCouponSchema", method = RequestMethod.POST)
    public String addCouponSchema(@ModelAttribute("couponSchema") CouponSchema couponSchema,HttpServletRequest request) {
        try {
            couponService.addCouponSchema(couponSchema);
        } catch (EntityExistException e) {
            e.printStackTrace();
        }
        return "redirect:../common/success.do?reurl=operation/couponSchemas.do";

    }

    @RequestMapping(value = "toEditCouponSchema")
    public String toEditCouponSchema(@RequestParam("schemaId") int schemaId,ModelMap modelMap) {
        modelMap.put("couponSchema",couponService.getCouponSchemaById(schemaId));
        return "operation/editCouponSchema";
    }

    @RequestMapping(value = "editCouponSchema", method = RequestMethod.POST)
    public String editCouponSchema(@ModelAttribute("couponSchema") CouponSchema couponSchema,HttpServletRequest request) {
        try {
            couponService.updateCouponSchema(couponSchema);
        } catch (EntityExistException e) {
            e.printStackTrace();
        }
        return "redirect:../common/success.do?reurl=operation/couponSchemas.do";

    }

    @RequestMapping(value = "removeCouponSchemaById", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeCouponSchemaById(@RequestParam("schemaId") int schemaId,HttpServletRequest request) {
        try{
            couponService.removeCouponSchemaById(schemaId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
