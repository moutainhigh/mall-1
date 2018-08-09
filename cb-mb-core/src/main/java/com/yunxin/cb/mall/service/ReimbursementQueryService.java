package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.*;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface ReimbursementQueryService {

    /**
     * 查询可报账分页列表
     * @param q
     * @return
     * @throws Exception
     */
    public PageFinder<ReimbursementVO> pageReimbursementQuery(Query q)throws Exception;

    /**
     * 报账
     * @param list
     * @return
     * @throws Exception
     */
    public ReimbursementSuccessVO addReimbursement(List<AddReimbursementRequestVO> list)throws Exception;

    /**
     * 已报账列表查询
     * @param q
     * @return
     * @throws Exception
     */
    PageFinder<AlreadyReimbursementVO> selectAlreadyReimbursementQuery(Query q)throws Exception;

    /**
     * 已报账详情
     * @param reimbursementId
     * @param cuntomerId
     * @return
     * @throws Exception
     */
    public AlreadyReimbursementVO selectAlreadyReimbursementDetail(int reimbursementId,int cuntomerId)throws Exception;

    /**
     * 用户取消报账
     * @param reimbursementId
     * @param cuntomerId
     * @throws Exception
     */
    public void cancelReimbursement(int reimbursementId,int cuntomerId)throws Exception;

    /**
     * 报账已完成列表
     * @param q
     * @return
     * @throws Exception
     */
    public PageFinder<CompleteReimbursementVO> selectCompleteReimbursement(Query q)throws Exception;

    /**
     * 报账已完成详情
     * @param reimbursementId
     * @param cuntomerId
     * @return
     * @throws Exception
     */
    public CompleteReimbursementDetailVO getCompleteReimbursementDetail(int reimbursementId,int cuntomerId)throws Exception;


}
