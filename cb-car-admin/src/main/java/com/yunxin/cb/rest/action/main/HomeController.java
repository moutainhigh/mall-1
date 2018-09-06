package com.yunxin.cb.rest.action.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunxin.cb.mall.entity.UserInfo;
import com.yunxin.cb.mall.service.PermissionService;
import com.yunxin.cb.mall.service.UserInfoService;
import com.yunxin.cb.mall.service.UserRoleService;
import com.yunxin.cb.rest.common.BaseController;
import com.yunxin.cb.rest.common.ECMgtConstant;
import com.yunxin.cb.rest.common.SessionUtil;
import com.yunxin.cb.util.ECMd5Utils;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController extends BaseController {

    @Resource
    public UserInfoService userInfoService;
    @Resource
    public UserRoleService userRoleService;
    @Resource
    private PermissionService permissionService;

    @RequestMapping({ "/login" })
    public ModelAndView login() {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    @RequestMapping({ "/loginInfo" })
    @ResponseBody
    public String loginInfo(
            @RequestParam("loginName") String loginName,
            @RequestParam("loginPassword") String loginPassword,
            HttpServletRequest request, HttpServletResponse response,
            ModelMap modelMap) {
        if (loginPassword != null) {
            loginPassword = ECMd5Utils.crypt(loginPassword);
        }
        // 根据用户名查找用户是否存在
        UserInfo user = userInfoService.getSysUser(loginName,loginPassword);
        if (null!=user) {
            return "redirect:toLogin.do";
        }else {
            return "redirect:toLogin.do";
        }
    }

    @RequestMapping({ "index" })
    public String toIndexPage(HttpServletRequest request,
                              HttpServletResponse response, ModelMap modelMap) {
        UserInfo sysUser = getLoginSysUser();
        if (null != sysUser) {
            sysUser = userInfoService.getSysUser(sysUser.getUserName(),sysUser.getPassword());
            modelMap.put("sysUser", sysUser);
            List roleList = SessionUtil.getDataFromSession(request,
                    ECMgtConstant.SESSION_KEY_OF_LOGIN_SYS_ROLE, List.class);
            modelMap.put("sysRole", roleList);
            if (null == sysUser) {
                return "redirect:toLogin.do";
            }
        } else {
            return "redirect:toLogin.do";
        }
        return "index";
    }

}