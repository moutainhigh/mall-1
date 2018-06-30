package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.console.entity.User;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Administrator on 2016/1/10.
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class ProductChangePrice implements java.io.Serializable {

    /**
     * id
     */
    private int changeId;

    /**
     * 调价编号
     */
    private String changeNo;

    /**
     * 货品
     */
    private Product product;

    /**
     * 调价操作人编号
     */
    private User user;

    /**
     * 调价操作时间
     */
    private Date operatorTime;

    /**
     * 调整前价格
     */
    private float prePrice;

    /**
     * 调整后价格
     */
    private float newPrice;

    /**
     * 调价表审核状态 "false"表示未审核 ，"true"表示已审核
     */
    private boolean status;

    /**
     * 调价表审核意见
     */
    private String auditOpinion;

    /**
     * 调价描述
     */
    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
    }

    @Column(nullable = false, length = 32)
    public String getChangeNo() {
        return changeNo;
    }

    public void setChangeNo(String changeNo) {
        this.changeNo = changeNo;
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
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    @Column(nullable = false, precision = 11, scale = 2)
    public float getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(float prePrice) {
        this.prePrice = prePrice;
    }

    @Column(nullable = false, precision = 11, scale = 2)
    public float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(float newPrice) {
        this.newPrice = newPrice;
    }

    @Column(nullable = false, precision = 1)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Column(length = 512)
    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    @Column(length = 512)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
