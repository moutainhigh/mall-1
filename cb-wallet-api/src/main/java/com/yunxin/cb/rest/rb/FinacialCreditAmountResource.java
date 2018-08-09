package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.BankInfoService;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.mall.service.FinacialWalletService;
import com.yunxin.cb.mall.vo.*;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title:  信用额度接口
 * @auther: gws
 * @date: 2018/8/8 14:28
 */
@Api(description = "信用额度接口")
@RestController
@RequestMapping(value = "/{version}/rb/creditAmount")
public class FinacialCreditAmountResource extends BaseResource {

    @Resource
    private CustomerService customerService;

    @Resource
    private BankInfoService bankInfoService;

    @Resource
    private FinacialWalletService finacialWalletService;

    
    @ApiOperation(value = "信用额度信息")
    @ApiImplicitParams({
    })
    @GetMapping()
    @ApiVersion(1)
    public ResponseResult<FinacialCreditLineVO> getCreditAmountInfo() {
        try {
            FinacialWalletVO finacialWalletVO = finacialWalletService.getFinacialWalletByCustomerId(getCustomerId());
            FinacialCreditLineVO vo = new FinacialCreditLineVO();
            BeanUtils.copyProperties(finacialWalletVO, vo);
            vo.setLoanCount(5);
            vo.setMaxLoanCount(5);
            vo.setLoanReminder("信用金额：保险返利50%+点赞感恩5%额度，/n不可使用，作为借款额度，月利息3%/n 您的账号最多只可借款5次。请谨慎使用。");
            return new ResponseResult(vo);
        } catch (Exception e) {
            logger.info("getCreditAmountInfo failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "借款页面数据获取")
    @ApiImplicitParams({
    })
    @GetMapping()
    @ApiVersion(1)
    public ResponseResult<FinacialLoanInitDateVO> getLoanInitDataInfo() {
        try {
            Customer customer = customerService.getCustomerById(getCustomerId());
            if (customer.getAuthFlag() != 1) {
                return new ResponseResult(Result.FAILURE, "未实名认证");
            }
            List<BankInfoVO> bankInfoVOs = bankInfoService.selectAll(getCustomerId());
            if (bankInfoVOs == null || bankInfoVOs.isEmpty()) {
                return new ResponseResult(Result.FAILURE, "请先绑定银行卡再申请贷款");
            }
            //期限
            //我的银行卡
            //所有支持的银行卡列表
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.info("getLoanInitDataInfo failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "提交借款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "贷款金额", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "repaymentTerm", value = "借款期限", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "bankId", value = "银行卡ID", required = true, paramType = "form", dataType = "String"),
    })
    @PostMapping(value = "submitLoan")
    @ApiVersion(1)
    public ResponseResult submitLoan(@RequestBody FinacialLoanVO finacialLoanVO) {
        //判断额度是否满足贷款
        //计算贷款金额数据
        //减少额度（优先减少保单额度，再减少感恩额度）
        //添加额度明细
        //Customer customer = customerService.getCustomerById(CustomerId());
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "额度明细")
    @ApiImplicitParams({
    })
    @GetMapping(value = "Quotas")
    @ApiVersion(1)
    public ResponseResult<PageFinder<FinacialCreditLineBillVO>> Quotas() {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "借款明细")
    @ApiImplicitParams({
    })
    @GetMapping(value = "loanDetails")
    @ApiVersion(1)
    public ResponseResult<PageFinder<FinacialLoanVO>> loanDetails() {
        return new ResponseResult(Result.SUCCESS);
    }


}