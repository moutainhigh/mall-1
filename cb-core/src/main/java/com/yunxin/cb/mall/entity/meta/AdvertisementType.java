package com.yunxin.cb.mall.entity.meta;

public enum AdvertisementType {

    PTHOTO_AND_TEXT("图文"), /*TEXT("纯文字"),*/ VIDEO("视频");


    private String name;

    AdvertisementType(String name) {
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
