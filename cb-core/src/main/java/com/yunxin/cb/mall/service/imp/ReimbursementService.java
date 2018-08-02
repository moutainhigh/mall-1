package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.ReimbursementDao;
import com.yunxin.cb.mall.dao.ReimbursementOrderDao;
import com.yunxin.cb.mall.entity.Reimbursement;
import com.yunxin.cb.mall.entity.ReimbursementOrder;
import com.yunxin.cb.mall.entity.Reimbursement_;
import com.yunxin.cb.mall.service.IReimbursementService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * 报账信息
 */
@Service
@Transactional
public class ReimbursementService implements IReimbursementService {
    private static final Logger logger = LoggerFactory.getLogger(ReimbursementService.class);
    @Resource
    private ReimbursementDao reimbursementDao;
    @Resource
    private ReimbursementOrderDao reimbursementOrderDao;

    @Override
    public Page<Reimbursement> pageReimbursement(PageSpecification<Reimbursement> query) {
        query.setCustomSpecification(new CustomSpecification<Reimbursement>(){
            @Override
            public void buildFetch(Root<Reimbursement> root) {
                root.fetch(Reimbursement_.customer, JoinType.LEFT);
                root.fetch(Reimbursement_.reimbursementOrder, JoinType.LEFT);
            }
            @Override
            public void addConditions(Root<Reimbursement> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Reimbursement_.createTime)));
            }
        });
        return reimbursementDao.findAll(query,query.getPageRequest());
    }

    @Override
    public List<ReimbursementOrder> queryOrderByIds(int reimbursementId) {
        List<ReimbursementOrder> listOrder= reimbursementOrderDao.getReimbursementOrderById(reimbursementId);
        return listOrder;
    }
}
