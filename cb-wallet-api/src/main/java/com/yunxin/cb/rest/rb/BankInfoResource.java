package com.yunxin.cb.rest.rb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.service.BankInfoService;
import com.yunxin.cb.mall.service.CustomerService;
import com.yunxin.cb.mall.vo.BankInfoVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.util.ValidateBankUtils;
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

    @Resource
    private CustomerService customerService;

    /**
     * @title: 实名认证验证银行卡四要素
     * @param: [bankInfoVO]
     * @return: com.yunxin.cb.vo.ResponseResult<com.yunxin.cb.mall.vo.BankInfoVO>
     * @auther: eleven
     * @date: 2018/8/7 19:34
     */
    @ApiOperation(value = "实名认证验证银行卡四要素")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardholder", value = "持卡人", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号码", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "customerCardNo", value = "证件号码", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "银行预留手机号码", required = true, paramType = "path", dataType = "string")
    })
    @ApiVersion(1)
    @PostMapping(value = "checkBankInfo")
    public ResponseResult checkBankInfo(@RequestBody BankInfoVO bankInfoVO){
        ResponseResult responseResult=new ResponseResult(Result.FAILURE);
        try {
            String result=ValidateBankUtils.validateBank(bankInfoVO);
            if(LogicUtils.isNotNullAndEmpty(result)){
                JSONObject jsonObject= JSON.parseObject(result);
                String respCode=String.valueOf(jsonObject.get("respCode"));
                responseResult.setData(String.valueOf(jsonObject.get("respMessage")));
                if(respCode.equals("0000")){
                    //验证通过，返回银行名称
                    responseResult.setData(String.valueOf(jsonObject.get("bankName")));
                    responseResult.setResult(Result.SUCCESS);
                }
            }else{
                responseResult.setData("访问错误或者验证帐号可能已欠费");
            }
        } catch (Exception e) {
            logger.info("checkBankInfo failed", e);
        }
        return responseResult;
    }

    /**
     * @title: 提交实名认证
     * @param: [bankInfoVO]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/8/9 14:50
     */
    @ApiOperation(value = "提交实名认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardholder", value = "持卡人", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "bankCardNumber", value = "银行卡号码", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "customerCardNo", value = "证件号码", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "银行预留手机号码", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "bankName", value = "银行名称", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "path", dataType = "string")
    })
    @ApiVersion(1)
    @PostMapping(value = "submitAuth")
    public ResponseResult submitAuth(@RequestBody BankInfoVO bankInfoVO){
        ResponseResult responseResult=new ResponseResult(Result.FAILURE);
        try {
            //校验验证码
            String checkStr = verificationCode(bankInfoVO.getMobile(), bankInfoVO.getCode());
            if (checkStr != null) {
                return new ResponseResult(Result.FAILURE, checkStr);
            }
            //验证通过，修改用户为已认证
            int count=bankInfoService.submitAuth(bankInfoVO,getCustomerId());
            if(count>0){
                responseResult.setResult(Result.SUCCESS);
            }
        } catch (Exception e) {
            logger.info("submitAuth failed", e);
        }
        return responseResult;
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
