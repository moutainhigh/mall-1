package com.yunxin.core.meta;

public enum IDType {
    GOODS("GOODS"),
    ADV("ADV"),
    BRAND("BRAND"),
    PRICE("PRICE"),
    PRODUCT("PRODUCT"),
    SELLER("SELLER");

    private String type;

    IDType (String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
