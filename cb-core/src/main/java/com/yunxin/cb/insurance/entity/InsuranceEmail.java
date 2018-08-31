package com.yunxin.cb.insurance.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 保单合同号邮件提醒
 *
 * @author wangteng
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceEmail implements Serializable {

    @ApiModelProperty(value="保单合同号邮件提醒ID",name="insuranceEmailId",example="1")
    @Max(9999999999L)
    private int insuranceEmailId;

    @ApiModelProperty(value="发送邮箱",name="fromEmail",example="1")
    private String fromEmail;

    @ApiModelProperty(value="接收邮箱",name="receiveEmail",example="1")
    private String receiveEmail;

    @ApiModelProperty(value="接收文本",name="context",example="1")
    private String context;

    @ApiModelProperty(value="创建时间",name="createTime",example="1")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;



    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getInsuranceEmailId() {
        return insuranceEmailId;
    }

    public void setInsuranceEmailId(int insuranceEmailId) {
        this.insuranceEmailId = insuranceEmailId;
    }
    @Column
    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
    @Column
    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }
    @Column
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
