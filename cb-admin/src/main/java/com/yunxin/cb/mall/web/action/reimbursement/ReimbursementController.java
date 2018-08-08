package com.yunxin.cb.mall.web.action.reimbursement;

import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.Reimbursement;
import com.yunxin.cb.mall.service.IReimbursementService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @RequestMapping(value = "reimbursementOrders", method = RequestMethod.GET)
    public String reimbursementOrders(@RequestParam("reimbursementId") int reimbursementId, ModelMap modelMap) {
            List<OrderItem> orderItem =iReimbursementService.queryOrderItemByIds(reimbursementId);
            modelMap.put("orderItems",orderItem);
            modelMap.put("reimbursement",iReimbursementService.getReimbursement(reimbursementId));
        return "reimbursement/reimbursementOrders";
    }


    @RequestMapping(value = "reimbursementOrders", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> reimbursementOrders(@RequestParam("reimbursementId") int reimbursementId) {
        return new HashMap<String,Object>(){
            {
                put("data",iReimbursementService.queryOrderItemByIds(reimbursementId));
            }
        };
    }
}
