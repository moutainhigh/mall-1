package com.yunxin.cb.console.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.console.entity.meta.OperateType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 系统日志
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class SystemLog implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */

    private int logId;
    /**
     * 用户ID
     */

    private int userId;
    /**
     * 用户名
     */

    private String userName;

    /**
     * 操作时间
     */

    private Date createTime;
    /**
     * 操作事项
     */

    private String remark;

    private OperateType operateType;


    private String methodName;

    private String argNames;

    private String returning;

    public SystemLog() {
    }

    public SystemLog(OperateType operateType, String methodName, String argNames, String returning, Date operateTime, int userId, String userName) {
        this.operateType = operateType;
        this.methodName = methodName;
        this.argNames = argNames;
        this.returning = returning;
        this.createTime = operateTime;
        this.userId = userId;
        this.userName = userName;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getLogId() {
        return logId;
    }

    public void setLogId(int systemLogId) {
        this.logId = systemLogId;
    }

    @Column(nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(nullable = false, length = 64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Column(nullable = true, length = 1024)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Column(nullable = true, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    @Column(nullable = true, length = 255)
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Column(nullable = true, length = 255)
    public String getArgNames() {
        return argNames;
    }

    public void setArgNames(String argNames) {
        this.argNames = argNames;
    }

    @Column(nullable = true, length = 255)
    public String getReturning() {
        return returning;
    }

    public void setReturning(String returning) {
        this.returning = returning;
    }
}
