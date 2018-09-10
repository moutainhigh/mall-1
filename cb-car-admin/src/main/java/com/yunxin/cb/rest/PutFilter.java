package com.yunxin.cb.rest;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * put多个参数时不能接收到参数
 */
@Component
public class PutFilter extends HttpPutFormContentFilter {
}