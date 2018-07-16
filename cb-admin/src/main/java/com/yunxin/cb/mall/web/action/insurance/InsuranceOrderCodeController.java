package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.cb.mall.web.util.ExcelUtils;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import net.sf.json.JSONArray;
import org.omg.CORBA.portable.InputStream;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

/**
 * @author likang
 */
@Controller
@RequestMapping(value = "/insuranceordercode")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class InsuranceOrderCodeController {

    @Resource
    private IInsuranceOrderCodeService insuranceOrderCodeService;


    @RequestMapping(value = "insuranceordercodes")
    public String insuranceordercodes(ModelMap modelMap) {
        return "insuranceordercode/insuranceordercodes";
    }

    /**
     * InsuranceOrderCode分页
     *
     * @param
     * @return
     */
    @RequestMapping(value = "pageInsuranceOrderCode",method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceOrderCode> pageInsuranceOrderCode(@RequestBody PageSpecification<InsuranceOrderCode> query) {
        return insuranceOrderCodeService.pageInsuranceOrderCode(query);
    }


    /**
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "upload")
    public String upload(ModelMap modelMap) {
        return "insuranceordercode/upload";
    }

    /**
     * 导入excel
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "uploadPayerCreditInfoExcel")
    public String uploadPayerCreditInfoExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upfile");
        if (file.isEmpty()) {
            throw new Exception("文件不存在！");
        }
        java.io.InputStream in = file.getInputStream();
        List<List<Object>> listob = ExcelUtils.getBankListByExcel(in,file.getOriginalFilename());
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            String codeno=ob.get(0).toString();
            if (insuranceOrderCodeService.getInsuranceOrderCodeByCodeNo(codeno) == null) {
                InsuranceOrderCode insuranceOrderCode = new InsuranceOrderCode();
                insuranceOrderCode.setCodeNo(codeno);
                insuranceOrderCode.setUseed(0);
                insuranceOrderCode.setCreateTime(new Date());
                insuranceOrderCodeService.addInsuranceOrderCode(insuranceOrderCode);
            }
        }
        return "redirect:../common/success.do?reurl=insuranceordercode/insuranceordercodes.do";
    }
}
