package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.service.BankInfoService;
import com.yunxin.cb.mall.vo.BankInfoVO;
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
 * @title: 银行卡接口
 * @auther: eleven
 * @date: 2018/7/17 18:29
 */
@Api(description = "银行卡接口")
@RestController
@RequestMapping(value = "/{version}/mall/bankinfo")
public class BankInfoResource extends BaseResource implements ServletContextAware {

    @Resource
    private BankInfoService bankInfoService;
    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @ApiOperation(value = "添加银行卡")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @PostMapping(value = "addBankInfo")
    public ResponseResult<BankInfoVO> addBankInfo(@RequestBody BankInfoVO bankInfoVO) throws Exception{
        logger.info("bankInfoVO:" + bankInfoVO.toString());
        BankInfo bankInfo = new BankInfo();
        try {
            //校验验证码
            VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(bankInfoVO.getMobile());
            //验证码不存在
            if (verificationCode == null){
                return new ResponseResult(Result.FAILURE, "验证码不存在");
            }
            //验证码超过5分钟，失效
            if ((System.currentTimeMillis() - verificationCode.getSendTime()) > 300000) {
                return new ResponseResult(Result.FAILURE, "验证码失效");
            }
            //验证码错误
            if (!verificationCode.getCode().equals(bankInfoVO.getCode())) {
                return new ResponseResult(Result.FAILURE, "验证码错误");
            }
            bankInfoVO.setCreateTime(new Date());
            BeanUtils.copyProperties(bankInfo, bankInfoVO);
            bankInfo.setCustomerId(getCustomerId());
            bankInfoService.insert(bankInfo);
            return new ResponseResult(bankInfoVO);
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "删除银行卡")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @DeleteMapping(value = "deleteBankInfo/{bankId}")
    public ResponseResult deleteBankInfo(@PathVariable Integer bankId) throws Exception{
        try {
            bankInfoService.deleteByPrimaryKey(bankId,getCustomerId());
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
            return new ResponseResult(Result.FAILURE,"删除失败");
        }
        return new ResponseResult(Result.SUCCESS,"删除成功");
    }

    @ApiOperation(value = "获取银行卡列表")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "selectBankInfo")
    public ResponseResult<BankInfoVO> selectBankInfo() throws Exception{
        try {
            List<BankInfoVO> listVo = bankInfoService.selectAll(getCustomerId());
            return new ResponseResult(listVo);
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "获取银行卡信息")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "selectBankInfoDetail/{bankId}")
    public ResponseResult<BankInfoVO> selectBankInfoDetail(@PathVariable Integer bankId) throws Exception{
        try {
            BankInfoVO bankInfoVO = bankInfoService.selectByPrimaryKey(bankId,getCustomerId());
            return new ResponseResult(bankInfoVO);
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }


}
