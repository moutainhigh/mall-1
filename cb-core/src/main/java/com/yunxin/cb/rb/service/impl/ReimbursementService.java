package com.yunxin.cb.rb.service.impl;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.dao.OrderItemDao;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.rb.dao.ReimbursementDao;
import com.yunxin.cb.rb.dao.ReimbursementProcessDao;
import com.yunxin.cb.rb.entity.Reimbursement;
import com.yunxin.cb.rb.entity.ReimbursementProcess;
import com.yunxin.cb.rb.entity.Reimbursement_;
import com.yunxin.cb.rb.entity.meta.ReimbursementProcessType;
import com.yunxin.cb.rb.entity.meta.ReimbursementType;
import com.yunxin.cb.rb.service.IReimbursementService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 报账信息wangteng
 */
@Service
@Transactional
public class ReimbursementService implements IReimbursementService {
    private static final Logger logger = LoggerFactory.getLogger(ReimbursementService.class);
    @Resource
    private ReimbursementDao reimbursementDao;
    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private ReimbursementProcessDao reimbursementProcessDao;
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
    public List<OrderItem> queryOrderItemByIds(int reimbursementId) {
        return orderItemDao.getOrderItemByReimbursement(reimbursementId);
    }



    @Override
    public Reimbursement getReimbursement(int reimbursementId) {
        return  reimbursementDao.findOne(reimbursementId);
    }

    @Override
    public boolean reimbursementAuditing(int reimbursementId, ReimbursementType reimbursementType,String remarks,int operType, HttpServletRequest request) {

       Reimbursement reimbursement=reimbursementDao.getOne(reimbursementId);
        User user = (User) request.getSession().getAttribute("loginSession");
        ReimbursementProcess reimbursementProcess=new ReimbursementProcess();
        switch (reimbursementType){
            //财务人员审批
            case FINANCE_IN_APPROVAL:
                if(!reimbursement.getOrderState().equals(ReimbursementType.FINANCE_IN_APPROVAL))
                    return false;
                //审核通过
                if(operType==1){
                    reimbursementProcess.setOrderState(ReimbursementProcessType.FINANCE_IN_APPROVAL);
                    reimbursementDao.updateReimbursementState(ReimbursementType.DIRECTOR_IN_APPROVAL,reimbursementId);
                }else{
                    reimbursementProcess.setOrderState(ReimbursementProcessType.FINANCE_NOT_PASS_THROUGH);
                    reimbursementDao.updateReimbursementState(ReimbursementType.NOT_PASS_THROUGH,reimbursementId);
                }

                break;
            //财务主管审批
            case DIRECTOR_IN_APPROVAL:
                if(!reimbursement.getOrderState().equals(ReimbursementType.DIRECTOR_IN_APPROVAL))
                    return false;
                //审核通过
                if(operType==1){
                    reimbursementProcess.setOrderState(ReimbursementProcessType.DIRECTOR_IN_APPROVAL);
                    reimbursementDao.updateReimbursementState(ReimbursementType.ALREADY_TO_ACCOUNT,reimbursementId);
                }else{
                    reimbursementProcess.setOrderState(ReimbursementProcessType.DIRECTOR_NOT_PASS_THROUGH);
                    reimbursementDao.updateReimbursementState(ReimbursementType.NOT_PASS_THROUGH,reimbursementId);
                }
                break;
        }
        reimbursementProcess.setCreateTime(new Date());
        reimbursementProcess.setRemarks(remarks==null?"":remarks);
        reimbursementProcess.setUser(user);
        reimbursementProcess.setReimbursement(reimbursement);
        reimbursementProcessDao.save(reimbursementProcess);
        return true;

    }
}
