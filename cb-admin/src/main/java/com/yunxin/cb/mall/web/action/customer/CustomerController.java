package com.yunxin.cb.mall.web.action.customer;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.service.IAddressService;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IRankService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
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

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<Customer> pageCustomers(@RequestBody PageSpecification<Customer> specification) {
        Page<Customer> page = customerService.pageCustomers(specification);
        return page;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String customerDetail(@ModelAttribute("customerId") int customerId, ModelMap modelMap) {
        modelMap.addAttribute("customer", customerService.getCustomerById(customerId));
        List<DeliveryAddress> deliveryAddresses = addressService.getAllAddressesByCustomerId(customerId);
        modelMap.addAttribute("deliveryAddresses", deliveryAddresses);
        return "customer/customerDetail";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toAddCustomer(@ModelAttribute("customer") Customer customer, ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("ranks", rankService.getAllRanks());
        return "customer/addCustomer";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return customers();
        }
        try {
            customerService.addCustomer(customer);
            return "redirect:../common/success.do?reurl=customer/customers.do";
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=customer/customers.do";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toEditCustomer(@RequestParam("customerId") int customerId, ModelMap modelMap) {
        modelMap.addAttribute("customer",customerService.getCustomerById(customerId));
        return "customer/editCustomer";
    }


    @RequestMapping(method = RequestMethod.POST)
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

    @RequestMapping(method = RequestMethod.GET)
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

    @RequestMapping(method = RequestMethod.GET)
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


    @RequestMapping(method = RequestMethod.GET)
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

}
