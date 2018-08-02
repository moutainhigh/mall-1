package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.service.CustomerTradingRecordService;
import com.yunxin.cb.mall.vo.CustomerTradingRecordVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.List;

@Api(description = "交易记录接口")
@RestController
@RequestMapping(value = "/{version}/mall/customertradingrecord")
public class CustomerTradingRecordController extends BaseResource implements ServletContextAware {

    @Resource
    private CustomerTradingRecordService customerTradingRecordService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @ApiOperation(value = "获取银行卡列表")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "select")
    public ResponseResult<CustomerTradingRecordVO> select() throws Exception {
        try {
            List<CustomerTradingRecordVO> listVo = customerTradingRecordService.getCustomerTradingRecordByCustomerId(getCustomerId());
            return new ResponseResult(listVo);
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

}
