/**
 *
 */
package com.yunxin.cb.mall.web.action.store;

import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.Store;
import com.yunxin.cb.mall.service.IStoreService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.Store;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.service.IStoreService;
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
 * @author z001075
 */
@Controller
@RequestMapping(value = "/store")
public class StoreController {
    @Resource
    private ILogsService systemsServicelog;

    @Resource
    private IStoreService storeService;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "stores",method = RequestMethod.GET)
    public String stores() {
        return "store/stores";
    }


    @RequestMapping(value = "pageStores",method = RequestMethod.POST)
    @ResponseBody
    public Page<Store> pageStores(@RequestBody PageSpecification<Store> query) {
        Page<Store> page = storeService.pageStores(query);
        return page;
    }

    @RequestMapping(value = "toAddStore",method = RequestMethod.GET)
    public String toAddStore(@ModelAttribute("store") Store store) {
        return "store/addStore";
    }

    @RequestMapping(value = "addStore",method = RequestMethod.POST)
    public String addStore(@Valid @ModelAttribute("store") Store store, HttpServletRequest request, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return toAddStore(store);
        }
        try {
            storeService.addStore(store);
            return "redirect:../common/success.do?reurl=store/stores.do";
        } catch (EntityExistException e) {
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=store/stores.do";
        }
    }

    @RequestMapping(value = "toEditStore",method = RequestMethod.GET)
    public String toEditStore(@RequestParam("storeId") int storeId, ModelMap modelMap) {
        modelMap.addAttribute("store", storeService.getStoreById(storeId));
        return "store/editStore";
    }

    @RequestMapping(value = "editStore",method = RequestMethod.POST)
    public String editStore(@Valid @ModelAttribute("store") Store store, BindingResult result, HttpServletRequest request, Locale locale) {
        if (result.hasErrors()) {
            return stores();
        }
        try {
            storeService.updateStore(store);
            return "redirect:../common/success.do?reurl=store/stores.do";
        } catch (EntityExistException e) {
            result.addError(new FieldError("store", "storeName", store.getStoreName(), true, null, null,
                    messageSource.getMessage("storeName", null, locale)));
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=store/stores.do";
        }
    }

    @RequestMapping(value = "updateStoreStatusById",method = RequestMethod.GET)
    public String updateStoreStatusById(@RequestParam("enable") boolean enable, @RequestParam("storeId") int storeId, HttpServletRequest request) {
//        Store store = storeService.getStoreById(storeId);
//        store.setEnabled(enable);
//        try {
////            storeService.updateStore(store);
//            return "redirect:../common/success.do?reurl=store/stores.do";
//        } catch (EntityExistException e) {
//            e.printStackTrace();
//            request.getSession().setAttribute("exception", e.getMessage());
//            return "redirect:../common/failure.do?reurl=store/stores.do";
//        }
        return null;
    }

    @RequestMapping(value = "removeStoreById",method = RequestMethod.GET)
    @ResponseBody
    public boolean removeStoreById(@RequestParam("storeId") int storeId) {
        try {
            storeService.removeStoreById(storeId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
