package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinacialInsuCashbackLogDao;
import com.yunxin.cb.mall.entity.FinacialInsuCashbackLog;
import com.yunxin.cb.mall.entity.FinacialInsuCashbackLog_;
import com.yunxin.cb.mall.service.IFinacialInsuCashbackLogService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class FinacialInsuCashbackLogService implements IFinacialInsuCashbackLogService {
    @Resource
    private FinacialInsuCashbackLogDao finacialInsuCashbackLogDao;
    @Override
    public Page<FinacialInsuCashbackLog> pageFinacialInsuCashbackLog(PageSpecification<FinacialInsuCashbackLog> query) {
        query.setCustomSpecification(new CustomSpecification<FinacialInsuCashbackLog>(){
            @Override
            public void buildFetch(Root<FinacialInsuCashbackLog> root) {
                root.fetch(FinacialInsuCashbackLog_.customer, JoinType.LEFT);
            }
            @Override
            public void addConditions(Root<FinacialInsuCashbackLog> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {

                query.orderBy(builder.asc(root.get(FinacialInsuCashbackLog_.createTime)));
            }
        });
        return finacialInsuCashbackLogDao.findAll(query,query.getPageRequest());
    }
}
