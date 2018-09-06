/**
 * 
 */
package com.yunxin.cb.mall.web.action.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tanggangyi
 * 
 */
@Controller
@RequestMapping(value = "/common")
//@SessionAttributes({ SecurityConstants.LOGIN_SESSION })
public class ErrorController {

	@RequestMapping(value = "error_500", method = RequestMethod.GET)
	public String denial() {
		return "common/error_500";
	}

	@RequestMapping(value = "error_404", method = RequestMethod.GET)
	public String error_404() {
		return "common/error_404";
	}

	@RequestMapping(value = "error_403", method = RequestMethod.GET)
	public String error_503() {
		return "common/error_403";
	}

    /**
     * 用于商品推送数据到solr服务器时发生错误调用的方法
     * @return
     */
    @RequestMapping(value = "solrError",method = RequestMethod.GET)
    public String solrError(){
        return "common/solrError";
    }

}
