/**
 *
 */
package com.yunxin.core.orm;

/**
 * @author gonglei
 */
public class CustomerContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setManageType() {
        contextHolder.set("adminDataSource");
    }

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return (String) contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }

}
