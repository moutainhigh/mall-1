package com.yunxin.cb.aop;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.vo.ResponseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;


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
//			Map<String, Object> map = new HashMap<String, Object>();
//			@SuppressWarnings("rawtypes")
//			Enumeration paramNames = request.getParameterNames();
//			while (paramNames.hasMoreElements()) {
//				String paramName = (String) paramNames.nextElement();
//				String[] paramValues = request.getParameterValues(paramName);
//				if (paramValues.length == 1) {
//					String paramValue = paramValues[0];
//					//if (paramValue.length() != 0) {
//						map.put(paramName, paramValue);
//					//}
//				}
//			}
			//打印到info文件中
			//CommonUtil.printHTTPURL(request);
			// 执行目标方法
			outputParam = joinPoint.proceed();
			long endTime = System.currentTimeMillis();
			float excTime = (float) (endTime - startTime) / 1000;
			log.info("methodName：" + methodName + ";" + "\n result："
					+ JSON.toJSON(outputParam) + "执行时间:" + excTime+ "\n "
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
}