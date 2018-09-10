/**
 *
 */
package com.yunxin.cb.mall.exception;

/**
 * @author Aidy_He
 */
public class CommonException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -2962096872166778599L;

    /**
     *
     */
    public CommonException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public CommonException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public CommonException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public CommonException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public CommonException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
