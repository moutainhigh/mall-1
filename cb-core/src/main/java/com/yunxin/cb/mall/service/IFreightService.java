package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Freight;
import com.yunxin.cb.mall.query.FreightQuery;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

/**
 * @author qulei
 */
public interface IFreightService {

    public Freight getFreightByAreaCode(String areaCode);

    public Freight addFreight(Freight freight);

    public Page<Freight> pageFreight(PageSpecification<Freight> query);

    public Freight getFreightById(int freightId);

    public Freight updatefreight(Freight freight);

    public Page<Freight> listProvinceFreight(FreightQuery query);

    public Page<Freight> listFreightByParenCode(FreightQuery freightQuery, String code);

}
