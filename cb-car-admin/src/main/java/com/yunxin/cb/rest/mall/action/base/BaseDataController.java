package com.yunxin.cb.rest.mall.action.base;

import com.yunxin.cb.mall.entity.CarBaseData;
import com.yunxin.cb.mall.service.CarBaseDataService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.rest.mall.action.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
/**
 * @Title:  基础数据控制器
 * @Auther: eleven
 * @Date: 2018/9/10 11:09
 * @Description:
 */
@Controller
@RequestMapping("/base")
public class BaseDataController extends BaseController {

    @Resource
    private CarBaseDataService carBaseDataService;

    /**
     * @title: 获取列表数据
     * @param: [modelMap, request]
     * @return: java.lang.String
     * @auther: eleven
     * @date: 2018/9/11 10:46
     */
    @RequestMapping(value = "carBaseData",method = RequestMethod.GET)
    public String catalogs(ModelMap modelMap,HttpServletRequest request) {
        List<CarBaseData> datas = carBaseDataService.getCarBaseDataList(null);
        datas.stream().forEach(p -> {
            if (p.getId() == p.getParentBaseDataId()) {
                //根节点只能有一个且必须为null，所以根节点父节点设置为null（kendo:treeList根节点规则）
                p.setParentBaseDataId(null);
            }
        });
        modelMap.addAttribute("datas", datas);
        String dataMsg = (String) request.getParameter("dataMsg");
        String msgType = (String) request.getParameter("msgType");
        modelMap.addAttribute("dataMsg", dataMsg);
        modelMap.addAttribute("msgType", msgType);
        return "base/baseDatas";
    }

    /**
     * @title: 进入添加页面
     * @param: [data, modelMap]
     * @return: java.lang.String
     * @auther: eleven
     * @date: 2018/9/11 10:51
     */
    @RequestMapping(value = "toAddBaseData",method = RequestMethod.GET)
    public String toAddCatalog(@ModelAttribute("baseData") CarBaseData baseData, ModelMap modelMap) {
        TreeViewItem dataTree = carBaseDataService.getDataTree();
        modelMap.addAttribute("dataTree", Arrays.asList(dataTree));
        return "base/addBaseData";
    }

    /**
     * @title: 添加基础数据
     * @param: [catalog, result, modelMap, request, locale]
     * @return: java.lang.String
     * @auther: eleven
     * @date: 2018/9/11 10:53
     */
    @RequestMapping(value = "addBaseData",method = RequestMethod.POST)
    public String addCatalog(@Valid @ModelAttribute("baseData") CarBaseData baseData, ModelMap modelMap, HttpServletRequest request, RedirectAttributes redirect) throws Exception {
        try {

            baseData.setOperateId(getOperateId(request));
            int result=carBaseDataService.addCarBaseData(baseData);
            if(result>0){
                redirect.addAttribute("dataMsg","操作成功");
                redirect.addAttribute("msgType","success");
            }else{
                redirect.addAttribute("dataMsg","操作失败");
            }
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            modelMap.put("dataMsg",e.getMessage());
            return toAddCatalog(baseData, modelMap);
        }
        return "redirect:/base/carBaseData";
    }

    /**
     * @title: 进入编辑页面
     * @param: [catalogId, modelMap]
     * @return: java.lang.String
     * @auther: eleven
     * @date: 2018/9/11 11:05
     */
    @RequestMapping(value = "toEditBaseData",method = RequestMethod.GET)
    public String toEditCatalog(@RequestParam("bastDataId") int bastDataId,ModelMap modelMap) {
        CarBaseData baseData = carBaseDataService.getCarBaseData(bastDataId);
        TreeViewItem dataTree = carBaseDataService.getDataTree();
        modelMap.addAttribute("dataTree", Arrays.asList(dataTree));
        modelMap.addAttribute("baseData", baseData);
        return "base/editBaseData";
    }

    /**
     * @title: 编辑基础数据
     * @param: [catalog, result, modelMap, locale]
     * @return: java.lang.String
     * @auther: eleven
     * @date: 2018/9/11 11:09
     */
    @RequestMapping(value = "editCatalog",method = RequestMethod.POST)
    public String editCatalog(@Valid @ModelAttribute("catalog") CarBaseData baseData, ModelMap modelMap, RedirectAttributes redirect)  throws Exception  {
        try {
            int result=carBaseDataService.updateCarBaseData(baseData);
            if(result>0){
                redirect.addAttribute("dataMsg","操作成功");
                redirect.addAttribute("msgType","success");
            }else{
                redirect.addAttribute("dataMsg","操作失败");
            }
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            modelMap.put("dataMsg",e.getMessage());
            return toEditCatalog(baseData.getId(), modelMap);
        }
        return "redirect:/base/carBaseData";
    }


    /**
     * @title: 删除基础数据
     * @param: [baseDataId]
     * @return: boolean
     * @auther: eleven
     * @date: 2018/9/11 11:16
     */
    @RequestMapping(value = "removeById",method = RequestMethod.POST)
    @ResponseBody
    public boolean removeCatalogById(@RequestParam("baseDataId") int baseDataId) {
        try {
            carBaseDataService.delCarBaseData(baseDataId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * @title: 停用/启用
     * @param: [catalogId, enabled]
     * @return: java.lang.String
     * @auther: eleven
     * @date: 2018/9/11 11:17
     */
    @RequestMapping(value = "enableBaseDataById",method = RequestMethod.GET)
    @ResponseBody
    public boolean enableBaseDataById(@RequestParam("baseDataId") int baseDataId, @RequestParam("enabled") boolean enabled) {
        boolean flag=false;
        try {
            flag = carBaseDataService.enableBaseDataById(baseDataId, enabled);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return flag;
    }
}
