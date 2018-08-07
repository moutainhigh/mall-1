package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.AddReimbursementRequestVO;
import com.yunxin.cb.mall.vo.ReimbursementSuccessVO;
import com.yunxin.cb.mall.vo.ReimbursementVO;
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


}
