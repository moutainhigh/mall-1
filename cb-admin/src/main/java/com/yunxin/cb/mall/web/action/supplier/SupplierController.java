/**
 *
 */
package com.yunxin.cb.mall.web.action.supplier;

import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.Supplier;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.mall.service.ISupplierService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.Supplier;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.service.ICommodityService;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.service.ISupplierService;
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
import java.util.Locale;

/**
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Resource
    private ISupplierService supplierService;

    @Resource
    private ILogsService systemLogsService;


    @Resource
    private ICommodityService commodityService;

    @Resource
    private MessageSource messageSource;


    @RequestMapping(value = "suppliers",method = RequestMethod.GET)
    public String suppliers() {
        return "supplier/suppliers";
    }

    @RequestMapping(value = "pageSuppliers",method = RequestMethod.POST)
    @ResponseBody
    public Page<Supplier> pageSuppliers(@RequestBody PageSpecification<Supplier> query) {
        Page<Supplier> page = supplierService.pageSuppliers(query);
        return page;
    }

    /**
     * 跳转到新增供应商页面
     *
     * @param supplier
     * @return
     */
    @RequestMapping(value = "toAddSupplier",method = RequestMethod.GET)
    public String toAddSupplier(@ModelAttribute("supplier") Supplier supplier, ModelMap modelMap) {
        return "supplier/addSupplier";
    }

    /**
     * 保存新增数据到供应商列表
     *
     * @param supplier
     * @param modelMap
     * @param request
     * @param result
     * @param locale
     * @return
     */
    @RequestMapping(value = "addSupplier",method = RequestMethod.POST)
    public String addSupplier(@Valid @ModelAttribute("supplier") Supplier supplier, ModelMap modelMap, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return toAddSupplier(supplier, modelMap);
        }
        try {
            supplierService.addSupplier(supplier);
            return "redirect:../common/success.do?reurl=supplier/suppliers.do";
        } catch (EntityExistException e) {
            result.addError(new FieldError("supplier", "supplierName", supplier.getSupplierName(), true, null, null,
                    messageSource.getMessage("supplierName", null, locale)));
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/simplefailure.do?reurl=supplier/suppliers.do";
        }
    }

    /**
     * 去修改供应商页面
     *
     * @param supplierId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "toEditSupplier",method = RequestMethod.GET)
    public String toEditSupplier(@RequestParam("supplierId") int supplierId, ModelMap modelMap) {
        Supplier supplier = supplierService.getSupplierById(supplierId);
        modelMap.addAttribute("supplier", supplier);
        return "supplier/addSupplier";
    }

    /**
     * 保存修改数据，跳转到供应商列表页面
     *
     * @param supplier
     * @param request
     * @return
     */
    @RequestMapping(value = "editSupplier",method = RequestMethod.POST)
    public String editSupplier(@ModelAttribute("supplier") Supplier supplier, HttpServletRequest request, ModelMap modelMap, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return suppliers();
        }
        try {
            supplierService.updateSupplier(supplier);
            return "redirect:../common/success.do?reurl=supplier/suppliers.do";
        } catch (Exception e) {
            result.addError(new FieldError("supplier", "supplierName", supplier.getSupplierName(), true, null, null,
                    messageSource.getMessage("supplierName", null, locale)));
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/simplefailure.do?reurl=supplier/suppliers.do";
        }
    }

    @RequestMapping(value = "removeSupplierById",method = RequestMethod.GET)
    @ResponseBody
    public int removeSupplierById(@RequestParam("supplierId") int supplierId) {
        supplierService.removeSupplierById(supplierId);
        return supplierId;
    }


}
