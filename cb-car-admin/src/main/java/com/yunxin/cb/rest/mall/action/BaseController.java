package com.yunxin.cb.rest.mall.action;

import com.yunxin.cb.mall.entity.UserInfo;
import com.yunxin.cb.rest.mall.action.main.IndexController;
import com.yunxin.cb.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Title:
 * @Auther: eleven
 * @Date: 2018/9/7 11:17
 * @Description:
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @title: 获取当前操作人的userId
     * @param: []
     * @return: java.lang.Integer
     * @auther: eleven
     * @date: 2018/9/11 13:33
     */
    public Integer getOperateId(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userInfo=(UserInfo)session.getAttribute(IndexController.LOGIN_SESSION);
        if(LogicUtils.isNotNull(userInfo)){
            return userInfo.getUserId();
        }
        return null;
    }

}
