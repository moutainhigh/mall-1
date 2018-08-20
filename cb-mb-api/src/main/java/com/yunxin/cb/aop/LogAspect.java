package com.yunxin.cb.aop;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static Logger log = LoggerFactory.getLogger(LogAspect.class);
	/**
	 * @Title: doAround
	 *
	 * @Description: API环绕触发
	 * @param
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.yunxin.cb.rest.mall..*.*(..)) ")
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
//			inputParam = joinPoint.getArgs();
			// 每个请求的方法第一个参数都必须为HttpServletRequest
//			HttpServletRequest request = (HttpServletRequest) inputParam[0];

			//获取request的另一种方案
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			ServletRequestAttributes sra = (ServletRequestAttributes) ra;
			HttpServletRequest request = sra.getRequest();

			Map<String, Object> map = new HashMap<>();
			@SuppressWarnings("rawtypes")
			Enumeration<String> paramNames = request.getParameterNames();
			System.err.println("paramNames:"+paramNames);
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				System.err.println("paramName:"+paramName+",paramValues:"+paramValues);
				if (paramValues.length > 0) {
					String paramValue = paramValues[0];
					System.err.println("paramValue:"+paramValue);
						map.put(paramName, paramValue);
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
			//打印到info文件中
			//CommonUtil.printHTTPURL(request);
			// 执行目标方法
			outputParam = joinPoint.proceed();
			long endTime = System.currentTimeMillis();
			float excTime = (float) (endTime - startTime) / 1000;
			log.info("methodName：" + methodName + ", param:" + map + ";" + ",headermap:" + headermap + "\n result："
					+ isJson(outputParam) + "执行时间:" + excTime+ "\n "
			);
		} catch (Throwable e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			log.error(str + "类名称" + joinPoint.getSignature().getDeclaringTypeName() + ",方法名称:" + methodName);
			return new ResponseResult(Result.FAILURE,"系统繁忙，请稍后重试...");
		}
		return outputParam;
	}

	public String isJson(Object outputParam){
		try {
			return JSON.toJSON(outputParam).toString();
		} catch (Exception e) {
			return outputParam.toString();
		}
	}
}