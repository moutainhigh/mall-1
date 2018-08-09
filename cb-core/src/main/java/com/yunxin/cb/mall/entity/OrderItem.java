package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 订单详情
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class OrderItem implements java.io.Serializable {

    private int itemId;
    /**
     * 订单
     */
    private Order order;
    /**
     * 货品名称
     */
    private Product product;
    /**
     * 销售价格
     */
    private float salePrice;
    /**
     * 总价格
     */
    private float orderItemPrice;
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
     * 是否做了评价
     */
    private boolean evaluate;

    /**
     * 买家留言
     */
    private String buyerMessage;

    private Activity activity;
    /**
     * 报账信息
     */
    private List<ReimbursementOrder> reimbursementOrders=new ArrayList<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int orderDetailId) {
        this.itemId = orderDetailId;
    }

    @Column(nullable = true, length = 512)
    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(nullable = false)
    public boolean isEvaluate() {
        return evaluate;
    }

    public void setEvaluate(boolean evaluate) {
        this.evaluate = evaluate;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public float getOrderItemPrice() {
        return orderItemPrice;
    }

    public void setOrderItemPrice(float orderItemPrice) {
        this.orderItemPrice = orderItemPrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public float getSalePrice() {
        return salePrice;
    }

    @Column(nullable = false, length = 12)
    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    @Column(length = 255)
    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITY_ID", nullable = true)
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "orderItem")
    public List<ReimbursementOrder> getReimbursementOrders() {
        return reimbursementOrders;
    }

    public void setReimbursementOrders(List<ReimbursementOrder> reimbursementOrders) {
        this.reimbursementOrders = reimbursementOrders;
    }
}
