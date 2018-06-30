package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.CommodityDao;
import com.yunxin.cb.mall.dao.LogisticDao;
import com.yunxin.cb.mall.dao.LogisticPriceDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.ILogisticsService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by sheh on 14-11-21.
 */
@Service
@Transactional
public class LogisticsService implements ILogisticsService {

    @Resource
    private LogisticDao logisticDao;

    @Resource
    private LogisticPriceDao logisticPriceDao;

    @Resource
    private CommodityDao commodityDao;

    @Override
    public Logistic addLogistics(Logistic logistic) throws EntityExistException {
        if (!logisticDao.isOrUnique(logistic, Logistic_.logisticName, Logistic_.logisticCode)) {
            throw new EntityExistException("物流公司名称或物流公司编码已存在");
        }
        return logisticDao.save(logistic);
    }

    @Override
    public Logistic updateLogistics(Logistic logistic) throws EntityExistException {
        if (!logisticDao.isOrUnique(logistic, Logistic_.logisticName, Logistic_.logisticCode)) {
            throw new EntityExistException("物流公司名称或物流公司编码已存在");
        }
        Logistic logistic1 = logisticDao.findOne(logistic.getLogisticId());
        AttributeReplication.copying(logistic, logistic1, Logistic_.logisticName, Logistic_.logisticCode, Logistic_.province, Logistic_.city,
                Logistic_.district, Logistic_.logisticAddress, Logistic_.url, Logistic_.linkman, Logistic_.telephone, Logistic_.mobile,
                Logistic_.email, Logistic_.weChatNo, Logistic_.qq, Logistic_.remark);
        return logistic1;
    }

    @Override
    public void removeLogisticById(int logisticsId) {
        logisticDao.delete(logisticsId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Logistic> pageLogistics(PageSpecification<Logistic> query) {
        query.setCustomSpecification(new CustomSpecification<Logistic>() {
            @Override
            public void addConditions(Root<Logistic> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.asc(root.get(Logistic_.logisticName)));
            }
        });
        Page<Logistic> pages = logisticDao.findAll(query, query.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Logistic findById(int logistics) {
        return logisticDao.findOne(logistics);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Logistic> findByEnable(boolean b) {
        return logisticDao.findByEnabled(b);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<LogisticPrice> findLogisticPricesByLogisticId(int logisticId) {
        return logisticPriceDao.findByLogistic_LogisticIdOrderByWeightPriceAsc(logisticId);
    }

    @Override
    public LogisticPrice addLogisticPrice(LogisticPrice logistic) throws EntityExistException {
        String[] cityCodes = logistic.getCityCode();
        if (LogicUtils.isNotNullAndEmpty(cityCodes)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cityCodes.length; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                sb.append(cityCodes[i]);
            }
            logistic.setCityCodes(sb.toString());
        }
        return logisticPriceDao.save(logistic);
    }

    @Override
    public LogisticPrice updateLogisticPrice(LogisticPrice logistic) throws EntityExistException {
        LogisticPrice price = logisticPriceDao.findOne(logistic.getPriceId());
        String[] cityCodes = logistic.getCityCode();
        if (LogicUtils.isNotNullAndEmpty(cityCodes)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cityCodes.length; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                sb.append(cityCodes[i]);
            }
            logistic.setCityCodes(sb.toString());
        }
        AttributeReplication.copying(logistic, price, LogisticPrice_.cityCodes,
                LogisticPrice_.weight,
                LogisticPrice_.weightPrice,
                LogisticPrice_.continuePrice,
                LogisticPrice_.remark);
        return price;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public LogisticPrice getLogisticPriceById(int priceId) {
        return logisticPriceDao.findOne(priceId);
    }

    @Override
    public void removeLogisticPrice(int priceId) {
        logisticPriceDao.delete(priceId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Logistic> getAllLogisticAndPrices() {
        return logisticDao.findAll(new Specification<Logistic>() {
            @Override
            public Predicate toPredicate(Root<Logistic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                root.fetch(Logistic_.logisticPrices, JoinType.LEFT);
                criteriaQuery.distinct(true);
                return null;
            }
        });
    }

    @Override
    public Commodity saveCommodityLogisticPrices(int commodityId, int[] priceIds) {
        Commodity commodity = commodityDao.findOne(commodityId);
        commodity.getLogisticPrices().clear();
        if (null != priceIds) {
            for (int priceId : priceIds) {
                LogisticPrice item = logisticPriceDao.findOne(priceId);
                commodity.getLogisticPrices().add(item);
            }
        }
        return commodity;
    }

    @Override
    public void enableLogisticById(int logisticId, boolean enabled) {
        logisticDao.enableLogisticById(enabled, logisticId);
    }
}
