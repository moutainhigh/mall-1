package com.yunxin.cb.mall.entity.meta;

/**
 * @Auther: hfy
 * @Date: 2018/9/11 17:19
 * @Description:
 */
public enum FloorType {
    BANNER(0,"BANNER"), CATEGORY(1,"运营分类"), BRAND_CHOICE(2,"品牌精选"),RECOMMENDATION(3,"RecommendationVO");
    private String name;
    private Integer value;
    private FloorType(Integer value,String name){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
