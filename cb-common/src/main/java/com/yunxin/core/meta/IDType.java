package com.yunxin.core.meta;

public enum IDType {
    GOODS("GDS"),//商品编码
    ADV("ADV"),//广告编码
    BRAND("BRD"),//品牌编码
    PRICE("PRI"),//价格段编码
    PRODUCT("PRD"),//货品编码
    ROLE("ROL"),//角色编码
    CATALOG("CAG"),//运营分类
    SELLER("SLR");//商家编码

    private String type;

    IDType (String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
