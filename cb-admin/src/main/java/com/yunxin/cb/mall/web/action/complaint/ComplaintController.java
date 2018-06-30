package com.yunxin.cb.mall.web.action.complaint;

import com.yunxin.cb.mall.entity.Complaint;
import com.yunxin.cb.mall.service.IComplaintService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.Complaint;
import com.yunxin.cb.mall.service.IComplaintService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by chenxing on 2016/1/29.
 */
@Controller
@RequestMapping(value = "/operation")
public class ComplaintController {

    @Resource
    private IComplaintService complaintService;

    @RequestMapping(method = RequestMethod.GET)
    public String complaints() {
        return "operation/complaints";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<Complaint> pageComplaints(@RequestBody PageSpecification<Complaint> query) {
        Page<Complaint> page = complaintService.pageComplaints(query);
        return page;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String toAddComplaint(@ModelAttribute("complaint") Complaint complaint) {
        return "operation/addComplaint";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addComplaint(@Valid @ModelAttribute("complaint") Complaint complaint, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return toAddComplaint(complaint);
        }
        complaintService.addComplaint(complaint);
        return "redirect:../common/success.do?reurl=operation/complaints.do";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String updateComplaintStatusById(@RequestParam("enable") boolean enable, @RequestParam("complaintId") int complaintId, HttpServletRequest request) {
        complaintService.logicDeleteByArticleId(complaintId);
        return "redirect:../common/success.do?reurl=operation/complaints.do";
    }
}
