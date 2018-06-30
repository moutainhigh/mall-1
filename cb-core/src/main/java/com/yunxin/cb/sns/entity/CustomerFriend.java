package com.yunxin.cb.sns.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.core.web.json.deserializer.JsonTimestampDeserializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gonglei on 2015/6/13.
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class CustomerFriend implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CustomerFriendId id;

    private Customer customer;

    private Customer friend;

    private Date createTime;


    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "customerId", column = @Column(name = "CUSTOMER_ID", nullable = false)), @AttributeOverride(name = "friendId", column =
    @Column(name = "FRIEND_ID", nullable =
            false))})
    public CustomerFriendId getId() {
        return id;
    }

    public void setId(CustomerFriendId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false, insertable = false, updatable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FRIEND_ID", nullable = false, insertable = false, updatable = false)
    public Customer getFriend() {
        return friend;
    }

    public void setFriend(Customer contact) {
        this.friend = contact;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date lastChatTime) {
        this.createTime = lastChatTime;
    }


}
