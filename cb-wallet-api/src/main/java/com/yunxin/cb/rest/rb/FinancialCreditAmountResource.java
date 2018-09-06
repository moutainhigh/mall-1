package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.entity.FinancialLoanConfig;
import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.restful.meta.Result;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.vo.*;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.vo.AddFinancialLoanVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @title:  信用额度接口
 * @auther: gws
 * @date: 2018/8/8 14:28
 */
@Api(description = "信用额度-借款接口")
@RestController
@RequestMapping(value = "/{version}/rb/creditAmount")
public class FinancialCreditAmountResource extends BaseResource {

    @Resource
    private CustomerService customerService;

    @Resource
    private BankInfoService bankInfoService;

    @Resource
    private FinancialWalletService financialWalletService;

    @Resource
    private FinancialLoanService financialLoanService;

    @Resource
    private FinancialLoanConfigService financialLoanConfigService;


    @ApiOperation(value = "信用额度信息")
    @ApiImplicitParams({
    })
    @GetMapping(value = "getCreditAmountInfo")
    @ApiVersion(1)
    public ResponseResult<FinancialCreditLineVO> getCreditAmountInfo() {
        try {
            FinancialWalletVO financialWalletVO = financialWalletService.getFinancialWalletByCustomerId(getCustomerId());
            FinancialCreditLineVO vo = new FinancialCreditLineVO();
            vo.setCreditAmount(financialWalletVO.getCreditAmount());
            // 查询审核通过的我的借款次数
            vo.setLoanCount(financialLoanService.countByCustomerId(getCustomerId()));
            return new ResponseResult<>(vo);
        } catch (Exception e) {
            logger.info("getCreditAmountInfo failed", e);
            return new ResponseResult<>(Result.FAILURE);
        }
    }

    @ApiOperation(value = "借款页面数据获取")
    @ApiImplicitParams({
    })
    @GetMapping("getLoanInitDataInfo")
    @ApiVersion(1)
    public ResponseResult<FinancialLoanInitDateVO> getLoanInitDataInfo() {
        try {
            Customer customer = customerService.getCustomerById(getCustomerId());
            if (customer.getAuthFlag() != 1) {
                return new ResponseResult<>(Result.FAILURE, "未实名认证");
            }
            List<BankInfoVO> bankInfoVOs = bankInfoService.selectAll(getCustomerId());
            if (bankInfoVOs == null || bankInfoVOs.isEmpty()) {
                return new ResponseResult<>(Result.FAILURE, "请先绑定银行卡再申请贷款");
            }
            FinancialLoanInitDateVO vo = new FinancialLoanInitDateVO();
            //最高可贷金额
            FinancialWalletVO financialWalletVO = financialWalletService.getFinancialWalletByCustomerId(getCustomerId());
            vo.setTotalAmount(financialWalletVO.getCreditAmount());
            //期限
            List<FinancialLoanConfig> list = financialLoanConfigService.getFinancialLoanConfigs();
            List<FinancialLoanConfigVO> listFinancialLoanConfigVO = new ArrayList<>();
            list.forEach(p ->{
                FinancialLoanConfigVO financialLoanConfigVO = new FinancialLoanConfigVO();
                BeanUtils.copyProperties(p, financialLoanConfigVO);
                listFinancialLoanConfigVO.add(financialLoanConfigVO);
            });
            vo.setFinancialLoanConfigVOList(listFinancialLoanConfigVO);
            //我的银行卡
            vo.setBankInfoVO(bankInfoVOs.get(0));
            return new ResponseResult<>(vo);
        } catch (Exception e) {
            logger.info("getLoanInitDataInfo failed", e);
            return new ResponseResult<>(Result.FAILURE);
        }
    }

    @ApiOperation(value = "提交借款")
    @ApiImplicitParams({
    })
    @PostMapping(value = "submitLoan")
    @ApiVersion(1)
    public ResponseResult submitLoan(@Valid @RequestBody AddFinancialLoanVO addFinancialLoanVO) {
        try {
            //添加借款记录
            return financialLoanService.add(getCustomerId(), addFinancialLoanVO.getLoanConfigId(),
                    addFinancialLoanVO.getBankId(), addFinancialLoanVO.getAmount());
        } catch (Exception e) {
            logger.info("submitLoan failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "借款明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")
    })
    @GetMapping(value = "getLoanDetails")
    @ApiVersion(1)
    public ResponseResult<PageFinder<FinancialLoanVO>> getLoanDetails(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        try {
            PageFinder<FinancialLoan> pageFinder = financialLoanService.pageByCustomer(getCustomerId(),pageNo, pageSize);
            PageFinder<FinancialLoanVO> page = FinancialLoanVO.convertVOPage(pageFinder);
            return new ResponseResult<>(page);
        } catch (Exception e) {
            logger.info("getLoanDetails failed", e);
            return new ResponseResult<>(Result.FAILURE);
        }
    }

}