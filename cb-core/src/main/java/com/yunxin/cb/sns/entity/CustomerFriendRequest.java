


package com.yunxin.cb.sns.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 添加好友请求消息
 * @author
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class CustomerFriendRequest implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 请求ID
     */
    @Max(9999999999L)
    private int requestId;
    /**
     * 请求添加好友客户
     */
    private Customer customer;
    /**
     * 邀请添加好友
     */
    private Customer friendCustomer;
    /**
     *
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     *
     */
    @Length(max = 100)
    private String requestMessage;
    /**
     * 0,新请求；1,同意，2，拒绝，3，忽略
     */
    @Max(9999999999L)
    private int state;
    //columns END


    public CustomerFriendRequest() {
    }

    public CustomerFriendRequest(
            int requestId
    ) {
        this.requestId = requestId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getRequestId() {
        return this.requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }



    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 100)
    public String getRequestMessage() {
        return this.requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CUSTOMER_ID", nullable = false, insertable = true, updatable = true)
    })
    public Customer getCustomer() {
        return customer;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "FRIEND_ID", nullable = false, insertable = true, updatable = true)
    })
    public Customer getFriendCustomer() {
        return friendCustomer;
    }

    public void setFriendCustomer(Customer friendCustomer) {
        this.friendCustomer = friendCustomer;
    }
}