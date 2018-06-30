package com.yunxin.core.exception;

/**
 * Created by gonglei on 15/12/26.
 */
public class EntityExistException extends Exception {

    public EntityExistException() {
    }

    public EntityExistException(String message) {
        super(message);
    }

    public EntityExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
