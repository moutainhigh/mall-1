/**
 * 
 */
package com.yunxin.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xin
 * 
 */
public class WebContext {

    private static ThreadLocal<WebContext> tlv = new ThreadLocal<WebContext>();
    private HttpServletRequest request;
    private HttpServletResponse response;

    protected WebContext() {
    }

    private WebContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static WebContext getInstance() {
        return tlv.get();
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public static void create(HttpServletRequest request, HttpServletResponse response) {
        WebContext wc = new WebContext(request, response);
        tlv.set(wc);
    }

    public static void clear() {
        tlv.set(null);
    }

}
