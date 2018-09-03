package com.yunxin.cb.mall.web.vo;

import java.util.List;

public class AutohomeSpec {

    private String name;

    private List<AutohomeSpecItem> valueitems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AutohomeSpecItem> getValueitems() {
        return valueitems;
    }

    public void setValueitems(List<AutohomeSpecItem> valueitems) {
        this.valueitems = valueitems;
    }
}
