package com.yunxin.cb.aop;

import com.yunxin.cb.console.entity.SystemLog;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.entity.meta.OperateType;
import com.yunxin.cb.console.service.ILogsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by gonglei on 2015/5/13.
 */
@Aspect
public class AspectLogger {

    @Resource
    private ILogsService logService;


    @AfterReturning(value = "execution (* com.yunxin.cb.*.service.*.add*(..))", returning = "returnObj")
    public void endTransaction(JoinPoint point, Object returnObj) throws Throwable {
        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        Object[] args = point.getArgs();
        //获取方法名
        String methodName = point.getSignature().getName();
        User user = getUser();
        if (user != null) {
            String returnStr = returnObj == null ? "" : returnObj.toString();
            SystemLog operateLog = new SystemLog(OperateType.ADD, methodName, getArgsContent(args), returnStr, new Date(), user.getUserId(), user.getUserName());
            logService.createSystemLog(operateLog);
        }
    }

    @AfterReturning(value = "execution (* com.yunxin.cb.*.service.*.update*(..))", returning = "returnObj")
    public void endUpdateTransaction(JoinPoint point, Object returnObj) throws Throwable {
        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        Object[] args = point.getArgs();
        //获取方法名
        String methodName = point.getSignature().getName();

        User user = getUser();
        if (user != null) {
            String returnStr = returnObj == null ? "" : returnObj.toString();
            SystemLog operateLog = new SystemLog(OperateType.UPDATE, methodName, getArgsContent(args), returnStr, new Date(), user.getUserId(), user.getUserName());
            logService.createSystemLog(operateLog);
        }
    }

    @AfterReturning(value = "execution (* com.yunxin.cb.*.service.*.remove*(..))", returning = "returnObj")
    public void endRemoveTransaction(JoinPoint point, Object returnObj) throws Throwable {
        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        Object[] args = point.getArgs();
        //获取方法名
        String methodName = point.getSignature().getName();

        User user = getUser();
        if (user != null) {
            String returnStr = returnObj == null ? "" : returnObj.toString();
            SystemLog operateLog = new SystemLog(OperateType.REMOVE, methodName, getArgsContent(args), returnStr, new Date(), user.getUserId(), user.getUserName());
            logService.createSystemLog(operateLog);
        }
    }

    private User getUser() {
        User user = null;
        try {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            user = (User) session.getAttribute("loginSession");
        } catch (Exception ignored) {
        }
        return user;
    }

    private String getArgsContent(Object[] args) {
        if (args == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object obj : args) {
            stringBuilder.append(obj.toString());
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    @AfterThrowing(pointcut = "execution (* com.yunxin.cb.*.service.*.update*(..))", throwing = "ex")
    // 声明异常，StudentMgr类的update方法出现异常时执行
    public void printException(Exception ex) {

        System.out.println("执行update方法时发生错误" + ex.getMessage());

    }
}
