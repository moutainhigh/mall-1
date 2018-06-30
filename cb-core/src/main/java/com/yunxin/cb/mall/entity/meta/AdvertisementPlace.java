package com.yunxin.cb.mall.entity.meta;

public enum AdvertisementPlace {

    HOME("首页", "home");


    private String name;

    private String code;

    AdvertisementPlace(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return name;
    }
}
