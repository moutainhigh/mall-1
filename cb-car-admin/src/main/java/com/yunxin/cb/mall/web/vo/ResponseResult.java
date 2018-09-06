package com.yunxin.cb.mall.web.vo;

/**
 * Created by gonglei on 16/1/24.
 */

public class ResponseResult<T> {

    private ResultType resultType;
    private String message;
    private T data;

    public ResponseResult() {

    }

    public ResponseResult(ResultType resultType) {
        this.resultType = resultType;
    }

    public ResponseResult(ResultType resultType, String message) {
        this.resultType = resultType;
        this.message = message;
    }

    public ResponseResult(ResultType resultType, T data) {
        this.resultType = resultType;
        this.data = data;
    }

    public ResponseResult(T data) {
        this.resultType = ResultType.SUCCESS;
        this.data = data;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
