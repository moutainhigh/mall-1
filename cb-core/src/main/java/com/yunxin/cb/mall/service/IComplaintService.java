package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Complaint;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.ProductEvaluateReply;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IComplaintService {
    Complaint addComplaint(Complaint complaint);

    Complaint updateComplaint(Complaint complaint) throws EntityExistException;

    void removeComplaint(int complaintId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Complaint> pageComplaints(PageSpecification<Complaint> query);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Complaint> pageComplaints(PageSpecification<Complaint> query, Customer customer);

    void logicDeleteByArticleId(int articleId);


    ProductEvaluate addCommodityEvaluate(
            ProductEvaluate productEvaluates);

    ProductEvaluate getCommodityEvaluateById(int evaId);

    List<ProductEvaluate> getAllEvaluate(Customer customer);

    ProductEvaluate updateCommodityEvaluate(
            ProductEvaluate productEvaluates);

    void removeCommodityEvaluateById(int evaId);

    /**
     * 商品评价回复
     *
     * @param productEvaluateReply
     * @return
     */
    ProductEvaluateReply addCommodityEvalReply(
            ProductEvaluateReply productEvaluateReply);

    // 投诉建议
    List<Complaint> getComplaintProposalByCustomer(
            Customer customer, int limit);

    Complaint addComplaintProposal(Customer customer,
                                   Complaint complaint);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Complaint> getLastedComplaints(int limit);
}
