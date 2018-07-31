package com.yunxin.cb.mall.web.action.reimbursement;

import com.yunxin.cb.mall.entity.Reimbursement;
import com.yunxin.cb.mall.service.IReimbursementService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author wangteng
 */
@Controller
@RequestMapping(value = "/reimbursement")
public class ReimbursementController {
    @Resource
    private IReimbursementService iReimbursementService;
    @RequestMapping(value = "reimbursements", method = RequestMethod.GET)
    public String reimbursements() {
        return "reimbursement/reimbursements";
    }
    /**
     * 分页列表
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "pageReimbursement", method = RequestMethod.POST)
    @ResponseBody
    public Page<Reimbursement> pageReimbursement(@RequestBody PageSpecification<Reimbursement> query) {
        Page<Reimbursement> page = iReimbursementService.pageReimbursement(query);
        return page;
    }
}
