package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.service.BankInfoService;
import com.yunxin.cb.mall.vo.BankInfoVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @title: 银行卡接口
 * @auther: eleven
 * @date: 2018/8/7 17:08
 */
@Api(description = "银行卡接口")
@RestController
@RequestMapping(value = "/{version}/rb/bankinfo")
public class BankInfoResource extends BaseResource {

    @Resource
    private BankInfoService bankInfoService;

    /**
     * @title: 验证银行卡四要素
     * @param: [bankInfoVO]
     * @return: com.yunxin.cb.vo.ResponseResult<com.yunxin.cb.mall.vo.BankInfoVO>
     * @auther: eleven
     * @date: 2018/8/7 19:34
     */
    @ApiOperation(value = "验证银行卡四要素")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardholder", value = "持卡人", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号码", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "customerCardNo", value = "证件号码", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "银行预留手机号码", required = true, paramType = "path", dataType = "string")
    })
    @ApiVersion(1)
    @GetMapping(value = "checkBankInfo")
    public ResponseResult<BankInfoVO> checkBankInfo(@RequestBody BankInfoVO bankInfoVO){
        try {
            //此处调用第三方接口验证银行卡四要素
            return new ResponseResult(bankInfoVO);
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    /**
     * @title: 获取用户所有银行卡
     * @param: []
     * @return: com.yunxin.cb.vo.ResponseResult<java.util.List<com.yunxin.cb.mall.vo.BankInfoVO>>
     * @auther: eleven
     * @date: 2018/8/7 19:34
     */
    @ApiOperation(value = "获取用户所有银行卡")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @GetMapping(value = "selectAll")
    public ResponseResult<List<BankInfoVO>> selectAll(){
        try {
            List<BankInfoVO> listVo = bankInfoService.selectAll(getCustomerId());
            return new ResponseResult(listVo);
        } catch (Exception e) {
            logger.info("selectAll failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    /**
     * @title: 获取银行卡信息
     * @param: [bankId]
     * @return: com.yunxin.cb.vo.ResponseResult<com.yunxin.cb.mall.vo.BankInfoVO>
     * @auther: eleven
     * @date: 2018/8/7 19:34
     */
    @ApiOperation(value = "获取银行卡信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bankId", value = "银行卡ID", required = true, paramType = "path", dataType = "int")
    })
    @ApiVersion(1)
    @GetMapping(value = "detail/{bankId}")
    public ResponseResult<BankInfoVO> detail(@PathVariable Integer bankId){
        try {
            BankInfoVO bankInfoVO = bankInfoService.selectByPrimaryKey(bankId,getCustomerId());
            return new ResponseResult(bankInfoVO);
        } catch (Exception e) {
            logger.info("addProductReturn failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    /**
     * @title: 添加银行卡
     * @param: [bankInfoVO]
     * @return: com.yunxin.cb.vo.ResponseResult<com.yunxin.cb.mall.vo.BankInfoVO>
     * @auther: eleven
     * @date: 2018/8/7 19:34
     */
    @ApiOperation(value = "添加银行卡")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @PostMapping(value = "add")
    public ResponseResult<BankInfoVO> add(@RequestBody BankInfoVO bankInfoVO){
        logger.info("bankInfoVO:" + bankInfoVO.toString());
        BankInfo bankInfo = new BankInfo();
        try {
            //校验验证码
            String checkStr = verificationCode(bankInfoVO.getMobile(), bankInfoVO.getCode());
            if (checkStr != null) {
                return new ResponseResult(Result.FAILURE, checkStr);
            }
            bankInfoVO.setCreateTime(new Date());
            BeanUtils.copyProperties(bankInfo, bankInfoVO);
            bankInfo.setCustomerId(getCustomerId());
            bankInfoService.insert(bankInfo);
            return new ResponseResult(bankInfoVO);
        } catch (Exception e) {
            logger.info("add failed", e);
        }
        return new ResponseResult(Result.FAILURE);
    }

    /**
     * @title: 删除银行卡
     * @param: [bankId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/8/7 19:35
     */
    @ApiOperation(value = "删除银行卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bankId", value = "银行卡ID", required = true, paramType = "path", dataType = "int")
    })
    @ApiVersion(1)
    @DeleteMapping(value = "delete/{bankId}")
    public ResponseResult delete(@PathVariable Integer bankId){
        try {
            bankInfoService.deleteByPrimaryKey(bankId,getCustomerId());
            return new ResponseResult(Result.SUCCESS,"删除成功");
        } catch (Exception e) {
            logger.info("delete failed", e);
        }
        return new ResponseResult(Result.FAILURE,"删除失败");
    }

}
