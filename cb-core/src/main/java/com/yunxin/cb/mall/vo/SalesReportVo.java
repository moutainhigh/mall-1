package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.OrderItem;

/**
 * @author qulei
 */
public class SalesReportVo {

    protected int productId;

    /**
     * 货品名称
     */
    protected String productName;

    /**
     * 货品图片
     */
    protected String productImg;

    /**
     * 当月销售总量
     */
    protected Long totalSales;

    /**
     * 时间
     */
    protected int calendar;

    public SalesReportVo() {

    }

    public SalesReportVo(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getProductId();
        this.productName = orderItem.getProduct().getProductName();
        this.productImg = orderItem.getProductImg();
    }

    public SalesReportVo(int productId, String productName, String productImg,
                         Long totalSales, int calendar) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productImg = productImg;
        this.totalSales = totalSales;
        this.calendar = calendar;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Long getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Long totalSales) {
        this.totalSales = totalSales;
    }

    public int getCalendar() {
        return calendar;
    }

    public void setCalendar(int calendar) {
        this.calendar = calendar;
    }
}
