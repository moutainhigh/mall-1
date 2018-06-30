package com.yunxin.cb.mall.web.vo;

/**
 * Created by gonglei on 16/1/24.
 */
public class ResponseResult {

    private ResultType resultType;

    private Object data;

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
