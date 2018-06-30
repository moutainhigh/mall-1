package com.yunxin.cb.console.service.imp;

import com.yunxin.cb.console.dao.SystemLogDao;
import com.yunxin.cb.console.entity.SystemLog;
import com.yunxin.cb.console.entity.SystemLog_;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.entity.meta.OperateType;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.dao.OrdersLogDao;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.OrderLog;
import com.yunxin.cb.mall.query.OrdersLogQuery;
import com.yunxin.cb.mall.query.SystemLogQuery;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.util.CalendarUtils;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author x001393
 */
@Service
@Transactional
public class LogsService implements ILogsService {

    private static final Logger logger = LoggerFactory.getLogger(LogsService.class);

    @Resource
    private SystemLogDao systemLogDao;

    @Resource
    private OrdersLogDao ordersLogDao;


    /**
     * 系统日志
     */

    @Override
    public SystemLog addSystemLog(SystemLog systemLog) {
        return systemLogDao.save(systemLog);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<SystemLog> pageSystemLogs(final SystemLogQuery logQusery) {

        createSystemLog(logQusery);
        Page<SystemLog> pages = systemLogDao.findAll(logQusery,
                logQusery.getPageRequest());

        return pages;
    }

    @Override
    public long countSystemLog(SystemLogQuery logQusery) {
        createSystemLog(logQusery);
        return systemLogDao.count(logQusery);
    }

    private void createSystemLog(final SystemLogQuery logQusery) {
        logQusery
                .setCustomSpecification(new CustomSpecification<SystemLog>() {

                    @Override
                    public void addConditions(Root<SystemLog> root,
                                              CriteriaQuery<?> query, CriteriaBuilder builder,
                                              List<Predicate> predicates) {
                        Date beginDate = logQusery.getStartDate();
                        Date endDate = logQusery.getEndDate();
                        if (beginDate != null) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(beginDate);
                            cal.set(Calendar.HOUR_OF_DAY, 0);
                            cal.set(Calendar.MINUTE, 0);
                            cal.set(Calendar.SECOND, 0);
                            cal.set(Calendar.MILLISECOND, 0);
                            beginDate = cal.getTime();
                            cal.add(Calendar.DATE, 1);
                            endDate = cal.getTime();
                        }
//                        if (LogicUtils.isNotNullAndEmpty(logQusery.getSearch1())) {
//                            String str = logQusery.getSearch1().replaceAll("\\s*", "").toUpperCase();
//                            predicates.add(builder.like(builder.upper(root.get(SystemLog_.userName)), "%" + str + "%"));
//
//                        }
//                        if (LogicUtils.isNotNullAndEmpty(logQusery.getSearch2())) {
//                            String str = logQusery.getSearch2().replaceAll("\\s*", "").toUpperCase();
//                            predicates.add(builder.like(builder.upper(root.get(SystemLog_.ip)), "%" + str + "%"));
//                        }
//                        query.orderBy(builder.desc(root.get(SystemLog_.time)));

                    }

                });
    }

    @Override
    public void log(String msg) {
        SystemLog systemlog = new SystemLog();
        if (LogicUtils.isNotNullAndEmpty(msg)) {
            systemlog.setRemark(msg);
        } else {
            return;
        }
//        systemlog.setTime(new Date());
//        systemlog.setType(OperateType.OTHER);
        systemLogDao.save(systemlog);
    }

    @Override
    public void log(HttpServletRequest request, String msg) {
        User user = (User) request.getSession().getAttribute("loginSession");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreateTime(new Date());
        systemLog.setUserId(user.getUserId());
        systemLog.setUserName(user.getUserName());
        String str = user.getUserName() + "于" + CalendarUtils.formatDate(new Date()).toString() + msg;
        systemLog.setRemark(str);
        systemLogDao.save(systemLog);
    }

    @Override
    public void addLog(HttpServletRequest request, String msg, int operationId, OperateType type) {
        SystemLog systemLog = new SystemLog();
        String path = request.getRequestURL().toString();
        String str;
        if (path.indexOf("mall") > 0) {
            Customer handler = (Customer) request.getSession().getAttribute("loginSession");
            systemLog.setUserId(handler.getCustomerId());
            systemLog.setUserName(handler.getAccountName());
            str = handler.getAccountName() + "于" + CalendarUtils.formatDate(new Date()).toString() + msg;
        } else {
            User handler = (User) request.getSession().getAttribute("loginSession");
            systemLog.setUserId(handler.getUserId());
            systemLog.setUserName(handler.getUserName());
            str = handler.getUserName() + "于" + CalendarUtils.formatDate(new Date()).toString() + msg;
        }

        systemLog.setRemark(str);
        systemLog.setCreateTime(new Date());
        systemLog.setOperateType(type);
        systemLogDao.save(systemLog);
    }

    /**
     * 订单日志
     */
    @Override
    public OrderLog addOrdersLog(OrderLog orderLog) {

        return ordersLogDao.save(orderLog);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<OrderLog> pageOrdersLogs(final OrdersLogQuery ordQuery) {
        Page<OrderLog> pages = ordersLogDao.findAll(ordQuery, ordQuery.getPageRequest());
        return pages;
    }

    @Override
    public long countOrderLog(OrdersLogQuery ordquery) {
        return ordersLogDao.count(ordquery);
    }


    @Override
    public void ordLog(String msg) {
        OrderLog orderLog = new OrderLog();
        if (LogicUtils.isNotNullAndEmpty(msg)) {
            orderLog.setRemark(msg);
        } else {
            return;
        }
        orderLog.setTime(new Date());
        ordersLogDao.save(orderLog);
    }

    @Override
    public void ordLog(String orderCode, String msg, String handler) {
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderCode(orderCode);
        if (LogicUtils.isNotNullAndEmpty(handler)) {
            orderLog.setHandler(handler);
        }
        orderLog.setTime(new Date());
        orderLog.setRemark(msg);
        ordersLogDao.save(orderLog);
    }

    @Override
    public Page<SystemLog> findByOperationId(final SystemLogQuery logQusery) {
        logQusery.setCustomSpecification(new CustomSpecification<SystemLog>() {

            @Override
            public void addConditions(Root<SystemLog> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                Date beginDate = logQusery.getStartDate();
                Date endDate = logQusery.getEndDate();
                if (beginDate != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(beginDate);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    beginDate = cal.getTime();
                    cal.add(Calendar.DATE, 1);
                    endDate = cal.getTime();
                }
                predicates.add(builder.greaterThan(root.get(SystemLog_.createTime), logQusery.getEndDate()));
            }
        });

        return systemLogDao.findAll(logQusery, logQusery.getPageRequest());
    }

    @Override
    public SystemLog createSystemLog(SystemLog systemLog) {
        return systemLogDao.save(systemLog);
    }

}
