package com.yunxin.cb.mall.entity.meta;

public enum ActivityRuleType {
    XSTJ("限时特价"), XSDZ("限时打折"), MEMJ("满额打折"), MEMZ("满额满赠");
    private String name;

    private ActivityRuleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
