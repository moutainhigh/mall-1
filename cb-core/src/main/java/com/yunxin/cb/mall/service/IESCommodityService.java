/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.query.CommodityQuery;
import com.yunxin.cb.mall.vo.Commodity;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author gonglei
 */
public interface IESCommodityService {

    public void addCommodity();

    public Iterable<CommodityEs> findAll();

    public List<CommodityEs> findByCommodityName(String commodityName);

}
