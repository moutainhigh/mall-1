package com.yunxin.cb.vo;

/**
 * Created by gonglei on 16/1/24.
 */
public class ResponseResult {

    private ResultType resultType;

    private Object data;

    public ResponseResult(ResultType resultType) {
        this.resultType = resultType;
    }

    public ResponseResult(ResultType resultType, Object data) {
        this.resultType = resultType;
        this.data = data;
    }

    public ResponseResult(Object data) {
        this.resultType = ResultType.SUCCESS;
        this.data = data;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}