package com.yunxin.cb.mall.web.util;

/**
 * Created by chenxing on 16/4/8.
 */
public class BuildCommonURL {


    static public String buildFailureURL(String reurl,String msgTitle,String msgContent){
        return  "redirect:../common/failure.do?reurl="+reurl+"&msgTitle="+msgTitle+"&msgContent="+msgContent;
    }

    static public String buildSuccessURL(String reurl){
        return "redirect:../common/success.do?reurl="+reurl;
    }
}
