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
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author j000101
 * 货品评价
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class ProductEvaluate implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 评价ID
     */
    private int evaluateId;

    /**
     * 订单
     */
    private Order order;

    /**
     * 订单项
     */
    private OrderItem orderItem;

    /**
     * 商品
     */
    private Commodity commodity;

    /**
     * 客户
     */
    private Customer customer;

    /**
     * 评分
     */
    private int score;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否已回复
     */
    private boolean replyStatus = false;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 评价回复
     */
    private Set<ProductEvaluateReply> productEvaluateReplies = new HashSet<ProductEvaluateReply>();

    private String remark;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(int evaId) {
        this.evaluateId = evaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = true)
    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = true)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = true)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEvaluate", cascade = CascadeType.REMOVE)
    public Set<ProductEvaluateReply> getProductEvaluateReplies() {
        return productEvaluateReplies;
    }

    public void setProductEvaluateReplies(Set<ProductEvaluateReply> productEvaluateReplies) {
        this.productEvaluateReplies = productEvaluateReplies;
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


    @Column(nullable = true, length = 512)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(nullable = false, length = 1)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Column(nullable = false)
    public boolean isReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(boolean replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
