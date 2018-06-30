/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 购物车
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class ShoppingCart implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private int cartId;
    /**
     * 货品
     */
    private Product product;
    /**
     * 货品类型
     */
    private int productType;
    /**
     * 客户
     */
    private Customer customer;
    /**
     * 数量
     */
    private int cartNum;
    /**
     * 创建时间
     */
    private Date createTime;

    //临时
    private int productId;
    private ActivityCommodity activityCommodity;
    private double activityPrice;
    private int activityId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(nullable = true, precision = 12)
    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, length = 7)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(nullable = true, precision = 12)
    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    @Transient
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Transient
    public ActivityCommodity getActivityCommodity() {
        return activityCommodity;
    }

    public void setActivityCommodity(ActivityCommodity activityCommodity) {
        this.activityCommodity = activityCommodity;
    }

    @Transient
    public double getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(double activityPrice) {
        this.activityPrice = activityPrice;
    }

    @Transient
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
