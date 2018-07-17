package com.yunxin.cb.vo;

import com.yunxin.cb.meta.Result;

/**
 * Created by gonglei on 16/1/24.
 */
public class ResponseResult {

    private Result result;

    private String message;

    private Object data;

    public ResponseResult(Result result) {
        this.result = result;
    }

    public ResponseResult(Result result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResponseResult(Result result, Object data) {
        this.result = result;
        this.data = data;
    }

    public ResponseResult(Object data) {
        this.result = Result.SUCCESS;
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
