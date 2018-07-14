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
