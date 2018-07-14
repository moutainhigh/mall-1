package com.yunxin.core.exception;

public class FieldException extends Exception {
    private String fieldName;
    private Object rejectedValue;

    public FieldException(String fieldName, Object rejectedValue) {
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
    }

    public FieldException(String fieldName, Object rejectedValue, String message) {
        super(message);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
    }

    public FieldException(String fieldName, Object rejectedValue, String message, Throwable cause) {
        super(message, cause);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
    }

    public FieldException(String fieldName, Object rejectedValue, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getRejectedValue() {
        return this.rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}