package com.yunxin.cb.mall.web.action.finance;

import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.mall.entity.FinancialWallet;
import com.yunxin.cb.mall.service.IFinancialLoanBillService;
import com.yunxin.cb.mall.service.IFinancialWalletService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/liabilities")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FinancialWalletController {

    private static final Logger logger = LoggerFactory.getLogger(FinancialWalletController.class);

    @Resource
    private IFinancialWalletService financialWalletService;
    @Resource
    private IFinancialLoanBillService financialLoanBillService;

    /**
     * @title: 页面跳转
     * @param: [modelMap]
     * @return: java.lang.String
     * @auther: pengcong
     * @date: 2018/9/4
     */
    @RequestMapping(value = "liabilitiesList")
    public String loan(ModelMap modelMap) {
        return "finance/wallet/financialWalletList";
    }

    /**
     * @title: 列表查询
     * @param: [query, modelMap]
     * @auther: pengcong
     * @date: 2018/9/4
     */
    @RequestMapping(value = "pageLiabilitiesList")
    @ResponseBody
    public Page<FinancialWallet> pageLiabilitiesList(@RequestBody PageSpecification<FinancialWallet> query, ModelMap modelMap) {
        return financialWalletService.pageServiceFinancialWallet(query);
    }

    @ResponseBody
    @RequestMapping(value = "choosePagedLiabilities",method = RequestMethod.POST)
    public Page<FinancialLoanBill> choosePagedLiabilities(@RequestBody PageSpecification<FinancialLoanBill> query, HttpServletRequest request) {
        return financialLoanBillService.pageServiceFinancialLoanBill(query);
    }
}
