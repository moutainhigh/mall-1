package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Product;

import java.util.Date;


public class OrderDetailVo {


    /**
     * ID
     */
    private int orderDetailId;
    /**
     * 订单
     */
    private OrderFormVo orderForm;

//	private OrderItemState status = OrderItemState.PENDING_PAYMENT;
    /**
     * 货品ID
     */
    private int productId;
    /**
     * 货品名称
     */
    private String productName;
    /**
     * 销售价格
     */
    private double salePrice;
    /**
     * 货品图片
     */
    private String productImg;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 货品购买数量
     */
    private int productNum;
    /**
     * 商品参与的活动ID
     */
    private int activityId;
    /**
     * 商品参与的活动名称
     */
    private String activityName;

    /**
     * 当后台图片变换时，前台个人中心图片也跟着变化
     */
    private Product product;

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderFormVo getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderFormVo orderForm) {
        this.orderForm = orderForm;
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

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
