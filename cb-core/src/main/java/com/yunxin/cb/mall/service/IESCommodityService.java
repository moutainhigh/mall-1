/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.CommodityEs;

import java.util.List;

/**
 * @author gonglei
 */
public interface IESCommodityService {

    public void addCommodity();

    public Iterable<CommodityEs> findAll();

    public List<CommodityEs> findByCommodityName(String commodityName);

}
