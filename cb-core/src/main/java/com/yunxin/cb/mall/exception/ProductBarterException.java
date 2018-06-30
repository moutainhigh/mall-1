package com.yunxin.cb.mall.exception;

/**
 * Created by Administrator on 2016/4/16.
 */
public class ProductBarterException extends Exception {


    public ProductBarterException() {
        super();
    }

    public ProductBarterException(String message) {
        super(message);
    }

    public ProductBarterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductBarterException(Throwable cause) {
        super(cause);
    }

    protected ProductBarterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
