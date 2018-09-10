package com.yunxin.cb.mall.web.action.reimbursement;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.rb.entity.Reimbursement;
import com.yunxin.cb.rb.entity.meta.ReimbursementType;
import com.yunxin.cb.rb.service.IReimbursementService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "rb/reimbursements";
    }

    @RequestMapping(value = "reimbursementEa", method = RequestMethod.GET)
    public String reimbursementEa() {
        return "rb/reimbursementEa";
    }

    @RequestMapping(value = "reimbursementEam", method = RequestMethod.GET)
    public String reimbursementEam() {
        return "rb/reimbursementEam";
    }

    /**
     * 分页列表
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "pageReimbursement", method = RequestMethod.POST)
    @ResponseBody
    public Page<Reimbursement> pageReimbursement(@RequestBody PageSpecification<Reimbursement> query,@RequestParam("orderState") int orderState) {
        Page<Reimbursement> page = iReimbursementService.pageReimbursement(query,orderState);
        return page;
    }

    @RequestMapping(value = "reimbursementOrders", method = RequestMethod.GET)
    public String reimbursementOrders(@RequestParam("reimbursementId") int reimbursementId, ModelMap modelMap) {
            List<OrderItem> orderItem =iReimbursementService.queryOrderItemByIds(reimbursementId);
            modelMap.put("orderItems",orderItem);
            modelMap.put("reimbursement",iReimbursementService.getReimbursement(reimbursementId));
        return "rb/reimbursementOrders";
    }


    @RequestMapping(value = "reimbursementOrder", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> reimbursementOrder(@RequestParam("reimbursementId") int reimbursementId) {
        return new HashMap<String,Object>(){
            {
                put("data",iReimbursementService.queryOrderItemByIds(reimbursementId));
            }
        };
    }

    @RequestMapping(value = "reimbursementDetil", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> reimbursementDetil(@RequestParam("reimbursementId") int reimbursementId) {
        return new HashMap<String,Object>(){
            {
                put("data",iReimbursementService.findOrder(reimbursementId));
                put("reimbursement",iReimbursementService.getReimbursement(reimbursementId));
                put("reimbursementProcess",iReimbursementService.getReimbursementProcessByRe(reimbursementId));
            }
        };
    }

    /**
     * 审批
     * @param reimbursementId
     * @param reimbursementType
     * @param remarks
     * @param operType
     * @param request
     * @return
     */
    @RequestMapping(value = "reimbursementAuditing", method = RequestMethod.GET)
    @ResponseBody
    public String reimbursementAuditing(@RequestParam("reimbursementId") int reimbursementId,@RequestParam("reimbursementType") ReimbursementType reimbursementType,
                                                    @RequestParam("remarks") String remarks,@RequestParam("operType") int operType,HttpServletRequest request) {
            User user = (User) request.getSession().getAttribute("loginSession");
            return iReimbursementService.reimbursementAuditing(reimbursementId,reimbursementType,remarks,operType,user);
    }

}
