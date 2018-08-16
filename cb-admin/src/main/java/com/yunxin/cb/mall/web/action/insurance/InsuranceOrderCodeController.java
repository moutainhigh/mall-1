package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import com.yunxin.cb.mall.web.util.ExcelUtils;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
* @Description:    保险合同编号
* @Author:         likang
* @CreateDate:     2018/7/17 20:59
*/
@Controller
@RequestMapping(value = "/insuranceordercode")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class InsuranceOrderCodeController {

    @Resource
    private IInsuranceOrderCodeService insuranceOrderCodeService;

    /**
     * 跳转页面
     * @author      likang
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 20:59
     */
    @RequestMapping(value = "insuranceordercodes")
    public String insuranceordercodes(ModelMap modelMap)
    {
        return "insuranceordercode/insuranceordercodes";
    }

    /**
     * 分页
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceOrderCode>
     * @exception
     * @date        2018/7/17 21:00
     */
    @RequestMapping(value = "pageInsuranceOrderCode",method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceOrderCode> pageInsuranceOrderCode(@RequestBody PageSpecification<InsuranceOrderCode> query) {
        return insuranceOrderCodeService.pageInsuranceOrderCode(query);
    }


    /**
     * 跳转到导入页面
     * @author      likang
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:00
     */
    @RequestMapping(value = "upload")
    public String upload(ModelMap modelMap) {
        return "insuranceordercode/upload";
    }

    /**
     * 导入EXCEL
     * @author      likang
    * @param response
    * @param session
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:01
     */
    @RequestMapping(value = "uploadPayerCreditInfoExcel")
    @ResponseBody
    public String uploadPayerCreditInfoExcel(@RequestParam("file") MultipartFile file,  HttpServletResponse response, HttpSession session) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("文件不存在！");
        }
        java.io.InputStream in = file.getInputStream();
        List<List<Object>> listob = ExcelUtils.getBankListByExcel(in,file.getOriginalFilename());
        int maxSort=insuranceOrderCodeService.getInsuranceOrderCodeMaxSort();
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            String codeno=ob.get(0).toString();
            if (insuranceOrderCodeService.getInsuranceOrderCodeByCodeNo(codeno) == null) {
                InsuranceOrderCode insuranceOrderCode = new InsuranceOrderCode();
                insuranceOrderCode.setCodeNo(codeno);
                insuranceOrderCode.setUseed(0);
                insuranceOrderCode.setCreateTime(new Date());
                insuranceOrderCode.setSort(maxSort+1);
                insuranceOrderCodeService.addInsuranceOrderCode(insuranceOrderCode);
            }
        }
        return "redirect:../common/success.do?reurl=insuranceordercode/insuranceordercodes.do";
    }
}
