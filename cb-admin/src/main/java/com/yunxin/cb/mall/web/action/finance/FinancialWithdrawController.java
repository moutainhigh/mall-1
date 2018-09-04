package com.yunxin.cb.mall.web.action.finance;

import com.yunxin.cb.mall.entity.FinancialWithdraw;
import com.yunxin.cb.mall.service.IFinancialWithdrawService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @title: 提现控制器
 * @auther: eleven
 * @date: 2018/8/10 15:01
 */
@Controller
@RequestMapping(value = "/withdraw")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FinancialWithdrawController {

    private static final Logger logger = LoggerFactory.getLogger(FinancialWithdrawController.class);

    @Resource
    private IFinancialWithdrawService financialWithdrawService;

    /**
     * @title: 页面跳转
     * @param: [modelMap]
     * @return: java.lang.String
     * @auther: eleven
     * @date: 2018/8/10 15:07
     */
    @RequestMapping(value = "withdrawList")
    public String withdraw(ModelMap modelMap) {
        return "finance/withdraw/withdrawList";
    }

    /**
     * @title: 列表查询
     * @param: [query, modelMap]
     * @return: org.springframework.data.domain.Page<com.yunxin.cb.mall.entity.FinancialWithdraw>
     * @auther: eleven
     * @date: 2018/8/10 15:07
     */
    @RequestMapping(value = "pageWithdrawList")
    @ResponseBody
    public Page<FinancialWithdraw> pageWithdrawList(@RequestBody PageSpecification<FinancialWithdraw> query, ModelMap modelMap) {
        return financialWithdrawService.pageServiceFinancialWithdraw(query);
    }

    /**
     * @title: 转账确认
     * @param: [ids]
     * @return: boolean
     * @auther: eleven
     * @date: 2018/8/10 15:07
     */
    @RequestMapping(value = "tansfer", method = RequestMethod.GET)
    @ResponseBody
    public boolean tansfer(@RequestParam("ids") String ids) {
        boolean flag=false;
        try{
            if(LogicUtils.isNotNullAndEmpty(ids)){
                int result=financialWithdrawService.tansfer(ids);
                if(result>0){
                    flag=true;
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return flag;
    }
}
