package com.yunxin.cb.mall.vo;

/**
 * @author x001393
 * 统计销售商品VO
 */
public class SalesData implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer month;
    //销量
    private Long salesVolume;
    //销售额
    private Double salesAmount;

    public SalesData() {

    }

    public SalesData(Integer month, Long salesVolume, Double salesAmount) {
        super();
        this.month = month;
        this.salesVolume = salesVolume;
        this.salesAmount = salesAmount;

    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Long salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }

}
