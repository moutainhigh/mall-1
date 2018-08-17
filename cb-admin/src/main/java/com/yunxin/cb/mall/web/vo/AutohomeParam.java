package com.yunxin.cb.mall.web.vo;

import java.util.List;

public class AutohomeParam {

    private String name;

    private List<AutohomeSpec> paramitems;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AutohomeSpec> getParamitems() {
        return paramitems;
    }

    public void setParamitems(List<AutohomeSpec> paramitems) {
        this.paramitems = paramitems;
    }
}
