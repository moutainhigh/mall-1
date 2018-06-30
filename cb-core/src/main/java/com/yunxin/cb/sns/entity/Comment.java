package com.yunxin.cb.sns.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by gonglei on 16/3/15.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private int commentId;

    private Customer customer;

    private Share share;

    private String content;

    private Date commentTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHARE_ID", nullable = false)
    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Column(length = 1024, nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
