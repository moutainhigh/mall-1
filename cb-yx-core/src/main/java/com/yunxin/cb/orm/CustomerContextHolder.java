/**
 *
 */
package com.yunxin.cb.orm;

import org.apache.commons.lang.StringUtils;

/**
 * @author tanggangyi
 */
public class CustomerContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerId(int customerId){
        contextHolder.set(String.valueOf(customerId));
    }

    public static int getCustomerId(){
        String s = contextHolder.get();
        if(StringUtils.isNotBlank(s)){
            return Integer.parseInt(contextHolder.get());
        }else {
            return -1;
        }
    }

    public static void clearCustomerId(){
        contextHolder.remove();
    }

}
