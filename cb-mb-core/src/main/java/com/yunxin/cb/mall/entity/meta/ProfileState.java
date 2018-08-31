package com.yunxin.cb.mall.entity.meta;

public enum ProfileState {

    FINACIAL_FREE_RATE("0.1"),//提现手续费
    TAX_RATE("0.23"),//税率
    MAX_LOAN_NUM("5"),//最多借款次数
    HOT_SEARCH("昂克赛拉,卡罗拉,福克斯,思域,凯美瑞,迈腾,雷克萨斯CT"),//热门搜索
    HOT_CITY("北京市&110000,上海市&310000,广州市&440100,深圳市&440300"),//热门城市
    CAR_CLASS_CONFIG("汽车根分类配置"),//汽车根分类配置
    ;

    private String name;

    private ProfileState(String name) {
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
        return super.toString() + "("+name+")";
    }
}
