package com.yunxin.cb.console.service;

import com.yunxin.cb.console.entity.SystemLog;
import com.yunxin.cb.console.entity.meta.OperateType;
import com.yunxin.cb.mall.entity.OrderLog;
import com.yunxin.cb.mall.query.OrdersLogQuery;
import com.yunxin.cb.mall.query.SystemLogQuery;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * @author x001393
 */
public interface ILogsService {
    /**
     * 系统日志
     */
    public SystemLog addSystemLog(SystemLog systemLog);

    //	public Page<SystemLog> pageSystemLog(final PageSpecification<SystemLog> query);
    public Page<SystemLog> pageSystemLogs(SystemLogQuery logQusery);

    public long countSystemLog(SystemLogQuery logQusery);

    public void log(String msg);

    /**
     * @param request
     * @param u                  ：SecurityConstants.LOGIN_SESSION
     * @param msg：如修改，增加，删除了什么。。
     */
    public void log(HttpServletRequest request, String msg);

    /**
     * 增加系统日志2
     *
     * @param request
     * @param msg
     * @param operationId
     * @param type
     */
    public void addLog(HttpServletRequest request, String msg, int operationId, OperateType type);

    /**
     * 订单日志
     */

    public OrderLog addOrdersLog(OrderLog orderLog);

    //    public Page<OrderLog> pageOrdersLogs(final PageSpecification<OrderLog> query);
    public Page<OrderLog> pageOrdersLogs(OrdersLogQuery ordquery);

    public long countOrderLog(OrdersLogQuery ordquery);

    public void ordLog(String msg);

    public void ordLog(String orderCode, String msg, String handler);

    public Page<SystemLog> findByOperationId(final SystemLogQuery logQusery);

    SystemLog createSystemLog(SystemLog systemLog);
}
