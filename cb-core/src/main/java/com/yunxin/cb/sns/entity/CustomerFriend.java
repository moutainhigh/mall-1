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

    private String aliasName;
    private String tag;
    private String phone;
    private String description;
    private String image;


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

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 60)
    public String getAliasName() {
        return this.aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 255)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 255)
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
