/*
 * Powered By [microlink-framework]
 * Web Site: http://www.microlinktech.net
 * Since 2015 - 2018
 */


package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.deserializer.JsonTimestampDeserializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 *
 * @author aidy www.microlinktech.net
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     *
     */
    @Max(9999999999L)
    private int id;
    /**
     *
     */
    @Max(9999999999L)
    private int customerId;
    /**
     *
     */
    @Length(max = 255)
    private String content;
    /**
     *
     */
    @Length(max = 1024)
    private String images;

    /**
     * 创建时间
     */
    private Date createTime;
    //columns END


    public Feedback() {
    }

    public Feedback(
            int id
    ) {
        this.id = id;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 255)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 1024)
    public String getImages() {
        return this.images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "customer_id", nullable = false, insertable = true, updatable = true)
    })
    public Customer getCustomer() {
        return customer;
    }


}