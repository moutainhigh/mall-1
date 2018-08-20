package com.yunxin.core.meta;

public enum IDType {
    GOODS("GDS"),
    ADV("ADV"),
    BRAND("BRD"),
    PRICE("PRI"),
    PRODUCT("PRD"),
    SELLER("SLR");

    private String type;

    IDType (String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
