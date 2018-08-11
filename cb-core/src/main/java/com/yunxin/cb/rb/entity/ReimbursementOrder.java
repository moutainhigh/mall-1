package com.yunxin.cb.rb.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@JsonAutoDetect
@Entity
@Table(name = "rb_reimbursement_order")
@DynamicInsert
@DynamicUpdate
@ApiModel(value="报账订单信息",description="报账订单信息ReimbursementOrder")
public class ReimbursementOrder implements java.io.Serializable {
    private int reimbursementOrderId;
    private OrderItem orderItem;
    private Reimbursement reimbursement;
    private BigDecimal amount;
    private BigDecimal tax;
    private BigDecimal productPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getReimbursementOrderId() {
        return reimbursementOrderId;
    }

    public void setReimbursementOrderId(int reimbursementOrderId) {
        this.reimbursementOrderId = reimbursementOrderId;
    }


    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ORDER_ITEM_ID", nullable = false, insertable = true, updatable = true)
    })
    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }




    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "REIMBURSEMENT_ID", nullable = false, insertable = true, updatable = true)
    })
    public Reimbursement getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(Reimbursement reimbursement) {
        this.reimbursement = reimbursement;
    }
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 11)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 11)
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 11)
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
