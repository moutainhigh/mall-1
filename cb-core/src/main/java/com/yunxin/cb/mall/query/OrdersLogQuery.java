package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.OrderLog;
import com.yunxin.core.persistence.PageSpecification;

import java.util.Date;

/**
 * @author x001393
 */
public class OrdersLogQuery extends PageSpecification<OrderLog> {

    //	private String accountName;
//	
//	private String userName;
    private String handler;

    private Date startDate;

    private Date endDate;


//	public String getAccountName() {
//		return accountName;
//	}
//
//	public void setAccountName(String accountName) {
//		this.accountName = accountName;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}

    public Date getStartDate() {
        return startDate;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
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


}
