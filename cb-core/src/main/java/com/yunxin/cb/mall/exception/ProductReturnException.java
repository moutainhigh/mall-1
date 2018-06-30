package com.yunxin.cb.mall.exception;

/**
 * Created by Administrator on 2016/4/28.
 */
public class ProductReturnException extends Exception {

    public ProductReturnException() {
        super();
    }

    public ProductReturnException(String message) {
        super(message);
    }

    public ProductReturnException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductReturnException(Throwable cause) {
        super(cause);
    }

    protected ProductReturnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
