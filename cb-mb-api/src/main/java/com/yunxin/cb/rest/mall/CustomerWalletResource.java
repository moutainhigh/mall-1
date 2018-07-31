package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.service.BankInfoService;
import com.yunxin.cb.mall.service.CustomerWalletService;
import com.yunxin.cb.mall.vo.BankInfoVO;
import com.yunxin.cb.mall.vo.CustomerWalletVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.Date;
import java.util.List;

/**
 * @title: 钱包信息接口
 * @auther: eleven
 * @date: 2018/7/17 18:29
 */
@Api(description = "钱包信息接口")
@RestController
@RequestMapping(value = "/{version}/mall/customerwallet")
public class CustomerWalletResource extends BaseResource implements ServletContextAware {

    @Resource
    private CustomerWalletService customerWalletService;
    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @ApiOperation(value = "获取钱包信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "customerwalletDetail/{customerId}")
    public ResponseResult<CustomerWalletVO> customerwalletDetail(@PathVariable Integer customerId) throws Exception{
        try {
            CustomerWalletVO customerWalletVO = customerWalletService.selectByCustomerId(customerId);
            return new ResponseResult(customerWalletVO);
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }


}
