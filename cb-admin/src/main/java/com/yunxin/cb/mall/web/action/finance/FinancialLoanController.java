package com.yunxin.cb.mall.web.action.finance;

import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.service.IFinancialLoanService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @title: 借款管理控制器
 * @auther: pengcong
 * @date: 2018/9/4
 */
@Controller
@RequestMapping(value = "/loan")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FinancialLoanController {

    private static final Logger logger = LoggerFactory.getLogger(FinancialLoanController.class);

    @Resource
    private IFinancialLoanService financialLoanService;

    /**
     * @title: 页面跳转
     * @param: [modelMap]
     * @return: java.lang.String
     * @auther: pengcong
     * @date: 2018/9/4
     */
    @RequestMapping(value = "loanList")
    public String loan(ModelMap modelMap) {
        return "finance/loan/loanList";
    }

    /**
     * @title: 借款审批页面跳转
     * @param: [modelMap]
     * @return: java.lang.String
     * @auther: pengcong
     * @date: 2018/9/4
     */
    @RequestMapping(value = "loanApprovalList")
    public String loanApproval(ModelMap modelMap) {
        return "finance/loan/loanApprovalList";
    }
    /**
     * @title: 列表查询
     * @param: [query, modelMap]
     * @auther: pengcong
     * @date: 2018/9/4
     */
    @RequestMapping(value = "pageLoanList")
    @ResponseBody
    public Page<FinancialLoan> pageLoanList(@RequestBody PageSpecification<FinancialLoan> query, ModelMap modelMap,@RequestParam("state") int state) {
        return financialLoanService.pageServiceFinancialLoan(query,state);
    }
    /**
     * @title: 详情
     * @param: [query, modelMap]
     * @auther: pengcong
     * @date: 2018/9/4
     */
    @RequestMapping(value = "getLoanDetail", method = RequestMethod.GET)
    public String getLoanDetail(@RequestParam("loanId") int loanId, ModelMap modelMap, Locale locale) {
        FinancialLoan financialLoan = financialLoanService.getFinancialLoanDetailById(loanId);
        financialLoan.setAmount(financialLoan.getAmount().setScale(2,BigDecimal.ROUND_DOWN));
        financialLoan.setInterest(financialLoan.getInterest().setScale(2,BigDecimal.ROUND_DOWN));
        financialLoan.setRepayAmount(financialLoan.getRepayAmount().setScale(2,BigDecimal.ROUND_DOWN));
        financialLoan.setInterestRate(financialLoan.getInterestRate().setScale(2,BigDecimal.ROUND_DOWN));
        modelMap.addAttribute("financialLoan", financialLoan);
        return "finance/loan/loanDetail";
    }
    /**
     * @title: 审批页面
     * @param: [query, modelMap]
     * @auther: pengcong
     * @date: 2018/9/4
     */
    @RequestMapping(value = "toExamine", method = RequestMethod.GET)
    public String toExamine(@RequestParam("loanId") int loanId, ModelMap modelMap, Locale locale) {
        FinancialLoan financialLoan = financialLoanService.getFinancialLoanDetailById(loanId);
        financialLoan.setAmount(financialLoan.getAmount().setScale(2,BigDecimal.ROUND_DOWN));
        financialLoan.setInterest(financialLoan.getInterest().setScale(2,BigDecimal.ROUND_DOWN));
        financialLoan.setRepayAmount(financialLoan.getRepayAmount().setScale(2,BigDecimal.ROUND_DOWN));
        financialLoan.setInterestRate(financialLoan.getInterestRate().setScale(2,BigDecimal.ROUND_DOWN));
        modelMap.addAttribute("financialLoan", financialLoan);
        return "finance/loan/toExamine";
    }
    @ResponseBody
    @RequestMapping(value = "editFinancialLoan", method = RequestMethod.POST)
    public Map<String,Object> editFinancialLoan(@RequestParam("loanId") int loanId,@RequestParam("state") String state,@RequestParam("auditRemark") String auditRemark) {
        Map<String,Object> map = new HashMap<>();
        try {
            FinancialLoan financialLoan = new FinancialLoan();
            if(state.equals("APPLY_SUCCESS")){
                financialLoan.setState(LoanState.APPLY_SUCCESS);
            }else if(state.equals("APPLY_FAILURE")){
                financialLoan.setState(LoanState.APPLY_FAILURE);
            }
            financialLoan.setLoanId(loanId);
            financialLoan.setAuditRemark(auditRemark);
            financialLoanService.updateFinancialLoan(financialLoan);
            map.put("result","success");
        } catch (Exception e) {
            map.put("result","fail");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "transferAccounts",method = RequestMethod.GET)
    public Map<String,Object> transferAccounts(@RequestParam("loanId") int loanId,@RequestParam("state") LoanState state) {
        Map<String,Object> map = new HashMap<>();
        try{
            return financialLoanService.undateState(loanId,state);
        }catch (Exception ex){
            logger.info(ex.getMessage());
            map.put("result","fail");
        }
        return map;
    }

}
