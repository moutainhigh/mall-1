package com.yunxin.cb.mall.web.action.security;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.RoleResc;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.security.InvocationSecurityMetadataSource;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.RoleResc;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.security.InvocationSecurityMetadataSource;
import com.yunxin.cb.security.SecurityConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author x001393
 *
 */
@Controller
@RequestMapping(value="/security")
@SessionAttributes(SecurityConstants.LOGIN_SELLER)
public class RoleController  implements ServletContextAware {
	
	@Resource
	private ISecurityService securityService;

	private ServletContext servletContext;

	@Resource
	private InvocationSecurityMetadataSource invocationSecurityMetadataSource;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping( method = RequestMethod.GET)
	public String roles(ModelMap modelMap,HttpSession session) {
		try {
			User user =(User)session.getAttribute(SecurityConstants.LOGIN_SESSION);
			Seller seller =(Seller)session.getAttribute(SecurityConstants.LOGIN_SELLER);
			boolean flag=false;
			for (Role role : user.getRoles()) {
				if(role.getRoleCode().equals("SUPER_ROLE")){
					flag=true;
					break;
				}
			}
			if(flag){
				modelMap.addAttribute("roles", securityService.getAllRoles());
			}else{
				modelMap.addAttribute("roles", securityService.getRolesBySeller(seller));
			}
		} catch (Exception e) {
			return "redirect:../common/failure.do?reurl=security/roles.do&msgTitle=获取角色失败&msgContent="+e.getMessage();
		}
		return "security/roles";
	}
	
	@RequestMapping(  method = RequestMethod.GET)
	public String toAddRole(@ModelAttribute("role") Role role,@ModelAttribute(SecurityConstants.LOGIN_SELLER) Seller seller,ModelMap modelMap) {
		Set<RoleResc> roleRescs = new HashSet<>();
		List<com.yunxin.cb.security.Resource> resources = (List<com.yunxin.cb.security.Resource>) servletContext.getAttribute(SecurityConstants.RESOURCES);
		List<TreeViewItem> viewItems = buildResourceTree(resources, roleRescs);
		modelMap.addAttribute("roleRescTree", viewItems);
		return "security/addRole";
	}

	@RequestMapping(  method = RequestMethod.POST)
	public String addRole(@Valid @ModelAttribute("role") Role role,BindingResult result,HttpSession session,ModelMap modelMap) {
		Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
		role.setSeller(seller);
		try {
			securityService.addRole(role);
		} catch (EntityExistException e) {
			result.addError(new FieldError("role", "roleName", role.getRoleName(), true, null, null,e.getMessage()));
			return toAddRole(role, seller, modelMap);
		}
		invocationSecurityMetadataSource.loadResourceDefine();
		return "redirect:../common/success.do?reurl=security/roles.do";
	}
	
	@RequestMapping(value = "toEditRole", method = RequestMethod.GET)
	public String toEditRole(@RequestParam("roleId") int roleId,@ModelAttribute(SecurityConstants.LOGIN_SELLER) Seller seller,ModelMap modelMap) {
		Role role = securityService.getRoleById(roleId);
		modelMap.addAttribute("role", role);
		List<RoleResc> roleRescs = securityService.getRoleRescsByRole(role);
		List<com.yunxin.cb.security.Resource> resources = (List<com.yunxin.cb.security.Resource>) servletContext.getAttribute(SecurityConstants.RESOURCES);
		List<TreeViewItem> viewItems = buildResourceTree(resources, new HashSet(roleRescs));
		modelMap.addAttribute("roleRescTree", viewItems);
		return "security/editRole";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String editRole(@Valid @ModelAttribute("role") Role role,BindingResult result,HttpSession session,ModelMap modelMap) {
		Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
		role.setSeller(seller);
		try {
			securityService.updateRole(role);
		} catch (EntityExistException e) {
			result.addError(new FieldError("role", "roleName", role.getRoleName(), true, null, null,e.getMessage()));
			return toEditRole(role.getRoleId(), seller, modelMap);
		}
		invocationSecurityMetadataSource.loadResourceDefine();
		return "redirect:../common/success.do?reurl=security/roles.do";
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public boolean removeRoleById(@RequestParam("roleId") int roleId) {
		try{
			securityService.removeRoleById(roleId);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	/**
	 * 初始化角色资源授权树
	 * @param resources
	 * @param roleRescs
	 * @return
	 */
	public List<TreeViewItem> buildResourceTree(List<com.yunxin.cb.security.Resource> resources, Set<RoleResc> roleRescs) {
		List<TreeViewItem> viewItems = new ArrayList<>();
		for (com.yunxin.cb.security.Resource resource : resources) {
			TreeViewItem viewItem = new TreeViewItem(resource.getCode(), resource.getName(), true, resource.getType().toString(), true, true);
			List<com.yunxin.cb.security.Resource> children = resource.getChildren();
			if (children != null && children.size() > 0) {
				List<TreeViewItem> childItems = buildResourceTree(children, roleRescs);
				if (childItems.size() > 0) {
					viewItem.setHasChildren(true);
				}
				viewItem.setItems(childItems);
			}
			//是否已经授权
			boolean checked = roleRescs.stream().anyMatch(p -> p.getRescCode().equals(resource.getCode()));
			viewItem.setChecked(checked);
			viewItems.add(viewItem);
		}
		return viewItems;
	}
}
