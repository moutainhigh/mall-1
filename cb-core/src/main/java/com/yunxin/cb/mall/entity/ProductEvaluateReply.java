package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.console.entity.User;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 评价回复
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class ProductEvaluateReply implements java.io.Serializable {

    // 回复id
    private int replyId;

    // 咨询
    private ProductEvaluate productEvaluate;

    // 回复内容
    private String replyContent;

    // 回复时间
    private Date createTime;
    // 回复人
    private User user;

    // 备注
    private String remark;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVALUATE_ID", nullable = false)
    public ProductEvaluate getProductEvaluate() {
        return productEvaluate;
    }

    public void setProductEvaluate(ProductEvaluate productEvaluate) {
        this.productEvaluate = productEvaluate;
    }

    @Column(nullable = false, length = 512)
    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = true, length = 512)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
