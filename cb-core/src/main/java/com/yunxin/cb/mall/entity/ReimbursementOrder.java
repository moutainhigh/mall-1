package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@ApiModel(value="报账订单信息",description="报账订单信息ReimbursementOrder")
public class ReimbursementOrder implements java.io.Serializable {
    private int reimbursementOrderId;
    private Order order;
    private Reimbursement reimbursement;
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
            @JoinColumn(name = "ORDER_ID", nullable = false, insertable = true, updatable = true)
    })
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
}