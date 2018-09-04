package com.yunxin.cb.mall.web.action.reimbursement;

import com.yunxin.cb.mall.entity.FinancialCashbackLog;
import com.yunxin.cb.mall.service.IFinancialCashbackLogService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/financialCashbackLog")
public class FinancialCashbackLogController {

    @Resource
    private IFinancialCashbackLogService iFinancialCashbackLogService;

    @RequestMapping(value = "financialCashbackLogs", method = RequestMethod.GET)
    public String financialCashbackLogs() {
        return "rb/financialCashbackLogs";
    }

    /**
     * 分页列表
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "pageFinancialCashbackLog", method = RequestMethod.POST)
    @ResponseBody
    public Page<FinancialCashbackLog> pageFinancialCashbackLog(@RequestBody PageSpecification<FinancialCashbackLog> query) {
        Page<FinancialCashbackLog> page = iFinancialCashbackLogService.pageFinancialCashbackLog(query);
        return page;
    }
}
