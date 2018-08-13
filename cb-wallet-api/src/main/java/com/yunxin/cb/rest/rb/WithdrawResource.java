package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Profile;
import com.yunxin.cb.mall.entity.meta.ProfileState;
import com.yunxin.cb.mall.service.BankInfoService;
import com.yunxin.cb.mall.service.FinacialWalletService;
import com.yunxin.cb.mall.service.ProfileService;
import com.yunxin.cb.mall.vo.BankInfoVO;
import com.yunxin.cb.mall.vo.FinacialWalletVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.WithdrawInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @title: 提现接口
 * @auther: eleven
 * @date: 2018/8/8 16:12
 */
@Api(description = "提现接口")
@RestController
@RequestMapping(value = "/{version}/rb/withdraw")
public class WithdrawResource extends BaseResource {

    @Resource
    private BankInfoService bankInfoService;

    @Resource
    private FinacialWalletService finacialWalletService;

    @Resource
    private ProfileService profileService;

    /**
     * @title: 获取申请提现页面数据
     * @param: []
     * @return: com.yunxin.cb.vo.ResponseResult<com.yunxin.cb.mall.vo.BankInfoVO>
     * @auther: eleven
     * @date: 2018/8/8 15:33
     */
    @ApiOperation(value = "获取申请提现页面数据")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "getWithdrawInfo")
    public ResponseResult<WithdrawInfoVO> getWithdrawInfo(){
        try {
            //查询用户的钱包余额
            FinacialWalletVO finacialWalletVO=finacialWalletService.getFinacialWalletByCustomerId(getCustomerId());
            //查询用户所有银行卡
            List<BankInfoVO> bankInfoVOs=bankInfoService.selectAll(getCustomerId());
            //查询后台配置提现手续费率
            BigDecimal freeRate=BigDecimal.ONE;
            Profile profile=profileService.getProfileByName(ProfileState.FINACIAL_FREE_RATE.name());
            if(LogicUtils.isNotNull(profile)&&LogicUtils.isNotNullAndEmpty(profile.getFileValue())){
                freeRate=BigDecimal.valueOf(Double.parseDouble(profile.getFileValue()));
            }
            WithdrawInfoVO withdrawInfoVO=new WithdrawInfoVO();
            withdrawInfoVO.setBankInfoVOS(bankInfoVOs);
            withdrawInfoVO.setBalance(finacialWalletVO.getBalance());
            withdrawInfoVO.setFreeRate(freeRate);
            return new ResponseResult(withdrawInfoVO);
        } catch (Exception e) {
            logger.info("getWithdrawInfo failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }
}
