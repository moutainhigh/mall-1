package com.yunxin.cb.mall.web.action.security;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Seller;
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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author z001075
 */

@Controller
@RequestMapping(value = "/security")
@SessionAttributes(SecurityConstants.LOGIN_SELLER)
public class UserController {

    @Resource
    private ISecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public String users(ModelMap modelMap) {
        return "security/users";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<User> pageUsers(@RequestBody PageSpecification<User> query, HttpSession session) {
        User user =(User)session.getAttribute(SecurityConstants.LOGIN_SESSION);
        Seller seller =(Seller)session.getAttribute(SecurityConstants.LOGIN_SELLER);
        boolean flag=false;
        for (Role role : user.getRoles()) {
            if(role.getRoleCode().equals("SUPER_ROLE")){
                flag=true;
                break;
            }
        }
        Page<User> page =null;
        if(flag){
            page = securityService.pageUsers(query);
        }else{
            PageSpecification.FilterDescriptor filterDescriptor = new PageSpecification.FilterDescriptor();
            filterDescriptor.setLogic("and");
            PageSpecification.FilterDescriptor filterDescriptor1 = new PageSpecification.FilterDescriptor();
            filterDescriptor1.setField("seller.sellerId");
            filterDescriptor1.setLogic("and");
            filterDescriptor1.setOperator("eq");
            filterDescriptor1.setValue(seller.getSellerId());
            filterDescriptor.getFilters().add(filterDescriptor1);
            query.setFilter(filterDescriptor);
            page = securityService.pageUsers(query);
        }
        return page;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toAddUser(@ModelAttribute("user") User user,@ModelAttribute(SecurityConstants.LOGIN_SELLER) Seller seller,ModelMap modelMap) {
        modelMap.addAttribute("roles",securityService.getRolesBySeller(seller));
        return "security/addUser";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user,BindingResult result, HttpSession session,ModelMap modelMap) {
        Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
        user.setSeller(seller);
        try {
            securityService.addUser(user);
        } catch (EntityExistException e) {
            result.addError(new FieldError("user", "userName", user.getUserName(), true, null, null,e.getMessage()));
            return toAddUser(user,seller,modelMap);
        }
        return "redirect:../common/success.do?reurl=security/users.do";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toEditUser(@RequestParam("userId") int userId, @ModelAttribute(SecurityConstants.LOGIN_SELLER) Seller seller,ModelMap modelMap) {
        modelMap.addAttribute("roles",securityService.getRolesBySeller(seller));
        User user = securityService.getUserById(userId);
        modelMap.addAttribute("user", user);
        return "security/editUser";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") User user,BindingResult result, HttpSession session,ModelMap modelMap) {
        Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
        user.setSeller(seller);
        try {
            securityService.updateUser(user);
        } catch (EntityExistException e) {
            result.addError(new FieldError("user", "userName", user.getUserName(), true, null, null,e.getMessage()));
            return toEditUser(user.getUserId(), seller, modelMap);
        }
        return "redirect:../common/success.do?reurl=security/users.do";
    }

    /**
     * 启用、禁用用户
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public int enableUserById(@RequestParam("enabled") boolean enabled, @RequestParam("userId") int userId) {
        securityService.enabledUserById(enabled, userId);
        return userId;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public boolean removeUserById(@RequestParam("userId") int userId) {
        try{
            securityService.removeUserById(userId);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("userId") int userId, @RequestParam("password") String password, HttpServletRequest request) {
        try {
            User user = securityService.changePassword(userId, password);
            return "redirect:../common/success.do?reurl=security/users.do";
        } catch (InvalidKeyException | NoSuchPaddingException
                | NoSuchAlgorithmException | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
            return "redirect:../common/simplefailure.do?reurl=security/users.do";
        }
    }


}