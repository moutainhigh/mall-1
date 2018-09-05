package com.yunxin.cb.rest.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.yunxin.cb.mall.entity.UserInfo;
import org.slf4j.LoggerFactory;

public class BaseController {
	
	@Resource
	HttpServletRequest request;

	protected final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * 取HttpSession对象
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return request.getSession();
	}

	/**
	 * 设置用户的登录信息
	 * 
	 * @Title: setLoginSysUser
	 * @Description: TODO
	 * @param request
	 * @param sysUser
	 * @return
	 */
	public static boolean setLoginSysUser(HttpServletRequest request, UserInfo sysUser) {
		try {
			request.getSession().setAttribute(ECMgtConstant.SESSION_KEY_OF_LOGIN_SYS_USER, sysUser);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 取已登录的系统人员用户对象（数据来自session）
	 * 
	 * @return
	 */
	public UserInfo getLoginSysUser() {
		UserInfo sysUser = null;
		HttpSession session = getSession();
		if (session != null) {
			sysUser = (UserInfo) session.getAttribute(ECMgtConstant.SESSION_KEY_OF_LOGIN_SYS_USER); // 从session中取已登录的系统人员用户对象
		}
		return sysUser;
	}

	/**
	 * 移除session 属性
	 * 
	 * @Title: removeSessionAttribut
	 * @Description: TODO
	 * @param request
	 * @param key
	 */
	public static void removeSessionAttribut(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	/**
	 * 移除登陆用户
	 * 
	 * @Title: removeSessionAttribut
	 * @Description: TODO
	 * @param request
	 * @param
	 */
	public static void removeSessionUser(HttpServletRequest request) {
		removeSessionAttribut(request, ECMgtConstant.SESSION_KEY_OF_LOGIN_SYS_USER);

	}

}
