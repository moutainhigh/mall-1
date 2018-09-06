package com.yunxin.cb.mall.web.action.operation;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.CommodityCategory;
import com.yunxin.cb.mall.entity.FilterItem;
import com.yunxin.cb.mall.entity.PropertyFilter;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.service.IFilterService;
import com.yunxin.cb.mall.service.ILogisticsService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.service.IFilterService;
import com.yunxin.cb.mall.service.ILogisticsService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.CommodityCategory;
import com.yunxin.cb.mall.entity.FilterItem;
import com.yunxin.cb.mall.entity.PropertyFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

/**
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/operation")
public class CommodityCategoryController implements ServletContextAware {

    @Resource
    private ICategoryService categoryService;

    @Resource
    private IFilterService filterService;

    @Resource
    private ILogisticsService logisticsService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @RequestMapping(value = "categoryCommodities",method = RequestMethod.GET)
    public String categoryCommodities(@RequestParam("categoryId") int categoryId, ModelMap modelMap) {
        Category category = categoryService.getCategoryById(categoryId);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", categoryTree.getItems());
        modelMap.addAttribute("category", category);

        modelMap.addAttribute("logistics", logisticsService.getAllLogisticAndPrices());
        return "operation/categoryCommodities";
    }


    @RequestMapping(value = "pageCommodityCategories",method = RequestMethod.POST)
    @ResponseBody
    public Page<CommodityCategory> pageCommodityCategories(@RequestBody PageSpecification<CommodityCategory> commodityQuery) {
        Page<CommodityCategory> page = categoryService.pageCommodityCategories(commodityQuery);
        return page;
    }

    @RequestMapping(value = "addCategoryCommodities",method = RequestMethod.POST)
    @ResponseBody
    public boolean addCategoryCommodities(@RequestParam("categoryId") int categoryId, @RequestParam("selectedCommodityId") int[] selectedCommodityId) {
        try{
            return categoryService.addCommodityCategories(categoryId, selectedCommodityId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "removeCommodityCategoryById",method = RequestMethod.GET)
    @ResponseBody
    public int removeCommodityCategoryById(@RequestParam("cocaId") int cocaId) {
        categoryService.removeCommodityCategoryById(cocaId);
        return cocaId;
    }

    @RequestMapping(value = "getFilterItemsByCommodityCommodity",method = RequestMethod.GET)
    @ResponseBody
    public List<PropertyFilter> getFilterItemsByCommodityCommodity(@RequestParam("cocaId") int cocaId) {
        CommodityCategory commodityCategory = categoryService.getCommodityCategoryById(cocaId);
        List<FilterItem> filterItems = filterService.getFilterItemsByCommodityCategory(cocaId);
        List<PropertyFilter> propertyFilters = filterService.getEnabledFiltersByCategory(commodityCategory.getCategory());
        for (PropertyFilter propertyFilter : propertyFilters) {
            for ( FilterItem filter : propertyFilter.getFilterItems()) {
                Optional<FilterItem> optional=filterItems.stream().filter(p->p.getItemId()==filter.getItemId()).findFirst();
                if (optional.isPresent()){
                    filter.setChecked(true);
                }
            }
        }
        return propertyFilters;
    }

    @RequestMapping(value = "saveCategoryCommodityFilterItems",method = RequestMethod.POST)
    @ResponseBody
    public int saveCategoryCommodityFilterItems(@RequestParam("cocaId") int cocaId, @RequestParam("itemId") int[] itemId) {
        categoryService.saveCategoryCommodityFilterItems(cocaId, itemId);
        return cocaId;
    }

    @RequestMapping(value = "saveCommodityLogisticPrices",method = RequestMethod.POST)
    @ResponseBody
    public boolean saveCommodityLogisticPrices(@RequestParam("commodityId") int commodityId, @RequestParam("priceId") int[] priceId) {
        try{
            logisticsService.saveCommodityLogisticPrices(commodityId, priceId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
