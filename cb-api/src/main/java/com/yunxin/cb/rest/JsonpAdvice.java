package com.yunxin.cb.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 对使用HttpMessageConverter的@ResponseBody的支持 
 */

//@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
//    public JsonpAdvice() {
//        super("callback"); //指定jsonpParameterNames
//    }
}