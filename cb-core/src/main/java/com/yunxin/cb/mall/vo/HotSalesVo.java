package com.yunxin.cb.mall.vo;

/**
 * 统计热销商品数量
 *
 * @author x001393
 */
public class HotSalesVo implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int productId;
    private String productName;
    private String productImg;
    private Long salesvolume;

    public HotSalesVo() {

    }

    public HotSalesVo(int productId, String productName, String productImg, Long salesvolume) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productImg = productImg;
        this.salesvolume = salesvolume;
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

    public Long getSalesvolume() {
        return salesvolume;
    }

    public void setSalesvolume(Long salesvolume) {
        this.salesvolume = salesvolume;
    }


}
