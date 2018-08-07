package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.deserializer.JsonTimestampDeserializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author wangteng
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@ApiModel(value="用户群组",description="用户群组CustomerGroup")
public class CustomerGroup  implements java.io.Serializable {

    @ApiModelProperty(value="用户群组ID",name="customerGroupId",example="1")
    private int customerGroupId;
    @ApiModelProperty(value="创建用户",name="customer",example="1")
    private Customer customer;
    @ApiModelProperty(value="群组名",name="groupName",example="划拳")
    private String groupName;
    @ApiModelProperty(value="创建时间",name="createTime",example="2018 07-21")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private List<CustomerGroupUser> customerGroupUser=new ArrayList<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(int customerGroupId) {
        this.customerGroupId = customerGroupId;
    }
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CUSTOMER_ID",nullable = false, insertable = true, updatable = true)
    })
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Column(unique = true, nullable = false, length = 250)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "customerGroup")
    public List<CustomerGroupUser> getCustomerGroupUser() {
        return customerGroupUser;
    }

    public void setCustomerGroupUser(List<CustomerGroupUser> customerGroupUser) {
        this.customerGroupUser = customerGroupUser;
    }
}
