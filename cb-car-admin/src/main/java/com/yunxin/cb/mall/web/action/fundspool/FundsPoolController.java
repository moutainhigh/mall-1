package com.yunxin.cb.mall.web.action.fundspool;

import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.service.IOrderService;
import com.yunxin.cb.rb.entity.FundsPool;
import com.yunxin.cb.rb.entity.FundsPoolLog;
import com.yunxin.cb.rb.entity.Reimbursement;
import com.yunxin.cb.rb.service.IFundsPoolService;
import com.yunxin.cb.rb.service.IReimbursementService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.common.ConstantsCB;
import com.yunxin.common.ConstantsCBEnumMap;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
* @Description:    资金池Controller
* @Author:         lxc
* @CreateDate:     2018/08/07 17:36
*/
@Controller
@RequestMapping(value = "/fundsPool")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FundsPoolController {

    @Resource
    private IFundsPoolService fundsPoolService;
    @Resource
    private IReimbursementService reimbursementService;
    @Resource
    private IOrderService orderService;
    /**
     * @Description:        页面跳转
     * @author: lxc
     * @param modelMap
     * @Return java.lang.String:
     * @DateTime: 2018/8/7 18:26
     */
    @RequestMapping(value = "fundsPools")
    public String fundsPools(ModelMap modelMap) {
        return "fundspool/fundsPool";
    }

    /**
     * @Description:    获取FundsPool的分页信息
     * @author: lxc
     * @param fundsPoolQuery
     * @Return org.springframework.data.domain.Page<com.yunxin.cb.rb.entity.FundsPool>:
     * @DateTime: 2018/8/7 17:44
     */
    @RequestMapping(value = "pageFundsPool")
    @ResponseBody
    public Page<FundsPool> pageFundsPool(@RequestBody PageSpecification<FundsPool> fundsPoolQuery,ModelMap modelMap) {
        return fundsPoolService.pageFundsPool(fundsPoolQuery);
    }

    /**
     * @Description:    详情
     * @author: lxc
     * @param id
     * @param modelMap
     * @Return java.lang.String:
     * @DateTime: 2018/8/7 21:19
     */
    @RequestMapping(value = "fundsPoolDetail",method = RequestMethod.GET)
    public String fundsPoolDetail(@RequestParam("id") int id,ModelMap modelMap) {
        FundsPool fundsPool = fundsPoolService.getFundsPoolByid(id);
        modelMap.addAttribute("fundsPool", fundsPool);
        return "fundspool/fundsPoolLog";
    }


    /**
     * @Description:        页面跳转
     * @author: lxc
     * @param modelMap
     * @Return java.lang.String:
     * @DateTime: 2018/8/7 18:26
     */
    @RequestMapping(value = "fundsPoolLogs")
    public String fundsPoolLogs(ModelMap modelMap) {
        List<FundsPool> list = fundsPoolService.getFundsPoolList();
        modelMap.addAttribute("fundsPoolList", list);
        return "fundspool/fundsPoolLog";
    }

    /**
     * @Description:    获取FundsPoolLog的分页信息
     * @author: lxc
     * @param fundsPoolLogQuery
     * @Return org.springframework.data.domain.Page<com.yunxin.cb.rb.entity.FundsPool>:
     * @DateTime: 2018/8/7 17:44
     */
    @RequestMapping(value = "pageFundsPoolLog")
    @ResponseBody
    public Page<FundsPoolLog> pageFundsPoolLog(@RequestBody PageSpecification<FundsPoolLog> fundsPoolLogQuery, ModelMap modelMap) {
//        fundsPoolService.updateAndCountOrderAmout(106);//测试
//        fundsPoolService.updateAndCountReimbursementAmout(18);//测试
        return fundsPoolService.pageFundsPoolLog(fundsPoolLogQuery);
    }

    /**
     * @Description:    详情
     * @author: lxc
     * @param id
     * @param modelMap
     * @Return java.lang.String:
     * @DateTime: 2018/8/7 21:19
     */
    @RequestMapping(value = "fundsPoolLogDetail",method = RequestMethod.GET)
    public String fundsPoolLogDetail(@RequestParam("id") int id,ModelMap modelMap) {
        FundsPoolLog fundsPoolLog = fundsPoolService.getFullFundsPoolLogByid(id);
        if(fundsPoolLog.getType() == ConstantsCB.FundsPoolLogType.REIMBURSE.getStatus()) {
            Reimbursement reimbursement = reimbursementService.getReimbursement(fundsPoolLog.getTransactionId());
            modelMap.addAttribute("reimbursement", reimbursement);
        }else if (fundsPoolLog.getType() == ConstantsCB.FundsPoolLogType.GRAND.getStatus()){
            Order order = orderService.getOrderDetailById(fundsPoolLog.getTransactionId());
            modelMap.addAttribute("order", order);
        }
        modelMap.addAttribute("fundsPoolLog", fundsPoolLog);
        modelMap.addAttribute("fundsPoolLogTypeMap", ConstantsCBEnumMap.getFundsPoolLogType());
        return "fundspool/fundsPoolLogDetail";
    }
}
