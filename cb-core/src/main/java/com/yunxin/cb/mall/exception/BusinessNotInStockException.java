/**
 *
 */
package com.yunxin.cb.mall.exception;

/**
 * @author apple
 */
public class BusinessNotInStockException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public BusinessNotInStockException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public BusinessNotInStockException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public BusinessNotInStockException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public BusinessNotInStockException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public BusinessNotInStockException(String message, Throwable cause,
                                       boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}

