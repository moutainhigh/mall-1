package com.yunxin.cb.mall.web.action.finance;

import com.yunxin.cb.mall.entity.FinancialLoanRepayment;
import com.yunxin.cb.mall.service.imp.FinancialLoanRepaymentService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能描述:还款控制器
 *
 * @auther yangzhen
 * @date 2018/9/4 15:25
 */
@Controller
@RequestMapping(value = "/financialLoanRepayment")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FinancialLoanRepaymentController {

    private static final Logger logger = LoggerFactory.getLogger(FinancialLoanRepaymentController.class);

    @Resource
    private FinancialLoanRepaymentService financialLoanRepaymentService;

    /**
     * 功能描述: 页面跳转
     *
     * @param
     * @return
     * @auther yangzhen
     * @date 2018/9/4 15:25
     */
    @RequestMapping(value = "financialLoanRepaymentLogs")
    public String loanRepaymentList(ModelMap modelMap) {
        return "finance/loanRepayment/financialLoanRepaymentLogs";
    }

    /**
     * 功能描述:列表查询
     *
     * @param
     * @return
     * @auther yangzhen
     * @date 2018/9/4 15:24
     */
    @RequestMapping(value = "pageLoanRepaymentList")
    @ResponseBody
    public Page<FinancialLoanRepayment> pageLoanRepaymentList(@RequestBody PageSpecification<FinancialLoanRepayment> query,
                                                              @RequestParam("loanRepaymentType") Integer loanRepaymentType,ModelMap modelMap) {
        return financialLoanRepaymentService.pageServiceFinancialLoanRepayment(query,loanRepaymentType);
    }

}
