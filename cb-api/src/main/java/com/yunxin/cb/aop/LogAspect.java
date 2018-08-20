package com.yunxin.cb.aop;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.vo.ResponseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 * 
 * @ClassName: LogAspect
 * @Description: 日志记录AOP实现
 * @author lxc
 * @date 2018年08月16日 下午2:51:59
 */
@Aspect
@Component
public class LogAspect {

//	private static Logger log = LoggerFactory.getLogger(LogAspect.class);
	private final Logger log = Logger.getLogger("logAspect");
	/**
	 * @Title: doAround
	 *
	 * @Description: API环绕触发
	 * @param
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.yunxin.cb.rest..*.*(..)) ")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		/**
		 * @Fields methodName : 请求方法(必需是局部变量)
		 * (必需在方法体内)
		 */
		String methodName = null;

		/**
		 * @Fields inputParam : 方法入参数
		 * (必需在方法体内)
		 */
		Object[] inputParam = null;

		/**
		 * @Fields outputParam : 方法出参
		 * (必需在方法体内)
		 */
		Object outputParam = null;

		try {
			long startTime = System.currentTimeMillis();
			methodName = joinPoint.getSignature().toLongString();
			inputParam = joinPoint.getArgs();
//			 每个请求的方法第一个参数都必须为HttpServletRequest
//			HttpServletRequest request = (HttpServletRequest) inputParam[0];
			//获取request的另一种方案
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			ServletRequestAttributes sra = (ServletRequestAttributes) ra;
			HttpServletRequest request = sra.getRequest();

			Map<String, Object> map = new HashMap<String, Object>();
			@SuppressWarnings("rawtypes")
			Enumeration paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1) {
					String paramValue = paramValues[0];
					//if (paramValue.length() != 0) {
						map.put(paramName, paramValue);
					//}
				}
			}

			Enumeration headerNames=request.getHeaderNames();
			//获取所有的头部参数
			Map<String, Object> headermap = new HashMap<>();
			while (headerNames.hasMoreElements()) {
				String paramName = (String) headerNames.nextElement();
				String paramValue = request.getHeader(paramName);
				if (paramValue != null) {
					headermap.put(paramName, paramValue);
				}
			}
			// 执行目标方法
			outputParam = joinPoint.proceed();
			long endTime = System.currentTimeMillis();
			float excTime = (float) (endTime - startTime) / 1000;
			log.info("\n 完整路径：" +getFullUrlIsTrue(request,true) + ",headermap:" + headermap + ",methodName:" + methodName + ";" + "\n result:"
					+ isJson(outputParam) + "执行时间:" + excTime
			);
		} catch (Throwable e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			log.info(str + "类名称" + joinPoint.getSignature().getDeclaringTypeName() + ",方法名称:" + methodName);
			return new ResponseResult(Result.FAILURE,"系统繁忙，请稍后重试...");
		}
		return outputParam;
	}

	/**
	 * 得到一个完整URL（包含参数）
	 * @param request
	 * @param urlIsTrue     是否包含URL,true包含,false不包含
	 * @return
	 */
	public static String getFullUrlIsTrue(HttpServletRequest request, boolean urlIsTrue) {
		StringBuffer url = new StringBuffer();
		int i = 0;
		if(urlIsTrue) {
			url.append(request.getRequestURL());
		}
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(key);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				//if (paramValue.length() != 0) {
				if (i++ == 0) {
					url.append("?" + key + "=" + paramValue);
				} else {
					url.append("&" + key + "=" + paramValue);
				}
				//}
			}
		}
		return url.toString();
	}

    public String isJson(Object outputParam){
        try {
            return JSON.toJSON(outputParam).toString();
        } catch (Exception e) {
            return outputParam.toString();
        }
    }
}