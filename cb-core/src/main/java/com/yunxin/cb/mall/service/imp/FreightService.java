package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FreightDao;
import com.yunxin.cb.mall.entity.Freight;
import com.yunxin.cb.mall.entity.Freight_;
import com.yunxin.cb.mall.query.FreightQuery;
import com.yunxin.cb.mall.service.IFreightService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author qulei
 */

@Service
@Transactional
public class FreightService implements IFreightService {

    @Resource
    private FreightDao freightDao;

    @Override
    public Freight addFreight(Freight freight) {
        return freightDao.save(freight);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Freight> pageFreight(final PageSpecification<Freight> freightQuery) {
        freightQuery.setCustomSpecification(new CustomSpecification<Freight>() {
            @Override
            public void addConditions(Root<Freight> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
//                super.addConditions(root, query, builder, predicates);
//				if (LogicUtils.isNotNullAndEmpty(freightQuery.getSearch1())) {
//					String str = freightQuery.getSearch1().replaceAll("\\s*", "").toUpperCase();
//					predicates.add(builder.like(builder.upper(root.get(Freight_.areaCode)), "%" + str + "%"));
//				}
            }
        });

        Page<Freight> pages = freightDao.findAll(freightQuery,
                freightQuery.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Freight getFreightById(int freightId) {
        return freightDao.findOne(freightId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Freight> listProvinceFreight(
            FreightQuery freightQuery) {

        freightQuery.setCustomSpecification(new CustomSpecification<Freight>() {
            @Override
            public void addConditions(Root<Freight> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                predicates.add(builder.like(root.get(Freight_.areaCode), "%0000"));
            }
        });

        Page<Freight> page = freightDao.findAll(freightQuery, freightQuery.getPageRequest());
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Freight> listFreightByParenCode(
            FreightQuery freightQuery, final String code) {

        freightQuery.setCustomSpecification(new CustomSpecification<Freight>() {

            @Override
            public void addConditions(Root<Freight> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                predicates.add(builder.like(root.get(Freight_.areaCode), code));

                String areaCode2 = "";
                if (code.endsWith("00")) {
                    areaCode2 = code.substring(0, 2) + "0000";
                }
                if (code.endsWith("%")) {
                    areaCode2 = code.substring(0, 4) + "00";
                }
                predicates.add(builder.notEqual(root.get(Freight_.areaCode), areaCode2));
            }
        });

        Page<Freight> page = freightDao.findAll(freightQuery, freightQuery.getPageRequest());
        return page;
    }

    @Override
    public Freight updatefreight(Freight freight) {
        Freight dbfreight = freightDao.findByAreaCode(freight.getAreaCode());
        dbfreight.setAreaCode(freight.getAreaCode());
//		dbfreight.setPrice(freight.getPrice());
        return dbfreight;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Freight getFreightByAreaCode(String areaCode) {
        return freightDao.findByAreaCode(areaCode);
    }

}
