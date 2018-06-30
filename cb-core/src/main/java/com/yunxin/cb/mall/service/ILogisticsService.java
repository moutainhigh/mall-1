package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Logistic;
import com.yunxin.cb.mall.entity.LogisticPrice;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sheh on 14-11-21.
 */
public interface ILogisticsService {

    public Logistic addLogistics(Logistic logistic) throws EntityExistException;

    public Logistic updateLogistics(Logistic logistic) throws EntityExistException;

    public void removeLogisticById(int logisticsId);

    public Page<Logistic> pageLogistics(final PageSpecification<Logistic> query);

    public Logistic findById(int logistics);


    List<Logistic> findByEnable(boolean b);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<LogisticPrice> findLogisticPricesByLogisticId(int logisticId);

    LogisticPrice addLogisticPrice(LogisticPrice logistic) throws EntityExistException;

    LogisticPrice updateLogisticPrice(LogisticPrice logistic) throws EntityExistException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    LogisticPrice getLogisticPriceById(int priceId);

    void removeLogisticPrice(int priceId);

    /***
     * 获取所有物流公司和物流价格
     * @return
     */
    List<Logistic> getAllLogisticAndPrices();

    Commodity saveCommodityLogisticPrices(int commodityId, int[] priceIds);

    public void enableLogisticById(int logisticId, boolean enabled);
}
