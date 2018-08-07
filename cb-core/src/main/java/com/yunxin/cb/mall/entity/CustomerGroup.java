package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author wangteng
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@ApiModel(value="用户群组",description="用户群组Customer")
public class CustomerGroup  implements java.io.Serializable {
    private int customerGroupId;
    private Customer customer;
    private String groupName;
    private Date crate_time;
}
