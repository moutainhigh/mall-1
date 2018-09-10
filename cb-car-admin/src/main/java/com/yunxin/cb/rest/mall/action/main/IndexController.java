package com.yunxin.cb.rest.mall.action.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunxin.cb.mall.entity.UserInfo;
import com.yunxin.cb.mall.service.UserInfoService;
import com.yunxin.cb.redis.RedisService;
import com.yunxin.cb.rest.mall.action.BaseController;
import com.yunxin.cb.rest.mall.common.PasswordHash;
import com.yunxin.cb.util.LogicUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Title:
 * @Auther: eleven
 * @Date: 2018/9/7 11:18
 * @Description:
 */
@Controller
@RequestMapping
public class IndexController extends BaseController {

    public static final String LOGIN_SESSION = "loginSession";
    public static final String LOGIN_IMAGE_VALID = "loginImageValid";
    public static final String USER_PRIVILEGES = "userPrivileges";
    public static final String PRIVILEGES = "privileges";
    public static final String SUPER_ROLE = "SUPER_ROLE";
    public static final String ADMINISTRATOR = "admin";
    public static final String FIRST_MENU = "FIRST_MENU";
    public static final String SECOND_MENU = "SECOND_MENU";
    public static final String THREE_MENU = "THREE_MENU";
    public static final String LOGIN_SELLER = "loginSeller";
    public static final String PIC_DIR="/upload/";
    public static final String PIC_PATH="picPath";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";
    public static final String ROLE_NAMES = "roleNames";
    public static final String USER_CREATE_TIME = "createTime";
    public static final String USER_LAST_TIME = "lastTime";
    public static final String USER_IP = "ip";
    public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RedisService redisService;

    @RequestMapping("login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @RequestMapping("index.do")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/console/dashboard.do";
    }

    @RequestMapping("doLogin.do")
    public String doDogin(HttpServletRequest request, HttpServletResponse response,String username,String password) throws Exception {
        if(LogicUtils.isNullOrEmpty(username)||LogicUtils.isNullOrEmpty(password)){
            request.setAttribute("errorMessage","帐号或者密码不能为空！");
            return "forward:login.do";
        }
//        password = PasswordHash.createHash(password);
//        UserInfo user = userInfoService.getSysUserByUserName(username,password);
        UserInfo user = userInfoService.getSysUserByUserName(username);
        if(LogicUtils.isNull(user)){
            request.setAttribute("errorMessage","帐号或者密码错误！");
            return "forward:login.do";
        }
        //读取权限数据
        ClassPathResource resource = new ClassPathResource("resources.json");
        InputStreamReader inputStreamReader=new InputStreamReader(resource.getInputStream(), "UTF-8");
        List<Privilege> allPrivileges = (List)(new Gson()).fromJson(inputStreamReader, (new TypeToken<List<Privilege>>() {
        }).getType());
        HttpSession session = request.getSession();
        session.setAttribute(USER_PRIVILEGES, allPrivileges);
        session.setAttribute(LOGIN_SESSION, user);
        session.setAttribute(USER_NAME, user.getUserName());
        session.setAttribute(USER_LAST_TIME, user.getLastTime());
        session.setAttribute(USER_CREATE_TIME, user.getCreateTime());
        session.setAttribute(USER_ID, user.getUserId());
        setRedisLoginSuccess(user.getUserId());
        return "redirect:/console/dashboard.do";
    }

    public void setRedisLoginSuccess(int userId){
        List<Integer> list = null;
        if(redisService.getKey(LOGIN_SUCCESS) != null){
            list = (List<Integer>)redisService.getKey(LOGIN_SUCCESS);
        }else{
            list = new ArrayList<>();
        }
        if(!list.contains(userId)){
            list.add(userId);
        }
        redisService.updateRedisByKey(LOGIN_SUCCESS,list);
    }

    @RequestMapping("logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(LOGIN_SUCCESS);
        return "redirect:login.do";
    }

}
