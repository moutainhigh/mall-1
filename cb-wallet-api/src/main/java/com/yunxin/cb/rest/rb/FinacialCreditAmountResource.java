package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.FinacialLoanConfig;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.vo.*;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

    @Resource
    private FinacialLoanService finacialLoanService;

    @Resource
    private FinacialLoanConfigService finacialLoanConfigService;


    @ApiOperation(value = "信用额度信息")
    @ApiImplicitParams({
    })
    @GetMapping(value = "getCreditAmountInfo")
    @ApiVersion(1)
    public ResponseResult<FinacialCreditLineVO> getCreditAmountInfo() {
        try {
            FinacialWalletVO finacialWalletVO = finacialWalletService.getFinacialWalletByCustomerId(getCustomerId());
            FinacialCreditLineVO vo = new FinacialCreditLineVO();
            BeanUtils.copyProperties(finacialWalletVO, vo);
            // 查询审核通过的我的借款次数
            vo.setLoanCount(finacialLoanService.countByCustomerId(getCustomerId()));
            return new ResponseResult(vo);
        } catch (Exception e) {
            logger.info("getCreditAmountInfo failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "借款页面数据获取")
    @ApiImplicitParams({
    })
    @GetMapping("getLoanInitDataInfo")
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
            FinacialLoanInitDateVO vo = new FinacialLoanInitDateVO();
            //最高可贷金额
            FinacialWalletVO finacialWalletVO = finacialWalletService.getFinacialWalletByCustomerId(getCustomerId());
            vo.setTotalAmount(finacialWalletVO.getTotalAmount());
            //期限
            List<FinacialLoanConfig> list = finacialLoanConfigService.getFinacilaLoanConfigs();
            List<FinacialLoanConfigVO> listFinacialLoanConfigVO = new ArrayList<FinacialLoanConfigVO>();
            list.stream().forEach(p ->{
                FinacialLoanConfigVO finacialLoanConfigVO = new FinacialLoanConfigVO();
                BeanUtils.copyProperties(p, finacialLoanConfigVO);
                listFinacialLoanConfigVO.add(finacialLoanConfigVO);
            });
            vo.setFinacialLoanConfigVOList(listFinacialLoanConfigVO);
            //我的银行卡
            vo.setBankInfoVO(bankInfoVOs.get(0));
            return new ResponseResult(vo);
        } catch (Exception e) {
            logger.info("getLoanInitDataInfo failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "提交借款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "贷款金额", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "loanConfigId", value = "借款期限id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "bankId", value = "银行卡ID", required = true, paramType = "form", dataType = "String"),
    })
    @PostMapping(value = "submitLoan")
    @ApiVersion(1)
    public ResponseResult submitLoan(@RequestBody FinacialLoanVO finacialLoanVO) {
        try {
            //判断额度是否满足贷款(先判断预期收益再判断总额度)
            //最高可贷金额
            FinacialWalletVO walletVO = finacialWalletService.getFinacialWalletByCustomerId(getCustomerId());
            BigDecimal totalAmount = walletVO.getTotalAmount();
            if (finacialLoanVO.getAmount().compareTo(totalAmount) > 0) {
                return new ResponseResult(Result.FAILURE, "您的可贷金额不足");
            }
            //判断是否超过最多次数
            int maxCount = 5;
            // 查询审核通过的我的借款次数
            int count = finacialLoanService.countByCustomerId(getCustomerId());
            if (count >= 5) {
                return new ResponseResult(Result.FAILURE, "您已经贷了"+maxCount+"次款了，不能再贷了");
            }

            //计算贷款金额数据
            FinacialLoanConfig finacialLoanConfig = finacialLoanConfigService.getFinacilaLoanConfigById(finacialLoanVO.getLoanConfigId());
            if (finacialLoanConfig == null) {
                return new ResponseResult(Result.FAILURE, "您选择的期限不存在");
            }
            BigDecimal lixi = finacialLoanVO.getAmount().multiply(finacialLoanConfig.getInterestRate()).setScale(2, RoundingMode.HALF_UP);
            finacialLoanVO.setInterest(lixi);
            finacialLoanVO.setInterestRate(finacialLoanConfig.getInterestRate());
            finacialLoanVO.setRepaymentTerm(finacialLoanConfig.getTerm());
            finacialLoanVO.setCustomerId(getCustomerId());
            //添加借款记录
            finacialLoanService.add(finacialLoanVO, walletVO);
            return new ResponseResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.info("getLoanInitDataInfo failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "借款明细")
    @ApiImplicitParams({
    })
    @GetMapping(value = "getLoanDetails")
    @ApiVersion(1)
    public ResponseResult<List<FinacialLoanVO>> getLoanDetails() {
        try {
            List<FinacialLoanVO> list = finacialLoanService.getByCustomerIdAndType(getCustomerId());
            return new ResponseResult(list);
        } catch (Exception e) {
            logger.info("getLoanDetails failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

}