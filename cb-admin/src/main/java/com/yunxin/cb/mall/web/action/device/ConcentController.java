package com.yunxin.cb.mall.web.action.device;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.entity.meta.PadState;
import com.yunxin.cb.monitor.entity.Concent;
import com.yunxin.cb.monitor.entity.Device;
import com.yunxin.cb.monitor.service.IConcentService;
import com.yunxin.cb.monitor.service.IDeviceService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.entity.meta.PadState;
import com.yunxin.cb.monitor.entity.Concent;
import com.yunxin.cb.monitor.entity.Device;
import com.yunxin.cb.monitor.service.IConcentService;
import com.yunxin.cb.monitor.service.IDeviceService;
import com.yunxin.cb.security.SecurityConstants;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by chenxing on 2016/3/23.
 */
@Controller
@RequestMapping(value = "/concent")
public class ConcentController {


    @Resource
    private IConcentService concentService;

    @Resource
    private IDeviceService deviceService;


    @RequestMapping(value = "concents",method = RequestMethod.GET)
    public String concents() {
        return "concent/concents";
    }

    @RequestMapping(value = "pageConcents",method = RequestMethod.POST)
    @ResponseBody
    public Page<Concent> pageConcents(@RequestBody PageSpecification<Concent> query) {
        Page<Concent> page = concentService.pageConcents(query);
        return page;
    }

    @RequestMapping(value = "getDevicesByConcentId",method = RequestMethod.POST)
    @ResponseBody
    public List<Device> getDevicesByConcentId(@RequestBody PageSpecification pageRequest) {
        String concentId = (String) pageRequest.getData().get("concentId");
        return concentService.getDevicesByConcentId(Integer.parseInt(concentId));
    }

    @RequestMapping(value = "toAddConcent",method = RequestMethod.GET)
    public String toAddConcent(@ModelAttribute("concent") Concent concent, ModelMap modelMap) {
        return "concent/addConcent";
    }

    @RequestMapping(value = "addConcent",method = RequestMethod.POST)
    public String addConcent(@ModelAttribute("concent") Concent concent, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return toAddConcent(concent, modelMap);
        }
        try {
            concentService.addConcent(concent);
        } catch (Exception e) {
            result.addError(new FieldError("concent", "concentName", concent.getConcentName(), true, null, null,e.getMessage()));
            return toAddConcent(concent, modelMap);
        }
        return "redirect:../common/success.do?concent=concent/concents.do";
    }

    @RequestMapping(value = "toEditConcent",method = RequestMethod.GET)
    public String toEditConcent(@RequestParam("concentId") int concentId, ModelMap modelMap) {

        Concent concent = concentService.getConcentById(concentId);
        modelMap.addAttribute("concent", concent);
        return "concent/editConcent";
    }

    @RequestMapping(value = "editConcent",method = RequestMethod.POST)
    public String editConcent(@Valid @ModelAttribute("concent") Concent concent, BindingResult result,
                                     ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toEditConcent(concent.getConcentId(), modelMap);
        }
        try {
            concentService.updateConcent(concent);
            return "redirect:../common/success.do?reurl=concent/concents.do";
        } catch (Exception e) {
            result.addError(new FieldError("concent", "concentName", concent.getConcentName(), true, null, null,e.getMessage()));
            return toEditConcent(concent.getConcentId(), modelMap);
        }
    }

    @RequestMapping(value = "removeConcentById",method = RequestMethod.GET)
    @ResponseBody
    public String removeConcentById(@RequestParam("concentId") int concentId) {
        try {
            concentService.removeConcentById(concentId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "concentAudit",method = RequestMethod.GET)
    @ResponseBody
    public boolean concentAudit(@RequestParam("concentId") int concentId, @RequestParam("padState") PadState padState, @RequestParam("remark") String remark) {
        try {
            concentService.concentAudit(concentId, padState, remark);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @RequestMapping(value = "devices",method = RequestMethod.GET)
    public String devices(ModelMap modelMap) {
        List<Concent> concents = concentService.getAllConcents();
        modelMap.addAttribute("concents", concents);
        return "concent/devices";
    }

    @RequestMapping(value = "pageDevices",method = RequestMethod.POST)
    @ResponseBody
    public Page<Device> pageDevices(@RequestBody PageSpecification<Device> query,HttpSession session) {
        User user =(User)session.getAttribute(SecurityConstants.LOGIN_SESSION);
        Seller seller =(Seller)session.getAttribute(SecurityConstants.LOGIN_SELLER);
        boolean flag=false;
        for (Role role : user.getRoles()) {
            if(role.getRoleCode().equals("SUPER_ROLE")){
                flag=true;
                break;
            }
        }
        Page<Device> page =null;
        if(flag){
            page = deviceService.pageDevices(query);
        }else{
            PageSpecification.FilterDescriptor filterDescriptor = new PageSpecification.FilterDescriptor();
            filterDescriptor.setLogic("and");
            PageSpecification.FilterDescriptor filterDescriptor1 = new PageSpecification.FilterDescriptor();
            filterDescriptor1.setField("seller.sellerId");
            filterDescriptor1.setLogic("and");
            filterDescriptor1.setOperator("eq");
            filterDescriptor1.setValue(seller.getSellerId());
            filterDescriptor.getFilters().add(filterDescriptor1);
            query.setFilter(filterDescriptor);
            page = deviceService.pageDevices(query);
        }
        return page;
    }

    @RequestMapping(value = "toAddDevice",method = RequestMethod.GET)
    public String toAddDevice(@ModelAttribute("device") Device device, ModelMap modelMap) {
        List<Concent> concents = concentService.getAllConcents();
        modelMap.addAttribute("concents", concents);
        return "concent/addDevice";
    }

    @RequestMapping(value = "addDevice",method = RequestMethod.POST)
    public String addDevice(@ModelAttribute("device") Device device, BindingResult result, ModelMap modelMap,HttpSession session) {
        Seller seller =(Seller)session.getAttribute(SecurityConstants.LOGIN_SELLER);
        try {
            device.setSeller(seller);
            deviceService.addDevice(device);
        } catch (Exception e) {
            result.addError(new FieldError("device", "deviceCode", device.getDeviceCode(), true, null, null,e.getMessage()));
            return toAddDevice(device, modelMap);
        }
        return "redirect:../common/success.do?reurl=concent/devices.do";
    }

    @RequestMapping(value = "toEditDevice",method = RequestMethod.GET)
    public String toEditDevice(@RequestParam("deviceId") int deviceId, ModelMap modelMap) {
        List<Concent> concents = concentService.getAllConcents();
        modelMap.addAttribute("concents", concents);
        modelMap.addAttribute("device", deviceService.getDeviceById(deviceId));
        return "concent/editDevice";
    }

    @RequestMapping(value = "editDevice",method = RequestMethod.POST)
    public String editDevice(@ModelAttribute("device") Device device, BindingResult result, ModelMap modelMap,HttpSession session) {
        try {
            deviceService.editDevice(device);
        } catch (Exception e) {
            result.addError(new FieldError("device", "deviceCode", device.getDeviceCode(), true, null, null,e.getMessage()));
            return toEditDevice(device.getDeviceId(), modelMap);
        }
        return "redirect:../common/success.do?reurl=concent/devices.do";
    }
}
