package com.yunxin.cb.search.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品价格段
 */
public class PriceSection implements java.io.Serializable {
    /**
     * 起始价格
     */
    @ApiModelProperty(value="起始价格",name="startPrice",example="500000-600000")
    private int startPrice;

    /**
     * 结束价格
     */
    @ApiModelProperty(value="结束价格",name="endPrice",example="500000")
    private int endPrice;

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }
}
