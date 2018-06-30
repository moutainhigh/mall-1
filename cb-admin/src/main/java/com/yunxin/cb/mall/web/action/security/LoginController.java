/**
 *
 */
package com.yunxin.cb.mall.web.action.security;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.console.service.ILogsService;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author ThinkPad
 */
@Controller
@RequestMapping(value = "/")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class LoginController {

    protected static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private ISecurityService securityService;

    @Resource
    private ILogsService systemLogService;

    @RequestMapping("index")
    public String index(ModelMap modelMap, HttpServletRequest request, HttpSession session) {

//		User user = (User) request.getSession().getAttribute(SecurityConstants.LOGIN_SESSION);

//		Object obj = session.getAttribute(SecurityConstants.LOGIN_SESSION);
//		if(obj != null){
//			User user = (User)obj;
//			System.out.println("session-----------"+user.getRealName()+","+user.getPassword());
//		}
//		
//		
//		String userName = null;
//		String password = null; 
//		Cookie[] cookies = request.getCookies();
//		if(LogicUtils.isNotNullAndEmpty(cookies)){
//			for (Cookie cookie : cookies) {
//				if ("COOKIE_LOGIN_USERNAME".equals(cookie.getName())) {
//					userName = cookie.getValue();
//				}
//				if ("COOKIE_LOGIN_PASSWORD".equals(cookie.getName())) {
//					password = cookie.getValue();
//				}
//			}
//			
//			if (userName != null && password != null) {
//				try {
//					User user = securityService.findByUserNameAndPassword(userName,password);
//					// 如果存在				
//					if(LogicUtils.isNotNull(user)){
//						// 登陆成功的处理
//						return "redirect:console/dashboard.do";
//					}else{
//						// 登陆不成功的处理
//						return "index";
//					} 
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}			
//		}

        // Cookie[] cookies = request.getCookies();
        // if (cookies != null) {
        // // 从cookies中查找家庭信息
        // for (Cookie cookie : cookies) {
        // if (cookie.getName().equals("projectCode")) {
        // projectCode = cookie.getValue();
        // continue;
        // }
        // }
        // }
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "userName") String userName,
                        @RequestParam(value = "password") String password, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
//		String validRand = (String) request.getSession().getAttribute(SecurityConstants.LOGIN_IMAGE_VALID);
//		String errorPath = "redirect:index.do?name=" + userName + "&code=" + validCode;
//		if (validRand == null) {
//			return errorPath;
//		}
//		if (!validRand.equals(validCode)) {
//			return errorPath + "&error=0";
//		}
        User user = securityService.findByUserNameAndPassword(userName, password);
//		if (user == null) {
//			return errorPath + "&error=3";
//		}
//		if (!user.getPassword().equals(password)) {
//			return errorPath + "&error=4";
//		}
//		if (!user.isEnable()) {
//			return errorPath + "&error=5";
//		}
//		Date date = new Date();
//
        request.getSession().setAttribute(SecurityConstants.LOGIN_SESSION, user);
        request.getSession().setAttribute(SecurityConstants.LOGIN_SELLER, user.getSeller());


//		String host = request.getServerName();
//		System.out.println("host--------------"+host+"---------");
//		Cookie cookie = new Cookie("COOKIE_LOGIN_USERNAME", user.getUserName()); // 保存用户名到Cookie
//		cookie.setPath("/");
//		cookie.setDomain(host);
//		cookie.setMaxAge(99999999);
//		response.addCookie(cookie);
//		
//		cookie = new Cookie("COOKIE_LOGIN_PASSWORD", user.getPassword());
//		cookie.setPath("/");
//		cookie.setDomain(host);
//		cookie.setMaxAge(99999999);
//		response.addCookie(cookie);


        // 记录登录日志
//        SystemLog systemLog = new SystemLog();
//        systemLog.setIp(request.getRemoteAddr());
//        systemLog.setCreateTime(new Date());
//        systemLog.setUserId(user.getUserId());
//        systemLog.setUserName(user.getUserName());


        // Cookie cookie = new Cookie("projectCode", projectCode);
        // cookie.setMaxAge(1209600);
        // response.addCookie(cookie);

//        systemLogService.addSystemLog(systemLog);

        return "redirect:console/dashboard.do";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(SecurityConstants.LOGIN_SESSION);
        request.getSession().removeAttribute(SecurityConstants.LOGIN_SELLER);
        Enumeration<String> allStr = request.getSession().getAttributeNames();
        for (Enumeration<String> e = allStr; e.hasMoreElements(); ) {
            request.getSession().removeAttribute(e.nextElement());
        }
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String cookieName = cookies[i].getName();
            if (cookieName.equals("COOKIE_LOGIN_USERNAME")) {
                cookies[i].setValue(null);
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        return "redirect:index.do";
    }

    /**
     * 指定无访问额权限页面
     *
     * @return
     */
    @RequestMapping(value = "denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        logger.debug("Received request to show denied page");

        return "denied";
    }

}
