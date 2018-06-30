/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.CommodityDao;
import com.yunxin.cb.mall.dao.ComplaintDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.IComplaintService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author j000101
 */
@Service
@Transactional
public class ComplaintService implements IComplaintService {

    @Resource
    private CommodityDao commodityDao;

    @Resource
    private ComplaintDao complaintDao;


    @Override
    public Complaint addComplaint(Complaint complaint) {
        complaint.setCreateTime(new Date());
        return complaintDao.save(complaint);
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) throws EntityExistException {
        Complaint complaintOld = complaintDao.findOne(complaint.getComplaintId());
        AttributeReplication.copying(complaint, complaintOld, Complaint_.title, Complaint_.content, Complaint_.customer, Complaint_.enabled);
        return complaintOld;
    }

    @Override
    public void removeComplaint(int complaintId) {
        complaintDao.delete(complaintId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Complaint> pageComplaints(final PageSpecification<Complaint> query) {
        query.setCustomSpecification(new CustomSpecification<Complaint>() {
            @Override
            public void addConditions(Root<Complaint> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                Path<Boolean> delPath = root.get(Complaint_.enabled);
                predicates.add(builder.equal(delPath, false));
                query.orderBy(builder.asc(root.get(Complaint_.title)));
            }
        });
        Page<Complaint> complaints = complaintDao.findAll(query, query.getPageRequest());
        return complaints;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Complaint> pageComplaints(final PageSpecification<Complaint> query, Customer customer) {
        query.setCustomSpecification(new CustomSpecification<Complaint>() {
            @Override
            public void addConditions(Root<Complaint> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                Path<Customer> delPath = root.get(Complaint_.customer);
                predicates.add(builder.equal(delPath, customer));
                query.orderBy(builder.desc(root.get(Complaint_.createTime)));
            }
        });
        Page<Complaint> complaints = complaintDao.findAll(query, query.getPageRequest());
        return complaints;
    }

    @Override
    public void logicDeleteByArticleId(int complaintId) {
        Complaint complaintOld = complaintDao.findOne(complaintId);
        complaintOld.setEnabled(true);
    }


    @Override
    public ProductEvaluate addCommodityEvaluate(
            ProductEvaluate productEvaluates) {
        productEvaluates.setCreateTime(new Date());
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ProductEvaluate getCommodityEvaluateById(int evaId) {
        return null;
    }

    @Override
    public ProductEvaluate updateCommodityEvaluate(
            ProductEvaluate productEvaluates) {
        return null;
    }

    @Override
    public void removeCommodityEvaluateById(int evaId) {
        // commodityEvaluateDao.
        // delete(evaId);
    }

    @Override
    public ProductEvaluateReply addCommodityEvalReply(
            ProductEvaluateReply productEvaluateReply) {
        productEvaluateReply.setCreateTime(new Date());
        // return commodityEvalReplyDao.save(commodityEvalReply);
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductEvaluate> getAllEvaluate(Customer customer) {
        // return commodityEvaluateDao.findByCustomer(member);
        return null;
    }

    /**
     * 获取该用户投诉建议内容
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Complaint> getComplaintProposalByCustomer(
            Customer customer, int limit) {
        return complaintDao.findByCustomer(customer, new PageRequest(0,
                limit));
    }

    /**
     * 用户添加投诉建议内容
     */
    @Override
    public Complaint addComplaintProposal(Customer customer,
                                          Complaint complaint) {
        complaint.setCustomer(customer);
        complaint.setCreateTime(new Date());
        return complaintDao.save(complaint);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Complaint> getLastedComplaints(int limit) {
        return complaintDao.findByOrderCreateTime(new PageRequest(0, limit));
    }

}
