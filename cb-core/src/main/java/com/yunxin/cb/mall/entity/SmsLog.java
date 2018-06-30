package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 短信日志
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class SmsLog implements java.io.Serializable {


    private int smsId;


    private String smsCode;


    private String smsPhone;


    private String smsContent;


    private Date smsTime;


    private int smsResult;


    private String smsRemark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    @Column(nullable = false, length = 32)
    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Column(nullable = false, length = 11)
    public String getSmsPhone() {
        return smsPhone;
    }

    public void setSmsPhone(String smsPhone) {
        this.smsPhone = smsPhone;
    }

    @Column(nullable = false, length = 255)
    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, length = 7)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    @Column(nullable = false, precision = 4)
    public int getSmsResult() {
        return smsResult;
    }

    public void setSmsResult(int smsResult) {
        this.smsResult = smsResult;
    }

    @Column(nullable = true, length = 255)
    public String getSmsRemark() {
        return smsRemark;
    }

    public void setSmsRemark(String smsRemark) {
        this.smsRemark = smsRemark;
    }
}
