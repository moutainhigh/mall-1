package com.yunxin.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author moxin E-mail: moxinchn@163.com
 * @version 2017-12-16-0016 20:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomJsonFilter {
    String value() default "";
}