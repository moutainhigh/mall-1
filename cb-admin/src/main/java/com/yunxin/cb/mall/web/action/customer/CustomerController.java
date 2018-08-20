package com.yunxin.cb.mall.web.action.customer;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.service.IAddressService;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IRankService;
import com.yunxin.cb.util.HttpsUtils;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.CalendarUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//import com.yunxin.cb.mall.entity.meta.CustomerCountry;
//import com.yunxin.cb.mall.query.CustomerQuery;

/**
 * @author z001075
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Resource
    private ICustomerService customerService;

    @Resource
    private IRankService rankService;

    @Resource
    private IAddressService addressService;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "customers")
    public String customers() {
        return "customer/customers";
    }

    @RequestMapping(value = "pageCustomers",method = RequestMethod.POST)
    @ResponseBody
    public Page<Customer> pageCustomers(@RequestBody PageSpecification<Customer> specification) {
        Page<Customer> page = customerService.pageCustomers(specification);
        return page;
    }

    @RequestMapping(value = "customerDetail",method = RequestMethod.GET)
    public String customerDetail(@ModelAttribute("customerId") int customerId, ModelMap modelMap) {
        Customer customer=customerService.getCustomerById(customerId);

        //S     add by lxc  2018-08-07        customer.getRecommendCustomer()推荐人的数据时,报could not initialize proxy - no Session,拿到推荐人id,再找推荐人信息
        if(null!=customer.getRecommendCustomer()){
            Customer recommendCustomer = customerService.findByCustomerId(customer.getRecommendCustomer().getCustomerId());
            customer.setRecommendCustomer(recommendCustomer);
        }

        //E

        List<Customer> listVo=customerService.findCustomerByLikeLevelCode(customer);
        modelMap.addAttribute("listVo", listVo);
        modelMap.addAttribute("customer", customer);
        List<DeliveryAddress> deliveryAddresses = addressService.getAllAddressesByCustomerId(customerId);
        modelMap.addAttribute("deliveryAddresses", deliveryAddresses);
        return "customer/customerDetail";
    }

    @RequestMapping(value = "toAddCustomer",method = RequestMethod.GET)
    public String toAddCustomer(@ModelAttribute("customer") Customer customer, ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("ranks", rankService.getAllRanks());
        return "customer/addCustomer";
    }

    @RequestMapping(value = "addCustomer",method = RequestMethod.POST)
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return customers();
        }
        try {
            customerService.addAdminCustomer(customer);
            return "redirect:../common/success.do?reurl=customer/customers.do";
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("msgContent", e.getMessage());
            return "redirect:../common/failure.do?reurl=customer/customers.do";
        }
    }

    @RequestMapping(value = "toEditCustomer",method = RequestMethod.GET)
    public String toEditCustomer(@RequestParam("customerId") int customerId, ModelMap modelMap) {
        Customer customer = customerService.getCustomerById(customerId);
        modelMap.addAttribute("customer",customerService.getCustomerById(customerId));
        return "customer/editCustomer";
    }


    @RequestMapping(value = "editCustomer",method = RequestMethod.POST)
    public String editCustomer(@Valid @ModelAttribute("customer") Customer customer, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return customers();
        }
        try {
            customerService.updateCustomer(customer);
            return "redirect:../common/success.do?reurl=customer/customers.do";
        } catch (EntityExistException e) {
            result.addError(new FieldError("customer", "accountName", customer.getAccountName(), true, null, null,
                    messageSource.getMessage("accountName", null, locale)));
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=customer/customers.do";
        }
    }

    @RequestMapping(value = "updateCustomerStatusById",method = RequestMethod.GET)
    public String updateCustomerStatusById(@RequestParam("enable") boolean enable, @RequestParam("customerId") int customerId, HttpServletRequest request) {
        Customer customer = customerService.getCustomerById(customerId);
        customer.setEnabled(enable);
        try {
            customerService.updateCustomer(customer);
            return "redirect:../common/success.do?reurl=customer/customers.do";
        } catch (EntityExistException e) {
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=customer/customers.do";
        }
    }

    @RequestMapping(value = "removeCustomerById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeCustomerById(@RequestParam("customerId") int customerId) {
        try {
            customerService.removeCustomerById(customerId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping(value = "resetCustomerPwd",method = RequestMethod.GET)
    @ResponseBody
    public boolean resetCustomerPwd(@RequestParam("customerId") int customerId) {
        try {
            customerService.resetCustomerPwd(customerId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改状态
     * @param customerId
     * @param enabled
     * @return
     */
    @RequestMapping(value = "enableCustomerById",method = RequestMethod.GET)
    @ResponseBody
    public boolean enableCustomerById(@RequestParam("customerId") int customerId,@RequestParam("enabled") boolean enabled) {
        try{
            customerService.enableCustomerById(customerId,enabled);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 注销
     * @param customerId
     * @param ynDelete
     * @return
     */
    @RequestMapping(value = "CancellationCustomerById",method = RequestMethod.GET)
    @ResponseBody
    public boolean CancellationCustomerById(@RequestParam("customerId") int customerId,@RequestParam("ynDelete") boolean ynDelete) {
        try{
            Customer customer = customerService.getCustomerById(customerId);
            String time=customer.getMobile()+"-delete-"+CalendarUtils.formatDate(new Date());
            customerService.CancellationCustomerById(customerId, ynDelete,time);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
