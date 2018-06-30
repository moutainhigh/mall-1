package com.yunxin.cb.mall.query;

import com.yunxin.cb.console.entity.SystemLog;
import com.yunxin.core.persistence.PageSpecification;

import java.util.Date;

/**
 * @author x001393
 */
public class SystemLogQuery extends PageSpecification<SystemLog> {

    private String userName;

    private Date startDate;

    private Date endDate;

    private int operationId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }
}
