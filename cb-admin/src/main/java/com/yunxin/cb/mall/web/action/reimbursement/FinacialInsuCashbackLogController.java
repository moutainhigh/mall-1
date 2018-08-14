package com.yunxin.cb.mall.web.action.reimbursement;

import com.yunxin.cb.mall.entity.FinacialInsuCashbackLog;
import com.yunxin.cb.mall.service.IFinacialInsuCashbackLogService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/finacialInsuCashbackLog")
public class FinacialInsuCashbackLogController {

    @Resource
    private IFinacialInsuCashbackLogService iFinacialInsuCashbackLogService;

    @RequestMapping(value = "finacialInsuCashbackLogs", method = RequestMethod.GET)
    public String finacialInsuCashbackLogs() {
        return "rb/finacialInsuCashbackLogs";
    }

    /**
     * 分页列表
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "pageFinacialInsuCashbackLog", method = RequestMethod.POST)
    @ResponseBody
    public Page<FinacialInsuCashbackLog> pageFinacialInsuCashbackLog(@RequestBody PageSpecification<FinacialInsuCashbackLog> query) {
        Page<FinacialInsuCashbackLog> page = iFinacialInsuCashbackLogService.pageFinacialInsuCashbackLog(query);
        return page;
    }
}
